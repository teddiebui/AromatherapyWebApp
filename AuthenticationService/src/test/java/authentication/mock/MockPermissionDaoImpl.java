package authentication.mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import authentication.dao.impl.PermissionDaoImpl;
import authentication.model.Permission;
import authentication.model.PermissionGroup;

public class MockPermissionDaoImpl extends PermissionDaoImpl{
	
	private Connection connection;
	
	public MockPermissionDaoImpl(DataSource dataSource) {
		super(dataSource);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Permission> getByRole(String role_name) throws SQLException {
		List<Permission> permissions = new ArrayList<>();
		Permission permission;
		PermissionGroup permissionGroup;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			preparedStatement = connection.prepareStatement(SQL_GET_BY_ROLE);
			preparedStatement.setString(1, role_name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				permission = new Permission();
				permissionGroup = new PermissionGroup();
				permissionGroup.setPermissionGroupName(
						resultSet.getString("permission_group"));
				permission.setPermissionName(
						resultSet.getString("permission_name"));
				permission.setPermissionGroup(permissionGroup);
				permissions.add(permission);
			}
		} catch (SQLException exception) {
			throw exception;
		} 
		return permissions;
	}
	

}
