package cn.ohyeah.ww.client.model;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.io.Serializable;

import java.util.List;

public class ClientHallInfo implements Serializable {
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

    @Override
    public void serialize(ByteBuffer buf) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deserialize(ByteBuffer buf) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
