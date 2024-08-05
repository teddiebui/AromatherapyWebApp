package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/employees")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Get request to this controller will return
	 * "employees.jsp" content.
	 * @param request object injected by Servlet Container
	 * @param response object injected by Servlet Container
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/employees.jsp")
				.forward(request, response);
	}

	/**
	 * Post request by default will be handled as Get request.
	 * @param request object injected by Servlet Container
	 * @param response object injected by Servlet Container
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		doGet(request, response);
	}

}
