package cn.ohyeah.ww.manager;

import cn.ohyeah.ww.server.model.ServerTableInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameManager {
    /**
     * 正在游戏的table
     */
    private Map<Integer, ServerTableInfo> tables;

    public GameManager() {
        tables = new ConcurrentHashMap<>();
    }

    private int getKey(ServerTableInfo tableInfo) {
        return tableInfo.getRoom().getRoomId()*100+tableInfo.getTableId();
    }

    public void addGameTable(ServerTableInfo tableInfo) {
        int key = getKey(tableInfo);
        tables.put(key, tableInfo);
    }

    public void removeGameTable(ServerTableInfo tableInfo) {
        int key = getKey(tableInfo);
        tables.remove(key);
    }
}
