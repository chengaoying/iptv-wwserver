package cn.halcyon.asyndao;

import cn.halcyon.asyn.TaskExecutor;

public class DBExecutor extends TaskExecutor{
	private static DBExecutor instance = new DBExecutor();
	
	private DBExecutor() {
		super();
	}
	
	public static DBExecutor getInstance() {
		return instance;
	}
}
