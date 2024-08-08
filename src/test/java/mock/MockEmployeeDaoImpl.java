package mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import employee.dao.impl.EmployeeDaoImpl;
import employee.model.Employee;

public class MockEmployeeDaoImpl extends EmployeeDaoImpl{
	private Connection connection;
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public MockEmployeeDaoImpl(DataSource theDataSource) {
		super(theDataSource);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int create(Employee employee) throws SQLException {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		int id = 0;
		try {
			preparedStatement = connection.prepareStatement(SQL_CREATE,
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setString(2, employee.getEmployeeTitle());
			preparedStatement.setString(3, employee.getEmployeeInfo());
			preparedStatement.setString(4, employee.getEmployeeImgSrc());

			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (SQLException exception) {
			throw exception;
		}
		return id;
	}
	
	@Override
	public void update(Employee employee) throws SQLException {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(SQL_UPDATE);
			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setString(2, employee.getEmployeeTitle());
			preparedStatement.setString(3, employee.getEmployeeInfo());
			preparedStatement.setString(4, employee.getEmployeeImgSrc());
			preparedStatement.setTimestamp(5, employee.getEmployeeJoinDate());
			preparedStatement.setInt(6, employee.getEmployeeId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} 
	}
	
	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(SQL_DELETE);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} 
	}

}
