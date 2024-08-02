package controller.api.post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import controller.api.AbstractBasicCrudAPIController;
import dao.CrudDAO;

/**
 * Servlet implementation class PostController
 */
@WebServlet("/api/post/*")
public class PostAPIController extends AbstractBasicCrudAPIController {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		setDao((CrudDAO) getServletContext().getAttribute("postDao"));
	}
}
