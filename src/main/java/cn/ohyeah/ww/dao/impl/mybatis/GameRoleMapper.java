package cn.ohyeah.ww.dao.impl.mybatis;

import cn.halcyon.dao.IGeneralDao;
import cn.ohyeah.ww.model.GameRole;

import java.util.List;

public interface GameRoleMapper extends IGeneralDao<GameRole, Integer> {
	public GameRole readByName(String userName);
	public List<GameRole> readRoles(String userId);
}
