package cn.ohyeah.ww.service;

import cn.ohyeah.ww.dao.IRolePropsDao;
import cn.ohyeah.ww.model.RoleProps;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-8-20
 * Time: ионГ10:27
 * To change this template use File | Settings | File Templates.
 */
public class PropsService {
    private IRolePropsDao propsDao;

    public void setRolePropsDao(IRolePropsDao propsDao) {
        this.propsDao = propsDao;
    }

    public RoleProps read(int roleId) {
        return propsDao.read(roleId);
    }

    public void update(RoleProps roleProps) {
        propsDao.update(roleProps);
    }
}
