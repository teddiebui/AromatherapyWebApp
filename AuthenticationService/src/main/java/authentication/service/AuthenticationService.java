package authentication.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.auth0.jwt.interfaces.DecodedJWT;

import authentication.dao.LoginHistoryDAOImpl;
import authentication.dao.impl.AccountDAOImpl;
import authentication.dao.impl.PermissionDaoImpl;
import authentication.model.Account;
import authentication.model.LoginHistory;
import authentication.model.Permission;
import authentication.util.BCryptPasswordEncoder;
import authentication.util.JWTUtil;

public class AuthenticationService {

	public static final String REFRESH_TOKEN_CANNOT_BE_NULL = "Token cannot be null or empty.";

	public static final String SUCCESS_GENERATE_ACCESS_TOKEN = "OK.";

	public static final String REFRESH_TOKEN_IS_EXPIRED = "Refresh token is expired, please authenticate again.";

	public static final String REFRESH_TOKEN_NOT_FOUND_OR_REVOKED = "Refresh token not found or revoked, please authenticate again.";

	public static final String ALREADY_LOGGED_OUT = "Already logged out.";

	public static final String LOG_OUT_SUCCESS = "Log out success";

	public static final String PASSWORD_VALILDATE_MESSAGE = "Password cannot be empty. Must be 6 - 16 characters, "
			+ "including upper & lowercase characters & digits, "
			+ "with at least 1 special characters: !@#$%^&*()";

	public static final String USERNAME_VALIDATE_MESSAGE = "Username cannot be empty. Must be 4 - 16 characters and digits, "
			+ "and should starts with character.";

	public static final String FAILED_VALIDATION = "Authentication failed due to form validation errors.";

	public static final String FAILED_NOT_EXIST = "Authentication failed due to username doesn't exist.";

	public static final String FAILED_IS_LOCKED = "Authentication failed due to account is locked.";

	public static final String FAILED_LOGIN_ATTEMPT = "There is something wrong, please check again your account or password.";

	public static final String SUCCESS = "Authenticated success. ";

	public static final String UNEXPECTED_ERROR = "Unexpected error, please check with admin.";

	public static final Pattern PASSWORD_PATTERN = Pattern.compile(
			"^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()])[A-Za-z\\d!@#$%^&*()]{6,16}$");

	public static final Pattern USERNAME_PATTERN = Pattern
			.compile("^[A-Za-z][A-Za-z0-9]{3,15}");
	
	private AccountDAOImpl accountDao;

	private LoginHistoryDAOImpl loginHistoryDao;
	
	private PermissionDaoImpl permissionDao;

	public AuthenticationService() {
	}

	public AuthenticationService(AccountDAOImpl accountDao,
			LoginHistoryDAOImpl loginHistoryDao) {
		this.accountDao = accountDao;
		this.loginHistoryDao = loginHistoryDao;
	}

	public AccountDAOImpl getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDAOImpl accountDao) {
		this.accountDao = accountDao;
	}

	public LoginHistoryDAOImpl getLoginHistoryDao() {
		return loginHistoryDao;
	}

	public void setLoginHistoryDao(LoginHistoryDAOImpl loginHistoryDao) {
		this.loginHistoryDao = loginHistoryDao;
	}
	
	
	public PermissionDaoImpl getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(PermissionDaoImpl permissionDao) {
		this.permissionDao = permissionDao;
	}

	public Map<String, Object> generateResultSet() {
		return new HashMap<>();
	}

	public Map<String, String> validate(Account account) {
		Map<String, String> validationErrors = new HashMap<>();
		String username = account.getUsername();
		String password = account.getPassword();
		if (username == null || !USERNAME_PATTERN.matcher(username).matches()) {
			validationErrors.put("username", USERNAME_VALIDATE_MESSAGE);
		}

		if (password == null || !PASSWORD_PATTERN.matcher(password).matches()) {
			validationErrors.put("password", PASSWORD_VALILDATE_MESSAGE);
		}
		return validationErrors;
	}

	public void createLoginSession(LoginHistory loginHistory)
			throws SQLException {
		loginHistoryDao.create(loginHistory);
	}

	public Map<String, Object> authenticate(Account account) {
		Map<String, Object> resultSet = generateResultSet();
		Map<String, String> validationErrors;
		Account retrievedAccount = null;
		boolean result = false;
		int code;
		String message;

		validationErrors = validate(account);
		if (validationErrors != null && !validationErrors.isEmpty()) {
			message = FAILED_VALIDATION;
			code = 400;
			resultSet.put("validationErrors", validationErrors);
		} else {
			try {
				// get account
				retrievedAccount = accountDao
						.getLoginAccount(account.getUsername());
				LoginHistory loginHistory = retrievedAccount == null ? null
						: retrievedAccount.getLoginHistories().getLast();

				if (retrievedAccount == null) {
					code = 401;
					message = FAILED_NOT_EXIST;
				} else if (retrievedAccount.isLocked()) {
					code = 403;
					message = FAILED_IS_LOCKED;
				} else if (loginHistory.getLoginAttempt() > 3) {
					retrievedAccount.setLocked(true);
					accountDao.updateLock(retrievedAccount);
					code = 403;
					message = FAILED_IS_LOCKED;
				} else {
					// compare password
					result = checkPassword(account.getPassword(),
							retrievedAccount.getPassword());
					int loginAttempt = 0;
					String refreshToken = "";
					String accessToken = "";
					// set login attempt
					if (!result) {
						loginAttempt = loginHistory.getLoginAttempt() + 1;
						code = 401;
						message = FAILED_LOGIN_ATTEMPT;

					} else {
						// invalidate previous refresh key
						// try to get last history with refreshToken
						log_out(retrievedAccount);

						// generate new refresh key, access key
						List<Permission> permissions = permissionDao.getByRole(retrievedAccount.getRole().getRoleName());
						String[] permissionList = getPermissionList(permissions);
						boolean isAdmin = retrievedAccount.getRole().getRoleName().equalsIgnoreCase("admin");
						refreshToken = JWTUtil.getInstance()
								.generateRefreshToken(account.getUsername(), permissionList, isAdmin);
						accessToken = JWTUtil.getInstance()
								.generateAccessToken(account.getUsername(), permissionList, isAdmin);
						code = 200;
						message = SUCCESS;
						resultSet.put("refreshToken", refreshToken);
						resultSet.put("accessToken", accessToken);
					}
					// create Login Session
					LoginHistory newLoginHistory = new LoginHistory();
					newLoginHistory.setUsername(account.getUsername());
					newLoginHistory.setLoginStatus(result);
					newLoginHistory.setLoginDevice("not yet impl");
					newLoginHistory.setLoginIP("not yet impl");
					newLoginHistory.setLoginAttempt(loginAttempt);
					newLoginHistory.setRefreshKey(refreshToken);
					createLoginSession(newLoginHistory);
				}
			} catch (SQLException exception) {
				result = false;
				message = UNEXPECTED_ERROR;
				exception.printStackTrace();
				code = 500;
			}
		}

		resultSet.put("result", result);
		resultSet.put("message", message);
		resultSet.put("code", code);
		return resultSet;

	}

	private String[] getPermissionList(List<Permission> permissions) {
		String[] permissionList = new String[permissions.size()];
		for (int i=0; i < permissions.size(); i++) {
			permissionList[i] = permissions.get(i).getPermissionName();
		}
		return permissionList;
	}

	private void log_out(Account retrievedAccount) throws SQLException {
		LoginHistory lastLoginSuccess = loginHistoryDao
				.getLastLoginSuccess(retrievedAccount.getUsername());
		if (lastLoginSuccess != null && lastLoginSuccess.getRefreshKey() != null
				&& !lastLoginSuccess.getRefreshKey().isEmpty()
				&& lastLoginSuccess.isRefreshKeyActive()) {
			lastLoginSuccess.setRefreshKeyActive(false);
			loginHistoryDao.invalidateRefreshToken(lastLoginSuccess);
		}
	}

	public Map<String, Object> logout(String username) {
		Map<String, Object> resultSet = generateResultSet();
		boolean result = false;
		int code = 0;
		String message = "";

		LoginHistory lastRefreshToken;
		try {
			lastRefreshToken = loginHistoryDao.getLastLoginSuccess(username);
			if (lastRefreshToken.getRefreshKey() != null
					&& !lastRefreshToken.getRefreshKey().isEmpty()
					&& lastRefreshToken.isRefreshKeyActive()) {
				lastRefreshToken.setRefreshKeyActive(false);
				loginHistoryDao.invalidateRefreshToken(lastRefreshToken);
				code = 302;
				message = LOG_OUT_SUCCESS;
				result = true;
			} else {
				code = 302;
				message = ALREADY_LOGGED_OUT;
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
			code = 500;
			message = UNEXPECTED_ERROR;
		}

		resultSet.put("code", code);
		resultSet.put("result", result);
		resultSet.put("message", message);
		return resultSet;
	}

	public LoginHistory findLoginHistoryByToken(String token) {
		LoginHistory loginHistory = null;
		try {
			loginHistoryDao.findByToken(token);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return loginHistory;
	}

	private boolean checkPassword(String password, String password2) {
		return BCryptPasswordEncoder.getInstance().checkpw(password, password2);
	}

	public Map<String, Object> generateAccessToken(String refreshToken) {
		Map<String, Object> resultSet = generateResultSet();
		boolean result = false;
		int code;
		String message;
		LoginHistory loginHistory;
		String accessToken = "";

		if (refreshToken == null || refreshToken.isEmpty()) {
			code = 400;
			message = REFRESH_TOKEN_CANNOT_BE_NULL;
		} else {
			try {
				loginHistory = loginHistoryDao.findByToken(refreshToken);
				if (loginHistory != null && loginHistory.isRefreshKeyActive()) {
					DecodedJWT decodedToken = JWTUtil.getInstance()
							.verifyRefreshToken(loginHistory.getRefreshKey());
					if (!JWTUtil.getInstance().isTokenExpired(decodedToken)) {
						// generate token
						// return 200
						code = 200;
						result = true;
						message = SUCCESS_GENERATE_ACCESS_TOKEN;
						accessToken = JWTUtil.getInstance().generateAccessToken(
								loginHistory.getUsername(), 
								decodedToken.getClaim("permissions").asArray(String.class),
								decodedToken.getClaim("admin").asBoolean());

					} else {
						// return 403
						code = 403;
						message = REFRESH_TOKEN_IS_EXPIRED;
					}
				} else {
					// return 401
					code = 401;
					message = REFRESH_TOKEN_NOT_FOUND_OR_REVOKED;

				}
			} catch (SQLException exception) {
				exception.printStackTrace();
				code = 500;
				message = UNEXPECTED_ERROR;
			} catch (Exception exception2) {
				exception2.printStackTrace();
				code = 500;
				message = UNEXPECTED_ERROR;
			}
		}

		resultSet.put("code", code);
		resultSet.put("result", result);
		resultSet.put("message", message);
		resultSet.put("accessToken", accessToken);
		return resultSet;
	}

}
