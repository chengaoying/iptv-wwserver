package cn.ohyeah.ww.server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ServerRoomInfo {
    private int roomId;
    private String roomName;
    private int playerCount;
    private List<ServerTableInfo> tables;
    private ConcurrentHashMap roles;
    private ServerHallInfo hall;

    public ServerRoomInfo(int id, String name, int tableCount) {
        this.roomId = id;
        this.roomName = name;
        this.roles = new ConcurrentHashMap();
        this.tables = new ArrayList<ServerTableInfo>(tableCount);
        for (int i = 0; i < tableCount; ++i) {
            ServerTableInfo table = new ServerTableInfo(i);
            table.setRoom(this);
            tables.set(i, table);
        }
    }

    public ServerHallInfo getHall() {
        return hall;
    }

    public void setHall(ServerHallInfo hall) {
        this.hall = hall;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public List<ServerTableInfo> getTables() {
        return tables;
    }

    public void setTables(List<ServerTableInfo> tables) {
        this.tables = tables;
    }
}
