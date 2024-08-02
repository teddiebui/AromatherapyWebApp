package model;

public class Employee implements Model {
    private int employeeId;
    private String employeeName;
    private String employeeTitle;
    private String employeeInfo;
    private String employeeImgSrc;

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeTitle() {
        return employeeTitle;
    }

    public void setEmployeeTitle(String employeeTitle) {
        this.employeeTitle = employeeTitle;
    }

    public String getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(String employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public String getEmployeeImgSrc() {
        return employeeImgSrc;
    }

    public void setEmployeeImgSrc(String employeeImgSrc) {
        this.employeeImgSrc = employeeImgSrc;
    }

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", employeeTitle=" + employeeTitle
				+ ", employeeInfo=" + employeeInfo + ", employeeImgSrc="
				+ employeeImgSrc + "]";
	}
}
