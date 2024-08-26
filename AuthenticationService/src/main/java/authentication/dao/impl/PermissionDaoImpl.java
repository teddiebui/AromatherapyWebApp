package authentication.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import authentication.dao.PermissionDAO;
import authentication.model.Permission;
import authentication.model.PermissionGroup;
import authentication.model.Role;

public class PermissionDaoImpl implements PermissionDAO {
	

	private DataSource dataSource;
	
	public PermissionDaoImpl() {}
	
	public PermissionDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	@Override
	public List<Permission> getByRole(String role_name) throws SQLException {
		List<Permission> permissions = new ArrayList<>();
		Permission permission;
		PermissionGroup permissionGroup;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
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
			if (connection != null) {
				connection.rollback();
			}
			throw exception;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return permissions;
	}

	@Override
	public List<Permission> getByRole(Role role) throws SQLException {
		// TODO Auto-generated method stub
		return getByRole(role.getRoleName());
	}
	

}
