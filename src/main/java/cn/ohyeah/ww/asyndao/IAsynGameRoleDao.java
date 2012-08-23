package cn.ohyeah.ww.asyndao;

import cn.halcyon.asyn.ICallback;
import cn.halcyon.asyn.IExceptionHandler;
import cn.ohyeah.ww.model.GameRole;

import java.util.List;
import java.util.concurrent.Future;

public interface IAsynGameRoleDao {
    public abstract void create(final GameRole role,
                                final ICallback<Void> callback,
                                final IExceptionHandler exceptionHandler);

    public abstract Future<Void> create(final GameRole role);

    public abstract void update(final GameRole role,
                                final ICallback<Integer> callback,
                                final IExceptionHandler exceptionHandler);

    public abstract Future<Integer> update(final GameRole role);

    public abstract void readByRoleId(final int roleId,
                                         final ICallback<GameRole> callback,
                                         final IExceptionHandler exceptionHandler);

    public abstract Future<GameRole> readByRoleId(final int roleId);

    public abstract void readByRoleName(final String roleName,
                                        final ICallback<GameRole> callback,
                                        final IExceptionHandler exceptionHandler);

    public abstract Future<GameRole> readByRoleName(final String roleName);

    public abstract void readByUserId(final String userId,
                                      final ICallback<List<GameRole>> callback,
                                      final IExceptionHandler exceptionHandler);

    public abstract Future<List<GameRole>> readByUserId(final String userId);
}
