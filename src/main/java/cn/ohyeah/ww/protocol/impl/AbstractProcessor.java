package cn.ohyeah.ww.protocol.impl;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.protocol.IProcessor;

public abstract class AbstractProcessor implements IProcessor {

    protected void writeToken(ByteBuffer rsp, int[] token) {
        for (int i= 0; i < token.length; ++i) {
            rsp.writeInt(token[i]);
        }
    }

    protected int[] readToken(ByteBuffer req) {
        int[] token = new int[4];
        for (int i = 0; i < token.length; ++i) {
            token[i] = req.readInt();
        }
        return token;
    }

    protected int[] readPropIds(ByteBuffer req) {
        int[] propIds = null;
        int count = req.readInt();
        if (count > 0) {
            for (int i = 0; i < count; ++i) {
                propIds[i] = req.readInt();
            }
        }
        return propIds;
    }

}
