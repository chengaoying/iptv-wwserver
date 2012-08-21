package cn.ohyeah.ww.server;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.protocol.ProcessFrame;
import cn.ohyeah.ww.protocol.impl.DefaultProcessor;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestHandler extends SimpleChannelUpstreamHandler {
    private DefaultProcessor processor;

    @Autowired
    public void setDefaultProcessor(DefaultProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void channelConnected(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        // Send greeting for a new connection.
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);
        System.out.println("channel closed");
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
        System.out.println("channel disconnected");
    }

    @Override
    public void messageReceived(
            ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ProcessFrame frame = new ProcessFrame();
        frame.setChannel(e.getChannel());
        frame.setRequest((ByteBuffer)e.getMessage());
        ByteBuffer resp = processor.process(frame);
        ChannelBuffer buf = ChannelBuffers.wrappedBuffer(resp.buffer(), resp.getReaderIndex(), resp.length());
        ChannelFuture future = e.getChannel().write(buf);
        // Close the connection after sending 'Have a good day!'
        // if the client has sent 'bye'.
        //if (close) {
        //   future.addListener(ChannelFutureListener.CLOSE);
        //}
    }

    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getChannel().close();
        System.out.println("exception caught");
        e.getCause().printStackTrace();
    }
}
