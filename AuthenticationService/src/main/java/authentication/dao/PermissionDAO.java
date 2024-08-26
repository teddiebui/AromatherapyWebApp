package authentication.dao;

import java.sql.SQLException;
import java.util.List;

import authentication.model.Permission;
import authentication.model.Role;

public interface PermissionDAO {
	
	public static final String SQL_GET_BY_ROLE = 
			"SELECT r.[role_name], r.[permission_name], p.[permission_group] "
			+ "FROM [RolePermission] r "
			+ "RIGHT JOIN [Permission] p ON r.[permission_name] = p.[permission_name] "
			+ "WHERE [role_name] = ? ;";

	
	public List<Permission> getByRole(Role role) throws SQLException;
	
	public List<Permission> getByRole(String role_name) throws SQLException;

}
