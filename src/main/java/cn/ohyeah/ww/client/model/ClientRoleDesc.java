package cn.ohyeah.ww.client.model;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.io.Serializable;

public class ClientRoleDesc implements Serializable {
    /**
     * 空闲状态，刚加入游戏桌的状态
     */
    private static final byte STATE_IDLE = 0;
    /**
     * 准备好了的状态
     */
    private static final byte STATE_PREPARING = 1;
    /**
     * 游戏状态
     */
    private static final byte STATE_PLAYING = 2;
    /**
     * 游戏失败
     */
    private static final byte STATE_LOSING = 3;

    private int roleId;
    private String roleName;
    private int state;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setStateIdle() {
        this.state = STATE_IDLE;
    }
    public boolean isStateIdle() {
        return this.state == STATE_IDLE;
    }
    public void setStatePreparing() {
        this.state = STATE_PREPARING;
    }
    public boolean isStatePreparing() {
        return this.state == STATE_PREPARING;
    }
    public void setStatePlaying() {
        this.state = STATE_PLAYING;
    }
    public boolean isStatePlaying() {
        return this.state == STATE_PLAYING;
    }
    public boolean isStateLosing() {
        return this.state == STATE_LOSING;
    }
    public void setStateLosing() {
        this.state = STATE_LOSING;
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
