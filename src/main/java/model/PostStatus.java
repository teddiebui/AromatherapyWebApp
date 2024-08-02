package model;


public class PostStatus {
    private int postStatusId;
    private String postStatusName;

    // Getters and Setters
    public int getPostStatusId() {
        return postStatusId;
    }

    public void setPostStatusId(int postStatusId) {
        this.postStatusId = postStatusId;
    }

    public String getPostStatusName() {
        return postStatusName;
    }

    public void setPostStatusName(String postStatusName) {
        this.postStatusName = postStatusName;
    }

	@Override
	public String toString() {
		return "PostStatus [postStatusId=" + postStatusId + ", postStatusName="
				+ postStatusName + "]";
	}
}

