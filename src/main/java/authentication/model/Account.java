package authentication.model;

import java.sql.Timestamp;

public class Account {

	private String username;

	private String password;

	private boolean locked;
	
	private int loginAttempt; 

	private Timestamp createTime;

	public Account() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLocked() {
		return locked;
	}

	public int getLoginAttempt() {
		return loginAttempt;
	}

	public void setLoginAttempt(int loginAttempt) {
		this.loginAttempt = loginAttempt;
	}

	public void setLocked(boolean lock) {
		this.locked = lock;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password
				+ ", isLocked=" + locked + ", loginAttempt=" + loginAttempt
				+ ", createTime=" + createTime + "]";
	}


}
