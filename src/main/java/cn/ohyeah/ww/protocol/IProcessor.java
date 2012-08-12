package cn.ohyeah.ww.protocol;

import cn.ohyeah.stb.util.ByteBuffer;

public interface IProcessor {
    public ByteBuffer process(ByteBuffer req);
}
