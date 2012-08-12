package cn.ohyeah.ww.server;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

public class RequestDecoder extends LengthFieldBasedFrameDecoder {

    public RequestDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
        ChannelBuffer frame = (ChannelBuffer)super.decode(ctx, channel, buffer);
        if (frame == null) {
            return null;
        }
        return new cn.ohyeah.stb.util.ByteBuffer(frame.array());
    }
}
