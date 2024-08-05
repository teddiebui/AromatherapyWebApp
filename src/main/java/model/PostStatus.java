package model;

/**
 * Represents the status of a post in the system.
 */
public class PostStatus {
    
    /**
     * The unique identifier of the post status.
     */
    private int postStatusId;
    
    /**
     * The name of the post status.
     */
    private String postStatusName;

    // Getters and Setters
    
    /**
     * Gets the unique identifier of the post status.
     * @return the postStatusId
     */
    public int getPostStatusId() {
        return postStatusId;
    }

    /**
     * Sets the unique identifier of the post status.
     * @param newPostStatusId the postStatusId to set
     */
    public void setPostStatusId(int newPostStatusId) {
        this.postStatusId = newPostStatusId;
    }

    /**
     * Gets the name of the post status.
     * @return the postStatusName
     */
    public String getPostStatusName() {
        return postStatusName;
    }

    /**
     * Sets the name of the post status.
     * @param newPostStatusName the postStatusName to set
     */
    public void setPostStatusName(String newPostStatusName) {
        this.postStatusName = newPostStatusName;
    }

    /**
     * Returns a string representation of the post status.
     * @return a string representation of the post status
     */
    @Override
    public String toString() {
        return "PostStatus [postStatusId=" + postStatusId + ", postStatusName="
                + postStatusName + "]";
    }
}
