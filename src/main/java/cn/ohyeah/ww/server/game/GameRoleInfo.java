package cn.ohyeah.ww.server.game;

import cn.ohyeah.ww.server.model.ServerRoleInfo;

public class GameRoleInfo {
    private static final byte STATE_IDLE = 0;
    private static final byte STATE_PREPARE = 1;
    private static final byte STATE_PLAYING = 2;

    private ServerRoleInfo roleInfo;
    private Influence influence;
    private int[] prepareProps;
    private int[] freeProps;
    private int state;

    public GameRoleInfo(ServerRoleInfo roleInfo) {
        this.roleInfo = roleInfo;
        this.state = STATE_IDLE;
    }

    public ServerRoleInfo getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(ServerRoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }

    public Influence getInfluence() {
        return influence;
    }

    public void setInfluence(Influence influence) {
        this.influence = influence;
    }

    public int[] getPrepareProps() {
        return prepareProps;
    }

    public void setPrepareProps(int[] prepareProps) {
        this.prepareProps = prepareProps;
    }

    public int[] getFreeProps() {
        return freeProps;
    }

    public void setFreeProps(int[] freeProps) {
        this.freeProps = freeProps;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
