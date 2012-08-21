package cn.ohyeah.ww.client.message;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.io.Serializable;

public class GameEndMessage implements Serializable {
    private short winnerInfluenceId;
    private short deltaScores;
    private int scores;
    private short captureRegions;
    private short loseRegions;
    private short useProps;
    private short annihilateSoldiers;
    private short loseSoldiers;

    @Override
    public void serialize(ByteBuffer buf) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deserialize(ByteBuffer buf) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
