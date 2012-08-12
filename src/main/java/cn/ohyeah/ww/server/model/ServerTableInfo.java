package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.model.GameRole;

import java.util.List;

public class ServerTableInfo {
    private int tableId;
    private String tableName;
    private int state;
    private List<ServerRoleInfo> players;
    private ServerRoomInfo room;

    public ServerTableInfo(int id) {
        this.tableId = id;
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

    public void setTableId(int tableId) {
        this.tableId = tableId;
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
