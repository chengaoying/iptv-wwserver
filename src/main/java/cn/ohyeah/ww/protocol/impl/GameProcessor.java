package cn.ohyeah.ww.protocol.impl;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.protocol.ProcessContext;
import cn.ohyeah.ww.service.GameService;

import java.util.Map;

public class GameProcessor extends AbstractProcessor {
    private GameService gameService;

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public ByteBuffer prepare(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        params.put("propIds", readPropIds(req));
        gameService.prepare(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer quit(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        gameService.attack(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer useProp(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        params.put("propId", req.readInt());
        params.put("destRegionId", req.readShort());
        gameService.attack(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer attack(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        params.put("srcRegionId", req.readShort());
        params.put("destRegionId", req.readShort());
        gameService.attack(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer endRound(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        gameService.endRound(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }
}
