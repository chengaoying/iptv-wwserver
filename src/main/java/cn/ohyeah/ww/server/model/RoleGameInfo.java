package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.server.game.Influence;
import cn.ohyeah.ww.server.model.ServerRoleInfo;

public class RoleGameInfo {
    private static final byte STATE_IDLE = 0;
    private static final byte STATE_PREPARE = 1;
    private static final byte STATE_PLAYING = 2;

    private ServerRoleInfo serverRole;
    private Influence influence;
    private int[] prepareProps;
    private int[] freeProps;
    private int state;

    public RoleGameInfo(ServerRoleInfo serverRole) {
        this.serverRole = serverRole;
        this.state = STATE_IDLE;
    }

    public ServerRoleInfo getServerRole() {
        return serverRole;
    }

    public void setServerRole(ServerRoleInfo serverRole) {
        this.serverRole = serverRole;
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
}
