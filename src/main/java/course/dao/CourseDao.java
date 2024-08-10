package course.dao;

import java.sql.SQLException;
import java.util.List;

import course.model.Course;

public interface CourseDao {

	/**
	 * SQL statement to get all courses.
	 */
	public static final String SQL_GET_ALL = "SELECT * FROM COURSE";

	/**
	 * SQL statement to get course by id.
	 */
	public static final String SQL_GET_BY_ID = "SELECT * FROM COURSE WHERE [course_id]=?";
	
	/**
	 * SQL statement to update a course record.
	 */
	public static final String SQL_UPDATE = "UPDATE [Course] SET "
			+ "[employee_id] = ?, "
			+ "[course_title] = ?, "
			+ "[course_info] = ?, "
			+ "[course_content] = ?, "
			+ "[course_img_src] = ?, "
			+ "[course_price] = ? "
			+ "WHERE [course_id] = ?";
	
	/**
	 * SQL statement to create a new course record.
	 */
	public static final String SQL_CREATE = "INSERT INTO [Course] ("
			+ "[employee_id], "
			+ "[course_title], "
			+ "[course_info], "
			+ "[course_content], "
			+ "[course_img_src], "
			+ "[course_price]) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	
	/**
	 * SQL statement to delete a course record.
	 */
	public static final String SQL_DELETE = "DELETE FROM [Course] WHERE [course_id] = ?";

	/**
	 * Constructor with injected data source.
	 * 
	 * @param theDataSource the data source to use for database connections
	 */ 
	int create(Course course) throws SQLException;
	
	void update(Course course) throws SQLException;
	
	void delete(int id) throws SQLException;

	List<Course> getAll() throws SQLException;
	
	Course findById(int id) throws SQLException;
}
