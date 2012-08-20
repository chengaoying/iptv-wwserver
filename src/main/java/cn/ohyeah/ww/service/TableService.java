package cn.ohyeah.ww.service;

import cn.ohyeah.ww.client.model.ClientGameInfo;
import cn.ohyeah.ww.client.model.ClientTableInfo;
import cn.ohyeah.ww.manager.GameManager;
import cn.ohyeah.ww.manager.HallManager;
import cn.ohyeah.ww.server.game.MapManager;
import cn.ohyeah.ww.server.model.ServerRoleInfo;
import cn.ohyeah.ww.server.model.ServerTableInfo;
import cn.ohyeah.ww.server.model.ServerGameInfo;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class TableService {
    private HallManager hallManager;
    private GameManager gameManager;
    private MapManager mapManager;

    @Inject
    public void setHallManager(HallManager hallManager) {
        this.hallManager = hallManager;
    }
    @Inject
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    @Inject
    public void setMapManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public void prepare(int roleId, int[] token, int[] propIds) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        roleInfo.prepare(propIds);
        ServerTableInfo tableInfo = roleInfo.getTable();
        if (tableInfo.getReady()) {
            //进入游戏状态
            ServerGameInfo gameInfo = tableInfo.getGameInfo();
            gameInfo.setGameMap(mapManager.randomLoadMap(tableInfo.getTableId()));
            gameInfo.setStartMillis(System.currentTimeMillis());
            gameManager.addGameTable(tableInfo);
            //TODO
            //返回状态，其他玩家推送消息
            ClientGameInfo cgInfo = gameInfo.createClientGameInfo();
            cgInfo.setFirstFrame(true);
            for(ServerRoleInfo player : tableInfo.getPlayers()) {
                if (player != roleInfo) {
                    //TODO ClientGameInfo
                    //player.getChannel().write();
                }
            }
        }
        else {
            //其他玩家推送消息
            ClientTableInfo ctInfo = tableInfo.createClientTableInfo();
            for(ServerRoleInfo player : tableInfo.getPlayers()) {
                if (player != roleInfo) {
                    //TODO ClientTableInfo
                    //player.getChannel().write();

                }
            }
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
            if (!tableInfo.isReady()) {
                tableInfo.roleQuit(roleInfo);
            }
            else {
                //TODO error
            }
        }
    }

    public ClientTableInfo queryInfo(int roleId, int[] token) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        ServerTableInfo tableInfo = roleInfo.getTable();
        return tableInfo.createClientTableInfo();
    }
}
