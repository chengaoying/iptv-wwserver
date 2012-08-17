package cn.ohyeah.ww.protocol.impl;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.client.model.ClientRoomInfo;
import cn.ohyeah.ww.protocol.ProcessContext;
import cn.ohyeah.ww.service.RoomService;

import java.util.Map;

public class RoomProcessor extends AbstractProcessor {
    private RoomService roomService;

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    public ByteBuffer login(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        int roomId = req.readInt();
        roomService.login(roleId, token, roomId);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer quit(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        roomService.quit(roleId, token);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }


    public ByteBuffer quickJoin(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        roomService.quickJoinTable(roleId, token);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer queryInfo(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        ClientRoomInfo croomInfo = roomService.queryInfo(roleId, token);
        ByteBuffer rsp = context.createResponse(16);
        croomInfo.serialize(rsp);
        return rsp;
    }
}
