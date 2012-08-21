package cn.ohyeah.ww.service;


import cn.ohyeah.ww.client.message.GameRoundMessage;
import cn.ohyeah.ww.client.message.GameStateMessage;
import cn.ohyeah.ww.manager.GameManager;
import cn.ohyeah.ww.manager.HallManager;
import cn.ohyeah.ww.message.MessageSender;
import cn.ohyeah.ww.server.game.GameLogic;
import cn.ohyeah.ww.server.model.ServerRoleGameInfo;
import cn.ohyeah.ww.server.model.ServerGameInfo;
import cn.ohyeah.ww.server.model.ServerRoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private HallManager hallManager;
    private GameManager gameManager;
    private GameLogic gameLogic;
    private MessageSender messageSender;

    @Autowired
    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Autowired
    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Autowired
    public void setHallManager(HallManager hallManager) {
        this.hallManager = hallManager;
    }

    @Autowired
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void quit(int roleId, int[] token) {
        //TODO
        //ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        //ServerGameInfo gameInfo = roleInfo.getTable().getGame();

    }

    public void coerceQuit(int roleId, int[] token) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        ServerGameInfo gameInfo = roleInfo.getTable().getGame();
        if (gameInfo != null) {

        }
        ServerRoleGameInfo roleGameInfo = gameInfo.getCurGameRole();
        roleGameInfo.setStateFleeing();
        //TODO 扣分，如果为两人游戏，其他玩家直接胜利
    }

    public GameStateMessage useProp(int roleId, int[] token, int propId, int attackRegionId, int defenseRegionId) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        ServerGameInfo gameInfo = roleInfo.getTable().getGame();
        ServerRoleGameInfo roleGameInfo = gameInfo.getCurGameRole();
        if (roleGameInfo.getRole() != roleInfo) {
            //TODO throw new RuntimeException();
        }
        if (!roleGameInfo.useProp(propId)) {
            //TODO throw new RuntimeException();
        }

        GameStateMessage stateMessage = gameLogic.useProp(gameInfo.getMap(), propId, attackRegionId, defenseRegionId);
        //TODO other logic
        return stateMessage;
    }

    public GameStateMessage attack(int roleId, int[] token, int attackRegionId, int defenseRegionId) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        ServerGameInfo gameInfo = roleInfo.getTable().getGame();
        ServerRoleGameInfo roleGameInfo = gameInfo.getCurGameRole();
        if (roleGameInfo.getRole() != roleInfo) {
            //TODO throw new RuntimeException();
        }

        GameStateMessage stateMessage = gameLogic.attack(gameInfo.getMap(), attackRegionId, defenseRegionId);
        //TODO
        return stateMessage;
    }

    public GameRoundMessage endRound(int roleId, int[] token) {
        ServerRoleInfo roleInfo = hallManager.queryAndCheckRole(roleId, token);
        ServerGameInfo gameInfo = roleInfo.getTable().getGame();
        ServerRoleGameInfo roleGameInfo = gameInfo.getCurGameRole();
        if (roleGameInfo.getRole() != roleInfo) {
            //TODO throw new RuntimeException();
        }

        GameRoundMessage roundMessage = gameLogic.endRound(gameInfo.getMap(), roleGameInfo.getInfluenceId());
        roundMessage.setLastRoleIndex((short) gameInfo.getCurRoleIndex());
        roundMessage.setCurRoleIndex((byte)gameInfo.nextRoleIndex());
        //TODO
        return roundMessage;
    }
}
