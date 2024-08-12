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
			+ "[login_attempt]) "
			+ "VALUES (?, ?, ?, ?, ?)";
	
	public static final String SQL_GET_ALL = 
			"SELECT * FROM [LoginHistory] ";
	
	public static final String SQL_GET_ALL_BY_USERNAME = 
			"SELECT * FROM [LoginHistory] WHERE [username] = ?";
	
	void create(LoginHistory loginSession) throws SQLException;
	
	List<LoginHistory> getAll() throws SQLException;
	
	List<LoginHistory> getAllByUsername(String username) throws SQLException;
	
}
