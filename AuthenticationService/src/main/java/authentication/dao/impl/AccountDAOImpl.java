package authentication.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import authentication.dao.AccountDAO;
import authentication.model.Account;
import authentication.model.LoginHistory;

public class AccountDAOImpl implements AccountDAO {
	
	private DataSource dataSource;

	public AccountDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}



	public Account getLoginAccount(String username) throws SQLException {
		Account account = null;
		LoginHistory loginHistory = null;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_GET_ACCOUNT_JOIN_LOGIN_SESSION);
			preparedStatement.setString(1, username.toLowerCase());
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
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		
		return account;
	}

	public void updateLock(Account account) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_UPDATE_LOCK_ACCOUNT);
			preparedStatement.setBoolean(1, account.isLocked());
			preparedStatement.setString(2, account.getUsername());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException exception) {
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

}
