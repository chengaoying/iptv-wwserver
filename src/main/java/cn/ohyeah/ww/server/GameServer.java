package cn.ohyeah.ww.server;

import cn.ohyeah.ww.manager.Configurations;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

@Service
public class GameServer implements ApplicationContextAware{
    private ApplicationContext applicationContext;
    private final int port;
    public GameServer() {
        this(9000);
    }
    public GameServer(int port) {
        this.port = port;
    }
    public void run() {
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        bootstrap.setPipelineFactory(applicationContext.getBean(GameServerPipelineFactory.class));
        bootstrap.bind(new InetSocketAddress(port));
    }

    public static void main(String[] args) throws Exception {
        //ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(Configurations.class);
        context.getBean(GameServer.class).run();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
