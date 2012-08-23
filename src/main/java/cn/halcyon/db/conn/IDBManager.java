package cn.halcyon.db.conn;

public interface IDBManager<ConnectionType> {
	public ConnectionType getConnection();
	public ConnectionType getBatchConnection();
}
