package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.client.model.ClientRoleDesc;
import cn.ohyeah.ww.client.model.ClientTableDesc;
import cn.ohyeah.ww.client.model.ClientTableInfo;

import java.util.ArrayList;
import java.util.List;

public class ServerTableInfo {
    private final int tableId;
    private String tableName;
    private final int limitPlayers;
    private ServerRoomInfo room;
    private List<ServerRoleInfo> players;
    volatile private ServerGameInfo game;

    public ServerTableInfo(int id, int limitPlayers) {
        this.tableId = id;
        this.limitPlayers = limitPlayers;
        this.tableName = "";
        this.players = new ArrayList<>(limitPlayers);
    }

    private boolean isThirstForRole() {
        return limitPlayers-players.size()<=2;
    }

    synchronized public boolean roleQuickJoin(ServerRoleInfo roleInfo) {
        if (isThirstForRole()) {
            return roleJoin(roleInfo);
        }
        return false;
    }

    synchronized public boolean roleJoin(ServerRoleInfo roleInfo) {
        boolean result = false;
        if (players.size() < limitPlayers) {
            result = players.add(roleInfo);
            if (result) {
                roleInfo.setTable(this);
            }
        }
        return result;
    }

    synchronized public boolean getReady() {
        if (players.size() < limitPlayers) {
            return false;
        }
        for (ServerRoleInfo roleInfo : players) {
            if (!roleInfo.isReady()) {
               return false;
            }
        }
        List<ServerRoleGameInfo> serverRoleGames = new ArrayList<>(limitPlayers);
        for (ServerRoleInfo roleInfo : players) {
            serverRoleGames.add(new ServerRoleGameInfo(roleInfo));
        }
        this.game = new ServerGameInfo(this, serverRoleGames);
        return true;
    }

    public boolean isReady() {
        return game !=null;
    }

    synchronized public boolean roleQuit(ServerRoleInfo roleInfo) {
        boolean result = players.remove(roleInfo);
        if (result) {
            roleInfo.setRoleGame(null);
            roleInfo.setTable(null);
        }
        return result;
    }

    public ClientTableDesc createClientTableDesc() {
        ClientTableDesc tableDesc = new ClientTableDesc();
        tableDesc.setTableId(tableId);
        tableDesc.setTableName(tableName);
        List<ClientRoleDesc> roleDescList = new ArrayList<>(players.size());
        for (ServerRoleInfo roleInfo : players) {
            roleDescList.add(roleInfo.createClientRoleDesc());
        }
        tableDesc.setPlayers(roleDescList);
        return tableDesc;
    }

    public ClientTableInfo createClientTableInfo() {
        ClientTableInfo ctableInfo = new ClientTableInfo();
        ctableInfo.setTableId(tableId);
        ctableInfo.setTableName(tableName);
        List<ClientRoleDesc> roleDescList = new ArrayList<>(players.size());
        for (ServerRoleInfo roleInfo : players) {
            roleDescList.add(roleInfo.createClientRoleDesc());
        }
        ctableInfo.setPlayers(roleDescList);
        return ctableInfo;
    }

    public ServerRoomInfo getRoom() {
        return room;
    }

    public void setRoom(ServerRoomInfo room) {
        this.room = room;
    }

    public int getTableId() {
        return tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ServerRoleInfo> getPlayers() {
        return players;
    }

    public void setPlayers(List<ServerRoleInfo> players) {
        this.players = players;
    }

    public ServerGameInfo getGame() {
        return game;
    }

    public void setGame(ServerGameInfo game) {
        this.game = game;
    }
}
