package authentication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import authentication.model.LoginHistory;

public class LoginHistoryDAOImpl implements LoginHistoryDAO{
	private DataSource dataSource;

	public LoginHistoryDAOImpl(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	@Override
	public void create(LoginHistory loginHistory) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_CREATE);
			preparedStatement.setString(
					1, loginHistory.getUsername());
			preparedStatement.setBoolean(
					2, loginHistory.isLoginStatus());
			preparedStatement.setString(
					3, loginHistory.getLoginDevice());
			preparedStatement.setString(
					4, loginHistory.getLoginIP());
			preparedStatement.setInt(
					5, loginHistory.getLoginAttempt());
			
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException exception) {
			if (connection != null) {
				connection.rollback();
			}
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		
	}

	@Override
	public List<LoginHistory> getAll() throws SQLException {
		List<LoginHistory> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_ALL);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				LoginHistory loginHistory = constructModel(resultSet);
				list.add(loginHistory);
			}
		} catch (SQLException exception) {
			if (connection != null) {
				connection.rollback();
			}
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		
		return list;
		
	}

	@Override
	public List<LoginHistory> getAllByUsername(String username) throws SQLException {
		List<LoginHistory> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_ALL_BY_USERNAME);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				LoginHistory loginHistory = constructModel(resultSet);
				list.add(loginHistory);
			}
		} catch (SQLException exception) {
			if (connection != null) {
				connection.rollback();
			}
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		
		return list;
		
	}

	protected LoginHistory constructModel(ResultSet resultSet)
			throws SQLException {
		LoginHistory loginHistory = new LoginHistory();
		loginHistory.setId(
				resultSet.getInt(1));
		loginHistory.setUsername(
				resultSet.getString(2));
		loginHistory.setLoginStatus(
				resultSet.getBoolean(3));
		loginHistory.setLoginDevice(
				resultSet.getString(4));
		loginHistory.setLoginIP(
				resultSet.getString(5));
		loginHistory.setLoginAttempt(
				resultSet.getInt(6));
		loginHistory.setLoginTime(
				resultSet.getTimestamp(7));
		return loginHistory;
	}
}
