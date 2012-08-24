package cn.halcyon.asyn;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class AsynTaskProxy implements MethodInterceptor {
	private Object obj;
	private ExecutorService executor;
	private Map<String, Method> methods;
	
	public AsynTaskProxy(ExecutorService executor, Object obj) {
		this.executor = executor;
		this.obj = obj;
		methods = new HashMap<String, Method>();
		Method[] ms = obj.getClass().getMethods();
		for (Method m : ms) {
			methods.put(m.getName(), m);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object intercept(Object instance, Method method, final Object[] args,
			MethodProxy methodProxy) throws Throwable {
		final Method m = methods.get(method.getName());
		Object result = null;
		if (method.getReturnType().isAssignableFrom(java.util.concurrent.Future.class)) {
			Callable caller = new Callable() {
				@Override
				public Object call() throws Exception {
					return m.invoke(obj, args);
				}
			};
			result = executor.submit(caller);
		}
		else {
			Runnable runner = new Runnable() {
				@Override
				public void run() {
					try {
						Object v = m.invoke(obj, Arrays.copyOf(args, args.length-2));
						ICallback callback = (ICallback)args[args.length-2];
						if (callback != null) {
							callback.call(v);
						}
					}
					catch (Throwable ex) {
						IExceptionHandler exceptionHandler = (IExceptionHandler)args[args.length-1];
						if (exceptionHandler != null) {
							exceptionHandler.handleException(ex);
						}
					}
				}
			};
			executor.submit(runner);
		}
		return result;
	}
}
