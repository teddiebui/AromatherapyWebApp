package authentication.service;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import authentication.mock.MockAccountDAOImpl;
import authentication.mock.MockAuthenticationDataSource;
import authentication.mock.MockLoginHistoryDaoImpl;
import authentication.model.Account;
import authentication.model.LoginHistory;

public class AuthenticationServiceTest {
	private static DataSource dataSource;
	private static MockAccountDAOImpl accountDao;
	private static MockLoginHistoryDaoImpl loginHistoryDao;
	private static AuthenticationService service;
	private static Connection connection;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		dataSource = MockAuthenticationDataSource.getDataSource();
		accountDao = new MockAccountDAOImpl(dataSource);
		loginHistoryDao = new MockLoginHistoryDaoImpl(dataSource);
		service = new AuthenticationService();
		service.setAccountDao(accountDao);
		service.setLoginHistoryDao(loginHistoryDao);
	}

	@Before
	public void setUp() {
		try {
			connection = dataSource.getConnection();
			accountDao.setConnection(connection);
			loginHistoryDao.setConnection(connection);
		} catch (SQLException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}

	}

	@After
	public void tearDown() {
		connection = accountDao.getConnection();
		try {
			if (connection != null) {
				connection.rollback();
				connection.close();
			}
		} catch (SQLException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}

	}

	@AfterClass
	public static void destroy() {
		MockAuthenticationDataSource.closeDataSource();
	}

	@Test
	public void testValidate_invalidUsername_returnfail() {
		Account account = new Account();
		account.setUsername("1aaaaa");

		Map<String, String> resultSet = service.validate(account);
		String message = resultSet.get("username");

		assertTrue(message == AuthenticationService.USERNAME_VALIDATE_MESSAGE);
	}

	@Test
	public void testValidate_validUsername_returnSucess() {
		Account account = new Account();
		account.setUsername("aaaaa1");

		Map<String, String> resultSet = service.validate(account);

		assertTrue(!resultSet.containsKey("username"));
	}

	@Test
	public void testValidate_invalidPassword_validationFail() {
		Account account = new Account();
		account.setUsername("1aaaaa");
		account.setUsername("abcd1234");

		Map<String, String> resultSet = service.validate(account);
		String message = resultSet.get("password");

		assertTrue(message == AuthenticationService.PASSWORD_VALILDATE_MESSAGE);
	}

	@Test
	public void testValidate_validPassword_returnSucess() {
		Account account = new Account();
		account.setUsername("aaaaa1");
		account.setPassword("Zzzz@1111");

		Map<String, String> resultSet = service.validate(account);

		assertTrue(!resultSet.containsKey("password"));
	}

	@Test
	public void testAuthenticate_inputNonExistAccount_returnFail() {
		Account account = new Account();
		account.setUsername("aaaaa1");
		account.setPassword("Zzzz@1111");

		Map<String, Object> resultSet = service.authenticate(account);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");

		assertTrue(result == false
				&& message.equals(AuthenticationService.FAILED_NOT_EXIST));
	}

	@Test
	public void testAuthenticate_inputLockedAccount_returnFail() {
		Account account = new Account();
		account.setUsername("ewhite");
		account.setPassword("Zzzz@1111");

		Map<String, Object> resultSet = service.authenticate(account);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");

		assertTrue(result == false
				&& message.equals(AuthenticationService.FAILED_IS_LOCKED));
	}

	@Test
	public void testAuthenticate_inputLoginAttemptOver3_returnFail()
			throws SQLException {
		Account account = new Account();
		account.setUsername("ewhite");
		account.setPassword("Abcd@1234");

		Map<String, Object> resultSet = service.authenticate(account);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");

		Account retrievedAccount = accountDao
				.getLoginAccount(account.getUsername());

		assertTrue(result == false
				&& message.equals(AuthenticationService.FAILED_IS_LOCKED)
				&& retrievedAccount.isLocked() == true);
	}

	@Test
	public void testAuthenticate_inputLoginAttemptUpTo3_returnFail()
			throws SQLException {
		Account account = new Account();
		account.setUsername("jdoe");
		account.setPassword("Zzzz@1111");

		Map<String, Object> resultSet = service.authenticate(account);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");

		List<LoginHistory> list = loginHistoryDao
				.getAllByUsername(account.getUsername());

		boolean loginStatus = list.getLast().isLoginStatus();

		assertTrue(result == false
				&& message.equals(AuthenticationService.FAILED_LOGIN_ATTEMPT)
				&& loginStatus == false);
	}

	@Test
	public void testAuthenticate_validInput_returnSuccess()
			throws SQLException {
		Account account = new Account();
		boolean loginStatus;
		int loginAttempt;
		account.setUsername("jdoe");
		account.setPassword("Abcd@1234");

		Map<String, Object> resultSet = service.authenticate(account);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		String refreshToken = (String) resultSet.get("refreshToken");
		String accessToken = (String) resultSet.get("accessToken");

		List<LoginHistory> list = loginHistoryDao
				.getAllByUsername(account.getUsername());

		loginStatus = list.getLast().isLoginStatus();
		loginAttempt = list.getLast().getLoginAttempt();
		assertTrue(
				result == true && message.equals(AuthenticationService.SUCCESS)
						&& loginStatus == true && loginAttempt == 0
						&& !refreshToken.isEmpty() && refreshToken != null
						&& !accessToken.isEmpty() && accessToken != null);
	}

	@Test
	public void testAuthentica_validInput_invalidateOldRefreshToken()
			throws SQLException {
		Account account = new Account();
		LoginHistory previousLoginSuccess;
		account.setUsername("jdoe");
		account.setPassword("Abcd@1234");
		previousLoginSuccess = loginHistoryDao
				.getLastLoginSuccess(account.getUsername());
		String token = previousLoginSuccess.getRefreshKey();

		System.out.println("Unit test debug: ");
		System.out.println(previousLoginSuccess);

		assertTrue(previousLoginSuccess.isRefreshKeyActive());

		Map<String, Object> resultSet = service.authenticate(account);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		String refreshToken = (String) resultSet.get("refreshToken");
		String accessToken = (String) resultSet.get("accessToken");

		assertTrue(
				result == true && message.equals(AuthenticationService.SUCCESS)
						&& !refreshToken.isEmpty() && refreshToken != null
						&& !accessToken.isEmpty() && accessToken != null);

		previousLoginSuccess = loginHistoryDao.findByToken(token);
		System.out.println("Unit test debug again: ");
		System.out.println(previousLoginSuccess);
		assertTrue(previousLoginSuccess.isRefreshKeyActive() == false);
	}

	@Test
	public void testGenerateAccessToken_nullRefreshToken_fail() {
		String refreshToken = null;
		Map<String, Object> resultSet = service
				.generateAccessToken(refreshToken);

		String message = (String) resultSet.get("message");
		assertTrue(message.equalsIgnoreCase(
				AuthenticationService.REFRESH_TOKEN_CANNOT_BE_NULL));
	}

	@Test
	public void testGenerateAccessToken_emptyRefreshToken_fail() {
		String refreshToken = "";
		Map<String, Object> resultSet = service
				.generateAccessToken(refreshToken);

		String message = (String) resultSet.get("message");
		assertTrue(message.equalsIgnoreCase(
				AuthenticationService.REFRESH_TOKEN_CANNOT_BE_NULL));

	}

	@Test
	public void testGenerateAccessToken_nonexistsRefreshToken_fail() {
		String refreshToken = "abcdefghiklmnop";
		Map<String, Object> resultSet = service
				.generateAccessToken(refreshToken);

		String message = (String) resultSet.get("message");
		assertTrue(message.equalsIgnoreCase(
				AuthenticationService.REFRESH_TOKEN_NOT_FOUND_OR_REVOKED));
	}

}
