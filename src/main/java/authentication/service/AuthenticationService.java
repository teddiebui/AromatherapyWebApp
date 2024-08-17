package authentication.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import authentication.dao.LoginHistoryDAOImpl;
import authentication.dao.impl.AccountDAOImpl;
import authentication.model.Account;
import authentication.model.LoginHistory;
import authentication.util.BCryptPasswordEncoder;
import authentication.util.JWTUtil;

public class AuthenticationService {

	public static final String PASSWORD_VALILDATE_MESSAGE = "Password cannot be empty. Must be 6 - 16 characters, "
			+ "including upper & lowercase characters & digits, "
			+ "with at least 1 special characters: !@#$%^&*()";

	public static final String USERNAME_VALIDATE_MESSAGE = "Username cannot be empty. Must be 4 - 16 characters and digits, "
			+ "and should starts with character.";

	private AccountDAOImpl accountDao;

	private LoginHistoryDAOImpl loginHistoryDao;

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
	
	public AuthenticationService() {}
	
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

	public Map<String, Object> generateResultSet() {
		return new HashMap<>();
	}

	public Map<String, String> validate(Account account) {
		Map<String, String> validationErrors = new HashMap<>();
		// TODO: implement validation rule
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

	public void createLoginHistory(Account account, boolean result)
			throws SQLException {
		LoginHistory loginHistory = new LoginHistory();
		loginHistory.setUsername(account.getUsername());
		loginHistory.setLoginStatus(result);
		loginHistory.setLoginDevice("not yet implemented");
		loginHistory.setLoginIP("not yet implemented");
		loginHistory.setLoginAttempt(account.getLoginAttempt());
		loginHistoryDao.create(loginHistory);
	}

	public Map<String, Object> authenticate(Account account) {
		Map<String, Object> resultSet = generateResultSet();
		Map<String, String> validationErrors;
		Account retrievedAccount = null;
		boolean result = false;
		String message;

		// TODO: implement the validation rule.
		// check validation, return if failed validation
		validationErrors = validate(account);
		if (validationErrors != null && !validationErrors.isEmpty()) {
			message = FAILED_VALIDATION;
			resultSet.put("validationErrors", validationErrors);
		} else {
			try {
				// get account
				retrievedAccount = accountDao
						.getByUsername(account.getUsername());
				if (retrievedAccount == null) {
					message = FAILED_NOT_EXIST;
				} else if (retrievedAccount.isLocked()) {
					message = FAILED_IS_LOCKED;
				} else if (retrievedAccount.getLoginAttempt() > 3) {
					retrievedAccount.setLocked(true);
					accountDao.updateLock(retrievedAccount);
					message = FAILED_IS_LOCKED;
				} else {
					// compare password
					result = BCryptPasswordEncoder.getInstance().checkpw(
							account.getPassword(),
							retrievedAccount.getPassword());
					message = result ? SUCCESS : FAILED_LOGIN_ATTEMPT;
					// set login attempt
					if (!result) {
						retrievedAccount.setLoginAttempt(retrievedAccount.getLoginAttempt() + 1);
					} else {
						//login success, generate jwt token
						String refreshToken = JWTUtil.getInstance().generateRefreshToken(account.getUsername());
						resultSet.put("refreshToken", refreshToken);
						retrievedAccount.setLoginAttempt(0);
					}
					// create Login session
					createLoginHistory(retrievedAccount, result);
				}
			} catch (SQLException exception) {
				result = false;
				message = UNEXPECTED_ERROR;
				exception.printStackTrace();
			}
		}

		resultSet.put("result", result);
		resultSet.put("message", message);
		resultSet.put("retrievedAccount", retrievedAccount);
		return resultSet;

	}

}
