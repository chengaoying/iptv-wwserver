package cn.ohyeah.ww.dao.impl.mybatis;

import cn.halcyon.dao.AbstractMybatisGeneralDao;
import cn.halcyon.db.conn.DBException;
import cn.ohyeah.ww.dao.IGameRoleDao;
import cn.ohyeah.ww.model.GameRole;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRoleDao extends AbstractMybatisGeneralDao<GameRole, Integer, GameRoleMapper>
    implements IGameRoleDao {

    public GameRoleDao() {
        super(GameRoleMapper.class);
    }

    @Override
    public GameRole read(int roleId) {
        return super.read(roleId);
    }

    @Override
    public GameRole readByName(String roleName) {
        SqlSession session = null;
        try {
            session = openSession();
            GameRoleMapper mapper = session.getMapper(GameRoleMapper.class);
            return mapper.readByName(roleName);
        }
        catch (Exception e) {
            throw new DBException(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<GameRole> readRoles(String userId) {
        SqlSession session = null;
        try {
            session = openSession();
            GameRoleMapper mapper = session.getMapper(GameRoleMapper.class);
            return mapper.readRoles(userId);
        }
        catch (Exception e) {
            throw new DBException(e);
        }
        finally {
            session.close();
        }
    }
}
