package cn.ohyeah.ww.service;

import cn.ohyeah.ww.dao.IRolePropsDao;
import cn.ohyeah.ww.model.RoleProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropsService {
    private IRolePropsDao propsDao;

    @Autowired
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
