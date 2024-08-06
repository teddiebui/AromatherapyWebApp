package model;

/**
 * Represents a tag in the system.
 */
public class Tag {
    
    /**
     * The unique identifier of the tag.
     */
    private int tagId;
    
    /**
     * The name of the tag.
     */
    private String tagName;

    // Getters and Setters
    
    /**
     * Gets the unique identifier of the tag.
     * @return the tagId
     */
    public int getTagId() {
        return tagId;
    }

    /**
     * Sets the unique identifier of the tag.
     * @param newTagId the tagId to set
     */
    public void setTagId(int newTagId) {
        this.tagId = newTagId;
    }

    /**
     * Gets the name of the tag.
     * @return the tagName
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Sets the name of the tag.
     * @param newTagName the tagName to set
     */
    public void setTagName(String newTagName) {
        this.tagName = newTagName;
    }

    /**
     * Returns a string representation of the tag.
     * @return a string representation of the tag
     */
    @Override
    public String toString() {
        return "Tag [tagId=" + tagId + ", tagName=" + tagName + "]";
    }
}
