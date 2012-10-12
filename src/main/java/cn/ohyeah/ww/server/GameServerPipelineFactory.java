package cn.ohyeah.ww.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServerPipelineFactory implements ChannelPipelineFactory {
    private ExecutionHandler executionHandler;
    private RequestHandler requestHandler;

    @Autowired
    public void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = Channels.pipeline(
                new RequestDecoder(16*1024, 4, 4),
                executionHandler,
                requestHandler
        );
        return pipeline;
    }
}
