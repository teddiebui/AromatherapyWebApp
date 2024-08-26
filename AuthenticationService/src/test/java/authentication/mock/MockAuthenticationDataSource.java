package authentication.mock;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class MockAuthenticationDataSource {
	private static HikariDataSource dataSource;

	public static DataSource getDataSource() {
		synchronized (MockAuthenticationDataSource.class) {
			if (dataSource == null) {
				HikariConfig config = new HikariConfig();
				config.setJdbcUrl(
						"jdbc:sqlserver://localhost:1433;databaseName=authenticationService");
				config.setUsername("binh");
				config.setPassword("1234");
				config.setMaximumPoolSize(10);
				config.setConnectionTimeout(3000);
				config.setAutoCommit(false);
				dataSource = new HikariDataSource(config);
				System.out.println("mock data source generated");
			}
		}
		return dataSource;
	}
	
	public static void closeDataSource() {
		dataSource.close();
	}
}
