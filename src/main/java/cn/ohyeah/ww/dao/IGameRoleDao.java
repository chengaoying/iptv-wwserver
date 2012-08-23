package cn.ohyeah.ww.dao;

import cn.ohyeah.ww.model.GameRole;

import java.util.List;

public interface IGameRoleDao {
    public void create(GameRole role);
    public int update(GameRole role);
    public GameRole readByRoleId(int roleId);
    public GameRole readByRoleName(String roleName);
    public List<GameRole> readByUserId(String userId);
}
