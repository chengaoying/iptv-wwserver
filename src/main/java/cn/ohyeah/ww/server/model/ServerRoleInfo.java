package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.client.model.ClientRoleDesc;
import cn.ohyeah.ww.client.model.ClientRoleInfo;
import cn.ohyeah.ww.model.GameRole;
import org.jboss.netty.channel.Channel;

public class ServerRoleInfo {
    private ServerHallInfo serverHall;
    private ServerRoomInfo serverRoom;
    private ServerTableInfo serverTable;
    private Channel channel;
    private GameRole role;
    private int[] tolen;
    volatile private RoleGameInfo roleGame;

    public ServerRoleInfo() {}

    public ClientRoleDesc createClientRoleDesc() {
        ClientRoleDesc roleDesc = new ClientRoleDesc();
        roleDesc.setRoleId(role.getRoleId());
        roleDesc.setRoleName(role.getRoleName());
        if (serverTable !=null && serverTable.isReady()) {
            roleDesc.setStatePlaying();
        }
        else if (isReady()) {
            roleDesc.setStatePreparing();
        }
        else {
            roleDesc.setStateIdle();
        }
        return roleDesc;
    }

    public ClientRoleInfo createClientRoleInfo() {
        ClientRoleInfo croleInfo = new ClientRoleInfo();
        //TODO
        return croleInfo;
    }

    public void prepare(int[] propIds) {
        if (!isReady()) {
            RoleGameInfo state = new RoleGameInfo(this);
            state.setPrepareProps(propIds);
            this.roleGame = state;
        }
    }

    public boolean isReady() {
        return this.roleGame != null;
    }

    public RoleGameInfo getRoleGame() {
        return roleGame;
    }

    public void setRoleGame(RoleGameInfo roleGame) {
        this.roleGame = roleGame;
    }

    public int[] getTolen() {
        return tolen;
    }

    public void setTolen(int[] tolen) {
        this.tolen = tolen;
    }

    public ServerHallInfo getServerHall() {
        return serverHall;
    }

    public void setServerHall(ServerHallInfo serverHall) {
        this.serverHall = serverHall;
    }

    public ServerRoomInfo getServerRoom() {
        return serverRoom;
    }

    public void setServerRoom(ServerRoomInfo serverRoom) {
        this.serverRoom = serverRoom;
    }

    public ServerTableInfo getServerTable() {
        return serverTable;
    }

    public void setServerTable(ServerTableInfo serverTable) {
        this.serverTable = serverTable;
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
