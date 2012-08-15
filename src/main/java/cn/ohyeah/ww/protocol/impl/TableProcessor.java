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

    public ByteBuffer login(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        params.put("tableId", req.readInt());
        tableService.login(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer quit(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        tableService.quit(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer quickJoin(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        tableService.quickJoin(params);
        ByteBuffer rsp = context.createResponse(16);
        return rsp;
    }

    public ByteBuffer queryInfo(ProcessContext context, ByteBuffer req) {
        Map<String, Object> params = context.getParams();
        params.put("token", readToken(req));
        params.put("roleId", req.readInt());
        ClientTableInfo ctableInfo = tableService.queryInfo(params);
        ByteBuffer rsp = context.createResponse(16);
        ctableInfo.serialize(rsp);
        return rsp;
    }
}
