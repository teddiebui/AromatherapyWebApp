package post.service;

import java.util.Map;

import post.model.Post;


public interface PostService {
	
	public static final String CREATE_SUCCESS = "Create post success.";
	public static final String CREATE_FAIL_VALIDATE = "Failed due to validation errors.";
	public static final String UPDATE_SUCCESS = "Update post success.";
	public static final String UPDATE_FAIL_ID_NOT_EXIST = "Post is null or selected post doesn't exist.";
	public static final String UPDATE_FAIL_VALIDATE = CREATE_FAIL_VALIDATE;
	public static final String DELETE_SUCCESS = "Deleted success post.";
	public static final String DELETE_FAIL_ID_NOT_EXIST = "Post doesn't exist.";
	public static final String GET_ID_SUCCESS = "Get by id success.";
	public static final String GET_ID_FAIL_NOT_EXIST = "Failed, post id not found.";
	public static final String ERROR_UNEXPECTED = "Unexpected error, please check with admin.";
	public static final String GET_ALL_SUCCESS = "Get all success.";

    /**
     * Creates a new Post.
     *
     * @param post the Post entity to be created
     * @return int
     */
	Map<String, Object> create(Post post);

    /**
     * Updates an existing Post.
     *
     * @param post the Post entity to be updated
     */
    Map<String, Object> update(Post post);

    /**
     * Deletes a Post by its ID.
     *
     * @param id the ID of the Post to be deleted
     */
    Map<String, Object> delete(int id);

    /**
     * Retrieves all Posts.
     *
     * @return a list of all Post entities
     */
    Map<String, Object> getAll();

    /**
     * Finds a Post by its ID.
     *
     * @param id of the Post
     * @return the Post entity with the specified ID, or null if not found
     */
    Map<String, Object> findById(int id);

    /**
     * Validates a Post entity.
     *
     * @param post the Post entity to be validated
     * @return the result of the validation
     */
    Map<String, Object> validate(Post post);
}

