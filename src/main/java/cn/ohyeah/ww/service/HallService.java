package cn.ohyeah.ww.service;

import cn.ohyeah.ww.client.model.ClientHallInfo;
import cn.ohyeah.ww.dao.IGameRoleDao;
import cn.ohyeah.ww.manager.ChannelManager;
import cn.ohyeah.ww.manager.HallManager;
import cn.ohyeah.ww.model.GameRole;
import cn.ohyeah.ww.server.model.ServerRoleInfo;
import org.jboss.netty.channel.Channel;

import java.util.Map;

public class HallService {
    private ChannelManager channelManager;
    private HallManager hallManager;
    private IGameRoleDao roleDao;

    public void setHallManager(HallManager hallManager) {
        this.hallManager = hallManager;
    }

    public void setChannelManager(ChannelManager channelManager) {
        this.channelManager = channelManager;
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
        hallManager.loginHall(roleInfo, hallId);
        channelManager.addChannelRole(channel, roleInfo);
        return roleInfo.getTolen();
    }

    public void quit(Map<String, Object> params) {
        int roleId = (Integer)params.get("roleId");
        int[] token = (int[])params.get("token");
        ServerRoleInfo roleInfo = hallManager.quitHall(roleId, token);
        channelManager.removeChannelRole(roleInfo.getChannel());
    }

    public ClientHallInfo queryInfo(Map<String, Object> params) {
        int roleId = (Integer)params.get("roleId");
        int[] token = (int[])params.get("token");
        return hallManager.queryHallInfo(roleId, token);
    }
}
