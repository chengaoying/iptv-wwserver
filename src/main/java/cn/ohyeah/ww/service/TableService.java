package cn.ohyeah.ww.service;

import cn.ohyeah.ww.client.message.GameStartMessage;
import cn.ohyeah.ww.client.model.ClientGameInfo;
import cn.ohyeah.ww.client.model.ClientTableInfo;
import cn.ohyeah.ww.manager.GameManager;
import cn.ohyeah.ww.manager.HallManager;
import cn.ohyeah.ww.message.MessageSender;
import cn.ohyeah.ww.server.game.MapManager;
import cn.ohyeah.ww.server.model.ServerRoleInfo;
import cn.ohyeah.ww.server.model.ServerTableInfo;
import cn.ohyeah.ww.server.model.ServerGameInfo;
import org.jboss.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {
    private HallManager hallManager;
    private GameManager gameManager;
    private MapManager mapManager;
    private MessageSender messageSender;

    @Autowired
    public void setHallManager(HallManager hallManager) {
        this.hallManager = hallManager;
    }
    @Autowired
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    @Autowired
    public void setMapManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public void prepare(int roleId, int[] token, int[] propIds) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        roleInfo.prepare(propIds);
        ServerTableInfo tableInfo = roleInfo.getTable();
        if (tableInfo.getReady()) {
            //������Ϸ״̬
            ServerGameInfo gameInfo = tableInfo.getGame();
            MapManager.MapNode mapNode = mapManager.randomLoadMap(tableInfo.getTableId());
            if (mapNode.getPlayers() != gameInfo.getPlayerCount()) {
                //TODO map error
            }
            gameInfo.setMap(mapNode.getMap());
            gameInfo.assignInfluence();
            gameInfo.setStartMillis(System.currentTimeMillis());
            gameManager.addGameTable(gameInfo);

            //����״̬���������������Ϣ
            GameStartMessage startMessage = gameInfo.createGameStartMessage();
            startMessage.setMapData(mapNode.getData());
            for(ServerRoleInfo player : tableInfo.getPlayers()) {
                if (player != roleInfo) {
                    messageSender.sendGameStart(player, startMessage);
                }
            }
        }
        else {
            //�������������Ϣ
            ClientTableInfo ctInfo = tableInfo.createClientTableInfo();
            List<Channel> channels = new ArrayList<>(tableInfo.getPlayers().size()-1);
            for(ServerRoleInfo player : tableInfo.getPlayers()) {
                if (player != roleInfo) {
                    channels.add(player.getChannel());
                    messageSender.sendTableInfo(player, ctInfo);
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
