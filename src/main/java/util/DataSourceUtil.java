package util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceUtil {
	
	/**
	 * Data Source.
	 */
    private static HikariDataSource dataSource;

    static {
        try {
            // Load and register the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(
            		"Failed to load SQL Server JDBC driver", e);
        }
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private DataSourceUtil() {}

    /**
     * Initializes the HikariCP DataSource with the specified configuration.
     *
     * @param jdbcUrl           the JDBC URL of the database
     * @param username          the database username
     * @param password          the database password
     * @param maxPoolSize       the maximum size of the connection pool
     * @param connectionTimeout the maximum time in 
     * milliseconds to wait for a connection from the pool
     * @param autoCommit        whether to use auto-commit mode
     */
    public static void initializeDataSource(
    		String jdbcUrl, 
    		String username, 
    		String password,
    		int maxPoolSize, 
    		int connectionTimeout, 
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

    /**
     * Gets the DataSource.
     *
     * @return the DataSource
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Closes the DataSource.
     */
    public static void closeDataSource() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

    /**
     * Pings the server to check the connection.
     */
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
