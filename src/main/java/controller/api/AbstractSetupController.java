package controller.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.CrudDAO;

public abstract class AbstractSetupController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * Common objectMapper shared across sub-class servlets.
	 */
	private ObjectMapper objectMapper;
	
	/**
	 * Common dao required to be implemented in sub-class servlets.
	 */
	private CrudDAO dao;
	
	/**
	 * Set the Dao Implementation by sub-class servlets.
	 * 
	 * @param daoImplementation .
	 */
	public void setDao(CrudDAO daoImplementation) {
		this.dao = daoImplementation;
	}
	
	/**
	 * Get the Dao implementation.
	 * 
	 * @return the dao implementation.
	 */
	public CrudDAO getDao() {
		return dao;
	}
	
	/**
	 * Get the common Object Mapper.
	 * 
	 * @return objectMapper
	 */
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
	
	/**
	 * Set the common Object Mapper.
	 * @param theObjectMapper
	 */
	public void setObjectMapper(ObjectMapper theObjectMapper) {
		this.objectMapper = theObjectMapper;
	}

	@Override
	public void init() throws ServletException {
		super.init();
		objectMapper = (ObjectMapper) getServletContext()
				.getAttribute("objectMapper");
	}
}
