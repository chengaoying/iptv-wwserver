package cn.ohyeah.ww.client.model;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.io.Serializable;

import java.util.List;

public class ClientGameInfo implements Serializable {
    private int curRole;
    private boolean firstFrame;
    private List<ClientRoleDesc> roles;

    public int getCurRole() {
        return curRole;
    }

    public void setCurRole(int curRole) {
        this.curRole = curRole;
    }

    public boolean isFirstFrame() {
        return firstFrame;
    }

    public void setFirstFrame(boolean firstFrame) {
        this.firstFrame = firstFrame;
    }

    public List<ClientRoleDesc> getRoles() {
        return roles;
    }

    public void setRoles(List<ClientRoleDesc> roles) {
        this.roles = roles;
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
