package authentication.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private String username;

	private String password;

	private boolean locked;
	
	private Role role;
	
	private List<LoginHistory> loginHistories; 

	private Timestamp createTime;

	public Account() {
		loginHistories = new ArrayList<LoginHistory>();
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
	
	public List<LoginHistory> getLoginHistories() {
		return loginHistories;
	}

	public void setLoginHistory(LoginHistory loginHistory) {
		this.loginHistories.add(loginHistory);
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password
				+ ", locked=" + locked + ", role=" + role + ", loginHistories="
				+ loginHistories + ", createTime=" + createTime + "]";
	}
	
	





}
