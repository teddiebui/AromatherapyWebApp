package service.service.impl;

import java.util.HashMap;
import java.util.Map;

import service.dao.ServiceDao;
import service.dao.impl.ServiceDaoImpl;
import service.model.Service;
import service.service.ServiceService;

public class ServiceServiceImpl implements ServiceService {
	
	private ServiceDaoImpl serviceDao;
	
	public ServiceServiceImpl(ServiceDao serviceDao) {
		this.serviceDao = (ServiceDaoImpl) serviceDao;
	}

	public ServiceDaoImpl getServiceDao() {
		return serviceDao;
	}

	public void setServiceDao(ServiceDaoImpl serviceDao) {
		this.serviceDao = serviceDao;
	}
	
	public Map<String, Object> generateResultSet() {
		return new HashMap<>();
	}

	@Override
	public Map<String, Object> getAll() {
		Map<String, Object> resultSet = generateResultSet();
		boolean result;
		String message;
		Object data = null;
		
		try {
			data = serviceDao.getAll();
			result = true;
			message = GET_ALL_SUCCESS;
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
	public Map<String, Object> findById(int id) {
		Map<String, Object> resultSet = generateResultSet();
		boolean result;
		String message;
		Object data = null;
		
		try {
			data = serviceDao.findById(id);
			if (data != null) {
				result = true;
				message = GET_ID_SUCCESS;
			} else {
				result = false;
				message = GET_ID_FAIL_NOT_EXIST;
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
	public Map<String, Object> create(Service service) {
		Map<String, Object> resultSet = generateResultSet();
		Map<String, Object> validateErrors = null;
		boolean result;
		String message;
		try {
			validateErrors = validate(service);
			if (validateErrors.isEmpty()) {
				serviceDao.create(service);
				result = true;
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
	public Map<String, Object> update(Service service) {
		Map<String, Object> resultSet = generateResultSet();
		Map<String, Object> validateErrors = null;
		boolean result;
		String message;
		boolean isExist = isExist(service);
		try {
			if (isExist) {
				validateErrors = validate(service);
				if (validateErrors.isEmpty()) {
					serviceDao.update(service);
					result = true;
					message = UPDATE_SUCCESS;
				} else {
					result = false;
					message = UPDATE_FAIL_VALIDATION;
				}
			} else {
				result = false;
				message = UPDATE_FAIL_ID_NOT_EXIST;
				
			}
			
			
		} catch (Exception exception) {
			result = false;
			message = ERROR_UNEXPECTED;
			exception.printStackTrace();
		}
		resultSet.put("result", result);
		resultSet.put("message", message);
		resultSet.put("validateErrors", validateErrors);
		resultSet.put("data", service);
		return resultSet;
	}

	@Override
	public Map<String, Object> delete(int id) {
		Map<String, Object> resultSet = generateResultSet();
		boolean result;
		String message;
		boolean isExist = isExist(id);
		try {
			if (isExist) {
				serviceDao.delete(id);
				result = true;
				message = DELETE_SUCCESS;

			} else {
				result = false;
				message = DELETE_FAIL_ID_NOT_EXIST;
			}
		} catch (Exception exception) {
			result = false;
			message = ERROR_UNEXPECTED;
			exception.printStackTrace();
		}
		resultSet.put("result", result);
		resultSet.put("message", message);
		return resultSet;
	}

	@Override
	public Map<String, Object> validate(Service service) {
		// TODO Auto-generated method stub
		return new HashMap<>();
	}

	@Override
	public boolean isExist(int id) {
		try {
			return id != 0 && serviceDao.findById(id) != null;
		} catch (Exception exception) {
			return false;
		}
	}

	@Override
	public boolean isExist(Service service) {
		try {
			return service  != null && isExist(service.getServiceId());
		} catch (Exception exception) {
			return false;
		}
	}
	
	

}
