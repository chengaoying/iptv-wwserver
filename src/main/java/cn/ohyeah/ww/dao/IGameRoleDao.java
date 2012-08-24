package cn.ohyeah.ww.dao;

import cn.ohyeah.ww.model.GameRole;

import java.util.List;

public interface IGameRoleDao {
    public void create(GameRole role);
    public int update(GameRole role);
    public GameRole read(int roleId);
    public GameRole readByName(String roleName);
    public List<GameRole> readRoles(String userId);
}
