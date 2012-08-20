package cn.ohyeah.ww.message;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.client.model.ClientGameInfo;
import cn.ohyeah.ww.client.model.ClientRoomInfo;
import cn.ohyeah.ww.client.model.ClientTableInfo;
import cn.ohyeah.ww.protocol.HeadWrapper;
import org.jboss.netty.channel.Channel;

import java.util.List;

public class MessageSender {
    public HeadWrapper createHead() {
        HeadWrapper head = new HeadWrapper();
        head.setType(1);
        return head;
    }

    public void encode(ByteBuffer buf) {
        int len = buf.length()-8;
        buf.setInt(buf.getReaderIndex()+4, len);
    }

    public void sendRoomInfo(List<Channel> channels, ClientRoomInfo roomInfo) {
        HeadWrapper head = createHead();
        ByteBuffer buf = new ByteBuffer();
        buf.writeInt(head.getHead());
        buf.writeInt(0);
        roomInfo.serialize(buf);
        encode(buf);
        byte[] data = buf.readAllBytes();
        for (Channel c : channels) {
            c.write(data);
        }
    }

    public void sendTableInfo(List<Channel> channels, ClientTableInfo tableInfo) {
        HeadWrapper head = createHead();
        ByteBuffer buf = new ByteBuffer();
        buf.writeInt(head.getHead());
        buf.writeInt(0);
        tableInfo.serialize(buf);
        encode(buf);
        byte[] data = buf.readAllBytes();
        for (Channel c : channels) {
            c.write(data);
        }
    }

    public void sendGameInfo(List<Channel> channels, ClientGameInfo gameInfo) {
        HeadWrapper head = createHead();
        ByteBuffer buf = new ByteBuffer();
        buf.writeInt(head.getHead());
        buf.writeInt(0);
        gameInfo.serialize(buf);
        encode(buf);
        byte[] data = buf.readAllBytes();
        for (Channel c : channels) {
            c.write(data);
        }
    }

    public void sendGameDetail() {

    }

}
