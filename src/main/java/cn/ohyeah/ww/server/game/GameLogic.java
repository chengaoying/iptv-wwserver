package cn.ohyeah.ww.server.game;

import cn.ohyeah.ww.client.model.ClientGameDetail;

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

    public ClientGameDetail useProp(GameMap map, int propId, int srcRegionId, int destRegionId) {
        Region srcRegion = map.getRegion(srcRegionId);
        if (srcRegion == null) {
            //TODO throw new RuntimException();
        }
        Region destRegion = null;
        ClientGameDetail detail = new ClientGameDetail();
        detail.setCmdUseProp();
        detail.setAttackPropId((byte) propId);
        detail.setAttackRegionId((byte)srcRegionId);
        detail.setDefenseRegionId((byte)destRegionId);
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
                    detail.setDefensePropId((byte)0);
                }
                else {
                    int attackValue = randomInt(6)+1;
                    if (attackValue >= destRegion.getSoldiers()) {
                        destRegion.setSoldiers((short)1);
                    }
                    else {
                        destRegion.setSoldiers((short)(srcRegion.getSoldiers()-attackValue));
                    }
                    detail.setDefenseSoldiers((byte)destRegion.getSoldiers());
                    detail.setDefenseInflunceId((byte)destRegionId);
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
                detail.setDefenseSoldiers((byte)destRegion.getSoldiers());
                detail.setDefenseInflunceId((byte)destRegionId);
                break;
            case 8: //�߷���
                destRegion = randomEnemyRegion(map, srcRegion);
                if (destRegion == null) {
                    //TODO throw new RuntimException();
                }
                destRegion.setInfluenceId(srcRegion.getInfluenceId());
                detail.setDefenseSoldiers((byte)destRegion.getSoldiers());
                detail.setDefenseInflunceId((byte)destRegion.getInfluenceId());
                break;
            default:
                //TODO throw new RuntimException();
                break;
        }
        return detail;
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


    public ClientGameDetail attack(GameMap map, int srcRegionId, int destRegionId) {
        Region src = map.getRegion(srcRegionId);
        if (src == null) {
            //TODO throw new RuntimException();
        }
        Region dest = map.getRegion(destRegionId);
        if (dest == null) {
            //TODO throw new RuntimException();
        }

        ClientGameDetail detail = new ClientGameDetail();
        detail.setCmdAttack();
        detail.setAttackRegionId((byte) srcRegionId);
        detail.setDefenseRegionId((byte)destRegionId);
        detail.setAttackPropId((byte)src.getAttackPropId());
        detail.setDefensePropId((byte)dest.getDefensePropId());

        Random random = new Random();
        byte[] attackValues = new byte[src.getSoldiers()];
        for (int i = 0; i < attackValues.length; ++i) {
            attackValues[i] = (byte)(random.nextInt(6)+1);
        }
        byte[] defenseValues = new byte[dest.getSoldiers()];
        for (int i = 0; i < defenseValues.length; ++i) {
            defenseValues[i] = (byte)(random.nextInt(6)+1);
        }
        detail.setAttackValues(attackValues);
        detail.setDefenseValues(defenseValues);
        if (detail.getAttackPropId() == 5) { //װ����װ�׿�
            detail.setAttackPropAttackValue((byte)6);
        }
        if (detail.getDefensePropId() == 1) { //װ���˷�����
            detail.setDefensePropDefenseValue((byte)6);
        }

        //������ʤ��
        if (detail.sumAttackValues() > detail.sumDefenseValues()) {
            detail.setAttackSoldiers((byte)1);
            detail.setDefenseSoldiers((byte) (src.getSoldiers() - dest.getSoldiers()));
            detail.setDefenseInflunceId((byte)src.getInfluenceId());
        }
        else {
            detail.setAttackSoldiers((byte)1);
            detail.setDefenseSoldiers((byte)dest.getSoldiers());
            detail.setDefenseInflunceId((byte)dest.getInfluenceId());
        }

        //���������ݸ���,ɾ��ʹ�ù��ĵ���
        src.setAttackPropInvalid();
        dest.setDefensePropInvalid();
        //���������ݸ��£����½�����أ�������صı�������������صĹ�����
        src.setSoldiers(detail.getAttackSoldiers());
        dest.setSoldiers(detail.getDefenseSoldiers());
        dest.setInfluenceId(detail.getDefenseInflunceId());
        return detail;
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
