package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.client.model.ClientGameInfo;
import cn.ohyeah.ww.client.model.ClientRoleDesc;
import cn.ohyeah.ww.server.game.GameMap;
import cn.ohyeah.ww.server.game.GameRoleInfo;

import java.util.ArrayList;
import java.util.List;

public class ServerGameInfo {
    private ServerTableInfo table;
    private List<GameRoleInfo> gameRoles;
    private int curRole;
    private long startMillis;
    private GameMap gameMap;

    public ServerGameInfo(ServerTableInfo table, List<GameRoleInfo> gameRoles) {
        this.table = table;
        this.gameRoles = gameRoles;
        this.curRole = 0;
    }

    public ClientGameInfo createClientGameInfo() {
        ClientGameInfo cgInfo = new ClientGameInfo();
        List<ClientRoleDesc> roleDescList = new ArrayList<>(gameRoles.size());
        for (GameRoleInfo gameRole : gameRoles) {
            roleDescList.add(gameRole.getRoleInfo().createClientRoleDesc());
        }
        cgInfo.setCurRole(curRole);
        cgInfo.setRoles(roleDescList);
        return cgInfo;
    }

    public int getCurRole() {
        return curRole;
    }

    public void setCurRole(int curRole) {
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
