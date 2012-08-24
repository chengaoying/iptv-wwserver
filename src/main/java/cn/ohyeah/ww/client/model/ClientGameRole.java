package cn.ohyeah.ww.client.model;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-8-24
 * Time: ÏÂÎç5:49
 * To change this template use File | Settings | File Templates.
 */
public class ClientGameRole {
    private int roleId;
    private String roleName;
    private String nickName;
    private int privilege;
    private String userId;
    private String gender;
    private String headIcon;
    private int goldCoin;
    private int gameCoin;
    private int scores;
    private int onlineSeconds;
    private int victoryCount;
    private int failureCount;
    private int escapeCount;
    private int playCount;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public int getGoldCoin() {
        return goldCoin;
    }

    public void setGoldCoin(int goldCoin) {
        this.goldCoin = goldCoin;
    }

    public int getGameCoin() {
        return gameCoin;
    }

    public void setGameCoin(int gameCoin) {
        this.gameCoin = gameCoin;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public int getOnlineSeconds() {
        return onlineSeconds;
    }

    public void setOnlineSeconds(int onlineSeconds) {
        this.onlineSeconds = onlineSeconds;
    }

    public int getVictoryCount() {
        return victoryCount;
    }

    public void setVictoryCount(int victoryCount) {
        this.victoryCount = victoryCount;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    public int getEscapeCount() {
        return escapeCount;
    }

    public void setEscapeCount(int escapeCount) {
        this.escapeCount = escapeCount;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
}
