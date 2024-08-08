package employee.service;

import java.util.Map;

import employee.model.Employee;

public interface EmployeeService {
	
	public static final String DELETE_FAIL_ID_NOT_EXIST = "Failed deleting, employee id doesn't exist.";
	public static final String DELETE_SUCCESS = "Success deleting course.";
	public static final String UPDATE_FAIL_ID_NOT_EXIST = "Failed, employee id doesn't exist.";
	public static final String UPDATE_FAIL_VALIDATE = "Failed due to validate errors.";
	public static final String UPDATE_SUCCESS = "Success updating course.";
	public static final String CREATE_SUCCESS = "Success create course.";
	public static final String CREATE_FAIL_VALIDATE = "Failed, employee validate errors.";
	public static final String GET_FAILED_ID_NOT_FOUND = "Failed, employee id not found.";
	public static final String GET_BY_ID_SUCCESS = "Success get employee by id.";
	public static final String GET_ALL_SUCCESS = "Get all employees success.";
	public static final String ERROR_UNEXPECTED = "Unexpected error, please check with admin.";
	
	Map<String, Object> getAll();
	
	Map<String, Object> findById(int id);

	Map<String, Object> create(Employee employee);

	Map<String, Object> update(Employee employee);

	Map<String, Object> delete(int id);

	Map<String, Object> validate(Employee employee);

	boolean isExist(int id);
	
	boolean isExist(Employee employee);

}
