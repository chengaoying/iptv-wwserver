package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.server.game.GameMap;
import cn.ohyeah.ww.server.game.GameRoleInfo;

public class TableGameState {
    private GameRoleInfo curRole;
    private long startMillis;
    private GameMap gameMap;

    public GameRoleInfo getCurRole() {
        return curRole;
    }

    public void setCurRole(GameRoleInfo curRole) {
        this.curRole = curRole;
    }

    public long getStartMillis() {
        return startMillis;
    }

    public void setStartMillis(long startMillis) {
        this.startMillis = startMillis;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }
}
