package service.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import service.dao.ServiceDao;
import service.model.Service;

public class ServiceDaoImpl implements ServiceDao {
	/**
	 *  A common data source shared across DAO.
	 */
	private final DataSource dataSource;
	
	
	/**
	 * Constructor with injected data source.
	 * 
	 * @param theDataSource
	 */
	public ServiceDaoImpl(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}
	@Override
	public List<Service> getAll() throws SQLException {
		
		List<Service> list = new ArrayList<>();
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
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			} 
		}
		
		return list;

	}

	@Override
	public Service findById(int id) throws SQLException {
		Service service = null;
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
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			} 
		}
		return service;
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
				.getFloat("service_price"));
		service.setServiceCreateTime(resultSet
				.getDate("service_create_time"));
		return service;
	}
	@Override
	public int create(Service service) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		int id = 0;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(
					1, service.getEmployeeId());
			preparedStatement.setString(
					2, service.getServiceTitle());
			preparedStatement.setString(
					3, service.getServiceInfo());
			preparedStatement.setString(
					4, service.getServiceImgSrc());
			preparedStatement.setFloat(
					5, service.getServicePrice());
			
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			connection.commit();

		} catch (SQLException exception) {
			if (connection != null) {
				connection.rollback();
			}
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			} 
		}
		return id;
	}
	@Override
	public void update(Service service) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_UPDATE);
			preparedStatement.setString(
					1, service.getServiceTitle());
			preparedStatement.setString(
					2, service.getServiceInfo());
			preparedStatement.setString(
					3, service.getServiceImgSrc());
			preparedStatement.setFloat(
					4, service.getServicePrice());
			preparedStatement.setInt(
					5, service.getServiceId());
			
			preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException exception) {
			if (connection != null) {
				connection.rollback();
			}
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			} 
		}
	}
	@Override
	public void delete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_DELETE);
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException exception) {
			if (connection != null) {
				connection.rollback();
			}
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			} 
		}
	}

}
