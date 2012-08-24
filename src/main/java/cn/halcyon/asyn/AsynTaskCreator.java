package cn.halcyon.asyn;

import java.util.concurrent.ExecutorService;

import net.sf.cglib.proxy.Enhancer;

public class AsynTaskCreator {
	
	private ExecutorService executor;
	
	public AsynTaskCreator() {}
	
	public AsynTaskCreator(ExecutorService executor) {
		this.executor = executor;
	}
	
	public void setExecutorService(ExecutorService executor) {
		this.executor = executor;
	}

	@SuppressWarnings("unchecked")
	public <T> T create(Class<T> c, Object instance) {
		Enhancer en = new Enhancer();  
	    en.setSuperclass(c);  
	    en.setCallback(new AsynTaskProxy(executor, instance));
	    return (T)en.create();
	}
}
