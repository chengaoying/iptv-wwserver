package cn.ohyeah.ww.protocol.impl;

import cn.ohyeah.stb.util.ByteBuffer;
import cn.ohyeah.ww.client.model.ClientTableInfo;
import cn.ohyeah.ww.protocol.ProcessContext;
import cn.ohyeah.ww.service.TableService;

import java.util.Map;

public class TableProcessor extends AbstractProcessor {
    private TableService tableService;

    public void setTableService(TableService tableService) {
        this.tableService = tableService;
    }

    public ByteBuffer prepare(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        int[] propIds = readPropIds(req);
        tableService.prepare(roleId, token, propIds);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer join(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        int tableId = req.readInt();
        tableService.join(roleId, token, tableId);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer quit(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        tableService.quit(roleId, token);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer queryInfo(ProcessContext context, ByteBuffer req) {
        int roleId = req.readInt();
        int[] token = readToken(req);
        ClientTableInfo ctableInfo = tableService.queryInfo(roleId, token);
        ByteBuffer rsp = context.createResponse(16);
        ctableInfo.serialize(rsp);
        return rsp;
    }
}
