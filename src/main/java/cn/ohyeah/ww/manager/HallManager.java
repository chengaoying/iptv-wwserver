package cn.ohyeah.ww.manager;

import cn.ohyeah.ww.client.model.ClientHallInfo;
import cn.ohyeah.ww.client.model.ClientRoomDesc;
import cn.ohyeah.ww.client.model.ClientRoomInfo;
import cn.ohyeah.ww.client.model.ClientTableInfo;
import cn.ohyeah.ww.protocol.impl.ProtocolProcessException;
import cn.ohyeah.ww.server.model.ServerHallInfo;
import cn.ohyeah.ww.server.model.ServerRoleInfo;
import cn.ohyeah.ww.server.model.ServerRoomInfo;
import cn.ohyeah.ww.server.model.ServerTableInfo;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HallManager {
    private ServerHallInfo hallInfo;
    private Map<Integer, ServerRoleInfo> roles;
    private Map<Channel, ServerRoleInfo> channelRoles;

    public HallManager() {
        roles = new ConcurrentHashMap<>();
        channelRoles = new ConcurrentHashMap<>();
    }

    public ServerRoleInfo addChannelRole(Channel channel, ServerRoleInfo role) {
        return channelRoles.put(channel, role);
    }

    public ServerRoleInfo removeChannelRole(Channel channel) {
        return channelRoles.remove(channel);
    }

    public ServerRoleInfo lookupRole(int roleId) {
        return null;
    }

    public ServerHallInfo lookupHall(int hallId) {
        return hallInfo;
    }

    private boolean userTokenEquals(int[] token1, int[] token2) {
        if (token1 == null || token2 == null || token1.length != token2.length) {
            return false;
        }
        for (int i = 0; i < token1.length; ++i) {
            if (token1[i] != token2[i]) {
                return false;
            }
        }
        return true;
    }

    private void checkUserToken(int[] token1, int[] token2) {
        if (!userTokenEquals(token1, token2)) {
            throw new ProtocolProcessException("用户标识错误");
        }
    }

    private ServerRoleInfo checkReadRole(int roleId) {
        ServerRoleInfo roleInfo = roles.get(roleId);
        if (roleInfo == null) {
            throw new ProtocolProcessException("此账号已掉线，请重新登录");
        }
        return roleInfo;
    }

    private int[] createUserToken(ServerRoleInfo roleInfo) {
        //TODO
        return new int[] {1,2,3,4};
    }

    public void loginHall(ServerRoleInfo roleInfo, int hallId) {
        int[] token = createUserToken(roleInfo);
        roleInfo.setTolen(token);
        roleInfo.setHall(hallInfo);
        roles.put(roleInfo.getRole().getRoleId(), roleInfo);
        channelRoles.put(roleInfo.getChannel(), roleInfo);
    }

    public void quitHall(int roleId, int[] token) {
        ServerRoleInfo roleInfo = checkReadRole(roleId);
        checkUserToken(roleInfo.getTolen(), token);
        Channel channel = roleInfo.getChannel();
        if (channel != null) {
            channelRoles.remove(channel);
        }
        roles.remove(roleId);
    }

    public ClientHallInfo queryHallInfo(int roleId, int[] token) {
        ServerRoleInfo roleInfo = checkReadRole(roleId);
        checkUserToken(roleInfo.getTolen(), token);
        return hallInfo.createClientHallInfo();
    }

    public void loginRoom(int roleId, int[] token, int roomId) {
        ServerRoleInfo roleInfo = checkReadRole(roleId);
        checkUserToken(roleInfo.getTolen(), token);
        ServerRoomInfo roomInfo = hallInfo.getRooms().get(roomId);
        roomInfo.addRole(roleInfo);
        roleInfo.setRoom(roomInfo);
    }

    public void quitRoom(int roleId, int[] token) {
        ServerRoleInfo roleInfo = checkReadRole(roleId);
        checkUserToken(roleInfo.getTolen(), token);
        ServerRoomInfo roomInfo = roleInfo.getRoom();
        if (roomInfo != null) {
            roomInfo.removeRole(roleInfo);
            roleInfo.setRoom(null);
        }
    }

    public ClientRoomInfo queryRoomInfo(int roleId, int[] token) {
        ServerRoleInfo roleInfo = checkReadRole(roleId);
        checkUserToken(roleInfo.getTolen(), token);
        ServerRoomInfo roomInfo = roleInfo.getRoom();
        return roomInfo.createClientRoomInfo();
    }

    public void loginTable(int roleId, int[] token, int tableId) {
        ServerRoleInfo roleInfo = checkReadRole(roleId);
        checkUserToken(roleInfo.getTolen(), token);
        ServerTableInfo tableInfo = roleInfo.getRoom().getTables().get(tableId);
        tableInfo.addRole(roleInfo);
        roleInfo.setTable(tableInfo);
    }

    public void quitTable(int roleId, int[] token) {
        ServerRoleInfo roleInfo = checkReadRole(roleId);
        checkUserToken(roleInfo.getTolen(), token);
        ServerTableInfo tableInfo = roleInfo.getTable();
        if (tableInfo != null) {
            tableInfo.removeRole(roleInfo);
            roleInfo.setTable(null);
        }
    }

    public ClientTableInfo queryTableInfo(int roleId, int[] token) {
        ServerRoleInfo roleInfo = checkReadRole(roleId);
        checkUserToken(roleInfo.getTolen(), token);
        ServerTableInfo tableInfo = roleInfo.getTable();
        return tableInfo.createClientTableInfo();
    }



}
