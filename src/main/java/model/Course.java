package model;

import java.sql.Date;

public class Course implements Model {
	private int courseId;
    private int employeeId;
    private String courseTitle;
    private String courseContent;
    private String courseImgSrc;
    private Date courseCreateDate;
    
	public Course() {
		super();
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseContent() {
		return courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	public String getCourseImgSrc() {
		return courseImgSrc;
	}

	public void setCourseImgSrc(String courseImgSrc) {
		this.courseImgSrc = courseImgSrc;
	}

	public Date getCourseCreateDate() {
		return courseCreateDate;
	}

	public void setCourseCreateDate(Date courseCreateDate) {
		this.courseCreateDate = courseCreateDate;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", employeeId=" + employeeId
				+ ", courseTitle=" + courseTitle + ", courseContent="
				+ courseContent + ", courseImgSrc=" + courseImgSrc
				+ ", courseCreateDate=" + courseCreateDate + "]";
	}
    
    

}
