package cn.ohyeah.ww.service;

import cn.ohyeah.ww.client.model.ClientTableInfo;
import cn.ohyeah.ww.manager.GameManager;
import cn.ohyeah.ww.manager.HallManager;
import cn.ohyeah.ww.server.model.ServerRoleInfo;
import cn.ohyeah.ww.server.model.ServerTableInfo;

import java.util.Map;

public class TableService {
    private HallManager hallManager;
    private GameManager gameManager;
    public void setHallManager(HallManager hallManager) {
        this.hallManager = hallManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void prepare(int roleId, int[] token, int[] propIds) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        roleInfo.prepare(propIds);
        if (roleInfo.getTable().getReady()) {
            //½øÈëÓÎÏ·×´Ì¬
            //TODO
            gameManager.addGameTable(roleInfo.getTable());
        }
    }

    public void join(int roleId, int[] token, int tableId) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        ServerTableInfo tableInfo = hallManager.lookupTable(roleInfo.getRoom().getRoomId(), tableId);
        tableInfo.roleJoin(roleInfo);
    }

    public void quit(int roleId, int[] token) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        ServerTableInfo tableInfo = roleInfo.getTable();
        if (tableInfo != null) {
            tableInfo.roleQuit(roleInfo);
        }
    }

    public ClientTableInfo queryInfo(int roleId, int[] token) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        ServerTableInfo tableInfo = roleInfo.getTable();
        return tableInfo.createClientTableInfo();
    }
}
