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
@WebListener
public class AppInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		
		// setup jackson json mapper in the ServletContext
		ObjectMapper objectMapper = new ObjectMapper();
		// setup dataSource into ServletContext
		DataSource dataSource = setUpDataSource(sce);
		// setup CrudDAO into ServletContext
		CrudDAO postDao = new PostDao(dataSource);
		CrudDAO courseDao = new CourseDao(dataSource);
		CrudDAO employeeDao = new EmployeeDao(dataSource);
		CrudDAO serviceDao = new ServiceDao(dataSource);
		
		context.setAttribute("objectMapper", objectMapper);
		context.setAttribute("postDao", postDao);
		context.setAttribute("courseDao", courseDao);
		context.setAttribute("employeeDao", employeeDao);
		context.setAttribute("serviceDao", serviceDao);
	}

	

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
	

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		DataSourceUtil.closeDataSource();
	}
}