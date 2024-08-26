package authentication.model;

public class Permission {
	
	private String permissionName;
	
	private PermissionGroup permissionGroup;
	
	public Permission() {}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public PermissionGroup getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(PermissionGroup permissionGroup) {
		this.permissionGroup = permissionGroup;
	}

	@Override
	public String toString() {
		return "Permission [permissionName=" + permissionName
				+ ", permissionGroup=" + permissionGroup + "]";
	}


	
	
	
}
