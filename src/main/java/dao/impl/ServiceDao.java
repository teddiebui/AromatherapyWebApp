package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.CrudDAO;
import model.Model;
import model.Service;

public class ServiceDao implements CrudDAO {
	/**
	 *  A common data source shared across DAO.
	 */
	private final DataSource dataSource;
	
	/**
	 * SQL statement to get all services.
	 */
	private static final String SQL_GET_SERVICE_ALL = 
			"SELECT * FROM [Service]";
	
	/**
	 * SQL statement to get service by id.
	 */
	private static final String SQL_GET_SERVICE = 
			"SELECT * FROM [Service] WHERE [service_id]=?";
	
	/**
	 * Constructor with injected data source.
	 * 
	 * @param theDataSource
	 */
	public ServiceDao(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}
	@Override
	public List<Model> getAll() throws Exception {
		
		List<Model> list = new ArrayList<>();
		Service service;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_GET_SERVICE_ALL);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				service = constructModel(resultSet);
				list.add(service);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		
		return list;

	}

	@Override
	public Model findById(int id) throws Exception {
		List<Model> list = new ArrayList<>();
		Service service;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_GET_SERVICE);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				service = constructModel(resultSet);
				list.add(service);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			connection.close();
		}
		return null;
	}
	
	private Service constructModel(ResultSet resultSet) 
			throws SQLException {
		Service service;
		service = new Service();
		service.setServiceId(resultSet
				.getInt("service_id"));
		service.setEmployeeId(resultSet
				.getInt("employee_id"));
		service.setServiceTitle(resultSet
				.getString("service_title"));
		service.setServiceInfo(resultSet
				.getString("service_info"));
		service.setServiceImgSrc(resultSet
				.getString("service_img_src"));
		service.setServicePrice(resultSet
				.getBigDecimal("service_price"));
		service.setServiceCreateTime(resultSet
				.getDate("service_create_time"));
		return service;
	}

}
