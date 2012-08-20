package cn.ohyeah.ww.client.model;


import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.io.Serializable;

public class ClientGameDetail implements Serializable {
    private static final byte CMD_INVALID = 0;
    private static final byte CMD_USE_PROP = 1;
    private static final byte CMD_ATTACK = 2;

    private int cmd;
    private int propId;
    private short srcRegionId;
    private short destRegionId;
    private short attackValue;
    private short defenseValue;

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getPropId() {
        return propId;
    }

    public void setPropId(int propId) {
        this.propId = propId;
    }

    public short getSrcRegionId() {
        return srcRegionId;
    }

    public void setSrcRegionId(short srcRegionId) {
        this.srcRegionId = srcRegionId;
    }

    public short getDestRegionId() {
        return destRegionId;
    }

    public void setDestRegionId(short destRegionId) {
        this.destRegionId = destRegionId;
    }

    public short getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(short attackValue) {
        this.attackValue = attackValue;
    }

    public short getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(short defenseValue) {
        this.defenseValue = defenseValue;
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
