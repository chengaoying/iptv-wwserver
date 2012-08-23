package cn.halcyon.game.asyndao;

import java.util.List;
import java.util.concurrent.Future;

import cn.halcyon.asyn.ICallback;
import cn.halcyon.asyn.IExceptionHandler;
import cn.halcyon.game.model.Account;

public interface IAsynAccountDao {
	public abstract void create(final Account account,
			final ICallback<Void> callback,
			final IExceptionHandler exceptionHandler);

	public abstract Future<Void> create(final Account account);

	public abstract void update(final Account account,
			final ICallback<Integer> callback,
			final IExceptionHandler exceptionHandler);

	public abstract Future<Integer> update(final Account account);

	public abstract void readByAccountId(final int accountId,
			final ICallback<Account> callback,
			final IExceptionHandler exceptionHandler);

	public abstract Future<Account> readByAccountId(final int accountId);

	public abstract void readByUserName(final String userName,
			final ICallback<Account> callback,
			final IExceptionHandler exceptionHandler);

	public abstract Future<Account> readByUserName(final String userName);

	public abstract void readByUserId(final String userId,
			final ICallback<List<Account>> callback,
			final IExceptionHandler exceptionHandler);

	public abstract Future<List<Account>> readByUserId(final String userId);

}