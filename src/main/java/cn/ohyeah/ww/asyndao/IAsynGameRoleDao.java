package cn.ohyeah.ww.asyndao;

import cn.halcyon.asyn.ICallback;
import cn.halcyon.asyn.IExceptionHandler;
import cn.ohyeah.ww.model.GameRole;

import java.util.List;
import java.util.concurrent.Future;

public interface IAsynGameRoleDao {
    public void create(GameRole role, ICallback<Void> callback, IExceptionHandler exceptionHandler);
    public Future<Void> create(GameRole role);
    public void update(GameRole role, ICallback<Integer> callback, IExceptionHandler exceptionHandler);
    public Future<Integer> update(GameRole role);
    public void readByRoleId(int roleId, ICallback<GameRole> callback, IExceptionHandler exceptionHandler);
    public Future<GameRole> readByRoleId(int roleId);
    public void readByRoleName(String roleName, ICallback<GameRole> callback, IExceptionHandler exceptionHandler);
    public Future<GameRole> readByRoleName(String roleName);
    public void readByUserId(String userId, ICallback<List<GameRole>> callback, IExceptionHandler exceptionHandler);
    public Future<List<GameRole>> readByUserId(String userId);
}
