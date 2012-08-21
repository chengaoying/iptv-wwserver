package cn.ohyeah.ww.server.game;

import cn.ohyeah.ww.client.message.GameRoundMessage;
import cn.ohyeah.ww.client.message.GameStateMessage;

import java.util.Random;

public class GameLogic {

    private void checkDefenseCard(Region region) {
        if (!region.isDefensePropInvalid()) {
            //TODO new RuntimeException();
        }
    }

    private void checkAttackCard(Region region) {
        if (!region.isAttackPropInvalid()) {
            //TODO new RuntimeException();
        }
    }

    public GameRoundMessage endRound(GameMap map, int influenceId) {
        Influence inf = map.getInfluence(influenceId);
        if (inf == null) {
            //TODO throw new RuntimException();
        }
        Region[] regions = map.getRegions(influenceId);
        byte[][] raiseSoldiers = new byte[regions.length][];
        for (int i = 0; i < raiseSoldiers.length; ++i) {
            raiseSoldiers[i] = new byte[2];
            raiseSoldiers[i][0] = (byte)regions[i].getId();
        }
        int raiseCount = regions.length;
        Random random = new Random();
        while (raiseCount > 0) {
            raiseSoldiers[randomInt(random, regions.length)][1]++;
            raiseCount--;
        }
        byte[] freeProps = new byte[2];
        freeProps[0] = (byte)randomInt(random, 6);
        freeProps[1] = (byte)randomInt(random, 6);
        GameRoundMessage roundMessage = new GameRoundMessage();
        roundMessage.setRaiseSoldiers(raiseSoldiers);
        roundMessage.setFreeProps(freeProps);
        return roundMessage;
    }

    public GameStateMessage useProp(GameMap map, int propId, int srcRegionId, int destRegionId) {
        Region srcRegion = map.getRegion(srcRegionId);
        if (srcRegion == null) {
            //TODO throw new RuntimException();
        }
        Region destRegion = null;
        GameStateMessage stateMessage = new GameStateMessage();
        stateMessage.setCmdUseProp();
        stateMessage.setAttackPropId((byte) propId);
        stateMessage.setAttackRegionId((byte) srcRegionId);
        stateMessage.setDefenseRegionId((byte) destRegionId);
        switch (propId) {
            case 0://������
                checkDefenseCard(srcRegion);
                srcRegion.setDefensePropId(propId);
                break;
            case 1: //���˿�
                checkDefenseCard(srcRegion);
                srcRegion.setDefensePropId(propId);
                break;
            case 2: //���׿�
                checkDefenseCard(srcRegion);
                srcRegion.setDefensePropId(propId);
                break;
            case 3: //���ؿ�
                checkDefenseCard(srcRegion);
                srcRegion.setDefensePropId(propId);
                break;
            case 4: //�ڻ���
                if (!map.isConnected(srcRegionId, destRegionId)) {
                    //TODO throw new RuntimeException();
                }
                //Ŀ������з�����
                destRegion = map.getRegion(destRegionId);
                if (destRegion.getDefensePropId() == 0) {
                    stateMessage.setDefensePropId((byte) 0);
                }
                else {
                    int attackValue = randomInt(6)+1;
                    if (attackValue >= destRegion.getSoldiers()) {
                        destRegion.setSoldiers((short)1);
                    }
                    else {
                        destRegion.setSoldiers((short)(srcRegion.getSoldiers()-attackValue));
                    }
                    stateMessage.setDefenseSoldiers((byte) destRegion.getSoldiers());
                    stateMessage.setDefenseInfluenceId((byte) destRegionId);
                }
                break;
            case 5: //װ�׿�
                checkAttackCard(srcRegion);
                srcRegion.setAttackPropId(propId);
                break;
            case 6: //��鿨
                //
                break;
            case 7: //��Ϯ��
                destRegion = map.getRegion(destRegionId);
                if (destRegion == null) {
                    //TODO throw new RuntimException();
                }
                destRegion.setSoldiers((byte)1);
                stateMessage.setDefenseSoldiers((byte) destRegion.getSoldiers());
                stateMessage.setDefenseInfluenceId((byte) destRegionId);
                break;
            case 8: //�߷���
                destRegion = randomEnemyRegion(map, srcRegion);
                if (destRegion == null) {
                    //TODO throw new RuntimException();
                }
                destRegion.setInfluenceId(srcRegion.getInfluenceId());
                stateMessage.setDefenseSoldiers((byte) destRegion.getSoldiers());
                stateMessage.setDefenseInfluenceId((byte) destRegion.getInfluenceId());
                break;
            default:
                //TODO throw new RuntimException();
                break;
        }
        return stateMessage;
    }

    private Region randomEnemyRegion(GameMap map, Region self) {
        Region[] regions = map.getRegions();
        int enemyRegions = 0;
        for (int i = 0; i < regions.length; ++i) {
            if (regions[i] != self) {
                enemyRegions++;
            }
        }
        Region region = null;
        int randomEnemyRegionId = randomInt(enemyRegions);
        for (int i = 0; i < regions.length; ++i, --randomEnemyRegionId) {
            if (regions[i] != self && randomEnemyRegionId == 0) {
                region = regions[i];
                break;
            }
        }
        return region;
    }


    public GameStateMessage attack(GameMap map, int srcRegionId, int destRegionId) {
        Region src = map.getRegion(srcRegionId);
        if (src == null) {
            //TODO throw new RuntimException();
        }
        Region dest = map.getRegion(destRegionId);
        if (dest == null) {
            //TODO throw new RuntimException();
        }

        GameStateMessage stateMessage = new GameStateMessage();
        stateMessage.setCmdAttack();
        stateMessage.setAttackRegionId((byte) srcRegionId);
        stateMessage.setDefenseRegionId((byte)destRegionId);
        stateMessage.setAttackPropId((byte)src.getAttackPropId());
        stateMessage.setDefensePropId((byte)dest.getDefensePropId());

        Random random = new Random();
        byte[] attackValues = new byte[src.getSoldiers()];
        for (int i = 0; i < attackValues.length; ++i) {
            attackValues[i] = (byte)(random.nextInt(6)+1);
        }
        byte[] defenseValues = new byte[dest.getSoldiers()];
        for (int i = 0; i < defenseValues.length; ++i) {
            defenseValues[i] = (byte)(random.nextInt(6)+1);
        }
        stateMessage.setAttackValues(attackValues);
        stateMessage.setDefenseValues(defenseValues);
        if (stateMessage.getAttackPropId() == 5) { //װ����װ�׿�
            stateMessage.setAttackPropAttackValue((byte)6);
        }
        if (stateMessage.getDefensePropId() == 1) { //װ���˷�����
            stateMessage.setDefensePropDefenseValue((byte)6);
        }

        //������ʤ��
        if (stateMessage.sumAttackValues() > stateMessage.sumDefenseValues()) {
            stateMessage.setAttackSoldiers((byte)1);
            stateMessage.setDefenseSoldiers((byte) (src.getSoldiers() - dest.getSoldiers()));
            stateMessage.setDefenseInfluenceId((byte) src.getInfluenceId());
        }
        else {
            stateMessage.setAttackSoldiers((byte)1);
            stateMessage.setDefenseSoldiers((byte)dest.getSoldiers());
            stateMessage.setDefenseInfluenceId((byte) dest.getInfluenceId());
        }

        //���������ݸ���,ɾ��ʹ�ù��ĵ���
        src.setAttackPropInvalid();
        dest.setDefensePropInvalid();
        //���������ݸ��£����½�����أ�������صı�������������صĹ�����
        src.setSoldiers(stateMessage.getAttackSoldiers());
        dest.setSoldiers(stateMessage.getDefenseSoldiers());
        dest.setInfluenceId(stateMessage.getDefenseInfluenceId());
        return stateMessage;
    }

    private int randomInt(int n) {
        return randomInt(new Random(), n);
    }

    private int randomInt(Random rd, int n) {
        return rd.nextInt(n);
    }

    private int randomInt(int a, int b) {
        return randomInt(new Random(), a, b);
    }

    private int randomInt(Random rd, int a, int b) {
        return rd.nextInt(b-a)+a;
    }
}
