package cn.ohyeah.ww.client.model;

import java.util.List;

public class ClientHallInfo {
    private int hallId;
    private String hallName;
    private int playerCount;
    private List<ClientRoomDesc> rooms;

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public List<ClientRoomDesc> getRooms() {
        return rooms;
    }

    public void setRooms(List<ClientRoomDesc> rooms) {
        this.rooms = rooms;
    }
}
