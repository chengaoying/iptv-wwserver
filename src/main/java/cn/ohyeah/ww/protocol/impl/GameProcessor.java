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

    public ByteBuffer quit(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        gameService.quit(roleId, token);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer coerceQuit(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        gameService.coerceQuit(roleId, token);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer useProp(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        int propId = req.readInt();
        int destRegionId = req.readShort();
        gameService.attack(roleId, token, propId, destRegionId);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer attack(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        int srcRegionId = req.readShort();
        int destRegionId = req.readShort();
        gameService.attack(roleId, token, srcRegionId, destRegionId);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer endRound(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        gameService.endRound(roleId, token);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }
}
