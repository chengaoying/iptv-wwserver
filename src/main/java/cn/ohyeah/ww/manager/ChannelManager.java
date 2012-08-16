package cn.ohyeah.ww.manager;

import cn.ohyeah.ww.server.model.ServerRoleInfo;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelManager {
    private Map<Channel, ServerRoleInfo> channelRoles;

    public ChannelManager() {
        channelRoles = new ConcurrentHashMap<>();
    }

    public ServerRoleInfo addChannelRole(Channel channel, ServerRoleInfo roleInfo) {
        return channelRoles.put(channel, roleInfo);
    }

    public ServerRoleInfo removeChannelRole(Channel channel) {
        return channelRoles.remove(channel);
    }

    public ServerRoleInfo getRoleByChannel(Channel channel) {
        return channelRoles.get(channel);
    }

    public ChannelGroupFuture closeAllChannel() {
        ChannelGroup channels = new DefaultChannelGroup("wwserver-channel-group");
        for (Channel ch : channelRoles.keySet()) {
            channels.add(ch);
        }
        return channels.close();
    }
}
