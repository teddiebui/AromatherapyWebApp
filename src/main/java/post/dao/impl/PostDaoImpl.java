package post.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import post.dao.PostDao;
import post.model.Post;

public class PostDaoImpl implements PostDao {
	/**
	 * A common data source shared across DAO.
	 */
	private final DataSource dataSource;	

	/**
	 * Constructor with injected data source.
	 * 
	 * @param theDataSource
	 */
	public PostDaoImpl(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}
	
	@Override
	public List<Post> getAll() {
		List<Post> postList = new ArrayList<Post>();
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		Post post;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(
					SQL_GET_ALL);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				post = constructModel(resultSet);
				postList.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		}

		return postList;
	}

	@Override
	public Post findById(int id) throws SQLException {
		Post post;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(
					SQL_GET_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				post = constructModel(resultSet);

				return post;
			}

		} catch (SQLException exception) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException exception2) {
					exception.printStackTrace();
				}
			}
			
			throw exception;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		}
		return null;
	}

	private Post constructModel(ResultSet resultSet) throws SQLException {
		Post post;
		post = new Post();
		try {
			post.setPostId(
					resultSet.getInt("post_id"));
			post.setEmployeeId(
					resultSet.getInt("employee_id"));
			post.setPostStatus(
					resultSet.getInt("post_status"));
			post.setPostLastUpdateEmployee(
					resultSet.getInt("post_last_update_employee"));
			post.setPostLastUpdateTime(
					resultSet.getTimestamp("post_last_update_time"));
			post.setPostTitle(
					resultSet.getString("post_title"));
			post.setPostContent(
					resultSet.getString("post_content"));
			post.setPostSlug(
					resultSet.getString("post_slug"));
			post.setPostIntroImgSrc(
					resultSet.getString("post_intro_img_src"));
			post.setPostExcerpt(
					resultSet.getString("post_excerpt"));
			post.setPostExcerptImgSrc(
					resultSet.getString("post_excerpt_img_src"));
			post.setPostCreateTime(
					resultSet.getTimestamp("post_create_time"));
		} catch (SQLException exception) {
			throw exception;
		}
		return post;
	}

	@Override
	public void create(Post post) throws SQLException{
		
		Connection connection = null;
		PreparedStatement preparedStatement;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(
					SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, 
					post.getEmployeeId());
			preparedStatement.setInt(2, 
					post.getPostStatus());
			preparedStatement.setInt(3, 
					post.getPostLastUpdateEmployee());
			preparedStatement.setString(4, 
					post.getPostTitle());
			preparedStatement.setString(5, 
					post.getPostContent());
			preparedStatement.setString(6,
					post.getPostSlug());
			preparedStatement.setString(7, 
					post.getPostIntroImgSrc());
			preparedStatement.setString(8, 
					post.getPostExcerpt());
			preparedStatement.setString(9, 
					post.getPostExcerptImgSrc());
			
			
			preparedStatement.executeUpdate();

            connection.commit();
			
		} catch (Exception exception) {
			if (connection != null) {
				try {
					connection.rollback();
					
				} catch (SQLException exception2) {
					exception.printStackTrace();
				}
			}

			throw exception;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void update(Post post) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_UPDATE);
			preparedStatement.setInt(
					1, post.getPostStatus());
			preparedStatement.setInt(
					2, post.getPostLastUpdateEmployee());
			preparedStatement.setTimestamp(
					3, post.getPostLastUpdateTime());
			preparedStatement.setString(
					4, post.getPostTitle());
			preparedStatement.setString(
					5, post.getPostContent());
			preparedStatement.setString(
					6, post.getPostSlug());
			preparedStatement.setString(
					7, post.getPostIntroImgSrc());
			preparedStatement.setString(
					8, post.getPostExcerpt());
			preparedStatement.setString(
					9, post.getPostExcerptImgSrc());
			preparedStatement.setInt(
					10, post.getPostId());
			
			preparedStatement.executeUpdate();
			connection.commit();
			
		} catch (SQLException exception) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException exception2) {
					exception.printStackTrace();
				}
			}
			
			throw exception;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void delete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE);
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			connection.commit();
			
		} catch (SQLException exception) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException exception2) {
					exception.printStackTrace();
				}
			}
			throw exception;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}
		}
	}

}
