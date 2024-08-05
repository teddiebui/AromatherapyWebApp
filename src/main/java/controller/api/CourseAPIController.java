package controller.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import dao.CrudDAO;
import dao.impl.CourseDao;


@WebServlet("/api/course/*")
public class CourseAPIController extends AbstractBasicCrudAPIController {
	private static final long serialVersionUID = 1L;
       
	
	@Override
	public void init() throws ServletException {
		super.init();
		CrudDAO courseDao = (CourseDao) getServletContext()
				.getAttribute("courseDao");
		setDao(courseDao);
	}
}
