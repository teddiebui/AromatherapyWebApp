package authentication.mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import authentication.dao.impl.AccountDAOImpl;
import authentication.model.Account;
import authentication.model.LoginHistory;

public class MockAccountDAOImpl extends AccountDAOImpl {
	
	public MockAccountDAOImpl(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	private Connection connection;
	
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void updateLock(Account account) throws SQLException {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection
					.prepareStatement(SQL_UPDATE_LOCK_ACCOUNT);
			preparedStatement.setBoolean(1, account.isLocked());
			preparedStatement.setString(2, account.getUsername());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw exception;
		}
	}
	
	public Account getLoginAccount(String username) throws SQLException {
		Account account = null;
		LoginHistory loginHistory = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			preparedStatement = connection
					.prepareStatement(SQL_GET_ACCOUNT_JOIN_LOGIN_SESSION);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				account = new Account();
				loginHistory = new LoginHistory();
				account.setUsername(
						resultSet.getString("username"));
				account.setPassword(
						resultSet.getString("hashed_password"));
				account.setLocked(
						resultSet.getBoolean("is_locked"));
				loginHistory.setId(
						resultSet.getInt("login_history_id"));
				loginHistory.setLoginAttempt(
						resultSet.getInt("login_attempt"));
				loginHistory.setRefreshKey(
						resultSet.getString("refresh_key"));
				loginHistory.setRefreshKeyActive(
						resultSet.getBoolean("is_refresh_key_active"));
				account.setLoginHistory(loginHistory);
			}
		} catch (SQLException exception) {
			throw exception;
		}
		
		return account;
	}
	
	
	
	

}
