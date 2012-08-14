package cn.ohyeah.ww.protocol.impl;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.protocol.ProcessContext;
import cn.ohyeah.ww.service.RoomService;

import java.util.Map;

public class RoomProcessor extends AbstractProcessor {
    private RoomService service;

    public void setRoomService(RoomService service) {
        this.service = service;
    }

    public ByteBuffer login(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        params.put("hallId", req.readInt());
        params.put("roomId", req.readInt());
        service.login(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer quit(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        service.quit(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer queryInfo(ProcessContext context, ByteBuffer req) {
        return null;
    }
}
