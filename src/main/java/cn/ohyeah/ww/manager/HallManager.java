package cn.ohyeah.ww.manager;

import cn.ohyeah.ww.client.model.ClientHallInfo;
import cn.ohyeah.ww.client.model.ClientRoomInfo;
import cn.ohyeah.ww.client.model.ClientTableInfo;
import cn.ohyeah.ww.protocol.impl.ProtocolProcessException;
import cn.ohyeah.ww.server.model.ServerHallInfo;
import cn.ohyeah.ww.server.model.ServerRoleInfo;
import cn.ohyeah.ww.server.model.ServerRoomInfo;
import cn.ohyeah.ww.server.model.ServerTableInfo;
import org.jboss.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HallManager {
    private ServerHallInfo hallInfo;
    private Map<Integer, ServerRoleInfo> roles;

    public HallManager() {
        roles = new ConcurrentHashMap<>();
    }

    public ServerHallInfo lookupHall(int hallId) {
        return hallInfo;
    }

    public ServerRoomInfo lookupRoom(int roomId) {
        return hallInfo.getRooms().get(roomId);
    }

    public ServerTableInfo lookupTable(int roomId, int tableId) {
        return lookupRoom(roomId).getTables().get(tableId);
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
            throw new ProtocolProcessException("�û���ʶ����");
        }
    }

    private ServerRoleInfo checkReadRole(int roleId) {
        ServerRoleInfo roleInfo = roles.get(roleId);
        if (roleInfo == null) {
            throw new ProtocolProcessException("���˺��ѵ��ߣ������µ�¼");
        }
        return roleInfo;
    }

    private int[] createUserToken(ServerRoleInfo roleInfo) {
        //TODO
        return new int[] {1,2,3,4};
    }

    public ServerRoleInfo queryAndCheckRole(int roleId, int[] token) {
        ServerRoleInfo roleInfo = checkReadRole(roleId);
        checkUserToken(roleInfo.getTolen(), token);
        return roleInfo;
    }

    public void loginHall(ServerRoleInfo roleInfo, int hallId) {
        int[] token = createUserToken(roleInfo);
        roleInfo.setTolen(token);
        if (hallInfo.roleLogin(roleInfo)) {
            roles.put(roleInfo.getRole().getRoleId(), roleInfo);
        }
    }

    public ServerRoleInfo quitHall(int roleId, int[] token) {
        ServerRoleInfo roleInfo = checkReadRole(roleId);
        checkUserToken(roleInfo.getTolen(), token);
        if (hallInfo.roleQuit(roleInfo)) {
            roles.remove(roleId);
        }
        return roleInfo;
    }

    public ClientHallInfo queryHallInfo(int roleId, int[] token) {
        ServerRoleInfo roleInfo = checkReadRole(roleId);
        checkUserToken(roleInfo.getTolen(), token);
        return hallInfo.createClientHallInfo();
    }

}
