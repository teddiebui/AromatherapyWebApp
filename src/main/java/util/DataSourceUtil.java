package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceUtil {

	private static HikariDataSource dataSource;

	static {
		try {
			// Load and register the SQL Server JDBC driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load SQL Server JDBC driver",
					e);
		}
	}

	private DataSourceUtil() {
		// Private constructor to prevent instantiation
	}

	public static void initializeDataSource(String jdbcUrl, String username,
			String password, int maxPoolSize, int connectionTimeout,
			boolean autoCommit) {
		synchronized (DataSourceUtil.class) {
			if (dataSource == null) {
				HikariConfig config = new HikariConfig();
				config.setJdbcUrl(jdbcUrl);
				config.setUsername(username);
				config.setPassword(password);
				config.setMaximumPoolSize(maxPoolSize);
				config.setConnectionTimeout(connectionTimeout);
				config.setAutoCommit(autoCommit);

				dataSource = new HikariDataSource(config);

				// Ping the server to check the connection
				pingServer();
			}
		}
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static void closeDataSource() {
		if (dataSource != null && !dataSource.isClosed()) {
			dataSource.close();
		}
	}

	public static void pingServer() {
		if (dataSource != null) {
			try (Connection conn = dataSource.getConnection()) {
				if (conn.isValid(2)) {
					System.out.println("Ping to server was successful.");
				} else {
					System.out.println("Ping to server failed.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error pinging server: " + e.getMessage());
			}
		} else {
			System.out.println("DataSource is not initialized.");
		}
	}
}
