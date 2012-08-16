package cn.ohyeah.ww.service;

import cn.ohyeah.ww.client.model.ClientTableInfo;
import cn.ohyeah.ww.manager.HallManager;

import java.util.Map;

public class TableService {
    private HallManager hallManager;

    public void setHallManager(HallManager hallManager) {
        this.hallManager = hallManager;
    }

    public void join(Map<String, Object> params) {
        int roleId = (Integer)params.get("roleId");
        int[] token = (int[])params.get("token");
        int tableId = (Integer)params.get("tableId");
        hallManager.joinTable(roleId, token, tableId);
    }

    public void quit(Map<String, Object> params) {
        int roleId = (Integer)params.get("roleId");
        int[] token = (int[])params.get("token");
        hallManager.quitTable(roleId, token);
    }

    public ClientTableInfo queryInfo(Map<String, Object> params) {
        int roleId = (Integer)params.get("roleId");
        int[] token = (int[])params.get("token");
        return hallManager.queryTableInfo(roleId, token);
    }
}
