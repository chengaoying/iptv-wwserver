package cn.ohyeah.ww.service;

import cn.ohyeah.ww.client.model.ClientRoomInfo;
import cn.ohyeah.ww.manager.HallManager;

import java.util.Map;

public class RoomService {
    private HallManager hallManager;

    public void setHallManager(HallManager hallManager) {
        this.hallManager = hallManager;
    }

    public void login(Map<String, Object> params) {
        int roleId = (Integer)params.get("roleId");
        int[] token = (int[])params.get("token");
        int roomId = (Integer)params.get("roomId");
        hallManager.loginRoom(roleId, token, roomId);
    }

    public void quickJoinTable(Map<String, Object> params) {
        int roleId = (Integer)params.get("roleId");
        int[] token = (int[])params.get("token");
        hallManager.quickJoinTable(roleId, token);
    }

    public void quit(Map<String, Object> params) {
        int roleId = (Integer)params.get("roleId");
        int[] token = (int[])params.get("token");
        hallManager.quitRoom(roleId, token);
    }

    public ClientRoomInfo queryInfo(Map<String, Object> params) {
        int roleId = (Integer)params.get("roleId");
        int[] token = (int[])params.get("token");
        return hallManager.queryRoomInfo(roleId, token);
    }
}
