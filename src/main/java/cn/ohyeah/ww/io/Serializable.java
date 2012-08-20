package cn.ohyeah.ww.io;

import cn.ohyeah.stb.util.ByteBuffer;

public interface Serializable {
    public void serialize(ByteBuffer buf);
    public void deserialize(ByteBuffer buf);
}
