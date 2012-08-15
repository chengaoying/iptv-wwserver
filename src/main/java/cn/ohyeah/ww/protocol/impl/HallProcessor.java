package cn.ohyeah.ww.protocol.impl;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.client.model.ClientHallInfo;
import cn.ohyeah.ww.protocol.ProcessContext;
import cn.ohyeah.ww.service.HallService;

import java.util.Map;

public class HallProcessor extends AbstractProcessor {

    private HallService hallService;

    public void setHallService(HallService hallService) {
        this.hallService = hallService;
    }

    public ByteBuffer login(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("roleId", req.readInt());
        params.put("roleName", req.readString());
        params.put("hallId", req.readInt());
        int[] token = hallService.login(params);
        ByteBuffer rsp = context.createResponse(32);
        writeToken(rsp, token);
        return rsp;
    }

    public ByteBuffer quit(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        hallService.quit(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer queryInfo(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        ClientHallInfo challInfo = hallService.queryInfo(params);
        ByteBuffer rsp = context.createResponse(16);
        challInfo.serialize(rsp);
        return rsp;
    }

}
