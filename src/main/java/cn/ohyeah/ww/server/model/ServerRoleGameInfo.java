package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.client.model.ClientRoleGameInfo;
import cn.ohyeah.ww.server.game.Influence;

public class ServerRoleGameInfo {
    /**
     * 空闲状态，刚加入游戏桌的状态
     */
    private static final byte STATE_IDLE = 0;
    /**
     * 准备好了的状态
     */
    private static final byte STATE_PREPARED = 1;
    /**
     * 游戏状态
     */
    private static final byte STATE_PLAYING = 2;
    /**
     * 游戏失败
     */
    private static final byte STATE_LOST = 3;
    /**
     * 逃跑
     */
    private static final byte STATE_FLEEING = 4;

    private ServerRoleInfo role;
    private int influenceId;
    private int[] prepareProps;
    private int[] freeProps;
    private int state;

    public ServerRoleGameInfo(ServerRoleInfo role) {
        this.role = role;
        this.state = STATE_IDLE;
    }

    public ClientRoleGameInfo createClientRoleGameInfo() {
        ClientRoleGameInfo croleGame = new ClientRoleGameInfo();
        croleGame.setInfluenceId((byte)influenceId);
        croleGame.setRoleName(role.getGameRole().getRoleName());
        return croleGame;
    }

    public ServerRoleInfo getRole() {
        return role;
    }

    public void setRole(ServerRoleInfo role) {
        this.role = role;
    }

    public int getInfluenceId() {
        return influenceId;
    }

    public void setInfluenceId(int influenceId) {
        this.influenceId = influenceId;
    }

    public int[] getPrepareProps() {
        return prepareProps;
    }

    public void setPrepareProps(int[] prepareProps) {
        this.prepareProps = prepareProps;
    }

    public boolean existPrepareProp(int propId) {
        if (prepareProps == null) {
            return false;
        }
        for (int i = 0; i < prepareProps.length; ++i) {
            if (prepareProps[i] == propId) {
                return true;
            }
        }
        return false;
    }

    public boolean usePrepareProp(int propId) {
        if (prepareProps == null) {
            return false;
        }
        for (int i = 0; i < prepareProps.length; ++i) {
            if (prepareProps[i] == propId) {
                if (prepareProps.length > 1) {
                    int[] props = new int[prepareProps.length-1];
                    for (int j = 0; j < i; ++j) {
                        props[j] = prepareProps[j];
                    }
                    for (int j = i+1; j < prepareProps.length; ++j) {
                        props[j-1] = prepareProps[j];
                    }
                    prepareProps = props;
                }
                else {
                    prepareProps = null;
                }
                return true;
            }
        }
        return false;
    }

    public int[] getFreeProps() {
        return freeProps;
    }

    public void setFreeProps(int[] freeProps) {
        this.freeProps = freeProps;
    }

    public boolean existFreeProp(int propId) {
        if (freeProps == null) {
            return false;
        }
        for (int i = 0; i < freeProps.length; ++i) {
            if (freeProps[i] == propId) {
                return true;
            }
        }
        return false;
    }
    public boolean useFreeProp(int propId) {
        if (freeProps == null) {
            return false;
        }
        for (int i = 0; i < freeProps.length; ++i) {
            if (freeProps[i] == propId) {
                if (freeProps.length > 1) {
                    int[] props = new int[freeProps.length-1];
                    for (int j = 0; j < i; ++j) {
                        props[j] = freeProps[j];
                    }
                    for (int j = i+1; j < freeProps.length; ++j) {
                        props[j-1] = freeProps[j];
                    }
                    freeProps = props;
                }
                else {
                    freeProps = null;
                }
                return true;
            }
        }
        return false;
    }

    public boolean useProp(int propId) {
        if (!useFreeProp(propId)) {
            return usePrepareProp(propId);
        }
        return true;
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
    public void setStatePrepared() {
        this.state = STATE_PREPARED;
    }
    public boolean isStatePrepared() {
        return this.state == STATE_PREPARED;
    }
    public void setStatePlaying() {
        this.state = STATE_PLAYING;
    }
    public boolean isStatePlaying() {
        return this.state == STATE_PLAYING;
    }
    public boolean isStateLost() {
        return this.state == STATE_LOST;
    }
    public void setStateLost() {
        this.state = STATE_LOST;
    }
    public boolean isStateFleeing() {
        return this.state == STATE_FLEEING;
    }
    public void setStateFleeing() {
        this.state = STATE_FLEEING;
    }
}
