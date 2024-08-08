package course.service;

import java.util.Map;

import course.model.Course;

public interface CourseService {
	
	public static final String DELETE_FAIL_ID_NOT_EXIST = "Failed deleting, course id not exists.";
	public static final String DELETE_SUCCESS = "Success deleting course.";
	public static final String UPDATE_FAIL_ID_NOT_EXIST = "Failed updating, course id not exists";
	public static final String UPDATE_FAIL_VALIDATION = "Failed updateing due to validation errors.";
	public static final String UPDATE_SUCCESS = "Success updating course.";
	public static final String CREATE_SUCCESS = "Success creating course";
	public static final String CREATE_FAIL_VALIDATION = "Failed, due to course validation fails.";
	public static final String GET_ID_FAIL_NOT_EXIST = "Failed. Course not exists.";
	public static final String GET_ID_SUCCESS = "Success.";
	public static final String GET_ALL_SUCCESS = "Success get all courses.";
	public static final String ERROR_UNEXPECTED = "Unexpected error, please check with admin.";
	
	Map<String, Object> getAll();
	

	Map<String, Object> findById(int id);
	

	Map<String, Object> create(Course course);
	

	Map<String, Object> update(Course course);
	

	Map<String, Object> delete(int id);
	

	Map<String, Object> validate(Course course);
	

	boolean isExist(Course course);
	

	boolean isExist(int id);
	
	
}
