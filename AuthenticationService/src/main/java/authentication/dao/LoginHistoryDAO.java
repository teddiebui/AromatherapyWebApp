package authentication.dao;



import java.sql.SQLException;
import java.util.List;

import authentication.model.LoginHistory;

public interface LoginHistoryDAO {
	public static final String SQL_CREATE = 
			"INSERT INTO [LoginHistory] ("
			+ "[username], "
			+ "[login_status], "
			+ "[login_device], "
			+ "[login_ip_address], "
			+ "[login_attempt], "
			+ "[refresh_key]) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	
	public static final String SQL_UPDATE = 
			"UPDATE [LoginHistory] "
			+ "SET "
			+ "[username] = ?, "
			+ "[login_status] = ?, "
			+ "[login_device] = ?, "
			+ "[login_ip_address] = ?, "
			+ "[login_attempt] = ?, "
			+ "[refresh_key] = ?, "
			+ "[is_refresh_key_active] = ? "
			+ "WHERE "
			+ "[login_history_id] = ? ";
	
	public static final String SQL_INVALIDATE_REFRESH_TOKEN =
			"UPDATE [LoginHistory] "
			+ "SET "
			+ "[is_refresh_key_active] = ? "
			+ "WHERE "
			+ "[login_history_id] = ? ";
	
	
	public static final String SQL_GET_ALL = 
			"SELECT * FROM [LoginHistory] ";
	
	public static final String SQL_GET_LATEST_LOGIN_SUCCESS =
			"SELECT TOP 1 * "
			+ "FROM [LoginHistory] "
			+ "WHERE [login_status] = 1 AND [username] = ? "
			+ "ORDER BY [login_create_time] DESC";
	
	public static final String SQL_GET_ALL_BY_USERNAME = 
			"SELECT * FROM [LoginHistory] WHERE [username] = ?";
	

	public static final String SQL_FIND_BY_TOKEN = 
			"SELECT * "
			+ "FROM [LoginHistory] "
			+ "WHERE [refresh_key] = ? ";
	
	void create(LoginHistory loginHistory) throws SQLException;
	
	void update(LoginHistory loginHistory) throws SQLException;
	
	void invalidateRefreshToken(LoginHistory loginHistory) throws SQLException;
	
	LoginHistory getLastLoginSuccess(String username) throws SQLException;
	
	LoginHistory findByToken(String Token) throws SQLException;

	
	List<LoginHistory> getAll() throws SQLException;
	
	List<LoginHistory> getAllByUsername(String username) throws SQLException;
	
}
