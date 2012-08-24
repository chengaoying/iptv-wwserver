package cn.ohyeah.ww.message;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.client.message.GameEndMessage;
import cn.ohyeah.ww.client.message.GameRoundMessage;
import cn.ohyeah.ww.client.message.GameStartMessage;
import cn.ohyeah.ww.client.model.ClientRoomInfo;
import cn.ohyeah.ww.client.model.ClientTableInfo;
import cn.ohyeah.ww.protocol.Constant;
import cn.ohyeah.ww.protocol.HeadWrapper;
import cn.ohyeah.ww.server.model.ServerRoleInfo;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {
    public HeadWrapper createHead(int cmd) {
        return new HeadWrapper.Builder().version(Constant.PROTOCOL_VERSION).type(1)
                .tag(Constant.PROTOCOL_TAG_GAME_SERVER).command(cmd).build();
    }

    public void encode(ByteBuffer buf) {
        int len = buf.length()-8;
        buf.setInt(buf.getReaderIndex()+4, len);
    }

    public void sendRoomInfo(ServerRoleInfo roleInfo, ClientRoomInfo roomInfo) {
        HeadWrapper head = createHead(Constant.PUSH_MSG_ROOM_INFO);
        ByteBuffer buf = new ByteBuffer();
        buf.writeInt(head.getHead());
        buf.writeInt(0);
        roomInfo.serialize(buf);
        encode(buf);
        ChannelBuffer chBuf = ChannelBuffers.wrappedBuffer(buf.buffer(), buf.getReaderIndex(), buf.length());
        roleInfo.getChannel().write(chBuf);
    }

    public void sendTableInfo(ServerRoleInfo roleInfo, ClientTableInfo tableInfo) {
        HeadWrapper head = createHead(Constant.PUSH_MSG_TABLE_INFO);
        ByteBuffer buf = new ByteBuffer();
        buf.writeInt(head.getHead());
        buf.writeInt(0);
        tableInfo.serialize(buf);
        encode(buf);
        ChannelBuffer chBuf = ChannelBuffers.wrappedBuffer(buf.buffer(), buf.getReaderIndex(), buf.length());
        roleInfo.getChannel().write(chBuf);
    }

    public void sendGameStart(ServerRoleInfo roleGameInfo, GameStartMessage startMessage) {
        HeadWrapper head = createHead(Constant.PUSH_MSG_GAME_START);
        ByteBuffer buf = new ByteBuffer();
        buf.writeInt(head.getHead());
        buf.writeInt(0);
        startMessage.serialize(buf);
        encode(buf);
        ChannelBuffer chBuf = ChannelBuffers.wrappedBuffer(buf.buffer(), buf.getReaderIndex(), buf.length());
        roleGameInfo.getChannel().write(chBuf);
    }

    public void sendGameRound(ServerRoleInfo roleGameInfo, GameRoundMessage roundMessage) {
        HeadWrapper head = createHead(Constant.PUSH_MSG_GAME_ROUND);
        ByteBuffer buf = new ByteBuffer();
        buf.writeInt(head.getHead());
        buf.writeInt(0);
        roundMessage.serialize(buf);
        encode(buf);
        ChannelBuffer chBuf = ChannelBuffers.wrappedBuffer(buf.buffer(), buf.getReaderIndex(), buf.length());
        roleGameInfo.getChannel().write(chBuf);
    }

    public void sendGameEnd(ServerRoleInfo roleGameInfo, GameEndMessage endMessage) {
        HeadWrapper head = createHead(Constant.PUSH_MSG_GAME_END);
        ByteBuffer buf = new ByteBuffer();
        buf.writeInt(head.getHead());
        buf.writeInt(0);
        endMessage.serialize(buf);
        encode(buf);
        ChannelBuffer chBuf = ChannelBuffers.wrappedBuffer(buf.buffer(), buf.getReaderIndex(), buf.length());
        roleGameInfo.getChannel().write(chBuf);
    }

}
