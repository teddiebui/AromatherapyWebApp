package controller.api.course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import controller.api.AbstractBasicCrudAPIController;
import dao.CrudDAO;

/**
 * Servlet implementation class CourseAPIController
 */
@WebServlet("/api/course/*")
public class CourseAPIController extends AbstractBasicCrudAPIController {
	private static final long serialVersionUID = 1L;
       
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		setDao((CrudDAO) getServletContext().getAttribute("courseDao"));
	}
}
