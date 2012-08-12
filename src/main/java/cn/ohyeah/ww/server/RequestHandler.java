package cn.ohyeah.ww.server;

import cn.halcyon.utils.ThreadSafeClientConnManagerUtil;
import cn.ohyeah.stb.util.ByteBuffer;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

public class RequestHandler extends SimpleChannelUpstreamHandler {
    private static final DefaultHttpClient httpClient;
    private int count;

    static {
        httpClient = ThreadSafeClientConnManagerUtil.buildDefaultHttpClient();
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

        ByteBuffer request = (ByteBuffer) e.getMessage();
        byte[] data = null;
        if (request.length() == 8) {
            System.out.println("heart beat count: "+count++);
            data = request.buffer();
        }
        else {
            count = 0;
            HttpPost httpPost = new HttpPost("http://localhost:8080/itvgame/protocolv2/processor");
            httpPost.setHeader("Content-Type", "application/octet-stream");
            httpPost.setEntity(new ByteArrayEntity(request.readAllBytes()));
            data = ThreadSafeClientConnManagerUtil.executeForBodyByteArray(httpClient, httpPost);
        }
        ChannelBuffer buf = ChannelBuffers.copiedBuffer(data);
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
