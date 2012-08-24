package cn.ohyeah.ww.service;

import cn.ohyeah.ww.dao.IGameRoleDao;
import cn.ohyeah.ww.model.GameRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleService {
    private IGameRoleDao gameRoleDao;

    @Autowired
    public void setGameRoleDao(IGameRoleDao gameRoleDao) {
        this.gameRoleDao = gameRoleDao;
    }

    public GameRole create(String roleName, String userId, String password) {
        GameRole role = new GameRole();
        role.setRoleName(roleName);
        role.setUserId(userId);
        role.setPassword(password);
        Date now = new Date();
        role.setCreateTime(now);
        role.setUpdateTime(now);
        gameRoleDao.create(role);
        return role;
    }

    public List<GameRole> queryRoles(String userId) {
        return gameRoleDao.readRoles(userId);
    }
}
