package post.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import post.dao.impl.PostDaoImpl;
import post.model.Post;
import post.service.PostService;

public class PostServiceImpl implements PostService {

	private final PostDaoImpl dao;

	/**
	 * 
	 * @param newDao
	 */
	public PostServiceImpl(PostDaoImpl postDao) {
		this.dao = postDao;
	}

	@Override
	public Map<String, Object> create(Post post) {
		Map<String, Object> resultSet = generateResultSet();
		Map<String, Object> validateErrors = null;
		boolean result;
		String message;
		
		try {
			validateErrors = validate(post);
			if (validateErrors != null && validateErrors.isEmpty()) {
				dao.create(post);
				result = true;
				message = CREATE_SUCCESS;
			} else {
				result = false;
				message = CREATE_FAIL_VALIDATE;
			}
		} catch (Exception exception) {
			result = false;
			message = ERROR_UNEXPECTED;
			exception.printStackTrace();
		}
		
		resultSet.put("result", result);
		resultSet.put("data", post);
		resultSet.put("message", message);
		
		return resultSet;
	}

	@Override
	public Map<String, Object> update(Post post) {
		Map<String, Object> resultSet = new HashMap<>();
		Map<String, Object> validateErrors = null;
		boolean updateResult;
		String message;
		boolean isPostExisted = isExisted(post);
		
		if (post == null || !isPostExisted) {
			updateResult = false;
			message = UPDATE_FAIL_ID_NOT_EXIST;
			
		} else {
			validateErrors = validate(post);
			if (validateErrors != null && validateErrors.isEmpty()) {
				try {
					this.dao.update(post);
					updateResult = true;
					message = UPDATE_SUCCESS;
				} catch (Exception exception) {
					updateResult = false;
					message = ERROR_UNEXPECTED;
					exception.printStackTrace();
				}
			} else {
				updateResult = false;
				message = UPDATE_FAIL_VALIDATE;
			}
		}
		
		resultSet.put("result", updateResult);
		resultSet.put("data", post);
		resultSet.put("validateErrors", validateErrors);
		resultSet.put("message", message);
		
		return resultSet;
	}

	@Override
	public Map<String, Object> delete(int id) {
		Map<String, Object> resultSet = generateResultSet();
		String message;
		boolean deleteResult;
		boolean isExisted = isExisted(id);
		
		if (isExisted) {
			try {
				dao.delete(id);
				message = DELETE_SUCCESS;
				deleteResult = true;
			} catch (Exception exception) {
				exception.printStackTrace();
				message = ERROR_UNEXPECTED;
				deleteResult = false;
			}
			
		} else {
			message = DELETE_FAIL_ID_NOT_EXIST;
			deleteResult = false;
		}
		
		resultSet.put("result", deleteResult);
		resultSet.put("message", message);
		
		return resultSet;

	}

	@Override
	public Map<String, Object> getAll() {
		Map<String, Object> resultSet = generateResultSet();
		List<? super Post> data;
		boolean result;
		String message;
		try {
			data = dao.getAll();
			result = true;
			message = GET_ALL_SUCCESS;
		} catch (Exception exception) {
			exception.printStackTrace();
			data = new ArrayList<>();
			result = false;
			message = ERROR_UNEXPECTED;
		}
		resultSet.put("result", result);
		resultSet.put("message", message);
		resultSet.put("data", data);
		
		return resultSet;
	}

	@Override
	public Map<String, Object> validate(Post post) {
		Map<String, Object> result = new HashMap<>();
		
		return result;
	}

	@Override
	public Map<String, Object> findById(int id) {
		Map<String, Object> resultSet = generateResultSet();
		boolean result;
		String message;
		Post data = null;
		try {
			data = dao.findById(id);
			if (data == null) {
				result = false;
				message = GET_ID_FAIL_NOT_EXIST;
			} else {
				result = true;
				message = GET_ID_SUCCESS;
			}
		} catch (Exception exception) {
			result = false;
			message = ERROR_UNEXPECTED;
			exception.printStackTrace();
		}
		resultSet.put("result", result);
		resultSet.put("message", message);
		resultSet.put("data", data);
		
		return resultSet;
	}

	/**
	 * Check if post is existed.
	 * 
	 * @param id int
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isExisted(int id){
		try {
			return id != 0 && dao.findById(id) != null;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	/**
	 * Check if post is existed.
	 * 
	 * @param post object
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isExisted(Post post) {
		try {
			return post != null && isExisted(post.getPostId());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}
	
	public Map<String, Object> generateResultSet() {
		return new HashMap<>();
	}

}
