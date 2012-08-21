package cn.ohyeah.ww.dao;


import cn.ohyeah.ww.model.RoleProps;

public interface IRolePropsDao {
    public void update(RoleProps props);
    public RoleProps read(int roleId);
}
