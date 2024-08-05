package configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.CrudDAO;
import dao.impl.CourseDao;
import dao.impl.EmployeeDao;
import dao.impl.PostDao;
import dao.impl.ServiceDao;
import util.DataSourceUtil;

/**
 * Application initializer to set up various components and configurations
 * during the startup and shutdown of the application.
 */
@WebListener
public class AppInitializer implements ServletContextListener {
	/**
	 * Contains the context of Servlet Application.
	 */
	private ServletContext context;

	/**
	 * A common Objet Mapper from JSON parser shared across servlets.
	 */
	private ObjectMapper objectMapper;

	/**
	 * A singleton scoped common Data Source shared across servlets.
	 */
	private DataSource dataSource;

	/**
	 * A singleton scoped PostDao shared across servlets.
	 */
	private CrudDAO postDao;

	/**
	 * A singleton scoped CourseDao shared across servlets.
	 */
	private CrudDAO courseDao;

	/**
	 * A singleton scoped EmployeeDao shared across servlets.
	 */
	private CrudDAO employeeDao;

	/**
	 * A singleton scoped ServiceDao shared across servlets.
	 */
	private CrudDAO serviceDao;

	/**
	 * Initializes the context with necessary attributes and setups.
	 * 
	 * @param sce the ServletContextEvent containing the ServletContext 
	 * that is being initialized
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		context = sce.getServletContext();

		// jackson json mapper in the ServletContext
		objectMapper = new ObjectMapper();
		// setup dataSource into ServletContext
		dataSource = setUpDataSource(sce);
		// setup CrudDAO into ServletContext
		postDao = new PostDao(dataSource);
		courseDao = new CourseDao(dataSource);
		employeeDao = new EmployeeDao(dataSource);
		serviceDao = new ServiceDao(dataSource);

		context.setAttribute("objectMapper", objectMapper);
		context.setAttribute("postDao", postDao);
		context.setAttribute("courseDao", courseDao);
		context.setAttribute("employeeDao", employeeDao);
		context.setAttribute("serviceDao", serviceDao);
	}

	/**
	 * Sets up the DataSource using parameters from the ServletContext.
	 * 
	 * @param sce the ServletContextEvent containing the ServletContext 
	 * that is being initialized
	 * @return the configured DataSource
	 * @throws IllegalArgumentException if any parameter is invalid
	 */
	private DataSource setUpDataSource(ServletContextEvent sce) {
		String jdbcUrl = sce.getServletContext()
				.getInitParameter("jdbc/url");
		
		String jdbcUser = sce.getServletContext()
				.getInitParameter("jdbc/username");
		
		String jdbcPassword = sce.getServletContext()
				.getInitParameter("jdbc/password");
		
		int maxPoolSize = Integer.parseInt(
				sce.getServletContext()
				.getInitParameter("jdbc/maxPoolSize"));
		
		int connectionTimeout = Integer.parseInt(sce.getServletContext()
				.getInitParameter("jdbc/connectionTimeout"));
		
		boolean autoCommit = Boolean.parseBoolean(
				sce.getServletContext()
				.getInitParameter("jdbc/autoCommit"));

		DataSourceUtil.initializeDataSource(
				jdbcUrl, 
				jdbcUser, 
				jdbcPassword,
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
