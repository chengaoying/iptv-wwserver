package cn.halcyon.db.conn;

public interface IConnection<ConnType> {
	public ConnType getConnection();
	public void close();
	public void commit();
	public void rollback();
	public void setAutoCommit(boolean auto);
}
