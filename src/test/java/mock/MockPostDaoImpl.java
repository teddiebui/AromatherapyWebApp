package mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import post.dao.impl.PostDaoImpl;
import post.model.Post;

public class MockPostDaoImpl extends PostDaoImpl{
	
	private Connection connection;

	public MockPostDaoImpl(DataSource theDataSource) {
		super(theDataSource);
		// TODO Auto-generated constructor stub
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void create(Post post) throws SQLException {
		PreparedStatement preparedStatement;
		try {
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
		} catch (Exception exception) {
			throw exception;
		} 
	}
	
	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement(SQL_DELETE);
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
		} catch (Exception exception) {
			throw exception;
		} 
	}
	
	@Override
	public void update(Post post) throws SQLException {
		PreparedStatement preparedStatement;

		try {
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

		} catch (SQLException exception) {
			throw exception;
		} 
	}
}
