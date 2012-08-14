package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.client.model.ClientRoleDesc;
import cn.ohyeah.ww.client.model.ClientRoleInfo;
import cn.ohyeah.ww.model.GameRole;
import org.jboss.netty.channel.Channel;

public class ServerRoleInfo {
    private ServerHallInfo hall;
    private ServerRoomInfo room;
    private ServerTableInfo table;
    private Channel channel;
    private GameRole role;
    private int[] tolen;

    public ServerRoleInfo() {}

    public ClientRoleDesc createClientRoleDesc() {
        ClientRoleDesc roleDesc = new ClientRoleDesc();
        roleDesc.setRoleId(role.getRoleId());
        roleDesc.setRoleName(role.getRoleName());
        return roleDesc;
    }

    public ClientRoleInfo createClientRoleInfo() {
        ClientRoleInfo croleInfo = new ClientRoleInfo();
        //TODO
        return croleInfo;
    }

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

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
