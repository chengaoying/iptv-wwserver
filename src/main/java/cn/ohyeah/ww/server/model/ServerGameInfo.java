package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.client.model.ClientGameInfo;
import cn.ohyeah.ww.client.model.ClientRoleDesc;
import cn.ohyeah.ww.server.game.GameMap;

import java.util.ArrayList;
import java.util.List;

public class ServerGameInfo {
    private ServerTableInfo serverTable;
    private List<RoleGameInfo> roleGames;
    private int curRoleIndex;
    volatile private long startMillis;
    private GameMap gameMap;

    public ServerGameInfo(ServerTableInfo serverTable, List<RoleGameInfo> roleGames) {
        this.serverTable = serverTable;
        this.roleGames = roleGames;
        this.curRoleIndex = 0;
    }

    public ClientGameInfo createClientGameInfo() {
        ClientGameInfo cgInfo = new ClientGameInfo();
        List<ClientRoleDesc> roleDescList = new ArrayList<>(roleGames.size());
        for (RoleGameInfo roleGame : roleGames) {
            roleDescList.add(roleGame.getServerRole().createClientRoleDesc());
        }
        cgInfo.setCurRole(curRoleIndex);
        cgInfo.setRoles(roleDescList);
        return cgInfo;
    }

    public RoleGameInfo getCurGameRole() {
        return roleGames.get(curRoleIndex);
    }

    public int getCurRoleIndex() {
        return curRoleIndex;
    }

    public void setCurRoleIndex(int curRoleIndex) {
        this.curRoleIndex = curRoleIndex;
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

    public ServerTableInfo getServerTable() {
        return serverTable;
    }
}
