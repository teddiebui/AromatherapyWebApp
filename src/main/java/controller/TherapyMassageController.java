package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/therapy_massage")
public class TherapyMassageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TherapyMassageController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get request to this controller will return
	 * "therapy_massage.jsp" content.
	 * @param request object injected by Servlet Container
	 * @param response object injected by Servlet Container
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		request.getRequestDispatcher(
				"/WEB-INF/views/therapy_massage.jsp")
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
