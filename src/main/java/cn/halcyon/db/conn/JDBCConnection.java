package cn.halcyon.db.conn;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCConnection implements IConnection<Connection> {
	private Connection conn;
	
	public JDBCConnection(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	@Override
	public void commit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	@Override
	public void rollback() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	@Override
	public void setAutoCommit(boolean autoCommit) {
		try {
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	@Override
	public Connection getConnection() {
		return conn;
	}

}
