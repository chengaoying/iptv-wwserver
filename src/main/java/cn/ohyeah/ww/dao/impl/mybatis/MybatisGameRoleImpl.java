package cn.ohyeah.ww.dao.impl.mybatis;

import cn.ohyeah.ww.dao.IGameRoleDao;
import cn.ohyeah.ww.model.GameRole;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisGameRoleImpl implements IGameRoleDao{
    @Override
    public GameRole read(int roleId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public GameRole readByName(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
