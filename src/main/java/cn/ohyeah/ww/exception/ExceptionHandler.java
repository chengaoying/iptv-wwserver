package cn.ohyeah.ww.exception;

import org.jboss.netty.channel.Channel;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-8-20
 * Time: ионГ10:10
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionHandler {
    public void handleChannelClosed(Channel channel) {
        //TODO
        System.out.println("channel closed");
    }

    public void handleChannelException(Channel channel, Throwable ex) {
        //TODO
        System.out.println("exception caught");
        ex.printStackTrace();
    }
}
