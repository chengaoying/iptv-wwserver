package cn.ohyeah.ww.service;

import cn.ohyeah.ww.client.model.ClientRoomInfo;
import cn.ohyeah.ww.manager.HallManager;
import cn.ohyeah.ww.server.model.ServerRoleInfo;
import cn.ohyeah.ww.server.model.ServerRoomInfo;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class RoomService {
    private HallManager hallManager;
    @Inject
    public void setHallManager(HallManager hallManager) {
        this.hallManager = hallManager;
    }

    public void login(int roleId, int[] token, int roomId) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        ServerRoomInfo roomInfo = hallManager.lookupRoom(roomId);
        roomInfo.roleLogin(roleInfo);
    }

    public void quickJoinTable(int roleId, int[] token) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        ServerRoomInfo roomInfo = roleInfo.getRoom();
        roomInfo.roleQuickJoin(roleInfo);
    }

    public void quit(int roleId, int[] token) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        ServerRoomInfo roomInfo = roleInfo.getRoom();
        if (roomInfo != null) {
            roomInfo.roleQuit(roleInfo);
        }
    }

    public ClientRoomInfo queryInfo(int roleId, int[] token) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        ServerRoomInfo roomInfo = roleInfo.getRoom();
        return roomInfo.createClientRoomInfo();
    }
}
