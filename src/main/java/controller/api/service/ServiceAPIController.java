package controller.api.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import controller.api.AbstractBasicCrudAPIController;
import dao.impl.ServiceDao;

/**
 * Servlet implementation class ServiceAPIController
 */
@WebServlet("/api/service/*")
public class ServiceAPIController extends AbstractBasicCrudAPIController {
	private static final long serialVersionUID = 1L;
       
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			super.init();
			setDao((ServiceDao)getServletContext().getAttribute("serviceDao"));
		}

}
