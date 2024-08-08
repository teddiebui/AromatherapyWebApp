package service.service;

import java.util.Map;

import service.model.Service;

public interface ServiceService {
	
	public static final String DELETE_FAIL_ID_NOT_EXIST = "Failed deleting id doesn't exist.";
	public static final String DELETE_SUCCESS = "Deleting service success.";
	public static final String UPDATE_FAIL_ID_NOT_EXIST = "Failed updating id doesn't exist.";
	public static final String UPDATE_FAIL_VALIDATION = "Failed due to form validation failed.";
	public static final String UPDATE_SUCCESS = "Update service success.";
	public static final String CREATE_FAIL_ID_NOT_EXIST = "Faid due to validation failed.";
	public static final String CREATE_FAIL_VALIDATE = CREATE_FAIL_ID_NOT_EXIST;
	public static final String CREATE_SUCCESS = "Create service success.";
	public static final String GET_ID_FAIL_NOT_EXIST = "Service id doesn't exist.";
	public static final String GET_ID_SUCCESS = "Get service success.";
	public static final String GET_ALL_SUCCESS = "Get all success.";
	public static final String ERROR_UNEXPECTED = "Unexpected error, please check with admin.";
	
	
	Map<String, Object> getAll();
	
	Map<String, Object> findById(int id);
	
	Map<String, Object> create(Service service);
	
	Map<String, Object> update(Service service);
	
	Map<String, Object> delete(int id);
	
	Map<String, Object> validate(Service service);
	
	boolean isExist(int id);
	
	boolean isExist(Service service);
}
