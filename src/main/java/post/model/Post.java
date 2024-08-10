package post.model;

import java.sql.Timestamp;


public class Post {
    
    /**
     * The unique identifier of the post.
     */
    private int postId;
    
    /**
     * The identifier of the employee who created the post.
     */
    private int employeeId;
    
    /**
     * The status of the post.
     */
    private int postStatus;
    
    /**
     * The identifier of the employee who last updated the post.
     */
    private Integer postLastUpdateEmployee;
    
    /**
     * The timestamp of the last update to the post.
     */
    private Timestamp postLastUpdateTime;
    
    /**
     * The title of the post.
     */
    private String postTitle;
    
    /**
     * The content of the post.
     */
    private String postContent;
    
    /**
     * The slug (URL-friendly version of the title) of the post.
     */
    private String postSlug;
    
    /**
     * The URL of the introduction image for the post.
     */
    private String postIntroImgSrc;
    
    /**
     * The excerpt (summary) of the post.
     */
    private String postExcerpt;
    
    /**
     * The URL of the excerpt image for the post.
     */
    private String postExcerptImgSrc;
    
    /**
     * The timestamp of when the post was created.
     */
    private Timestamp postCreateTime;

    // Getters and Setters
    
    /**
     * Gets the unique identifier of the post.
     * @return the postId
     */
    public int getPostId() {
        return postId;
    }

    /**
     * Sets the unique identifier of the post.
     * @param newPostId the postId to set
     */
    public void setPostId(int newPostId) {
        this.postId = newPostId;
    }

    /**
     * Gets the identifier of the employee who created the post.
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the identifier of the employee who created the post.
     * @param newEmployeeId the employeeId to set
     */
    public void setEmployeeId(int newEmployeeId) {
        this.employeeId = newEmployeeId;
    }

    /**
     * Gets the status of the post.
     * @return the postStatus
     */
    public int getPostStatus() {
        return postStatus;
    }

    /**
     * Sets the status of the post.
     * @param newPostStatus the postStatus to set
     */
    public void setPostStatus(int newPostStatus) {
        this.postStatus = newPostStatus;
    }

    /**
     * Gets the identifier of the employee who last updated the post.
     * @return the postLastUpdateEmployee
     */
    public Integer getPostLastUpdateEmployee() {
        return postLastUpdateEmployee;
    }

    /**
     * Sets the identifier of the employee who last updated the post.
     * @param newPostLastUpdateEmployee the postLastUpdateEmployee to set
     */
    public void setPostLastUpdateEmployee(Integer newPostLastUpdateEmployee) {
        this.postLastUpdateEmployee = newPostLastUpdateEmployee;
    }

    /**
     * Gets the timestamp of the last update to the post.
     * @return the postLastUpdateTime
     */
    public Timestamp getPostLastUpdateTime() {
        return postLastUpdateTime;
    }

    /**
     * Sets the timestamp of the last update to the post.
     * @param newPostLastUpdateTime the postLastUpdateTime to set
     */
    public void setPostLastUpdateTime(Timestamp newPostLastUpdateTime) {
        this.postLastUpdateTime = newPostLastUpdateTime;
    }

    /**
     * Gets the title of the post.
     * @return the postTitle
     */
    public String getPostTitle() {
        return postTitle;
    }

    /**
     * Sets the title of the post.
     * @param newPostTitle the postTitle to set
     */
    public void setPostTitle(String newPostTitle) {
        this.postTitle = newPostTitle;
    }

    /**
     * Gets the content of the post.
     * @return the postContent
     */
    public String getPostContent() {
        return postContent;
    }

    /**
     * Sets the content of the post.
     * @param newPostContent the postContent to set
     */
    public void setPostContent(String newPostContent) {
        this.postContent = newPostContent;
    }

    /**
     * Gets the slug (URL-friendly version of the title) of the post.
     * @return the postSlug
     */
    public String getPostSlug() {
        return postSlug;
    }

    /**
     * Sets the slug (URL-friendly version of the title) of the post.
     * @param newPostSlug the postSlug to set
     */
    public void setPostSlug(String newPostSlug) {
        this.postSlug = newPostSlug;
    }

    /**
     * Gets the URL of the introduction image for the post.
     * @return the postIntroImgSrc
     */
    public String getPostIntroImgSrc() {
        return postIntroImgSrc;
    }

    /**
     * Sets the URL of the introduction image for the post.
     * @param newPostIntroImgSrc the postIntroImgSrc to set
     */
    public void setPostIntroImgSrc(String newPostIntroImgSrc) {
        this.postIntroImgSrc = newPostIntroImgSrc;
    }

    /**
     * Gets the excerpt (summary) of the post.
     * @return the postExcerpt
     */
    public String getPostExcerpt() {
        return postExcerpt;
    }

    /**
     * Sets the excerpt (summary) of the post.
     * @param newPostExcerpt the postExcerpt to set
     */
    public void setPostExcerpt(String newPostExcerpt) {
        this.postExcerpt = newPostExcerpt;
    }

    /**
     * Gets the URL of the excerpt image for the post.
     * @return the postExcerptImgSrc
     */
    public String getPostExcerptImgSrc() {
        return postExcerptImgSrc;
    }

    /**
     * Sets the URL of the excerpt image for the post.
     * @param newPostExcerptImgSrc the postExcerptImgSrc to set
     */
    public void setPostExcerptImgSrc(String newPostExcerptImgSrc) {
        this.postExcerptImgSrc = newPostExcerptImgSrc;
    }

    /**
     * Gets the timestamp of when the post was created.
     * @return the postCreateTime
     */
    public Timestamp getPostCreateTime() {
        return postCreateTime;
    }

    /**
     * Sets the timestamp of when the post was created.
     * @param newPostCreateTime the postCreateTime to set
     */
    public void setPostCreateTime(Timestamp newPostCreateTime) {
        this.postCreateTime = newPostCreateTime;
    }
    
    /**
     * Returns a string representation of the post.
     * @return a string representation of the post
     */
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
