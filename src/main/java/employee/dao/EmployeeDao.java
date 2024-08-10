package employee.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import employee.model.Employee;

public interface EmployeeDao {

	/**
	 * SQL statement to get all employees.
	 */
	public static final String SQL_GET_EMPLOYEE_ALL = "SELECT * FROM [Employee]";

	/**
	 * SQL statement to get employee by id.
	 */
	public static final String SQL_GET_EMPLOYEE = "SELECT * FROM [Employee] WHERE [employee_id]=?";

	/**
	 * SQL statement for creating new record
	 */

	public static final String SQL_CREATE = "INSERT INTO [Employee] ("
			+ "[employee_name], " + "[employee_title], " + "[employee_info], "
			+ "[employee_img_src], " + "[employee_username], " + "[employee_hashed_password] "
			+ ") VALUES (?, ?, ?, ?, ?, ?)";

	/**
	 * SQL statement for updating new record
	 */

	public static final String SQL_UPDATE = "UPDATE [Employee] SET "
			+ "[employee_name] = ?, " + "[employee_title] = ?, "
			+ "[employee_info] = ?, " + "[employee_img_src] = ?, "
			+ "[employee_join_date] = ? " + " WHERE [employee_id] = ?";
	
	/**
	 * SQL statement for updating new record
	 */

	public static final String SQL_UPDATE_PASSWORD = "UPDATE [Employee] SET "
			+ "[employee_hashed_password] = ?, "
			+ " WHERE [employee_id] = ?";

	/**
	 * SQL statement for deleting new record
	 */

	public static final String SQL_DELETE = "DELETE FROM [Employee] WHERE [employee_id] = ?";

	int create(Employee employee) throws SQLException;

	void update(Employee employee) throws SQLException;
	
	void updatePassword(Employee employee) throws SQLException;

	void delete(int id) throws SQLException;

	List<Employee> getAll() throws SQLException;

	Employee findById(int id) throws SQLException;
}
