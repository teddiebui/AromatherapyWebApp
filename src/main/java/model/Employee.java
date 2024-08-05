package model;

public class Employee implements Model {
	/**
	 * Employee ID, primary key.
	 */
    private int employeeId;
    
    /**
     * Employee Name.
     */
    private String employeeName;
    
    /**
     * Employee Title.
     */
    private String employeeTitle;
    /**
     * Employe Info.
     */
    private String employeeInfo;
    /**
     * Employee Image Source.
     */
    private String employeeImgSrc;

    
    /**
     * Getter method of employee ID.
     * @return int
     */
    public int getEmployeeId() {
        return employeeId;
    }
    
    /**
     * Setter method of employee ID.
     * @param newEmployeeId
     */
    public void setEmployeeId(int newEmployeeId) {
        this.employeeId = newEmployeeId;
    }
    
    /**
     * Getter method of employee Name.
     * @return String
     */
    public String getEmployeeName() {
        return employeeName;
    }
    /**
     * Setter method of employee Name.
     * @param newEmployeeName
     */
    public void setEmployeeName(String newEmployeeName) {
        this.employeeName = newEmployeeName;
    }
    /**
     * Getter method of employee Title.
     * @return String
     */
    public String getEmployeeTitle() {
        return employeeTitle;
    }
    /**
     * Setter method of employee Title.
     * @param newEmployeeTitle
     */
    public void setEmployeeTitle(String newEmployeeTitle) {
        this.employeeTitle = newEmployeeTitle;
    }
    /**
     * Getter method of employee Info.
     * @return String
     */
    public String getEmployeeInfo() {
        return employeeInfo;
    }
    /**
     * Setter method of employee Info.
     * @param newEmployeeInfo
     */
    public void setEmployeeInfo(String newEmployeeInfo) {
        this.employeeInfo = newEmployeeInfo;
    }
    
    /**
     * Getter method of employee Img Source.
     * @return String
     */
    public String getEmployeeImgSrc() {
        return employeeImgSrc;
    }
    
    /**
     * Setter method of employee Img Source.
     * @param newEmployeeImgSrc
     */
    public void setEmployeeImgSrc(String newEmployeeImgSrc) {
        this.employeeImgSrc = newEmployeeImgSrc;
    }

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + 
				", employeeName="	+ employeeName + 
				", employeeTitle=" + employeeTitle	+ 
				", employeeInfo=" + employeeInfo + 
				", employeeImgSrc="	+ employeeImgSrc + 
				"]";
	}

    
}
