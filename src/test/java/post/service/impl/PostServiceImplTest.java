package post.service.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mock.MockDataSource;
import mock.MockPostDaoImpl;
import post.model.Post;
import post.service.PostService;

public class PostServiceImplTest {

	private static DataSource dataSource;
	private static MockPostDaoImpl postDao;
	private static PostServiceImpl postService;
	private static Connection connection;
	
	/**
	 * Sets up the test environment before any tests are run.
	 * 
	 * @throws Exception if an error occurs while setting up the test environment
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataSource = MockDataSource.getDataSource();
		postDao = new MockPostDaoImpl(dataSource);
		postService = new PostServiceImpl(postDao);
	}
	
	/**
	 * Initializes the database connection before each test.
	 */
	@Before
	public void setUp() {
		try {
			connection = dataSource.getConnection();
			postDao.setConnection(connection);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Rolls back any changes and closes the database connection after each test.
	 */
	@After
	public void tearDown() {
		connection = postDao.getConnection();
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
	 * Tests the success scenario of getting all posts.
	 * Expected Result: success returning a list of posts.
	 */
	@Test
	public void testGetAll_success() {
		Map<String, Object> resultSet = postService.getAll();
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		assertTrue(result == expected && message.equals(PostService.GET_ALL_SUCCESS));
	}
	
	/**
	 * Tests the success scenario of getting a post by a valid id.
	 * Parameter: Existing post id.
	 * Expected Result: failed & return message id doesn't exist.
	 */
	@Test
	public void testGetById_validId_success() {
		int postId = 1;
		Map<String, Object> resultSet = postService.findById(postId);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		assertTrue(result == expected && message.equals(PostService.GET_ID_SUCCESS));
	}
	
	/**
	 * Tests the failure scenario of getting a post by an invalid id.
	 * Parameter: Post id that doesn't exist.
	 * Expected Result: failed & return message id doesn't exist.
	 */
	@Test
	public void testGetById_invalidId_fail() {
		int postId = 0;
		Map<String, Object> resultSet = postService.findById(postId);
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		assertTrue(result == expected && message.equals(PostService.GET_ID_FAIL_NOT_EXIST));
	}
	
	/**
	 * Tests the success scenario of creating a post with valid input.
	 * Parameter: Employee id that doesn't exist.
	 * Expected Result: fails & return message create failed.
	 */
	@Test
	public void testCreate_invalidInput_fail() {
		Post post = new Post();
		post.setPostId(0);
		post.setPostStatus(0);
		post.setEmployeeId(0); // employee id doesn't exists.
		post.setPostLastUpdateEmployee(0);
		post.setPostLastUpdateTime(null);
		post.setPostTitle("test");
		post.setPostContent("test");
		post.setPostIntroImgSrc("test");
		post.setPostExcerpt("test");
		post.setPostExcerptImgSrc("test");
		post.setPostSlug("test-test");
		post.setPostCreateTime(null);

		
		Map<String, Object> resultSet = postService.create(post);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		
		assertTrue(result == expected && message.equals(PostService.ERROR_UNEXPECTED));
	}
	
	
	
	/**
	 * Tests the success scenario of creating a post with valid input.
	 * Parameter: valid parameters for post object.
	 * Expected Result: success & return message create success.
	 */
	@Test
	public void testCreate_validInput_success() {
		Post post = new Post();
		post.setPostId(0);
		post.setPostStatus(2);
		post.setEmployeeId(4);
		post.setPostLastUpdateEmployee(4);
		post.setPostLastUpdateTime(null);
		post.setPostTitle("test");
		post.setPostContent("test");
		post.setPostIntroImgSrc("test");
		post.setPostExcerpt("test");
		post.setPostExcerptImgSrc("test");
		post.setPostSlug("test-test");
		post.setPostCreateTime(null);

		
		Map<String, Object> resultSet = postService.create(post);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		
		assertTrue(result == expected && message.equals(PostService.CREATE_SUCCESS));
	}
	
	/**
	 * Tests the success scenario of updating a post with valid input.
	 * Parameter: Existing post id.
	 * Expected Result: success & return message update success.
	 */
	@Test
	public void testUpdate_validInput_success() {
		Post post = new Post();
		post.setPostId(5);
		post.setPostStatus(2);
		post.setEmployeeId(4);
		post.setPostLastUpdateEmployee(4);
		post.setPostLastUpdateTime(null);
		post.setPostTitle("test");
		post.setPostContent("test");
		post.setPostIntroImgSrc("test");
		post.setPostExcerpt("test");
		post.setPostExcerptImgSrc("test");
		post.setPostSlug("test-test");
		post.setPostCreateTime(null);

		Map<String, Object> resultSet = postService.update(post);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;
		
		assertTrue(result == expected && message.equals(PostService.UPDATE_SUCCESS));
	}
	
	/**
	 * Tests the failure scenario of updating a post with invalid input.
	 * Parameter: post id that doesn't exists
	 * Expected Result: fails & return message id not found.
	 */
	@Test
	public void testUpdate_invalidInput_fail() {
		Post post = new Post();
		post.setPostId(5555); // Post id doesn't exists.
		post.setPostStatus(2);
		post.setEmployeeId(4);
		post.setPostLastUpdateEmployee(4);
		post.setPostLastUpdateTime(null);
		post.setPostTitle("test");
		post.setPostContent("test");
		post.setPostIntroImgSrc("test");
		post.setPostExcerpt("test");
		post.setPostExcerptImgSrc("test");
		post.setPostSlug("test-test");
		post.setPostCreateTime(null);
		
		Map<String, Object> resultSet = postService.update(post);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		assertTrue(result == expected && message.equals(PostService.UPDATE_FAIL_ID_NOT_EXIST));
	}
	
	/**
	 * Tests the failure scenario of deleting a post with an invalid id.
	 * Parameter: post id that doesn't exists
	 * Expected Result: fails & return message id not found.
	 */
	@Test
	public void testDelete_invalidInput_fail() {
		int postId = 1111; // post id doesn't exist
		Map<String, Object> resultSet = postService.delete(postId); 
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = false;
		
		assertTrue(result == expected && message.equals(PostService.DELETE_FAIL_ID_NOT_EXIST));
	}
	
	/**
	 * Tests the success scenario of deleting a post with a valid id.
	 * Parameter: Existing post id
	 * Expected Result: success & return message delete success.
	 */
	@Test
	public void testDelete_validInput_success() {
		int postId = 4;
		Map<String, Object> resultSet = postService.delete(postId);
		
		boolean result = (boolean) resultSet.get("result");
		String message = (String) resultSet.get("message");
		boolean expected = true;

		assertTrue(result == expected && message.equals(PostService.DELETE_SUCCESS));
	}
	

}
