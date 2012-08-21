package cn.ohyeah.ww.service;

import cn.ohyeah.ww.client.model.ClientHallInfo;
import cn.ohyeah.ww.dao.IGameRoleDao;
import cn.ohyeah.ww.manager.ChannelManager;
import cn.ohyeah.ww.manager.HallManager;
import cn.ohyeah.ww.model.GameRole;
import cn.ohyeah.ww.protocol.Constant;
import cn.ohyeah.ww.server.model.ServerRoleInfo;
import org.jboss.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HallService {
    private ChannelManager channelManager;
    private HallManager hallManager;
    private IGameRoleDao roleDao;

    @Autowired
    public void setHallManager(HallManager hallManager) {
        this.hallManager = hallManager;
    }
    @Autowired
    public void setChannelManager(ChannelManager channelManager) {
        this.channelManager = channelManager;
    }
    @Autowired
    public void setGameRoleDao(IGameRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public int[] login(String roleName, String password, int hallId, Channel channel) {
        GameRole role = roleDao.readByName(roleName);
        if (role == null) {
            throw new ServiceException(Constant.getErrorMessage(Constant.EC_ROLE_NAME_NOT_EXIST));
        }
        ServerRoleInfo roleInfo = new ServerRoleInfo();
        roleInfo.setChannel(channel);
        hallManager.loginHall(roleInfo, hallId);
        channelManager.addChannelRole(channel, roleInfo);
        return roleInfo.getTolen();
    }

    public void quit(int roleId, int[] token) {
        ServerRoleInfo roleInfo = hallManager.quitHall(roleId, token);
        channelManager.removeChannelRole(roleInfo.getChannel());
    }

    public ClientHallInfo queryInfo(int roleId, int[] token) {
        return hallManager.queryHallInfo(roleId, token);
    }
}
