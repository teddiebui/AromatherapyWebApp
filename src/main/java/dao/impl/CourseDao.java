package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.CrudDAO;
import model.Course;
import model.Model;

public class CourseDao implements CrudDAO {
	/**
	 * A common data source shared across DAO.
	 */
	private DataSource dataSource;

	/**
	 * SQL statement to get all course.
	 */
	private static final String SQL_GET_ALL = 
			"SELECT * FROM COURSE";

	/**
	 * SQL statement to get by id.
	 */
	private static final String SQL_GET_BY_ID = 
			"SELECT * FROM COURSE WHERE [course_id]=?";
	
	/**
	 * Constructor with injected data source.
	 * 
	 * @param theDataSource
	 */
	public CourseDao(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}
	
	/**
	 * Returns a list of all courses.
	 * 
	 * @return list of courses
	 */
	@Override
	public List<Model> getAll() throws Exception {
		List<Model> courseList = new ArrayList<Model>();
		Course course;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_GET_ALL);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				course = constructModel(resultSet);
				courseList.add(course);
			}
		} catch (Exception exception) {
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
	 * @return course
	 */
	@Override
	public Model findById(int id) throws Exception {
		Course course;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_GET_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				course = constructModel(resultSet);
				return course;
			}

		} catch (Exception exception) {
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return null;
	}
	

	protected Course constructModel(ResultSet resultSet) 
			throws SQLException {
		Course course;
		course = new Course();
		course.setCourseId(resultSet
				.getInt("course_id"));
		course.setEmployeeId(resultSet
				.getInt("employee_id"));
		course.setCourseTitle(resultSet
				.getString("course_title"));
		course.setCourseContent(resultSet
				.getString("course_content"));
		course.setCourseImgSrc(resultSet
				.getString("course_img_src"));
		course.setCourseCreateDate(resultSet
				.getDate("course_create_date"));
		return course;
	}

}
