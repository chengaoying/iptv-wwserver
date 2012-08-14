package cn.ohyeah.ww.service;

import cn.ohyeah.ww.client.model.ClientHallInfo;
import cn.ohyeah.ww.dao.IGameRoleDao;
import cn.ohyeah.ww.manager.GameManager;
import cn.ohyeah.ww.model.GameRole;
import cn.ohyeah.ww.server.model.ServerHallInfo;
import cn.ohyeah.ww.server.model.ServerRoleInfo;
import org.jboss.netty.channel.Channel;

import java.util.Map;

public class HallService {
    private GameManager manager;
    private IGameRoleDao roleDao;

    public void setManager(GameManager manager) {
        this.manager = manager;
    }

    public void setGameRoleDao(IGameRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public int[] login(Map<String, Object> params) {
        int roleId = (Integer)params.get("roleId");
        String roleName = (String)params.get("roleName");
        int hallId = (Integer)params.get("hallId");
        Channel channel = (Channel)params.get("channel");

        GameRole role = roleDao.readByName(roleName);
        ServerRoleInfo roleInfo = new ServerRoleInfo();
        roleInfo.setChannel(channel);
        manager.loginHall(roleInfo);
        return roleInfo.getTolen();
    }

    public void quit(Map<String, Object> params) {
        int roleId = (Integer)params.get("roleId");
        manager.quitHall(roleId);
    }

    public ClientHallInfo queryInfo(Map<String, Object> params) {

        return null;
    }
}
