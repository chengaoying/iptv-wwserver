package cn.halcyon.db.conn;

public class DBException extends RuntimeException {

	private static final long serialVersionUID = 4954424601259206372L;

	public DBException() {
		super();
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBException(String message) {
		super(message);
	}

	public DBException(Throwable cause) {
		super(cause);
	}
}
