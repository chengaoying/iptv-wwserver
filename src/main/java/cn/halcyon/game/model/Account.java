package cn.halcyon.game.model;

public class Account implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7850482305753074587L;
	public static final byte ACCOUNT_INVALID = 0;			//Î´¼¤»î,ÎÞÐ§×´Ì¬
	public static final byte ACCOUNT_ACTIVATION = 1;			//¼¤»î×´Ì¬
	public static final byte ACCOUNT_FROZEN = 2;				//¶³½á×´Ì¬
	public static final byte ACCOUNT_DELETED = 3;			//É¾³ý×´Ì¬
	
	private int accountId;
	private String userName;
	private String nickName;
	private String password;
	private int privilege;
	private String userId;
	private int scores;
	private long goldCoin;
	private long gameCoin;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private int state;
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public int getScores() {
		return scores;
	}
	public void setScores(int scores) {
		this.scores = scores;
	}
	
	public void incScores(int inc) {
		scores += inc;
	}
	
	public void decScores(int dec) {
		scores -= dec;
	}
	
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public boolean isStateInvalid() {
		return this.state == ACCOUNT_INVALID;
	}
	public void setStateInvalid() {
		this.state = ACCOUNT_INVALID;
	}
	public boolean isStateActivation() {
		return this.state == ACCOUNT_ACTIVATION;
	}
	public void setStateActivation() {
		this.state = ACCOUNT_ACTIVATION;
	}
	public boolean isStateFrozen() {
		return this.state == ACCOUNT_FROZEN;
	}
	public void setStateFrozen() {
		this.state = ACCOUNT_FROZEN;
	}
	public boolean isStateDeleted() {
		return this.state == ACCOUNT_DELETED;
	}
	public void setStateDeleted() {
		this.state = ACCOUNT_DELETED;
	}

	public int getPrivilege() {
		return privilege;
	}
	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}
	
	public long getGoldCoin() {
		return goldCoin;
	}
	public void setGoldCoin(long goldCoin) {
		this.goldCoin = goldCoin;
	}
	
	public void incGoldCoin(long inc) {
		goldCoin += inc;
	}
	
	public void decGoldCoin(long dec) {
		goldCoin -= dec;
	}
	
	public long getGameCoin() {
		return gameCoin;
	}
	public void setGameCoin(long gameCoin) {
		this.gameCoin = gameCoin;
	}
	
	public void incGameCoin(long inc) {
		gameCoin += inc;
	}
	
	public void decGameCoin(long dec) {
		gameCoin -= dec;
	}
	
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "id:"+accountId+", userName:"+userName+", nickName:"+nickName;
	}
	
}
