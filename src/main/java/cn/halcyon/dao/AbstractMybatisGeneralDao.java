package cn.halcyon.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import cn.halcyon.db.conn.DBException;

import javax.annotation.Resource;


public abstract class AbstractMybatisGeneralDao<ModelType, PKType, MapperType extends IGeneralDao<ModelType, PKType>> 
	implements	IGeneralDao<ModelType, PKType> {
	
	private static Log log = LogFactory.getLog(AbstractMybatisGeneralDao.class); 
	private SqlSessionFactory sf;
	private Class<MapperType> mapperClass;

    public AbstractMybatisGeneralDao(Class<MapperType> mapperClass) {
        this.mapperClass = mapperClass;
    }

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sf = sqlSessionFactory;
    }

	protected SqlSession openSession() {
		return sf.openSession();
	}
	
	protected SqlSession openBatchSession() {
		return sf.openSession(ExecutorType.BATCH);
	}
	
	@Override
	public ModelType read(PKType id) {
		SqlSession session = null;
		try {
			session = sf.openSession();
			IGeneralDao<ModelType, PKType> mapper = session.getMapper(mapperClass);
			return mapper.read(id);
		}
		catch (Exception e) {
			throw new DBException(e);
		}
		finally {
			session.close();
		}
	}

	@Override
	public List<ModelType> readAll() {
		SqlSession session = null;
		try {
			session = sf.openSession();
			IGeneralDao<ModelType, PKType> mapper = session.getMapper(mapperClass);
			return mapper.readAll();
		}
		catch (Exception e) {
			throw new DBException(e);
		}
		finally {
			session.close();
		}
	}

	@Override
	public void create(ModelType model) {
		SqlSession session = null;
		try {
			session = sf.openSession();
			IGeneralDao<ModelType, PKType> mapper = session.getMapper(mapperClass);
			mapper.create(model);
			session.commit();
		}
		catch (Exception e) {
			throw new DBException(e);
		}
		finally {
			session.close();
		}
	}

	@Override
	public int update(ModelType model) {
		SqlSession session = null;
		int status = 0;
		try {
			session = sf.openSession();
			IGeneralDao<ModelType, PKType> mapper = session.getMapper(mapperClass);
			status = mapper.update(model);
			session.commit();
			return status;
		}
		catch (Exception e) {
			throw new DBException(e);
		}
		finally {
			session.close();
		}
	}

	@Override
	public int delete(ModelType model) {
		SqlSession session = null;
		int status = 0;
		try {
			session = sf.openSession();
			IGeneralDao<ModelType, PKType> mapper = session.getMapper(mapperClass);
			status = mapper.delete(model);
			session.commit();
			return status;
		}
		catch (Exception e) {
			throw new DBException(e);
		}
		finally {
			session.close();
		}
	}

}
