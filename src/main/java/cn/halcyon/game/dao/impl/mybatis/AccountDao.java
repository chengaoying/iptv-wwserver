package cn.halcyon.game.dao.impl.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import cn.halcyon.dao.AbstractMybatisGeneralDao;
import cn.halcyon.db.conn.DBException;
import cn.halcyon.game.dao.IAccountDao;
import cn.halcyon.game.model.Account;

public class AccountDao extends AbstractMybatisGeneralDao<Account, Integer, AccountMapper> 
	implements	IAccountDao {

	public AccountDao(SqlSessionFactory sf) {
		super(sf, AccountMapper.class);
	}

	@Override
	public void create(Account account) {
		super.create(account);
	}

	@Override
	public int update(Account account) {
		return super.update(account);
	}

	@Override
	public Account readByAccountId(int accountId) {
		return super.read(accountId);
	}

	@Override
	public Account readByUserName(String userName) {
		SqlSession session = null;
		try {
			session = openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			return mapper.readByUserName(userName);
		}
		catch (Exception e) {
			throw new DBException(e);
		}
		finally {
			session.close();
		}
	}

	@Override
	public List<Account> readByUserId(String userId) {
		SqlSession session = null;
		try {
			session = openSession();
			AccountMapper mapper = session.getMapper(AccountMapper.class);
			return mapper.readByUserId(userId);
		}
		catch (Exception e) {
			throw new DBException(e);
		}
		finally {
			session.close();
		}
	}

}
