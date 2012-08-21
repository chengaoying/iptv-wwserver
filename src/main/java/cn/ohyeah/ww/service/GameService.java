package cn.ohyeah.ww.service;


import cn.ohyeah.ww.manager.GameManager;
import cn.ohyeah.ww.manager.HallManager;
import cn.ohyeah.ww.server.model.RoleGameInfo;
import cn.ohyeah.ww.server.model.ServerGameInfo;
import cn.ohyeah.ww.server.model.ServerRoleInfo;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class GameService {
    private HallManager hallManager;
    private GameManager gameManager;

    @Inject
    public void setHallManager(HallManager hallManager) {
        this.hallManager = hallManager;
    }
    @Inject
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void quit(int roleId, int[] token) {
        //TODO
    }

    public void coerceQuit(int roleId, int[] token) {
        //TODO
    }

    public void useProp(int roleId, int[] token, int propId, int destRegionId) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        ServerGameInfo gameInfo = roleInfo.getServerTable().getServerGame();
        RoleGameInfo roleGame = gameInfo.getCurGameRole();
        if (roleGame.getServerRole().getRole().getRoleId() != roleId) {
            //TODO throw new RuntimeException();
        }
        if (roleGame.useProp(propId)) {

        }
        else {
            //TODO throw new RuntimeException();
        }
        //TODO other logic
    }

    public void attack(int roleId, int[] token, int srcRegionId, int destRegionId) {
        //TODO
    }

    public void endRound(int roleId, int[] token) {
        //TODO
    }
}
