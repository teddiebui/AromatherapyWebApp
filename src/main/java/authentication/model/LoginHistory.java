package authentication.model;

import java.sql.Timestamp;

public class LoginHistory {
	/**
	 * Auto Generated Session ID
	 */
	private int id;
	
	/**
	 *  Username of this login Session
	 */
	private String username;
	
	/**
	 * Login status success or fail
	 */
	private boolean loginStatus;
	
	/**
	 * Login device that attempt to authenticate
	 */
	
	private String loginDevice;
	
	/**
	 * Login IP that attempt to authenticate
	 */
	private String loginIP;
	
	/**
	 * Login attempt.
	 */
	private int loginAttempt;
	
	/**
	 * Login time that attempt to authenticate
	 */
	private Timestamp loginTime;

	
	public LoginHistory() {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public boolean isLoginStatus() {
		return loginStatus;
	}


	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}


	public String getLoginDevice() {
		return loginDevice;
	}


	public void setLoginDevice(String loginDevice) {
		this.loginDevice = loginDevice;
	}


	public String getLoginIP() {
		return loginIP;
	}


	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}


	public int getLoginAttempt() {
		return loginAttempt;
	}


	public void setLoginAttempt(int loginAttempt) {
		this.loginAttempt = loginAttempt;
	}


	public Timestamp getLoginTime() {
		return loginTime;
	}


	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}


	@Override
	public String toString() {
		return "LoginHistory [id=" + id + ", username=" + username
				+ ", loginStatus=" + loginStatus + ", loginDevice="
				+ loginDevice + ", loginIP=" + loginIP + ", loginAttempt="
				+ loginAttempt + ", loginTime=" + loginTime + "]";
	}


	
}
