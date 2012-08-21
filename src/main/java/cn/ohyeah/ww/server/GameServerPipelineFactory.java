package cn.ohyeah.ww.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.jboss.netty.channel.Channels.*;

@Service
public class GameServerPipelineFactory implements ChannelPipelineFactory {

    private RequestHandler requestHandler;

    @Autowired
    public void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = pipeline();

        // Add the text line codec combination first,
        pipeline.addLast("framer", new RequestDecoder(16*1024, 4, 4));

        // and then business logic.
        pipeline.addLast("handler", requestHandler);

        return pipeline;
    }
}
