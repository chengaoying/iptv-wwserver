package cn.halcyon.dao;

import java.sql.Connection;

import cn.halcyon.db.conn.JDBCConnection;
import cn.halcyon.db.conn.JDBCManager;

public abstract class AbstractJDBCGeneralDao<ModelType, PKType> implements
		IGeneralDao<ModelType, PKType> {
	private JDBCManager connManager;
	
	public AbstractJDBCGeneralDao(JDBCManager connManager) {
		this.connManager = connManager;
	}
	
	public JDBCConnection getJDBCConnection() {
		return connManager.getConnection();
	}
	
	public Connection getConnection() {
		return getJDBCConnection().getConnection();
	}
	
}
