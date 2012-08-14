package cn.ohyeah.ww.manager;

import cn.ohyeah.ww.server.model.ServerRoleInfo;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelManager {
    private Map<Channel, ServerRoleInfo> channelRoles;
    private ChannelGroup channels;

    public ChannelManager() {
        channels = new DefaultChannelGroup("wwserver-channel-group");
        channelRoles = new ConcurrentHashMap<>();
    }

    public boolean addChannel(Channel channel) {
        return channels.add(channel);
    }

    public boolean removeChannel(Channel channel) {
        return channels.remove(channel);
    }

    public ServerRoleInfo addChannelRole(Channel channel, ServerRoleInfo roleInfo) {
        return channelRoles.put(channel, roleInfo);
    }

    public ServerRoleInfo removeChannelRole(Channel channel) {
        return channelRoles.remove(channel);
    }
}
