package configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import authentication.dao.LoginHistoryDAOImpl;
import authentication.dao.impl.AccountDAOImpl;
import authentication.dao.impl.PermissionDaoImpl;
import authentication.service.AuthenticationService;
import authentication.util.DataSourceUtil;

/**
 * Application initializer to set up various components and configurations
 * during the startup and shutdown of the application.
 */
@WebListener
public class AppInitializer implements ServletContextListener {
	private ServletContext context;
	private ObjectMapper objectMapper;
	private DataSource dataSource;
	private LoginHistoryDAOImpl loginHistoryDao;
	private AccountDAOImpl accountDao;
	private PermissionDaoImpl permissionDao;
	private AuthenticationService authenticationService;

	/**
	 * Initializes the context with necessary attributes and setups.
	 * 
	 * @param sce the ServletContextEvent containing the ServletContext that is
	 *            being initialized
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		context = sce.getServletContext();

		// jackson json mapper in the ServletContext
		objectMapper = new ObjectMapper();
		// setup dataSource into ServletContext
		dataSource = setUpDataSource(sce);
		
		accountDao = new AccountDAOImpl(dataSource);
		loginHistoryDao = new LoginHistoryDAOImpl(dataSource);
		permissionDao = new PermissionDaoImpl(dataSource);
		authenticationService = new AuthenticationService(accountDao, loginHistoryDao);
		authenticationService.setPermissionDao(permissionDao);

		context.setAttribute("objectMapper", objectMapper);
		context.setAttribute("authenticationService", authenticationService);
		

	}

	/**
	 * Sets up the DataSource using parameters from the ServletContext.
	 * 
	 * @param sce the ServletContextEvent containing the ServletContext that is
	 *            being initialized
	 * @return the configured DataSource
	 * @throws IllegalArgumentException if any parameter is invalid
	 */
	private DataSource setUpDataSource(ServletContextEvent sce) {
		String jdbcUrl = sce.getServletContext().getInitParameter("jdbc/url");

		String jdbcUser = sce.getServletContext()
				.getInitParameter("jdbc/username");

		String jdbcPassword = sce.getServletContext()
				.getInitParameter("jdbc/password");

		int maxPoolSize = Integer.parseInt(
				sce.getServletContext().getInitParameter("jdbc/maxPoolSize"));

		int connectionTimeout = Integer.parseInt(sce.getServletContext()
				.getInitParameter("jdbc/connectionTimeout"));

		boolean autoCommit = Boolean.parseBoolean(
				sce.getServletContext().getInitParameter("jdbc/autoCommit"));

		DataSourceUtil.initializeDataSource(jdbcUrl, jdbcUser, jdbcPassword,
				maxPoolSize, connectionTimeout, autoCommit);

		return DataSourceUtil.getDataSource();
	}

	/**
	 * Gets a required initialization parameter from the ServletContext.
	 * 
	 * @param sce the ServletContextEvent containing the ServletContext
	 * @throws IllegalArgumentException if the parameter is missing or empty
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		DataSourceUtil.closeDataSource();
	}
}
