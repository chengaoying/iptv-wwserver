package cn.ohyeah.ww.dao;


import cn.ohyeah.ww.model.GameRole;

public interface IGameRoleDao {
    public GameRole read(int roleId);
    public GameRole readByName(String name);
}
