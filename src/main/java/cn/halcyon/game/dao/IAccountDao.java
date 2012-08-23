package cn.halcyon.game.dao;

import java.util.List;

import cn.halcyon.game.model.Account;

public interface IAccountDao {
	public void create(Account account);
	public int update(Account account);
	public Account readByAccountId(int accountId);
	public Account readByUserName(String userName);
	public List<Account> readByUserId(String userId);
}
