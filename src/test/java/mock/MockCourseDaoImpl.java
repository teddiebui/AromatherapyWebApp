package mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import course.dao.impl.CourseDaoImpl;
import course.model.Course;

public class MockCourseDaoImpl extends CourseDaoImpl{

	private Connection connection;
	
	public MockCourseDaoImpl(DataSource dataSource) {
		super(dataSource);
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	@Override
	public int create(Course course) throws SQLException {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		int id = 0;
		try {
			preparedStatement = connection.prepareStatement(SQL_CREATE, 
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, course.getEmployeeId());
			preparedStatement.setString(2, course.getCourseTitle());
			preparedStatement.setString(3, course.getCourseContent());
			preparedStatement.setString(4, course.getCourseImgSrc());
			
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			
		} catch (SQLException exception) {
			throw exception;
		} 
		
		return id;
	}

	@Override
	public void update(Course course) throws SQLException {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(SQL_UPDATE);
			
			preparedStatement.setInt(1, course.getEmployeeId());
			preparedStatement.setString(2, course.getCourseTitle());
			preparedStatement.setString(3, course.getCourseContent());
			preparedStatement.setString(4, course.getCourseImgSrc());
			preparedStatement.setInt(5, course.getCourseId());
			
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw exception;
		}
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(SQL_DELETE);
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw exception;
		}
		
	}
	
}
