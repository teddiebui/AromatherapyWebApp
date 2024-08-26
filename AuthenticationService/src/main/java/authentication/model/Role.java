package authentication.model;

import java.util.List;

public class Role {
	
	private String roleName;
	
	private List<Permission> permissions;
	
	public Role() {}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [roleName=" + roleName + ", permissions=" + permissions
				+ "]";
	}


	
	

}
