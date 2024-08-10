package employee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import employee.dao.EmployeeDao;
import employee.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	/**
	 * A common data source shared across DAO.
	 */
	private final DataSource dataSource;

	/**
	 * Constructor with injected data source.
	 * 
	 * @param theDataSource
	 */
	public EmployeeDaoImpl(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}

	@Override
	public List<Employee> getAll() throws SQLException {
		List<Employee> list = new ArrayList<Employee>();
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
				employee = constructModel(resultSet);

				list.add(employee);
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
	public Employee findById(int id) throws SQLException {
		Employee employee;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_EMPLOYEE);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				employee = constructModel(resultSet);

				return employee;
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return null;
	}

	private Employee constructModel(ResultSet resultSet) throws SQLException {
		Employee employee;
		employee = new Employee();
		employee.setEmployeeId(resultSet.getInt("employee_id"));
		employee.setEmployeeName(resultSet.getString("employee_name"));
		employee.setEmployeeTitle(
				resultSet.getString("employee_title"));
		employee.setEmployeeInfo(resultSet.getString("employee_info"));
		employee.setEmployeeImgSrc(
				resultSet.getString("employee_img_src"));
		employee.setUsername(
				resultSet.getString("employee_username"));
		employee.setPassword(
				resultSet.getString("employee_hashed_password"));
		employee.setEmployeeJoinDate(
				resultSet.getTimestamp("employee_join_date"));
		return employee;
	}

	@Override
	public int create(Employee employee) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		int id = 0;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_CREATE,
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setString(2, employee.getEmployeeTitle());
			preparedStatement.setString(3, employee.getEmployeeInfo());
			preparedStatement.setString(4, employee.getEmployeeImgSrc());
			preparedStatement.setString(5, employee.getUsername());
			preparedStatement.setString(6, employee.getPassword());

			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}

			connection.commit();

		} catch (SQLException e) {
			if (connection != null) {
				connection.rollback();
			}
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return id;
	}

	@Override
	public void update(Employee employee) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE);
			preparedStatement.setString(1, employee.getEmployeeName());
			preparedStatement.setString(2, employee.getEmployeeTitle());
			preparedStatement.setString(3, employee.getEmployeeInfo());
			preparedStatement.setString(4, employee.getEmployeeImgSrc());
			preparedStatement.setTimestamp(5, employee.getEmployeeJoinDate());
			preparedStatement.setInt(6, employee.getEmployeeId());

			preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			if (connection != null) {
				connection.rollback();
			}
			throw e;
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
			preparedStatement = connection.prepareStatement(SQL_DELETE);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			if (connection != null) {
				connection.rollback();
			}
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	@Override
	public void updatePassword(Employee employee) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE);
			preparedStatement.setString(1, employee.getPassword());
			preparedStatement.setInt(2, employee.getEmployeeId());
			preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			if (connection != null) {
				connection.rollback();
			}
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		
	}

}
