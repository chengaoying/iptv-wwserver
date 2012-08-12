package cn.ohyeah.ww.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import static org.jboss.netty.channel.Channels.*;

public class GameServerPipelineFactory implements ChannelPipelineFactory {

    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = pipeline();

        // Add the text line codec combination first,
        pipeline.addLast("framer", new RequestDecoder(16*1024, 4, 4));

        // and then business logic.
        pipeline.addLast("handler", new RequestHandler());

        return pipeline;
    }
}
