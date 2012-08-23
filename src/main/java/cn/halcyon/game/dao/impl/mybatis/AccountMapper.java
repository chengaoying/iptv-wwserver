package cn.halcyon.game.dao.impl.mybatis;

import java.util.List;

import cn.halcyon.dao.IGeneralDao;
import cn.halcyon.game.model.Account;

public interface AccountMapper extends IGeneralDao<Account, Integer> {
	public Account readByUserName(String userName);
	public List<Account> readByUserId(String userId);
}
