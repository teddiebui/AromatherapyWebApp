package authentication.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import authentication.dao.AccountDAO;
import authentication.model.Account;

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



	public Account getByUsername(String username) throws SQLException {
		Account account = null;
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
				account.setUsername(
						resultSet.getString("employee_username"));
				account.setPassword(
						resultSet.getString("employee_hashed_password"));
				account.setLocked(
						resultSet.getBoolean("employee_is_locked"));
				account.setLoginAttempt(
						resultSet.getInt("login_attempt"));
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
