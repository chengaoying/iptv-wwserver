package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.model.GameRole;

public class ServerRoleInfo {
    private ServerHallInfo hall;
    private ServerRoomInfo room;
    private ServerTableInfo table;
    private GameRole role;
    private int[] tolen;

    public int[] getTolen() {
        return tolen;
    }

    public void setTolen(int[] tolen) {
        this.tolen = tolen;
    }

    public ServerHallInfo getHall() {
        return hall;
    }

    public void setHall(ServerHallInfo hall) {
        this.hall = hall;
    }

    public ServerRoomInfo getRoom() {
        return room;
    }

    public void setRoom(ServerRoomInfo room) {
        this.room = room;
    }

    public ServerTableInfo getTable() {
        return table;
    }

    public void setTable(ServerTableInfo table) {
        this.table = table;
    }

    public GameRole getRole() {
        return role;
    }

    public void setRole(GameRole role) {
        this.role = role;
    }
}
