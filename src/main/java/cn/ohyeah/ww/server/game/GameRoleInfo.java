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
}
