package cn.ohyeah.ww.server;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class GameServer {
    private final int port;

    public GameServer(int port) {
        this.port = port;
    }

    public void run() {
        // Configure the server.
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        // Configure the pipeline factory.
        bootstrap.setPipelineFactory(new GameServerPipelineFactory());

        // Bind and start to accept incoming connections.
        bootstrap.bind(new InetSocketAddress(port));
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 9000;
        }
        new GameServer(port).run();
    }
}
