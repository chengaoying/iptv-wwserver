package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.client.message.GameStartMessage;
import cn.ohyeah.ww.client.model.ClientGameInfo;
import cn.ohyeah.ww.client.model.ClientPlayerInfo;
import cn.ohyeah.ww.client.model.ClientRoleDesc;
import cn.ohyeah.ww.server.game.GameMap;
import cn.ohyeah.ww.server.game.Influence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServerGameInfo {
    private ServerTableInfo table;
    private List<ServerPlayerInfo> players;
    volatile private int curRoleIndex;
    volatile private long startMillis;
    private GameMap map;

    public ServerGameInfo(ServerTableInfo table, List<ServerPlayerInfo> players) {
        this.table = table;
        this.players = players;
        this.curRoleIndex = 0;
    }

    public ClientGameInfo createClientGameInfo() {
        ClientGameInfo cgInfo = new ClientGameInfo();
        List<ClientRoleDesc> roleDescList = new ArrayList<>(players.size());
        for (ServerPlayerInfo serverPlayer : players) {
            roleDescList.add(serverPlayer.getRole().createClientRoleDesc());
        }
        cgInfo.setCurRole(curRoleIndex);
        cgInfo.setRoles(roleDescList);
        return cgInfo;
    }

    public GameStartMessage createGameStartMessage() {
        GameStartMessage startMessage = new GameStartMessage();
        startMessage.setCurRoleIndex((byte)curRoleIndex);
        List<ClientPlayerInfo> croleGames = new ArrayList<>(players.size());
        for (int i = 0; i < players.size(); ++i) {
            croleGames.add(players.get(i).createClientRoleGameInfo());
        }
        startMessage.setRoles(croleGames);
        return startMessage;
    }

    public ServerPlayerInfo getCurGameRole() {
        return players.get(curRoleIndex);
    }

    public int getCurRoleIndex() {
        return curRoleIndex;
    }

    synchronized public int nextRoleIndex() {
        int newIndex = curRoleIndex;
        int oldIndex = newIndex;
        int size = players.size();
        do {
            newIndex =  (1+newIndex)%size;
            if (newIndex == oldIndex) {
                break;
            }
        } while (!players.get(newIndex).isStatePlaying());
        curRoleIndex = newIndex;
        return newIndex;
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

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public ServerTableInfo getTable() {
        return table;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public void assignInfluence() {
        Influence[] infs = map.getInfluences();
        Random rand = new Random();
        int roleStartPos = rand.nextInt(infs.length);
        int infStartPos = rand.nextInt(infs.length);
        for (int i = roleStartPos; i < infs.length; ++i) {
            players.get(i).setInfluenceId(infs[infStartPos].getId());
            infStartPos = (infStartPos+1)%infs.length;
        }
        for (int i = 0; i < roleStartPos; ++i) {
            players.get(i).setInfluenceId(infs[infStartPos].getId());
            infStartPos = (infStartPos+1)%infs.length;
        }
        curRoleIndex = roleStartPos;
    }
}
