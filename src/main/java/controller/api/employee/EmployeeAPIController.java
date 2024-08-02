package controller.api.employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import controller.api.AbstractBasicCrudAPIController;
import dao.impl.EmployeeDao;

/**
 * Servlet implementation class EmployeeAPIController
 */
@WebServlet("/api/employee/*")
public class EmployeeAPIController extends AbstractBasicCrudAPIController {

	private static final long serialVersionUID = 1L;

	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		setDao((EmployeeDao) getServletContext().getAttribute("employeeDao"));
	}
}
