package controller.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import dao.impl.PostDao;


@WebServlet("/api/post/*")
public class PostAPIController extends AbstractBasicCrudAPIController {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Explicitly set PostDao for this servlet.
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		PostDao postDao = (PostDao) getServletContext()
				.getAttribute("postDao");
		setDao(postDao);
	}
}
