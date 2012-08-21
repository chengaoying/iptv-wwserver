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
    private GameRole gameRole;
    private int[] tolen;
    volatile private ServerRoleGameInfo roleGame;

    public ServerRoleInfo() {}

    public ClientRoleDesc createClientRoleDesc() {
        ClientRoleDesc roleDesc = new ClientRoleDesc();
        roleDesc.setRoleId(gameRole.getRoleId());
        roleDesc.setRoleName(gameRole.getRoleName());
        if (table !=null && table.isReady()) {
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
            ServerRoleGameInfo state = new ServerRoleGameInfo(this);
            state.setPrepareProps(propIds);
            this.roleGame = state;
        }
    }

    public boolean isReady() {
        return this.roleGame != null;
    }

    public ServerRoleGameInfo getRoleGame() {
        return roleGame;
    }

    public void setRoleGame(ServerRoleGameInfo roleGame) {
        this.roleGame = roleGame;
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

    public GameRole getGameRole() {
        return gameRole;
    }

    public void setGameRole(GameRole gameRole) {
        this.gameRole = gameRole;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
