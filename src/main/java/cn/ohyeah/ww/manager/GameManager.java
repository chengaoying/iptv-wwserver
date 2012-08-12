package cn.ohyeah.ww.manager;

import cn.ohyeah.ww.server.model.ServerHallInfo;
import cn.ohyeah.ww.server.model.ServerRoleInfo;
import cn.ohyeah.ww.server.model.ServerRoomInfo;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameManager {
    private ServerHallInfo hallInfo;
    private ConcurrentHashMap<String, ServerRoleInfo> roles;
    private ChannelGroup channels;
    private ConcurrentHashMap<Channel, ServerRoleInfo> channelRoles;

    public GameManager() {
        channels = new DefaultChannelGroup("wwserver-channel-group");
        channelRoles = new ConcurrentHashMap<>();
    }

    public boolean addChannel(Channel channel) {
        return channels.add(channel);
    }

    public boolean removeChannel(Channel channel) {
        return channels.remove(channel);
    }

    public ServerRoleInfo addChannelRole(Channel channel, ServerRoleInfo role) {
        return channelRoles.put(channel, role);
    }

    public ServerRoleInfo removeChannelRole(Channel channel) {
        return channelRoles.remove(channel);
    }

    public ServerRoleInfo lookupRole(String roleName) {
        return null;
    }

    public ServerHallInfo lookupHall(int hallId) {
        return hallInfo;
    }

    public void loginHall(ServerRoleInfo roleInfo) {

    }

    public void quitHall(ServerRoleInfo roleInfo) {

    }
}
