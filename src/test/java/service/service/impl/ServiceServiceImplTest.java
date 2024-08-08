package service.service.impl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mock.MockDataSource;
import mock.MockServiceDaoImpl;
import service.model.Service;
import service.service.ServiceService;

public class ServiceServiceImplTest {

	private static DataSource dataSource;
	private static MockServiceDaoImpl serviceDao;
	private static ServiceServiceImpl serviceService;
	private static Connection connection;
	
	/**
	 * Sets up the test environment before any tests are run.
	 * 
	 * @throws Exception if an error occurs while setting up the test environment
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataSource = MockDataSource.getDataSource();
		serviceDao = new MockServiceDaoImpl(dataSource);
		serviceService = new ServiceServiceImpl(serviceDao);
	}
	
	/**
	 * Initializes the database connection before each test.
	 */
	@Before
	public void setUp() {
		try {
			connection = dataSource.getConnection();
			serviceDao.setConnection(connection);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Rolls back any changes and closes the database connection after each test.
	 */
	@After
	public void tearDown() {
		connection = serviceDao.getConnection();
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
	 * Tests the success scenario of getting all services.
	 * Expected Result: success returning a list of services.
	 */
	@Test
	public void testGetAll_success() {
		Map<String, Object> resultSet = serviceService.getAll();
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		assertTrue(result == expected && message.equals(ServiceService.GET_ALL_SUCCESS));
	}
	
	/**
	 * Tests the success scenario of getting a service by a valid id.
	 * Parameter: Existing service id.
	 * Expected Result: failed & return message id doesn't exist.
	 * 
	 */
	@Test
	public void testGetById_validId_success() {
		int serviceId = 1;
		Map<String, Object> resultSet = serviceService.findById(serviceId);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		assertTrue(result == expected && message.equals(ServiceService.GET_ID_SUCCESS));
	}
	
	/**
	 * Tests the failure scenario of getting a service by an invalid id.
	 * Parameter: Service id that doesn't exist.
	 * Expected Result: failed & return message id doesn't exist.
	 */
	@Test
	public void testGetById_invalidId_fail() {
		int serviceId = 0;
		Map<String, Object> resultSet = serviceService.findById(serviceId);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		assertTrue(result == expected && message.equals(ServiceService.GET_ID_FAIL_NOT_EXIST));
	}
	
	/**
	 * Tests the success scenario of creating a service with valid input.
	 * Parameter: Employee id that doesn't exist.
	 * Expected Result: fails & return message create failed.
	 */
	@Test
	public void testCreate_invalidInput_fail() {
		Service service = new Service();
		service.setServiceId(1);
		service.setEmployeeId(5555); // Employee id doesn't exist.
		service.setServiceTitle("Test");
		service.setServiceInfo("Test");
		service.setServicePrice(1.0f);
		service.setServiceImgSrc("/test/test");
		service.setServiceCreateTime(null);
		
		Map<String, Object> resultSet = serviceService.create(service);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		
		System.out.println(resultSet);
		assertTrue(result == expected && message.equals(ServiceService.ERROR_UNEXPECTED));
	}
	
	
	
	/**
	 * Tests the success scenario of creating a service with valid input.
	 * Parameter: valid parameters for service object.
	 * Expected Result: success & return message create success.
	 * 
	 */
	@Test
	public void testCreate_validInput_success() {
		Service service = new Service();
		service.setServiceId(1);
		service.setEmployeeId(1);
		service.setServiceTitle("Test");
		service.setServiceInfo("Test");
		service.setServicePrice(1.0f);
		service.setServiceImgSrc("/test/test");
		service.setServiceCreateTime(null);
		
		Map<String, Object> resultSet = serviceService.create(service);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		
		assertTrue(result == expected && message.equals(ServiceService.CREATE_SUCCESS));
	}
	
	/**
	 * Tests the success scenario of updating a service with valid input.
	 * Parameter: Existing service id.
	 * Expected Result: success & return message update success.
	 */
	@Test
	public void testUpdate_validInput_success() {
		Service service = new Service();
		service.setServiceId(1);
		service.setEmployeeId(1);
		service.setServiceTitle("Test");
		service.setServiceInfo("Test");
		service.setServicePrice(1.0f);
		service.setServiceImgSrc("/test/test");
		service.setServiceCreateTime(null);

		Map<String, Object> resultSet = serviceService.update(service);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		
	
		assertTrue(result == expected && message.equals(ServiceService.UPDATE_SUCCESS));
	}
	
	/**
	 * Tests the failure scenario of updating a service with invalid input.
	 * Parameter: service id that doesn't exists
	 * Expected Result: fails & return message id not found.
	 */
	@Test
	public void testUpdate_invalidInput_fail() {
		Service service = new Service();
		service.setServiceId(11111); // Service id doesn't exist.
		service.setEmployeeId(1);
		service.setServiceTitle("Test");
		service.setServiceInfo("Test");
		service.setServicePrice(1.0f);
		service.setServiceImgSrc("/test/test");
		service.setServiceCreateTime(null);
		
		Map<String, Object> resultSet = serviceService.update(service);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		assertTrue(result == expected && message.equals(ServiceService.UPDATE_FAIL_ID_NOT_EXIST));
	}
	
	/**
	 * Tests the failure scenario of deleting a service with an invalid id.
	 * Parameter: service id that doesn't exists
	 * Expected Result: fails & return message id not found.
	 */
	@Test
	public void testDelete_invalidInput_fail() {
		int serviceId = 1111; // post id doesn't exist
		Map<String, Object> resultSet = serviceService.delete(serviceId); 
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		
		assertTrue(result == expected && message.equals(ServiceService.DELETE_FAIL_ID_NOT_EXIST));
	}
	
	/**
	 * Tests the success scenario of deleting a service with a valid id.
	 * Parameter: Existing service id
	 * Expected Result: success & return message delete success.
	 */
	@Test
	public void testDelete_validInput_success() {
		int serviceId = 4;
		Map<String, Object> resultSet = serviceService.delete(serviceId);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;

		assertTrue(result == expected && message.equals(ServiceService.DELETE_SUCCESS));
	}

}
