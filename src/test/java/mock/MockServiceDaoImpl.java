package mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import service.dao.impl.ServiceDaoImpl;
import service.model.Service;

public class MockServiceDaoImpl extends ServiceDaoImpl {
	
	private Connection connection;

	public MockServiceDaoImpl(DataSource theDataSource) {
		super(theDataSource);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public int create(Service service) throws SQLException {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		int id = 0;
		try {
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
			throw exception;
		}
		
		return id;
	}
	
	@Override
	public void update(Service service) throws SQLException {
		PreparedStatement preparedStatement;
		try {
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
			throw exception;
		}
	}
	
	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection
					.prepareStatement(SQL_DELETE);
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw exception;
		}
	}

}
