package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.CrudDAO;
import model.Course;
import model.Model;

public class CourseDao implements CrudDAO {
	private final DataSource dataSource;
	
	private static final String SQL_GET_COURSES_ALL = "SELECT * FROM COURSE";

	private static final String SQL_GET_COURSE = "SELECT * FROM COURSE WHERE [course_id]=?";
	
	public CourseDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public List<Model> getAll() throws Exception {
		// TODO Auto-generated method stub
		List<Model> courseList = new ArrayList<Model>();
		Course course;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_COURSES_ALL);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				course = new Course();
				//TODO: get data and set to object
				course.setCourseId(resultSet.getInt("course_id"));
	            course.setEmployeeId(resultSet.getInt("employee_id"));
	            course.setCourseTitle(resultSet.getString("course_title"));
	            course.setCourseContent(resultSet.getString("course_content"));
	            course.setCourseImgSrc(resultSet.getString("course_img_src"));
	            course.setCourseCreateDate(resultSet.getDate("course_create_date"));
	            
				courseList.add(course);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		
		return courseList;
	}
	/**
	 * Not yet implemented
	 */
	@Override
	public Model findById(int id) throws Exception {
		Course course;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_COURSE);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				course = new Course();
				course.setCourseId(resultSet.getInt("course_id"));
	            course.setEmployeeId(resultSet.getInt("employee_id"));
	            course.setCourseTitle(resultSet.getString("course_title"));
	            course.setCourseContent(resultSet.getString("course_content"));
	            course.setCourseImgSrc(resultSet.getString("course_img_src"));
	            course.setCourseCreateDate(resultSet.getDate("course_create_date"));
	            
				return course;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return null;
	}

}
