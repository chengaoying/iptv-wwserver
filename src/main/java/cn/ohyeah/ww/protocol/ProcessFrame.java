package cn.ohyeah.ww.protocol;

import cn.ohyeah.stb.util.ByteBuffer;
import org.jboss.netty.channel.Channel;

public class ProcessFrame {
    private ByteBuffer request;
    private Channel channel;

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
}
