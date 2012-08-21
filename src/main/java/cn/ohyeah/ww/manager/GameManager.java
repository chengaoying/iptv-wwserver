package cn.ohyeah.ww.manager;

import cn.ohyeah.ww.server.model.ServerGameInfo;
import cn.ohyeah.ww.server.model.ServerTableInfo;

import javax.inject.Named;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Named
public class GameManager {
    /**
     * 正在游戏的table
     */
    private Map<ServerTableInfo, ServerGameInfo> tables;

    public GameManager() {
        tables = new ConcurrentHashMap<>();
    }

    public ServerGameInfo addGameTable(ServerGameInfo gameInfo) {
        return tables.put(gameInfo.getTable(), gameInfo);
    }

    public ServerGameInfo removeGameTable(ServerTableInfo tableInfo) {
        return tables.remove(tableInfo);
    }
}
