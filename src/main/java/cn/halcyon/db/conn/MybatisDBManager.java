package cn.halcyon.db.conn;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MybatisDBManager implements IDBManager<SqlSession> {

	private SqlSessionFactory sf;
	
	public MybatisDBManager(SqlSessionFactory sf) {
		this.sf = sf;
	}
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sf;
	}
	
	@Override
	public SqlSession getConnection() {
		try {
			return sf.openSession();
		}
		catch (Exception e) {
			throw new DBException(e);
		} 
	}

	@Override
	public SqlSession getBatchConnection() {
		try {
			return sf.openSession(ExecutorType.BATCH);
		}
		catch (Exception e) {
			throw new DBException(e);
		} 
	}
}
