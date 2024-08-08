package employee.service.impl;

import java.util.HashMap;
import java.util.Map;

import employee.dao.impl.EmployeeDaoImpl;
import employee.model.Employee;
import employee.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDaoImpl employeeDao;

	public EmployeeServiceImpl(EmployeeDaoImpl employeeDao) {
		this.employeeDao = employeeDao;
	}

	public EmployeeDaoImpl getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDaoImpl employeeDao) {
		this.employeeDao = employeeDao;
	}

	public Map<String, Object> generateResultSet() {
		return new HashMap<String, Object>();
	}

	@Override
	public Map<String, Object> getAll() {
		Map<String, Object> resultSet = generateResultSet();
		boolean result;
		String message;
		Object data;
		try {
			data = employeeDao.getAll();
			result = true;
			message = GET_ALL_SUCCESS;
		} catch (Exception exception) {
			data = null;
			result = false;
			message = ERROR_UNEXPECTED;
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
		Employee data = null;
		try {
			data = employeeDao.findById(id);
			if (data != null) {
				result = true;
				message = GET_BY_ID_SUCCESS;
			} else {
				result = false;
				message = GET_FAILED_ID_NOT_FOUND;
			}
		} catch (Exception exception) {
			result = false;
			message = ERROR_UNEXPECTED;
			exception.printStackTrace();
		}
		resultSet.put("result", result);
		resultSet.put("message", message);
		resultSet.put("data", data);
		
		return resultSet;
	}

	@Override
	public Map<String, Object> create(Employee employee) {
		Map<String, Object> resultSet = generateResultSet();
		Map<String, Object> validateErrors = null;
		boolean result;
		String message;
		try {
			validateErrors = validate(employee);
			if (validateErrors.isEmpty()) {
				result = true;
				employeeDao.create(employee);
				message = CREATE_SUCCESS; 
			} else {
				result = false;
				message = CREATE_FAIL_VALIDATE;
			}
		} catch (Exception exception) {
			result = false;
			message = ERROR_UNEXPECTED;
			exception.printStackTrace();
		}

		resultSet.put("result", result);
		resultSet.put("message", message);
		resultSet.put("validateErrors", validateErrors);
		return resultSet;
	}

	@Override
	public Map<String, Object> update(Employee employee) {
		Map<String, Object> resultSet = generateResultSet();
		Map<String, Object> validateErrors = null;
		boolean result;
		String message;
		boolean isExist = isExist(employee);

		if (isExist) {
			validateErrors = validate(employee);
			if (validateErrors.isEmpty()) {
				try {
					employeeDao.update(employee);
					result = true;
					message = UPDATE_SUCCESS;
				} catch (Exception exception) {
					result = false;
					message = ERROR_UNEXPECTED;
					exception.printStackTrace();
				}
			} else {
				result = false;
				message = UPDATE_FAIL_VALIDATE;
			}
			
		} else {
			result = false;
			message = UPDATE_FAIL_ID_NOT_EXIST;
		}

		resultSet.put("result", result);
		resultSet.put("message", message);
		resultSet.put("validateErrors", validateErrors);
		resultSet.put("data", employee);
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
				employeeDao.delete(id);
				result = true;
				message = DELETE_SUCCESS;
			} catch (Exception exception) {
				result = false;
				message = ERROR_UNEXPECTED;
				exception.printStackTrace();
			}
		} else {
			result = false;
			message = DELETE_FAIL_ID_NOT_EXIST;
		}

		resultSet.put("result", result);
		resultSet.put("message", message);
		return resultSet;
	}

	@Override
	public Map<String, Object> validate(Employee employee) {
		Map<String, Object> result = new HashMap<>();
		return result;
	}

	@Override
	public boolean isExist(int id) {
		try {
			return id != 0 && employeeDao.findById(id) != null;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean isExist(Employee employee) {
		try {
			return employee != null && isExist(employee.getEmployeeId());
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

}
