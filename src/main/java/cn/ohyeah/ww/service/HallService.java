package cn.ohyeah.ww.service;

import cn.ohyeah.ww.client.model.ClientHallInfo;
import cn.ohyeah.ww.manager.GameManager;

import java.util.Map;

public class HallService {
    private GameManager manager;

    public GameManager getManager() {
        return manager;
    }

    public void setManager(GameManager manager) {
        this.manager = manager;
    }

    public int[] login(Map<String, Object> params) {
        return new int[] {1,2,3,4};
    }

    public void quit(Map<String, Object> params) {

    }

    public ClientHallInfo queryInfo(Map<String, Object> params) {
        return null;
    }
}
