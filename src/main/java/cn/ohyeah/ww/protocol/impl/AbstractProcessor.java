package cn.ohyeah.ww.protocol.impl;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.protocol.IProcessor;
import cn.ohyeah.ww.server.ProtocolFrame;
import cn.ohyeah.stb.buf.ByteBuffer;

import java.util.Map;

public abstract class AbstractProcessor implements IProcessor {
    protected ByteBuffer buffer;
    protected Map<String, Object> params;

    protected void writeToken(int[] token) {
        for (int i= 0; i < token.length; ++i) {
            buffer.writeInt(token[i]);
        }
    }

    protected int[] readToken() {
        int[] token = new int[4];
        for (int i = 0; i < token.length; ++i) {
            token[i] = buffer.readInt();
        }
        return token;
    }

    protected void refreshBuffer() {
        buffer.clear();
    }

    @Override
    public ByteBuffer process(ByteBuffer req) {
        return null;
    }
}
