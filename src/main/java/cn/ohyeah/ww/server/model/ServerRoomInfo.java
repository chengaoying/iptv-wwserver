package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.client.model.ClientRoomDesc;
import cn.ohyeah.ww.client.model.ClientRoomInfo;
import cn.ohyeah.ww.client.model.ClientTableDesc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerRoomInfo {
    private final int roomId;
    private String roomName;
    private final int limitPlayer;
    private AtomicInteger playerCount;
    private List<ServerTableInfo> tables;
    private Map<Integer, ServerRoleInfo> roles;
    private ServerHallInfo serverHall;

    public ServerRoomInfo(final int id, final String name, final int roomLimitPlayer,
                          final int tableCount, final int tableLimitPlayer) {
        this.roomId = id;
        this.roomName = name;
        this.limitPlayer = roomLimitPlayer;
        this.roles = new ConcurrentHashMap<>(roomLimitPlayer/2);
        this.tables = new ArrayList<ServerTableInfo>(tableCount);
        for (int i = 0; i < tableCount; ++i) {
            ServerTableInfo table = new ServerTableInfo(i, tableLimitPlayer);
            table.setServerRoom(this);
            tables.set(i, table);
        }
    }

    public boolean roleQuickJoin(ServerRoleInfo roleInfo) {
        boolean joined = false;
        for (ServerTableInfo tableInfo : tables) {
            joined = tableInfo.roleQuickJoin(roleInfo);
            if (joined) {
                break;
            }
        }
        return joined;
    }

    synchronized public boolean roleLogin(ServerRoleInfo roleInfo) {
        if (playerCount.get() < limitPlayer) {
            roles.put(roleInfo.getRole().getRoleId(), roleInfo);
            roleInfo.setServerRoom(this);
            playerCount.incrementAndGet();
            return true;
        }
        return false;
    }

    public boolean roleQuit(ServerRoleInfo roleInfo) {
        roles.remove(roleInfo.getRole().getRoleId());
        roleInfo.setServerRoom(null);
        playerCount.decrementAndGet();
        return true;
    }

    public ClientRoomDesc createClientRoomDesc() {
        ClientRoomDesc roomDesc = new ClientRoomDesc();
        roomDesc.setRoomId(roomId);
        roomDesc.setRoomName(roomName);
        roomDesc.setPlayerCount(playerCount.get());
        return roomDesc;
    }

    public ClientRoomInfo createClientRoomInfo() {
        return createClientRoomInfo(0, tables.size());
    }

    public ClientRoomInfo createClientRoomInfo(int tableIdOff, int count) {
        ClientRoomInfo croomInfo = new ClientRoomInfo();
        croomInfo.setRoomId(roomId);
        croomInfo.setRoomName(roomName);
        croomInfo.setPlayerCount(playerCount.get());
        List<ClientTableDesc> tableDescList = new ArrayList<>(count);
        for (int i = tableIdOff; i < tableIdOff+count; ++i) {
            tableDescList.add(tables.get(i).createClientTableDesc());
        }
        croomInfo.setTables(tableDescList);
        return croomInfo;
    }

    public ServerHallInfo getServerHall() {
        return serverHall;
    }

    public void setServerHall(ServerHallInfo serverHall) {
        this.serverHall = serverHall;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getPlayerCount() {
        return playerCount.get();
    }

    public List<ServerTableInfo> getTables() {
        return tables;
    }

    public void setTables(List<ServerTableInfo> tables) {
        this.tables = tables;
    }
}
