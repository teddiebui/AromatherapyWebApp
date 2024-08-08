package course.model;

import java.sql.Date;

public class Course {
	/**
	 * Course id, primary key.
	 */
	private int courseId;
	
	/**
	 * Employee id, owner of course, foreign key.
	 */
    private int employeeId;
    /**
     *  Course title.
     */
    private String courseTitle;
    /**
     *  Course title.
     */
    private String courseContent;
    
    /**
     *  Course Image Source.
     */
    private String courseImgSrc;
    
    /**
     *  Course Create Date.
     */
    private Date courseCreateDate;
    
    /**
     * No-args contructor.
     */
	public Course() {
		super();
	}
	
	/**
	 * Get Course ID.
	 * @return int
	 */
	public int getCourseId() {
		return courseId;
	}
	/**
	 * Set Course ID.
	 * @param newCourseId
	 */
	public void setCourseId(int newCourseId) {
		this.courseId = newCourseId;
	}
	/**
	 * Get Employee Id.
	 * @return int
	 */
	public int getEmployeeId() {
		return employeeId;
	}
	
	/**
	 * Set Employee Id.
	 * @param newEmployeeId
	 */
	public void setEmployeeId(int newEmployeeId) {
		this.employeeId = newEmployeeId;
	}
	
	/**
	 * Get Course Title.
	 * @return String
	 */
	public String getCourseTitle() {
		return courseTitle;
	}
	
	/**
	 * Set new Course Title.
	 * @param newCourseTitle
	 */
	public void setCourseTitle(String newCourseTitle) {
		this.courseTitle = newCourseTitle;
	}
	
	/**
	 * Get Course Content.
	 * 
	 * @return string
	 */
	public String getCourseContent() {
		return courseContent;
	}
	
	/**
	 * Set new Course content.
	 * @param newCourseContent
	 */
	public void setCourseContent(String newCourseContent) {
		this.courseContent = newCourseContent;
	}
	/**
	 * Get Course Image Source.
	 * @return string
	 */
	public String getCourseImgSrc() {
		return courseImgSrc;
	}
	/**
	 * Set Course Image Source.
	 * @param newCourseImgSrc
	 */
	public void setCourseImgSrc(String newCourseImgSrc) {
		this.courseImgSrc = newCourseImgSrc;
	}
	
	/**
	 * Get Course create date.
	 * @return sql Date type
	 */
	public Date getCourseCreateDate() {
		return courseCreateDate;
	}
	/**
	 * Set Course create date.
	 * @param newCourseCreateDate
	 */
	public void setCourseCreateDate(Date newCourseCreateDate) {
		this.courseCreateDate = newCourseCreateDate;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + 
				", employeeId=" + employeeId + 
				", courseTitle=" + courseTitle + 
				", courseContent=" + courseContent + 
				", courseImgSrc=" + courseImgSrc+ 
				", courseCreateDate=" + courseCreateDate + 
				"]";
	}


    
    

}
