package authentication.dao.impl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import authentication.mock.MockAuthenticationDataSource;
import authentication.mock.MockPermissionDaoImpl;
import authentication.model.Permission;

public class PermissionDaoImplTest {
	
	private static DataSource dataSource;
	private static MockPermissionDaoImpl permissionDao;
	private static Connection connection;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataSource = MockAuthenticationDataSource.getDataSource();
		permissionDao = new MockPermissionDaoImpl(dataSource);
	}
	
	@Before
	public void setUp() {
		try {
			connection = dataSource.getConnection();
			permissionDao.setConnection(connection);
		} catch (SQLException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
		
	}
	
	@After
	public void tearDown() {
		connection = permissionDao.getConnection();
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
	public void testGetByRole_returnList() throws SQLException {
		
		List<Permission> result = permissionDao.getByRole("manager");
		
		for (Permission permission : result) {
			System.out.println(permission);
		}
		
		
		assertTrue(!result.isEmpty());
	}

}
