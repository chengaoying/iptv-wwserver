package cn.ohyeah.ww.client.model;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.io.Serializable;

import java.util.List;

public class ClientRoomInfo implements Serializable {
    private int roomId;
    private String roomName;
    private int playerCount;
    private List<ClientTableDesc> tables;

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

    public List<ClientTableDesc> getTables() {
        return tables;
    }

    public void setTables(List<ClientTableDesc> tables) {
        this.tables = tables;
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
