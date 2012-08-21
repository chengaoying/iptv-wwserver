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
            case 0://防御卡
                checkDefenseCard(srcRegion);
                srcRegion.setDefensePropId(propId);
                break;
            case 1: //撤退卡
                checkDefenseCard(srcRegion);
                srcRegion.setDefensePropId(propId);
                break;
            case 2: //地雷卡
                checkDefenseCard(srcRegion);
                srcRegion.setDefensePropId(propId);
                break;
            case 3: //隐藏卡
                checkDefenseCard(srcRegion);
                srcRegion.setDefensePropId(propId);
                break;
            case 4: //炮击卡
                if (!map.isConnected(srcRegionId, destRegionId)) {
                    //TODO throw new RuntimeException();
                }
                //目标地区有防御卡
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
            case 5: //装甲卡
                checkAttackCard(srcRegion);
                srcRegion.setAttackPropId(propId);
                break;
            case 6: //侦查卡
                //
                break;
            case 7: //空袭卡
                destRegion = map.getRegion(destRegionId);
                if (destRegion == null) {
                    //TODO throw new RuntimException();
                }
                destRegion.setSoldiers((byte)1);
                detail.setDefenseSoldiers((byte)destRegion.getSoldiers());
                detail.setDefenseInflunceId((byte)destRegionId);
                break;
            case 8: //策反卡
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
        if (detail.getAttackPropId() == 5) { //装备了装甲卡
            detail.setAttackPropAttackValue((byte)6);
        }
        if (detail.getDefensePropId() == 1) { //装备了防御卡
            detail.setDefensePropDefenseValue((byte)6);
        }

        //进攻方胜利
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

        //服务器数据更新,删除使用过的道具
        src.setAttackPropInvalid();
        dest.setDefensePropInvalid();
        //服务器数据更新，更新进攻领地，防守领地的兵力，及防守领地的归属者
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
