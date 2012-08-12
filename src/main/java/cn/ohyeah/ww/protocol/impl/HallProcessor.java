package cn.ohyeah.ww.protocol.impl;

import cn.ohyeah.ww.service.HallService;

public class HallProcessor extends AbstractProcessor {

    private HallService service;

    public void setHallService(HallService service) {
        this.service = service;
    }

    public void login() {
        params.put("roleId", buffer.readInt());
        params.put("roleName", buffer.readString());
        params.put("hallId", buffer.readInt());
        refreshBuffer();
        writeToken(service.login(params));
    }

    public void quit() {
        params.put("token", readToken());
        params.put("roleId", buffer.readInt());
        refreshBuffer();
        service.quit(params);
    }

}
