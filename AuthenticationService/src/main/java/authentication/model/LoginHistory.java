package authentication.model;

import java.sql.Timestamp;

public class LoginHistory {

	private int id;
	

	private String username;
	

	private boolean loginStatus;
	
	
	private String loginDevice;
	

	private String loginIP;

	private int loginAttempt;
	
	private String refreshKey;
	
	private boolean isRefreshKeyActive;
	
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

	public String getRefreshKey() {
		return refreshKey;
	}

	public void setRefreshKey(String refreshKey) {
		this.refreshKey = refreshKey;
	}

	public boolean isRefreshKeyActive() {
		return isRefreshKeyActive;
	}

	public void setRefreshKeyActive(boolean isRefreshKeyActive) {
		this.isRefreshKeyActive = isRefreshKeyActive;
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
				+ loginAttempt + ", refreshKey=" + refreshKey
				+ ", isRefreshKeyActive=" + isRefreshKeyActive + ", loginTime="
				+ loginTime + "]";
	}
	
	
	
	
	
}
