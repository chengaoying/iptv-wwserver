package cn.ohyeah.ww.dao;

import cn.halcyon.asyn.ICallback;
import cn.halcyon.asyn.IExceptionHandler;
import cn.halcyon.asyn.Pipeline;
import cn.ohyeah.ww.asyndao.IAsynGameRoleDao;
import cn.ohyeah.ww.global.Configurations;
import cn.ohyeah.ww.model.GameRole;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class GameRoleDaoTest {
    private static ApplicationContext context;
    private static IAsynGameRoleDao asynDao;
    private static LinkedBlockingDeque<Object> queue;

    @BeforeClass public static void beforeClass() {
        context = new AnnotationConfigApplicationContext(Configurations.class);
        asynDao = context.getBean(IAsynGameRoleDao.class);
        queue = new LinkedBlockingDeque<Object>();
    }

    @AfterClass public static void afterClass() throws InterruptedException {
        Object obj;
        while ((obj=queue.poll(1, TimeUnit.SECONDS)) != null) {
            if (obj instanceof Throwable) {
                ((Throwable) obj).printStackTrace();
            }
            else {
                System.out.println(obj);
            }
        }
    }

    @Test public void testSave() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; ++i) {
            final GameRole role = new GameRole();
            role.setRoleName("user" + System.nanoTime());
            role.setAccountId(10000);
            role.setUserId("igsuper00");
            role.setHeadIcon("default");
            role.setCreateTime(new java.util.Date());
            role.setUpdateTime(role.getCreateTime());
            role.setPassword("password");

            System.out.println("Write Req ==> " + role.getRoleName());
            asynDao.create(role,
                new ICallback<Void>() {
                    @Override
                    public void call(Void v) {
                        queue.add("[Thread:"+Thread.currentThread().getId()+"]"+"Success Create ==> " + role.toString());
                    }
                },
                new IExceptionHandler() {
                    @Override
                    public void handleException(Throwable ex) {
                        queue.add(ex);
                    }
                });
        }
    }

    @Test public void testRead() {
        final int startId = 10000;
        for (int i = 0; i < 10; ++i) {
           System.out.println("Read Req ==> " + startId+i);
           asynDao.readByRoleId(startId+i,
                new ICallback<GameRole>() {
                    @Override
                    public void call(GameRole gameRole) {
                        queue.add("[Thread:"+Thread.currentThread().getId()+"]"+"Success Read ==> " + gameRole);
                    }
                },
                new IExceptionHandler() {
                    @Override
                    public void handleException(Throwable ex) {
                        queue.add(ex);
                    }
                });
        }
    }
}
