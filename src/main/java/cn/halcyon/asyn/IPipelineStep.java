package cn.halcyon.asyn;

public interface IPipelineStep<T, V> {
	public V process(T p);
}
