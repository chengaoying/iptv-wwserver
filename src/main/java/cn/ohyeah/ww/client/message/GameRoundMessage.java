package cn.ohyeah.ww.client.message;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.io.Serializable;

public class GameRoundMessage implements Serializable {
    private short curRoleIndex;
    private short lastRoleIndex;
    private byte[][] raiseSoldiers;
    private byte[] freeProps;

    public short getCurRoleIndex() {
        return curRoleIndex;
    }

    public void setCurRoleIndex(short curRoleIndex) {
        this.curRoleIndex = curRoleIndex;
    }

    public short getLastRoleIndex() {
        return lastRoleIndex;
    }

    public void setLastRoleIndex(short lastRoleIndex) {
        this.lastRoleIndex = lastRoleIndex;
    }

    public byte[][] getRaiseSoldiers() {
        return raiseSoldiers;
    }

    public void setRaiseSoldiers(byte[][] raiseSoldiers) {
        this.raiseSoldiers = raiseSoldiers;
    }

    public byte[] getFreeProps() {
        return freeProps;
    }

    public void setFreeProps(byte[] freeProps) {
        this.freeProps = freeProps;
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
