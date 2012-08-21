package cn.ohyeah.ww.client.message;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.io.Serializable;

public class GameStateMessage implements Serializable {
    /**
     * 无效指令
     */
    private static final byte CMD_INVALID = 0;
    /**
     * 使用道具
     */
    private static final byte CMD_USE_PROP = 1;
    /**
     * 进攻
     */
    private static final byte CMD_ATTACK = 2;

    /**
     * 游戏指令，使用道具或者进攻
     */
    private byte cmd;
    /**
     * 进攻方的道具ID，进攻方为当前回合操作的玩家
     */
    private byte attackPropId;
    /**
     * 防守方道具ID，防守方为被攻击的玩家，
     * 当进攻方没有攻击其他领地时，此值无效
     */
    private byte defensePropId;
    /**
     * 进攻方领地ID，进攻方为当前回合操作的玩家
     */
    private byte attackRegionId;
    /**
     * 防守方领地ID，防守方为被攻击的玩家，
     * 当进攻方没有攻击其他领地时，此值无效
     */
    private byte defenseRegionId;
    /**
     * 进攻方的战斗值
     * 当cmd为USE_PROP时，此值无效
     */
    private byte[] attackValues;
    /**
     * 防守方的战斗值
     * 当cmd为USE_PROP时，此值无效
     */
    private byte[] defenseValues;
    /**
     * 战斗结束后进攻领地的兵力
     * 当进攻方没有攻击其他领地时，此值无效
     */
    private byte attackSoldiers;
    /**
     * 战争结束后防守方领地的兵力
     * 当进攻方没有攻击其他领地时，此值无效
     */
    private byte defenseSoldiers;
    /**
     * 战争结束后防守方领地的归属者
     * 当进攻方没有攻击其他领地时，此值无效
     */
    private byte defenseInfluenceId;
    /**
     * 装备了装甲卡，产生的战斗值
     * 当进攻方没有攻击其他领地时，此值无效
     */
    private byte attackPropAttackValue;
    /**
     * 装备了防御卡，产生的战斗值
     * 当进攻方没有攻击其他领地时，此值无效
     */
    private byte defensePropDefenseValue;
    /**
     * 可以看见其他玩家道具的势力ID
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
