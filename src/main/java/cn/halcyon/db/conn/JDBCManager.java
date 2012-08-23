package cn.halcyon.db.conn;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JDBCManager implements IDBManager<JDBCConnection> {
	private final static Log log = LogFactory.getLog(JDBCManager.class);
	private final static ThreadLocal<JDBCConnection> conns = new ThreadLocal<JDBCConnection>();
	private static JDBCManager instance;
	private static DataSource dataSource;
	private static boolean showsql = false;
	
	private JDBCManager(){}
	
	private JDBCManager(String path) {
		init(load(path));
	}
	
	private JDBCManager(Properties props) {
		init(props);
	}
	
	private Properties load(String path) {
		Properties props = new Properties();
		InputStream is = null;
		try {
			is = JDBCManager.class.getResourceAsStream(path);
			props.load(is);
			Properties cp_props = new Properties();
			Iterator<Entry<Object, Object>> it = props.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Object, Object> e = it.next(); 
					String name = (String)e.getKey();
					String value = (String)e.getValue();
					if(name.startsWith("jdbc.")){
						String key = name.substring(5);
						cp_props.put(key, value);
					}
			}
			return cp_props;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					log.error("Can't close input stream", e);
				}
			}
		}
	}
	
	private void init(Properties props) {
		try {
			if (props.containsKey("showsql")) {
				showsql = BooleanUtils.toBoolean((String)props.get("showsql"));
			}
			dataSource = (DataSource)Class.forName(props.getProperty("datasource")).newInstance();
			if(dataSource.getClass().getName().indexOf("c3p0")>0){
				//Disable JMX in C3P0
				System.setProperty("com.mchange.v2.c3p0.management.ManagementCoordinator", 
						"com.mchange.v2.c3p0.management.NullManagementCoordinator");
			}
			log.info("Using DataSource : " + dataSource.getClass().getName());
			BeanUtils.populate(dataSource, props);

			JDBCConnection conn = instance.getConnection();
			DatabaseMetaData mdm = conn.getConnection().getMetaData();
			log.info("Connected to " + mdm.getDatabaseProductName() + " " + mdm.getDatabaseProductVersion());
			conn.close();
		} catch (Exception e) {
			throw new DBException(e);
		}
	}
	
	public static JDBCManager build(String path) {
		if (instance == null) {
			instance = new JDBCManager(path);
		}
		return instance;
	}
	
	public static JDBCManager build(Properties props) {
		if (instance == null) {
			instance = new JDBCManager(props);
		}
		return instance;
	}
	
	public final static JDBCManager getInstance() {
		return instance;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * ¶Ï¿ªÁ¬½Ó³Ø
	 */
	public final static void closeDataSource(){
		try {
			dataSource.getClass().getMethod("close").invoke(dataSource);
		} catch (NoSuchMethodException e){ 
		} catch (Exception e) {
			log.error("Unabled to destroy DataSource!!! ", e);
		}
	}
	
	@Override
	public JDBCConnection getConnection() {
		try {
			JDBCConnection jdbcConn = conns.get();
			if(jdbcConn==null || jdbcConn.getConnection()==null || jdbcConn.getConnection().isClosed()){
				Connection conn = dataSource.getConnection();
				if (showsql) {
					conn = new _DebugConnection(conn).getConnection();
				}
				jdbcConn = new JDBCConnection(conn);
				conns.set(jdbcConn);
			}
			return jdbcConn;
		}
		catch (Exception e) {
			throw new DBException(e);
		}
	}

	@Override
	public JDBCConnection getBatchConnection() {
		return getConnection();
	}

	
	static class _DebugConnection implements InvocationHandler {
		
		private final static Log log = LogFactory.getLog(_DebugConnection.class);
		
		private Connection conn = null;

		public _DebugConnection(Connection conn) {
			this.conn = conn;
		}

		public Connection getConnection() {
			return (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), 
                             conn.getClass().getInterfaces(), this);
		}
		
		public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
			try {
				String method = m.getName();
				if("prepareStatement".equals(method) || "createStatement".equals(method))
					log.info("[SQL] >>> " + args[0]);				
				return m.invoke(conn, args);
			} catch (InvocationTargetException e) {
				throw e.getTargetException();
			}
		}

	}

}
