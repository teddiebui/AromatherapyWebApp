package course.service.impl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import course.model.Course;
import course.service.CourseService;
import mock.MockCourseDaoImpl;
import mock.MockDataSource;

public class CourseServiceImplTest {
	private static DataSource dataSource;
	private static MockCourseDaoImpl courseDao;
	private static CourseServiceImpl courseService;
	private static Connection connection = null;
	
	/**
	 * Sets up the test environment before any tests are run.
	 * 
	 * @throws Exception if an error occurs while setting up the test environment
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataSource = MockDataSource.getDataSource();
		courseDao = new MockCourseDaoImpl(dataSource);
		courseService = new CourseServiceImpl(courseDao);
	}
	
	/**
	 * Initializes the database connection before each test.
	 */
	@Before 
	public void init() {
		try {
			connection = MockDataSource.getDataSource().getConnection();
			courseDao.setConnection(connection);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Rolls back any changes and closes the database connection after each test.
	 */
	@After
	public void tearDown() {
		if (connection != null) {
			try {
				connection.rollback();
				connection.close();
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
	}

	/**
	 * Tests the success scenario of getting all courses.
	 * Expected Result: success returning a list of services.
	 */
	@Test
	public void testGetAll_success() {
		Map<String, Object> resultSet = courseService.getAll();
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		assertTrue(result == expected && message.equals(CourseService.GET_ALL_SUCCESS));
	}
	
	/**
	 * Tests the success scenario of getting a course by a valid id.
	 * Parameter: Existing service id.
	 * Expected Result: failed & return message id doesn't exist.
	 */
	@Test
	public void testGetById_validId_success() {
		int courseId = 1;
		Map<String, Object> resultSet = courseService.findById(courseId);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		assertTrue(result == expected && message.equals(CourseService.GET_ID_SUCCESS));
	}
	
	/**
	 * Tests the failure scenario of getting a course by an invalid id.
	 * Parameter: Course id that doesn't exist.
	 * Expected Result: failed & return message id doesn't exist.
	 */
	@Test
	public void testGetById_invalidId_fail() {
		int courseId = 0;
		Map<String, Object> resultSet = courseService.findById(courseId);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		assertTrue(result == expected && message.equals(CourseService.GET_ID_FAIL_NOT_EXIST));
	}
	
	/**
	 * Tests the failure scenario of creating a course with invalid input.
	 * Parameter: Employee id that doesn't exist.
	 * Expected Result: fails & return message create failed.
	 */
	@Test
	public void testCreate_invalidInput_fail() {
		Course course = new Course();
		course.setCourseId(1);
		course.setEmployeeId(1111); // employee id doesn't exist
		course.setCourseTitle("new title");
		course.setCourseContent("new content");
		course.setCourseImgSrc(null);
		course.setCourseCreateDate(null);
		
		Map<String, Object> resultSet = courseService.create(course);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		assertTrue(result == expected && message.equals(CourseService.ERROR_UNEXPECTED));
	}
	
	/**
	 * Tests the success scenario of creating a course with valid input.
	 * Parameter: valid parameters for course object.
	 * Expected Result: success & return message create success.
	 */
	@Test
	public void testCreate_validInput_success() {
		Course course = new Course();
		course.setCourseId(1);
		course.setEmployeeId(1);
		course.setCourseTitle("new title");
		course.setCourseContent("new content");
		course.setCourseImgSrc(null);
		course.setCourseCreateDate(null);
		
		Map<String, Object> resultSet = courseService.create(course);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		assertTrue(result == expected && message.equals(CourseService.CREATE_SUCCESS));
	}
	
	/**
	 * Tests the success scenario of updating a course with valid input.
	 * Parameter: Existing course id.
	 * Expected Result: success & return message update success.
	 */
	@Test
	public void testUpdate_validInput_success() {
		Course course = new Course();
		course.setCourseId(1);
		course.setEmployeeId(1);
		course.setCourseTitle("new title");
		course.setCourseContent("new content");
		course.setCourseImgSrc(null);
		course.setCourseCreateDate(null);
		
		Map<String, Object> resultSet = courseService.update(course);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		assertTrue(result == expected && message.equals(CourseService.UPDATE_SUCCESS));
	}
	
	/**
	 * Tests the failure scenario of updating a course with invalid input.
	 * Parameter: course id that doesn't exists
	 * Expected Result: fails & return message id not found.
	 */
	@Test
	public void testUpdate_invalidInput_fail() {
		Course course = new Course();
		course.setCourseId(111111); // course id doesn't exist
		course.setEmployeeId(1); 
		course.setCourseTitle("new title");
		course.setCourseContent("new content");
		course.setCourseImgSrc(null);
		course.setCourseCreateDate(null);
		
		Map<String, Object> resultSet = courseService.update(course);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		assertTrue(result == expected && message.equals(CourseService.UPDATE_FAIL_ID_NOT_EXIST));
	}
	
	/**
	 * Tests the failure scenario of deleting a course with an invalid id.
	 * Parameter: course id that doesn't exists
	 * Expected Result: fails & return message id not found.
	 */
	@Test
	public void testDelete_invalidInput_fail() {
		int courseId = 1111; // course id doesn't exist
		Map<String, Object> resultSet = courseService.delete(courseId); 
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		
		assertTrue(result == expected && message.equals(CourseService.DELETE_FAIL_ID_NOT_EXIST));
	}
	
	/**
	 * Tests the success scenario of deleting a course with a valid id.
	 * Parameter: Existing course id
	 * Expected Result: success & return message delete success.
	 */
	@Test
	public void testDelete_validInput_success() {
		int courseId = 1;
		Map<String, Object> resultSet = courseService.delete(courseId);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		
		assertTrue(result == expected && message.equals(CourseService.DELETE_SUCCESS));
	}

}
