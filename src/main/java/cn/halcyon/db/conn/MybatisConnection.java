package cn.halcyon.db.conn;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

public class MybatisConnection implements IConnection<SqlSession> {
	private SqlSession session;
	
	@Override
	public void close() {
		try {
			session.close();
		}
		catch (Exception e) {
			throw new DBException(e);
		}
	}

	@Override
	public void commit() {
		try {
			session.commit();
		}
		catch (Exception e) {
			throw new DBException(e);
		}
	}

	@Override
	public void rollback() {
		try {
			session.rollback();
		}
		catch (Exception e) {
			throw new DBException(e);
		}
	}

	@Override
	public void setAutoCommit(boolean autoCommit) {
		try {
			session.getConnection().setAutoCommit(autoCommit);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	@Override
	public SqlSession getConnection() {
		return session;
	}


}
