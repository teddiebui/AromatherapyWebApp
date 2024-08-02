package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.CrudDAO;


public abstract class AbstractSetupController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper;
	private CrudDAO dao;
	
	public void setDao(CrudDAO dao) {
		this.dao = dao;
	}
	
	public CrudDAO getDao() {
		return dao;
	}
	
	
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}


	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}


	


	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
	}
}
