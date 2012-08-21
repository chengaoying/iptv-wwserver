package cn.ohyeah.ww.client.model;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.io.Serializable;

import java.util.List;

public class ClientGameInfo implements Serializable {
    private static final byte FRAME_TYPE_NEW = 0;
    private static final byte FRAME_TYPE_START = 1;
    private static final byte FRAME_TYPE_ROUND = 2;
    private static final byte FRAME_TYPE_END = 3;

    private byte frameType;
    private int curRole;
    private List<ClientRoleDesc> roles;

    public int getCurRole() {
        return curRole;
    }

    public void setCurRole(int curRole) {
        this.curRole = curRole;
    }

    public byte getFrameType() {
        return frameType;
    }

    public void setFrameType(byte frameType) {
        this.frameType = frameType;
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
