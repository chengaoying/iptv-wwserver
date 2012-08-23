package cn.halcyon.asyndao;

import java.util.concurrent.ExecutorService;

import net.sf.cglib.proxy.Enhancer;

public class AsynDaoCreator {
	
	private ExecutorService executor;
	
	public AsynDaoCreator() {}
	
	public AsynDaoCreator(ExecutorService executor) {
		this.executor = executor;
	}
	
	public void setDBExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	@SuppressWarnings("unchecked")
	public <T> T create(Class<T> c, Object dao) {
		Enhancer en = new Enhancer();  
	    en.setSuperclass(c);  
	    en.setCallback(new AsynDaoProxy(executor, dao));  
	    return (T)en.create();
	}
}
