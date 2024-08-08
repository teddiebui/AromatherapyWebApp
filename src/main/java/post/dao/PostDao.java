package post.dao;

import java.sql.SQLException;
import java.util.List;

import post.model.Post;

public interface PostDao {
	
	/**
	 * SQL statement to get all posts.
	 */
	public static final String SQL_GET_ALL = "SELECT * FROM [POST]";

	/**
	 * SQL statement to get post by id.
	 */
	public static final String SQL_GET_BY_ID = 
			"SELECT * FROM [POST] WHERE [post_id]=?";
	
	/**
	 * SQL statement for deleting record from database
	 */
	public static final String SQL_DELETE = 
			"DELETE FROM [POST] WHERE [post_id]=?";
	
	/**
	 * SQL statement for inserting new post.
	 */
	public static final String SQL_CREATE = "INSERT INTO [POST] ("
			+ "[employee_id], "
			+ "[post_status], "
			+ "[post_last_update_employee], "
			+ "[post_title], "
			+ "[post_content], "
			+ "[post_slug], "
			+ "[post_intro_img_src], "
			+ "[post_excerpt], [post_excerpt_img_src]) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String SQL_UPDATE = "UPDATE [Post] SET"
			+ "[post_status] = ?, "
			+ "[post_last_update_employee] = ?, "
			+ "[post_last_update_time] = ?, "
			+ "[post_title] = ?, "
			+ "[post_content] = ?, "
			+ "[post_slug] = ?, "
			+ "[post_intro_img_src] = ?, "
			+ "[post_excerpt] = ?, "
			+ "[post_excerpt_img_src] = ? "
			+ "WHERE [post_id] = ?";
		
	
	
	void create(Post post) throws SQLException;
	
	void update(Post post) throws SQLException;
	
	void delete(int id) throws SQLException;

	List<Post> getAll() throws SQLException;
	
	Post findById(int id) throws SQLException;

}
