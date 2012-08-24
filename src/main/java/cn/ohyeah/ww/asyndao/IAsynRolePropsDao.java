package cn.ohyeah.ww.asyndao;

import cn.halcyon.asyn.ICallback;
import cn.halcyon.asyn.IExceptionHandler;
import cn.ohyeah.ww.model.RoleProps;

import java.util.concurrent.Future;

public interface IAsynRolePropsDao {
    public Future<Integer> update(RoleProps props);
    public void update(RoleProps props, ICallback<Integer> callback, IExceptionHandler exceptionHandler);
    public Future<Void> create(RoleProps props);
    public void create(RoleProps props, ICallback<Void> callback, IExceptionHandler exceptionHandler);
    public Future<RoleProps> read(int roleId);
    public void read(int roleId, ICallback<RoleProps> callback, IExceptionHandler exceptionHandler);
}
