package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.CrudDAO;
import model.Model;
import model.Post;

public class PostDao implements CrudDAO {
	private static final String SQL_GET_POST_ALL = "SELECT * FROM [POST]";

	private static final String SQL_GET_POST = "SELECT * FROM [POST] WHERE [post_id]=?";
	
	private final DataSource dataSource;
	
	public PostDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Model> getAll() throws Exception{
		// TODO Auto-generated method stub
		List<Model> postList = new ArrayList<Model>();
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		Post post;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_POST_ALL);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				post = new Post();
				post.setPostId(resultSet.getInt("post_id"));
                post.setEmployeeId(resultSet.getInt("employee_id"));
                post.setPostStatus(resultSet.getInt("post_status"));
                post.setPostLastUpdateEmployee(resultSet.getInt("post_last_update_employee"));
                post.setPostLastUpdateTime(resultSet.getTimestamp("post_last_update_time"));
                post.setPostTitle(resultSet.getString("post_title"));
                post.setPostContent(resultSet.getString("post_content"));
                post.setPostSlug(resultSet.getString("post_slug"));
                post.setPostIntroImgSrc(resultSet.getString("post_intro_img_src"));
                post.setPostExcerpt(resultSet.getString("post_excerpt"));
                post.setPostExcerptImgSrc(resultSet.getString("post_excerpt_img_src"));
                post.setPostCreateTime(resultSet.getTimestamp("post_create_time"));
                postList.add(post);
			}
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
				
		
		return postList;
	}

	@Override
	public Model findById(int id) throws SQLException {
		Post post;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_POST);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				post = new Post();
				post.setPostId(resultSet.getInt("post_id"));
                post.setEmployeeId(resultSet.getInt("employee_id"));
                post.setPostStatus(resultSet.getInt("post_status"));
                post.setPostLastUpdateEmployee(resultSet.getInt("post_last_update_employee"));
                post.setPostLastUpdateTime(resultSet.getTimestamp("post_last_update_time"));
                post.setPostTitle(resultSet.getString("post_title"));
                post.setPostContent(resultSet.getString("post_content"));
                post.setPostSlug(resultSet.getString("post_slug"));
                post.setPostIntroImgSrc(resultSet.getString("post_intro_img_src"));
                post.setPostExcerpt(resultSet.getString("post_excerpt"));
                post.setPostExcerptImgSrc(resultSet.getString("post_excerpt_img_src"));
                post.setPostCreateTime(resultSet.getTimestamp("post_create_time"));
	            
				return post;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return null;
	}

}
