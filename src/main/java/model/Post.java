package model;

import java.sql.Timestamp;

public class Post implements Model {
    

	private int postId;
    private int employeeId;
    private int postStatus;
    private Integer postLastUpdateEmployee;
    private Timestamp postLastUpdateTime;
    private String postTitle;
    private String postContent;
    private String postSlug;
    private String postIntroImgSrc;
    private String postExcerpt;
    private String postExcerptImgSrc;
    private Timestamp postCreateTime;

    // Getters and Setters
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(int postStatus) {
        this.postStatus = postStatus;
    }

    public Integer getPostLastUpdateEmployee() {
        return postLastUpdateEmployee;
    }

    public void setPostLastUpdateEmployee(Integer postLastUpdateEmployee) {
        this.postLastUpdateEmployee = postLastUpdateEmployee;
    }

    public Timestamp getPostLastUpdateTime() {
        return postLastUpdateTime;
    }

    public void setPostLastUpdateTime(Timestamp postLastUpdateTime) {
        this.postLastUpdateTime = postLastUpdateTime;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostSlug() {
        return postSlug;
    }

    public void setPostSlug(String postSlug) {
        this.postSlug = postSlug;
    }

    public String getPostIntroImgSrc() {
        return postIntroImgSrc;
    }

    public void setPostIntroImgSrc(String postIntroImgSrc) {
        this.postIntroImgSrc = postIntroImgSrc;
    }

    public String getPostExcerpt() {
        return postExcerpt;
    }

    public void setPostExcerpt(String postExcerpt) {
        this.postExcerpt = postExcerpt;
    }

    public String getPostExcerptImgSrc() {
        return postExcerptImgSrc;
    }

    public void setPostExcerptImgSrc(String postExcerptImgSrc) {
        this.postExcerptImgSrc = postExcerptImgSrc;
    }

    public Timestamp getPostCreateTime() {
        return postCreateTime;
    }

    public void setPostCreateTime(Timestamp postCreateTime) {
        this.postCreateTime = postCreateTime;
    }
    
    @Override
	public String toString() {
		return "Post [postId=" + postId + ", employeeId=" + employeeId
				+ ", postStatus=" + postStatus + ", postLastUpdateEmployee="
				+ postLastUpdateEmployee + ", postLastUpdateTime="
				+ postLastUpdateTime + ", postTitle=" + postTitle
				+ ", postContent=" + postContent + ", postSlug=" + postSlug
				+ ", postIntroImgSrc=" + postIntroImgSrc + ", postExcerpt="
				+ postExcerpt + ", postExcerptImgSrc=" + postExcerptImgSrc
				+ ", postCreateTime=" + postCreateTime + "]";
	}
}
