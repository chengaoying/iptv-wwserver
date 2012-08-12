package cn.ohyeah.ww.server.model;

import java.util.List;

public class ServerHallInfo {
    private int hallId;
    private String hallName;
    private int playerCount;
    private List<ServerRoomInfo> rooms;

    public ServerHallInfo(int id, String name) {
        this.hallId = id;
        this.hallName = name;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public List<ServerRoomInfo> getRooms() {
        return rooms;
    }

    public void setRooms(List<ServerRoomInfo> rooms) {
        this.rooms = rooms;
    }
}
