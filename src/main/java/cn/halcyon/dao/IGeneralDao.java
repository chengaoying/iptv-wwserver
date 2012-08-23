package cn.halcyon.dao;

import java.util.List;

public interface IGeneralDao<ModelType, PKType> {
	public ModelType read(PKType id);
	public List<ModelType> readAll();
	public void create(ModelType model);
	public int update(ModelType model);
	public int delete(ModelType model);
}
