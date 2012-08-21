package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.client.model.ClientHallInfo;
import cn.ohyeah.ww.client.model.ClientRoomDesc;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Named
public class ServerHallInfo {
    private final int hallId;
    private final int limitPlayer;
    private String hallName;
    private AtomicInteger playerCount;
    private List<ServerRoomInfo> rooms;

    @Inject
    public ServerHallInfo(int id, String name, int hallLimitPlayer) {
        this.hallId = id;
        this.hallName = name;
        this.limitPlayer = hallLimitPlayer;
    }

    synchronized public boolean roleLogin(ServerRoleInfo roleInfo) {
        if (playerCount.get() < limitPlayer) {
            roleInfo.setServerHall(this);
            playerCount.incrementAndGet();
            return true;
        }
        return false;
    }

    public boolean roleQuit(ServerRoleInfo roleInfo) {
        roleInfo.setServerHall(null);
        playerCount.decrementAndGet();
        return true;
    }

    public ClientHallInfo createClientHallInfo() {
        ClientHallInfo challInfo = new ClientHallInfo();
        challInfo.setHallId(hallId);
        challInfo.setHallName(hallName);
        challInfo.setPlayerCount(playerCount.get());
        List<ClientRoomDesc> roomDescList = new ArrayList<>(rooms.size());
        for (ServerRoomInfo roomInfo : rooms) {
            roomDescList.add(roomInfo.createClientRoomDesc());
        }
        challInfo.setRooms(roomDescList);
        return challInfo;
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

    public int getPlayerCount() {
        return playerCount.get();
    }

    public List<ServerRoomInfo> getRooms() {
        return rooms;
    }

    @Inject
    public void setRooms(List<ServerRoomInfo> rooms) {
        this.rooms = rooms;
    }
}
