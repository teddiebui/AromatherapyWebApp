package authentication.dao;

import java.sql.SQLException;

import authentication.model.Account;

public interface AccountDAO {

	public static final String SQL_GET_ACCOUNT_JOIN_LOGIN_SESSION = "" +
			"SELECT TOP 1 "
			+ "    a.[username], "
			+ "    a.[hashed_password], "
			+ "    a.[is_locked], "
			+ "    ls.[login_history_id], "
			+ "    ls.[login_attempt], "
			+ "    ls.[refresh_key], "
			+ "    ls.[is_refresh_key_active] "
			+ "FROM "
			+ "    [Account] a "
			+ "LEFT JOIN "
			+ "    [LoginHistory] ls ON a.[username] = ls.[username] "
			+ "WHERE "
			+ "	   a.[username] = ? "
			+ "ORDER BY "
			+ "	   ls.[login_create_time] DESC ";

	public static final String SQL_UPDATE_LOCK_ACCOUNT = "UPDATE [Account] "
			+ "SET [is_locked]=? WHERE [username] = ?";

	
	Account getLoginAccount(String username) throws SQLException;

	void updateLock(Account account) throws SQLException;
}
