package authentication.model;

public class PermissionGroup {
	
	private String permissionGroupName;
	
	public PermissionGroup() {}

	public String getPermissionGroupName() {
		return permissionGroupName;
	}

	public void setPermissionGroupName(String permissionGroupName) {
		this.permissionGroupName = permissionGroupName;
	}

	@Override
	public String toString() {
		return "PermissionGroup [permissionGroupName=" + permissionGroupName
				+ "]";
	}
	
	
}
