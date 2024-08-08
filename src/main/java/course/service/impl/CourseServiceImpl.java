package course.service.impl;

import java.util.HashMap;
import java.util.Map;

import course.dao.impl.CourseDaoImpl;
import course.model.Course;
import course.service.CourseService;

public class CourseServiceImpl implements CourseService {
	
	
	private CourseDaoImpl courseDao;
	
	public CourseServiceImpl(CourseDaoImpl courseDao) {
		this.courseDao = courseDao;
	}
	

	public CourseDaoImpl getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoImpl courseDao) {
		this.courseDao = courseDao;
	}

	public Map<String, Object> generateResultSet() {
		return new HashMap<>();
	}

	@Override
	public Map<String, Object> getAll() {
		Map<String, Object> resultSet = generateResultSet();
		boolean result;
		String message;
		Object data;
		
		try {
			data = courseDao.getAll();
			message = GET_ALL_SUCCESS;
			result = true;
			
		} catch (Exception exception) {
			message = ERROR_UNEXPECTED;
			result = false;
			data = null;
			exception.printStackTrace();
		}
		
		resultSet.put("result", result);
		resultSet.put("message", message);
		resultSet.put("data", data);
		
		return resultSet;
	}

	@Override
	public Map<String, Object> findById(int id) {
		Map<String, Object> resultSet = generateResultSet();
		boolean result;
		String message;
		Course data = null;
		try {
			data = courseDao.findById(id);
			if (data != null) {
				message = GET_ID_SUCCESS;
				result = true;
			} else {
				message = GET_ID_FAIL_NOT_EXIST;
				result = false;
			}	
		} catch (Exception exception) {
			message = ERROR_UNEXPECTED;
			result = false;
			exception.printStackTrace();
		}
		
		resultSet.put("result", result);
		resultSet.put("message", message);
		resultSet.put("data", data);
		
		return resultSet;
	}

	@Override
	public Map<String, Object> create(Course course) {
		Map<String, Object> resultSet = generateResultSet();
		Map<String, Object> validateErrors = null;
		boolean result;
		String message;
		
		try {
			validateErrors = validate(course);
			if (validateErrors != null && validateErrors.isEmpty()) {
				courseDao.create(course);
				message = CREATE_SUCCESS;
				result = true;
			} else {
				result = false;
				message = CREATE_FAIL_VALIDATION;
			}
			
			
		} catch (Exception exception) {
			message = ERROR_UNEXPECTED;
			result = false;
			exception.printStackTrace();
		}
		
		resultSet.put("result", result);
		resultSet.put("message", message);
		resultSet.put("validateErrors", validateErrors);
		
		return resultSet;
	}

	@Override
	public Map<String, Object> update(Course course) {
		Map<String, Object> resultSet = generateResultSet();
		Map<String, Object> validateErrors = null;
		boolean result;
		String message;
		boolean isExist = isExist(course);
		
		if (isExist) {
			try {
				validateErrors = validate(course);
				if (validateErrors != null && validateErrors.isEmpty()) {
					courseDao.update(course);
					message = UPDATE_SUCCESS;
					result = true;
				} else {
					message = UPDATE_FAIL_VALIDATION;
					result = false;
				}
				
			} catch (Exception exception) {
				message = ERROR_UNEXPECTED;
				result = false;
				exception.printStackTrace();
			}
		} else {
			message = UPDATE_FAIL_ID_NOT_EXIST;
			result = false;
		}
		
		
		resultSet.put("result", result);
		resultSet.put("message", message);
		resultSet.put("validateErrors", validateErrors);
		resultSet.put("data", course);
		
		return resultSet;
	}

	@Override
	public Map<String, Object> delete(int id) {
		Map<String, Object> resultSet = generateResultSet();
		boolean result;
		String message;
		boolean isExist = isExist(id);
		if (isExist) {
			try {
				courseDao.delete(id);
				message = String.format(DELETE_SUCCESS);
				result = true;
				
			} catch (Exception exception) {
				message = ERROR_UNEXPECTED;
				result = false;
				exception.printStackTrace();
			}
		} else {
			message = DELETE_FAIL_ID_NOT_EXIST;
			result = false;
		}
		
		
		
		resultSet.put("result", result);
		resultSet.put("message", message);
		
		return resultSet;
	}

	@Override
	public Map<String, Object> validate(Course course) {
		Map<String, Object> resultSet = generateResultSet();
		
		return resultSet;
	}

	@Override
	public boolean isExist(Course course) {
		try {
			return course != null && isExist(course.getCourseId());
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
 	}

	@Override
	public boolean isExist(int id) {
		try {
			return id != 0 && courseDao.findById(id) != null;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

}
