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
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        params.put("roomId", req.readInt());
        roomService.login(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer quit(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        roomService.quit(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }


    public ByteBuffer quickJoin(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        roomService.quickJoinTable(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer queryInfo(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        ClientRoomInfo croomInfo = roomService.queryInfo(params);
        ByteBuffer rsp = context.createResponse(16);
        croomInfo.serialize(rsp);
        return rsp;
    }
}
