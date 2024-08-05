package controller.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import dao.CrudDAO;
import dao.impl.PostDao;
import dao.impl.ServiceDao;

@WebServlet("/api/service/*")
public class ServiceAPIController extends AbstractBasicCrudAPIController {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init() throws ServletException {
		super.init();
		CrudDAO serviceDao = (ServiceDao) getServletContext()
				.getAttribute("serviceDao");
		setDao(serviceDao);
	}

}
