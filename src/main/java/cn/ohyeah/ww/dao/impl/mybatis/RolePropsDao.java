package cn.ohyeah.ww.dao.impl.mybatis;

import cn.halcyon.dao.AbstractMybatisGeneralDao;
import cn.ohyeah.ww.dao.IRolePropsDao;
import cn.ohyeah.ww.model.RoleProps;
import org.springframework.stereotype.Repository;

@Repository
public class RolePropsDao extends AbstractMybatisGeneralDao<RoleProps, Integer, RolePropsMapper>
    implements IRolePropsDao {

    public RolePropsDao() {
        super(RolePropsMapper.class);
    }

    @Override
    public RoleProps read(int roleId) {
        return super.read(roleId);
    }
}
