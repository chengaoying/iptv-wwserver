package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.client.model.ClientRoleDesc;
import cn.ohyeah.ww.client.model.ClientTableDesc;
import cn.ohyeah.ww.client.model.ClientTableInfo;
import cn.ohyeah.ww.model.GameRole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerTableInfo {
    private final int tableId;
    private final int limitPlayers;
    private String tableName;
    private int state;
    private List<ServerRoleInfo> players;
    private ServerRoomInfo room;

    public ServerTableInfo(int id, int limitPlayers) {
        this.tableId = id;
        this.limitPlayers = limitPlayers;
        this.tableName = "";
        this.players = new CopyOnWriteArrayList<>();
    }

    public boolean addRole(ServerRoleInfo roleInfo) {
        return players.add(roleInfo);
    }

    public boolean removeRole(ServerRoleInfo roleInfo) {
        return players.remove(roleInfo);
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<ServerRoleInfo> getPlayers() {
        return players;
    }

    public void setPlayers(List<ServerRoleInfo> players) {
        this.players = players;
    }
}
