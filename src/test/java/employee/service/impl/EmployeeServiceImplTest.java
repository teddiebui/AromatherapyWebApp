package employee.service.impl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import employee.model.Employee;
import employee.service.EmployeeService;
import mock.MockDataSource;
import mock.MockEmployeeDaoImpl;

public class EmployeeServiceImplTest {
	
	private static DataSource dataSource;
	private static MockEmployeeDaoImpl employeeDao;
	private static EmployeeServiceImpl employeeService;
	private static Connection connection;
	
	/**
	 * Sets up the test environment before any tests are run.
	 * 
	 * @throws Exception if an error occurs while setting up the test environment
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataSource = MockDataSource.getDataSource();
		employeeDao = new MockEmployeeDaoImpl(dataSource);
		employeeService = new EmployeeServiceImpl(employeeDao);
	}
	
	/**
	 * Initializes the database connection before each test.
	 */
	@Before
	public void setUp() {
		try {
			connection = dataSource.getConnection();
			employeeDao.setConnection(connection);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Rolls back any changes and closes the database connection after each test.
	 */
	@After
	public void tearDown() {
		connection = employeeDao.getConnection();
		try {
			if (connection != null) {
				connection.rollback();
				connection.close();
			}
		} catch(SQLException exception) {
			exception.printStackTrace();
		}
	}
	

	/**
	 * Tests the success scenario of getting all employees.
	 * Expected Result: success returning a list of employees.
	 */
	@Test
	public void testGetAll_success() {
		Map<String, Object> resultSet = employeeService.getAll();
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		assertTrue(result == expected && message.equals(EmployeeService.GET_ALL_SUCCESS));
	}
	
	/**
	 * Tests the success scenario of getting a employee by a valid id.
	 * Parameter: Existing employee id.
	 * Expected Result: failed & return message id doesn't exist.
	 */
	@Test
	public void testGetById_validId_success() {
		int employeeId = 1;
		Map<String, Object> resultSet = employeeService.findById(employeeId);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		assertTrue(result == expected && message.equals(EmployeeService.GET_BY_ID_SUCCESS));
	}
	
	/**
	 * Tests the failure scenario of getting a employee by an invalid id.
	 * Parameter: Employee id that doesn't exist.
	 * Expected Result: failed & return message id doesn't exist.
	 */
	@Test
	public void testGetById_invalidId_fail() {
		int employeeId = 0;
		Map<String, Object> resultSet = employeeService.findById(employeeId);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		assertTrue(result == expected && message.equals(EmployeeService.GET_FAILED_ID_NOT_FOUND));
	}
	
	
	/**
	 * Tests the success scenario of creating a employee with valid input.
	 * Parameter: valid parameters for employee object.
	 * Expected Result: success & return message create success.
	 */
	@Test
	public void testCreate_validInput_success() {
		Employee employee = new Employee();
		employee.setEmployeeName("test");
		employee.setEmployeeTitle("test");
		employee.setEmployeeInfo("test");
		employee.setEmployeeImgSrc("test");

		
		Map<String, Object> resultSet = employeeService.create(employee);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		
		assertTrue(result == expected && message.equals(EmployeeService.CREATE_SUCCESS));
	}
	
	/**
	 * Tests the success scenario of updating a employee with valid input.
	 * Parameter: Existing employee id.
	 * Expected Result: success & return message update success.
	 */
	@Test
	public void testUpdate_validInput_success() {
		Employee employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("test 2");
		employee.setEmployeeTitle("test 2");
		employee.setEmployeeInfo("test 2");
		employee.setEmployeeImgSrc("test 2");

		Map<String, Object> resultSet = employeeService.update(employee);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		
		assertTrue(result == expected && message.equals(EmployeeService.UPDATE_SUCCESS));
	}
	
	/**
	 * Tests the failure scenario of updating a employee with invalid input.
	 * Parameter: employee id that doesn't exists
	 * Expected Result: fails & return message id not found.
	 */
	@Test
	public void testUpdate_invalidInput_fail() {
		Employee employee = new Employee();
		employee.setEmployeeId(1111); //employee id doesn't exists
		employee.setEmployeeName("test 2");
		employee.setEmployeeTitle("test 2");
		employee.setEmployeeInfo("test 2");
		employee.setEmployeeImgSrc("test 2");
		
		Map<String, Object> resultSet = employeeService.update(employee);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		assertTrue(result == expected && message.equals(EmployeeService.UPDATE_FAIL_ID_NOT_EXIST));
	}
	
	/**
	 * Tests the failure scenario of deleting a employee with an invalid id.
	 * Parameter: employee id that doesn't exists
	 * Expected Result: fails & return message id not found.
	 */
	@Test
	public void testDelete_invalidInput_fail() {
		int employeeId = 1111; // course id doesn't exist
		Map<String, Object> resultSet = employeeService.delete(employeeId); 
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		
		assertTrue(result == expected && message.equals(EmployeeService.DELETE_FAIL_ID_NOT_EXIST));
	}
	
	/**
	 * Tests the success scenario of deleting a employee with a valid id.
	 * Parameter: Existing employee id
	 * Expected Result: success & return message delete success.
	 */
	@Test
	public void testDelete_validInput_success() {
		int employeeId = 4;
		Map<String, Object> resultSet = employeeService.delete(employeeId);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;

		assertTrue(result == expected && message.equals(EmployeeService.DELETE_SUCCESS));
	}

}
