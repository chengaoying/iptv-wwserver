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
        String roleName = req.readUTF();
        String password = req.readUTF();
        int hallId = req.readInt();
        int[] token = hallService.login(roleName, password, hallId, context.getChannel());
        ByteBuffer rsp = context.createResponse(32);
        writeToken(rsp, token);
        return rsp;
    }

    public ByteBuffer quit(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        hallService.quit(roleId, token);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer queryInfo(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        ClientHallInfo challInfo = hallService.queryInfo(roleId, token);
        ByteBuffer rsp = context.createResponse(16);
        challInfo.serialize(rsp);
        return rsp;
    }

}
