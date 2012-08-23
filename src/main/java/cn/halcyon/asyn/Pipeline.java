package cn.halcyon.asyn;

import java.util.concurrent.ExecutionException;

public class Pipeline {
	private PipelineNode nodes;
	private PipelineNode curNode;
	private PipelineNode lastNode;
	private Object initValue;
	@SuppressWarnings("rawtypes")
	private ICallback callback;
	private IExceptionHandler exceptionHandler;

	@SuppressWarnings("rawtypes")
	public Pipeline(IPipelineStep... steps) {
		this(null, steps);
	}

	@SuppressWarnings("rawtypes")
	public Pipeline(Object initValue, IPipelineStep... steps) {
		this.initValue = initValue;
		pipeline(steps);
	}

	@SuppressWarnings("rawtypes")
	public void pipeline(IPipelineStep... steps) {
		if (steps != null && steps.length > 0) {
			for (IPipelineStep step : steps) {
				addStep(step);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void onSuccess(ICallback callback) {
		this.callback = callback;
	}

	public void onFailed(IExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	public <T> T runPipeline(Class<T> c) throws InterruptedException,
			ExecutionException {
		return runPipeline(c, this.initValue, this.callback,
				this.exceptionHandler);
	}

	public <T> T runPipeline(Class<T> c, Object initValue)
			throws InterruptedException, ExecutionException {
		return runPipeline(c, initValue, this.callback, this.exceptionHandler);
	}

	@SuppressWarnings("rawtypes")
	public <T> T runPipeline(Class<T> c, Object initValue, ICallback callback)
			throws InterruptedException, ExecutionException {
		return runPipeline(c, initValue, callback, this.exceptionHandler);
	}
	
	@SuppressWarnings("rawtypes")
	public <T> T runPipeline(Class<T> c, ICallback callback)
			throws InterruptedException, ExecutionException {
		return runPipeline(c, this.initValue, callback, this.exceptionHandler);
	}
	
	@SuppressWarnings("rawtypes")
	public <T> T runPipeline(Class<T> c, ICallback callback, IExceptionHandler exceptionHandler)
			throws InterruptedException, ExecutionException {
		return runPipeline(c, this.initValue, callback, exceptionHandler);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T runPipeline(Class<T> c, Object initValue, ICallback callback,
			IExceptionHandler exceptionHandler) throws InterruptedException,
			ExecutionException {
		Object param = initValue;
		if (param != null && param instanceof java.util.concurrent.Future) {
			param = ((java.util.concurrent.Future) param).get();
		}
		Object result = param;
		try {
			while (curNode != null) {
				param = result = curNode.step.process(param);
				if (param != null
						&& param instanceof java.util.concurrent.Future) {
					param = ((java.util.concurrent.Future) param).get();
				}
				curNode = curNode.nextNode;
			}
			if (callback != null) {
				callback.call(param);
			}
		} catch (Throwable ex) {
			result = null;
			if (exceptionHandler != null) {
				exceptionHandler.handleException(ex);
			}
		}
		return (T) result;
	}

	@SuppressWarnings("rawtypes")
	public Pipeline addStep(IPipelineStep step) {
		if (nodes == null) {
			nodes = new PipelineNode(step, null);
			curNode = nodes;
			lastNode = nodes;
		} else {
			lastNode.nextNode = new PipelineNode(step, null);
			lastNode = lastNode.nextNode;
		}
		return this;
	}

	private class PipelineNode {
		@SuppressWarnings("rawtypes")
		IPipelineStep step;
		PipelineNode nextNode;

		@SuppressWarnings("rawtypes")
		PipelineNode(IPipelineStep step, PipelineNode next) {
			this.step = step;
			this.nextNode = next;
		}
	}

}
