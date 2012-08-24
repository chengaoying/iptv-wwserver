package cn.ohyeah.ww.protocol;

import cn.ohyeah.stb.util.ByteBuffer;
import org.jboss.netty.channel.Channel;

import java.net.SocketAddress;

public class ProcessFrame {
    private ByteBuffer request;
    private Channel channel;
    private SocketAddress remoteAddress;

    public ByteBuffer getRequest() {
        return request;
    }

    public void setRequest(ByteBuffer request) {
        this.request = request;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public SocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(SocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }
}
