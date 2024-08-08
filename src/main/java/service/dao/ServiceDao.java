package service.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.model.Service;

public interface ServiceDao {
	
	/**
	 * SQL statement to get all services.
	 */
	public static final String SQL_GET_SERVICE_ALL = 
			"SELECT * FROM [Service]";
	
	/**
	 * SQL statement to get service by id.
	 */
	public static final String SQL_GET_SERVICE = 
			"SELECT * FROM [Service] WHERE [service_id]=?";
	
	/**
	 * SQL statement to get service by id.
	 */
	public static final String SQL_CREATE = ""
			+ "INSERT INTO [Service] ("
			+ "[employee_id], "
			+ "[service_title], "
			+ "[service_info], "
			+ "[service_img_src], "
			+ "[service_price]) "
			+ "VALUES (?, ?, ?, ?, ?)";
	
	/**
	 * SQL statement to get service by id.
	 */
	public static final String SQL_DELETE = 
			"DELETE FROM [Service] WHERE [service_id] = ?";
			
	
	/**
	 * SQL statement to get service by id.
	 */
	public static final String SQL_UPDATE = ""
			+ "UPDATE [Service] SET "
			+ "[service_title] = ?, "
			+ "[service_info] = ?, "
			+ "[service_img_src] = ?, "
			+ "[service_price] = ? "
			+ "WHERE [service_id] = ?";
	
	
	
	int create(Service service) throws SQLException;
	
	void update(Service service) throws SQLException;
	
	void delete(int id) throws SQLException;

	default List<Service> getAll() throws SQLException{
		return new ArrayList<>();
	};
	
	default Service findById(int id) throws SQLException {
		return null;
	};

}
