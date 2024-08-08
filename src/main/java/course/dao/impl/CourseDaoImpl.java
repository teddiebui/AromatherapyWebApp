package course.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import course.dao.CourseDao;
import course.model.Course;

public class CourseDaoImpl implements CourseDao {
	/**
	 * A common data source shared across DAO.
	 */
	private DataSource dataSource;

	/**
	 * Constructor with injected data source.
	 * 
	 * @param theDataSource the data source to use for database connections
	 */
	public CourseDaoImpl(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}
	
	/**
	 * Returns a list of all courses.
	 * 
	 * @return list of courses
	 * @throws SQLException if a database access error occurs
	 */
	@Override
	public List<Course> getAll() throws SQLException {
		List<Course> courseList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_ALL);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Course course = constructModel(resultSet);
				courseList.add(course);
			}
		} catch (SQLException exception) {
			if (connection != null) {
				connection.rollback();
			}
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		return courseList;
	}

	/**
	 * Returns course by id.
	 * 
	 * @param id the id of the course to retrieve
	 * @return the course with the specified id
	 * @throws SQLException if a database access error occurs
	 */
	@Override
	public Course findById(int id) throws SQLException {
		Course course = null;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				course = constructModel(resultSet);
			}
		} catch (SQLException exception) {
			if (connection != null) {
				connection.rollback();
			}
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return course;
	}
	
	/**
	 * Constructs a Course object from the given ResultSet.
	 * 
	 * @param resultSet the ResultSet containing course data
	 * @return a Course object
	 * @throws SQLException if a database access error occurs
	 */
	protected Course constructModel(ResultSet resultSet) throws SQLException {
		Course course = new Course();
		course.setCourseId(resultSet.getInt("course_id"));
		course.setEmployeeId(resultSet.getInt("employee_id"));
		course.setCourseTitle(resultSet.getString("course_title"));
		course.setCourseContent(resultSet.getString("course_content"));
		course.setCourseImgSrc(resultSet.getString("course_img_src"));
		course.setCourseCreateDate(resultSet.getDate("course_create_date"));
		return course;
	}

	/**
	 * Creates a new course record in the database.
	 * 
	 * @param course the course to create
	 * @return the generated id of the new course
	 * @throws SQLException if a database access error occurs
	 */
	@Override
	public int create(Course course) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		int id = 0;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_CREATE, 
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, course.getEmployeeId());
			preparedStatement.setString(2, course.getCourseTitle());
			preparedStatement.setString(3, course.getCourseContent());
			preparedStatement.setString(4, course.getCourseImgSrc());
			
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			connection.commit();
			
		} catch (SQLException exception) {
			if (connection != null) {
				connection.rollback();
			}
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		
		return id;
	}

	/**
	 * Updates an existing course record in the database.
	 * 
	 * @param course the course to update
	 * @throws SQLException if a database access error occurs
	 */
	@Override
	public void update(Course course) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE);
			
			preparedStatement.setInt(1, course.getEmployeeId());
			preparedStatement.setString(2, course.getCourseTitle());
			preparedStatement.setString(3, course.getCourseContent());
			preparedStatement.setString(4, course.getCourseImgSrc());
			preparedStatement.setInt(5, course.getCourseId());
			
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException exception) {
			if (connection != null) {
				connection.rollback();
			}
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * Deletes a course record from the database.
	 * 
	 * @param id the id of the course to delete
	 * @throws SQLException if a database access error occurs
	 */
	@Override
	public void delete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE);
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException exception) {
			if (connection != null) {
				connection.rollback();
			}
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
}
