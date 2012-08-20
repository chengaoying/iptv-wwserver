package cn.ohyeah.ww.server.model;

import cn.ohyeah.ww.server.game.Influence;

public class RoleGameState {
    private Influence influence;
    private int[] prepareProps;
    private int[] freeProps;
    private long roundStartMillis;

    public Influence getInfluence() {
        return influence;
    }

    public void setInfluence(Influence influence) {
        this.influence = influence;
    }

    public int[] getPrepareProps() {
        return prepareProps;
    }

    public void setPrepareProps(int[] prepareProps) {
        this.prepareProps = prepareProps;
    }

    public int[] getFreeProps() {
        return freeProps;
    }

    public void setFreeProps(int[] freeProps) {
        this.freeProps = freeProps;
    }

    public long getRoundStartMillis() {
        return roundStartMillis;
    }

    public void setRoundStartMillis(long roundStartMillis) {
        this.roundStartMillis = roundStartMillis;
    }
}
