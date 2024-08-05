package controller.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import dao.impl.EmployeeDao;


@WebServlet("/api/employee/*")
public class EmployeeAPIController extends AbstractBasicCrudAPIController {

	private static final long serialVersionUID = 1L;

	/**
	 * Explicitly set EmployeeDao for this servlet.
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		EmployeeDao employeeDao = (EmployeeDao) getServletContext()
				.getAttribute("employeeDao");
		setDao(employeeDao);
	}
}
