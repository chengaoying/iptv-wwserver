package cn.ohyeah.ww.client.message;


import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.client.model.ClientRoleGameInfo;
import cn.ohyeah.ww.io.Serializable;

import java.util.List;

public class GameStartMessage implements Serializable {
    private short curRoleIndex;
    private List<ClientRoleGameInfo> roles;
    private byte[] mapData;

    public int getCurRoleIndex() {
        return curRoleIndex;
    }

    public void setCurRoleIndex(short curRoleIndex) {
        this.curRoleIndex = curRoleIndex;
    }

    public List<ClientRoleGameInfo> getRoles() {
        return roles;
    }

    public void setRoles(List<ClientRoleGameInfo> roles) {
        this.roles = roles;
    }

    public byte[] getMapData() {
        return mapData;
    }

    public void setMapData(byte[] mapData) {
        this.mapData = mapData;
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
