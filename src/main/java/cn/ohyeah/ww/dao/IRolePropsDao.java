package cn.ohyeah.ww.dao;


import cn.ohyeah.ww.model.RoleProps;

public interface IRolePropsDao {
    public int update(RoleProps props);
    public void create(RoleProps props);
    public RoleProps read(int roleId);
}
