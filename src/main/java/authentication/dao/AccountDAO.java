package authentication.dao;

import java.sql.SQLException;

import authentication.model.Account;

public interface AccountDAO {

	public static final String SQL_GET_ACCOUNT_JOIN_LOGIN_SESSION = "" +
			"SELECT TOP 1 "
			+ "    e.[employee_username], "
			+ "    e.[employee_hashed_password], "
			+ "    e.[employee_is_locked], "
			+ "    ls.[login_attempt]"
			+ "FROM "
			+ "    [Employee] e "
			+ "LEFT JOIN "
			+ "    [LoginHistory] ls ON e.[employee_username] = ls.[username] "
			+ "WHERE "
			+ "	   e.[employee_username] = ? "
			+ "ORDER BY "
			+ "	   ls.[login_create_time] DESC ";

	public static final String SQL_UPDATE_LOCK_ACCOUNT = "UPDATE [Employee] "
			+ "SET [employee_is_locked]=? WHERE [employee_username] = ?";

	
	Account getByUsername(String username) throws SQLException;

	void updateLock(Account account) throws SQLException;
}
