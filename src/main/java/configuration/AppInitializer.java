package configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import course.dao.impl.CourseDaoImpl;
import course.service.impl.CourseServiceImpl;
import dao.CrudDAO;
import employee.dao.impl.EmployeeDaoImpl;
import employee.service.impl.EmployeeServiceImpl;
import post.dao.impl.PostDaoImpl;
import post.service.impl.PostServiceImpl;
import service.dao.impl.ServiceDaoImpl;
import service.service.impl.ServiceServiceImpl;
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
	private PostDaoImpl postDao;

	/**
	 * A singleton scoped CourseDao shared across servlets.
	 */
	private CourseDaoImpl courseDao;

	/**
	 * A singleton scoped EmployeeDao shared across servlets.
	 */
	private EmployeeDaoImpl employeeDao;

	/**
	 * A singleton scoped ServiceDao shared across servlets.
	 */
	private ServiceDaoImpl serviceDao;

	/**
	 * A singleton scoped PostService shared across servlets.
	 */
	private PostServiceImpl postService;
	/**
	 * A singleton scoped CourseService shared across servlets.
	 */
	private CourseServiceImpl courseService;

	/**
	 * A singleton scoped EmployeeService shared across servlets.
	 */
	private EmployeeServiceImpl employeeService;
	/**
	 * A singleton scoped EmployeeService shared across servlets.
	 */
	private ServiceServiceImpl serviceService;

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
		// setup CrudDAO into ServletContext
		postDao = new PostDaoImpl(dataSource);
		postService = new PostServiceImpl(postDao);

		courseDao = new CourseDaoImpl(dataSource);
		courseService = new CourseServiceImpl(courseDao);

		employeeDao = new EmployeeDaoImpl(dataSource);
		employeeService = new EmployeeServiceImpl(employeeDao);
		
		serviceDao = new ServiceDaoImpl(dataSource);
		serviceService = new ServiceServiceImpl(serviceDao);

		context.setAttribute("objectMapper", objectMapper);
		context.setAttribute("postService", postService);
		context.setAttribute("courseService", courseService);
		context.setAttribute("employeeService", employeeService);
		context.setAttribute("serviceService", serviceService);

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
