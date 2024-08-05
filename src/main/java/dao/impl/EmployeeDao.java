package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.CrudDAO;
import model.Employee;
import model.Model;

public class EmployeeDao implements CrudDAO {
	
	/**
	 *  A common data source shared across DAO.
	 */
	private final DataSource dataSource;
	
	/**
	 * SQL statement to get all employees.
	 */
	private static final String SQL_GET_EMPLOYEE_ALL = 
			"SELECT * FROM [Employee]";
	
	/**
	 * SQL statement to get employee by id.
	 */
	private static final String SQL_GET_EMPLOYEE = 
			"SELECT * FROM [Employee] WHERE [employee_id]=?";
	
	/**
	 * Constructor with injected data source.
	 * 
	 * @param theDataSource
	 */
	public EmployeeDao(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}
	

	@Override
	public List<Model> getAll() throws Exception {
		// TODO Auto-generated method stub
		List<Model> list = new ArrayList<Model>();
		Employee employee;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_GET_EMPLOYEE_ALL);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee = new Employee();
				employee.setEmployeeId(resultSet
						.getInt("employee_id"));
	            employee.setEmployeeName(resultSet
	            		.getString("employee_name"));
	            employee.setEmployeeTitle(resultSet
	            		.getString("employee_title"));
	            employee.setEmployeeInfo(resultSet.
	            		getString("employee_info"));
	            employee.setEmployeeImgSrc(resultSet
	            		.getString("employee_img_src"));
	            
				list.add(employee);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return list;
	}

	@Override
	public Model findById(int id) throws Exception {
		// TODO Auto-generated method stub
		Employee employee;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_GET_EMPLOYEE);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				employee = new Employee();
				employee.setEmployeeId(resultSet
						.getInt("employee_id"));
	            employee.setEmployeeName(resultSet
	            		.getString("employee_name"));
	            employee.setEmployeeTitle(resultSet
	            		.getString("employee_title"));
	            employee.setEmployeeInfo(resultSet.
	            		getString("employee_info"));
	            employee.setEmployeeImgSrc(resultSet
	            		.getString("employee_img_src"));
	            
				return employee;
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
