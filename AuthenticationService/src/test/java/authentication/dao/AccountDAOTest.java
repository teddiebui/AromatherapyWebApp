package authentication.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import authentication.mock.MockAccountDAOImpl;
import authentication.mock.MockAuthenticationDataSource;
import authentication.model.Account;

public class AccountDAOTest {
	private static DataSource dataSource;
	private static MockAccountDAOImpl accountDao;
	private static Connection connection;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataSource = MockAuthenticationDataSource.getDataSource();
		accountDao = new MockAccountDAOImpl(dataSource);
	}
	
	@Before
	public void setUp() {
		try {
			connection = dataSource.getConnection();
			accountDao.setConnection(connection);
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
	

	@Test
	public void testGetLoginAccount() throws SQLException {
		Account account = accountDao.getLoginAccount("jdoe");
		System.out.println(account);
		assertTrue (account != null);
	}
	
	@Test
	public void testUpdateLock() throws SQLException {
		Account account = accountDao.getLoginAccount("jdoe");
		account.setLocked(true);
		accountDao.updateLock(account);
		
		Account retrievedAccount = accountDao.getLoginAccount("jdoe");
		assertTrue (retrievedAccount.isLocked());
	}

}
