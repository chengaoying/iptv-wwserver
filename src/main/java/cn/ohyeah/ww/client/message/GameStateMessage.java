package cn.ohyeah.ww.client.message;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.io.Serializable;

public class GameStateMessage implements Serializable {
    /**
     * ��Чָ��
     */
    private static final byte CMD_INVALID = 0;
    /**
     * ʹ�õ���
     */
    private static final byte CMD_USE_PROP = 1;
    /**
     * ����
     */
    private static final byte CMD_ATTACK = 2;

    /**
     * ��Ϸָ�ʹ�õ��߻��߽���
     */
    private byte cmd;
    /**
     * �������ĵ���ID��������Ϊ��ǰ�غϲ��������
     */
    private byte attackPropId;
    /**
     * ���ط�����ID�����ط�Ϊ����������ң�
     * ��������û�й����������ʱ����ֵ��Ч
     */
    private byte defensePropId;
    /**
     * ���������ID��������Ϊ��ǰ�غϲ��������
     */
    private byte attackRegionId;
    /**
     * ���ط����ID�����ط�Ϊ����������ң�
     * ��������û�й����������ʱ����ֵ��Ч
     */
    private byte defenseRegionId;
    /**
     * ��������ս��ֵ
     * ��cmdΪUSE_PROPʱ����ֵ��Ч
     */
    private byte[] attackValues;
    /**
     * ���ط���ս��ֵ
     * ��cmdΪUSE_PROPʱ����ֵ��Ч
     */
    private byte[] defenseValues;
    /**
     * ս�������������صı���
     * ��������û�й����������ʱ����ֵ��Ч
     */
    private byte attackSoldiers;
    /**
     * ս����������ط���صı���
     * ��������û�й����������ʱ����ֵ��Ч
     */
    private byte defenseSoldiers;
    /**
     * ս����������ط���صĹ�����
     * ��������û�й����������ʱ����ֵ��Ч
     */
    private byte defenseInfluenceId;
    /**
     * װ����װ�׿���������ս��ֵ
     * ��������û�й����������ʱ����ֵ��Ч
     */
    private byte attackPropAttackValue;
    /**
     * װ���˷�������������ս��ֵ
     * ��������û�й����������ʱ����ֵ��Ч
     */
    private byte defensePropDefenseValue;
    /**
     * ���Կ���������ҵ��ߵ�����ID
     */
    private byte propsVisableInfluenceId;

    public GameStateMessage() {
        this.cmd = CMD_INVALID;
        this.attackPropId = -1;
        this.defensePropId = -1;
    }

    public byte getCmd() {
        return cmd;
    }

    public void setCmd(byte cmd) {
        this.cmd = cmd;
    }

    public void setCmdUseProp() {
        this.cmd = CMD_USE_PROP;
    }

    public boolean isCmdUseProp() {
        return this.cmd == CMD_USE_PROP;
    }

    public void setCmdAttack() {
        this.cmd = CMD_USE_PROP;
    }

    public boolean isCmdAttack() {
        return this.cmd == CMD_ATTACK;
    }

    public void setAttackPropInvalid() {
        this.attackPropId = -1;
    }

    public boolean isAttackPropInvalid() {
        return this.attackPropId == -1;
    }

    public byte getAttackPropId() {
        return attackPropId;
    }

    public void setAttackPropId(byte attackPropId) {
        this.attackPropId = attackPropId;
    }

    public void setDefensePropInvalid() {
        this.defensePropId = -1;
    }

    public boolean isDefensePropInvalid() {
        return this.defensePropId == -1;
    }

    public byte getDefensePropId() {
        return defensePropId;
    }

    public void setDefensePropId(byte defensePropId) {
        this.defensePropId = defensePropId;
    }

    public byte getAttackRegionId() {
        return attackRegionId;
    }

    public void setAttackRegionId(byte attackRegionId) {
        this.attackRegionId = attackRegionId;
    }

    public byte getDefenseRegionId() {
        return defenseRegionId;
    }

    public void setDefenseRegionId(byte defenseRegionId) {
        this.defenseRegionId = defenseRegionId;
    }

    public byte[] getAttackValues() {
        return attackValues;
    }

    public void setAttackValues(byte[] attackValues) {
        this.attackValues = attackValues;
    }

    public byte[] getDefenseValues() {
        return defenseValues;
    }

    public void setDefenseValues(byte[] defenseValues) {
        this.defenseValues = defenseValues;
    }

    public byte getAttackSoldiers() {
        return attackSoldiers;
    }

    public void setAttackSoldiers(byte attackSoldiers) {
        this.attackSoldiers = attackSoldiers;
    }

    public byte getDefenseSoldiers() {
        return defenseSoldiers;
    }

    public void setDefenseSoldiers(byte defenseSoldiers) {
        this.defenseSoldiers = defenseSoldiers;
    }

    public byte getDefenseInfluenceId() {
        return defenseInfluenceId;
    }

    public void setDefenseInfluenceId(byte defenseInfluenceId) {
        this.defenseInfluenceId = defenseInfluenceId;
    }

    public byte getAttackPropAttackValue() {
        return attackPropAttackValue;
    }

    public void setAttackPropAttackValue(byte attackPropAttackValue) {
        this.attackPropAttackValue = attackPropAttackValue;
    }

    public byte getDefensePropDefenseValue() {
        return defensePropDefenseValue;
    }

    public void setDefensePropDefenseValue(byte defensePropDefenseValue) {
        this.defensePropDefenseValue = defensePropDefenseValue;
    }

    public int sumAttackValues() {
        int value = attackPropAttackValue;
        for (int i = 0; i < attackValues.length; ++i) {
            value += attackValues[i];
        }
        return value;
    }

    public int sumDefenseValues() {
        int value = defensePropDefenseValue;
        for (int i = 0; i < defenseValues.length; ++i) {
            value += defenseValues[i];
        }
        return value;
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
