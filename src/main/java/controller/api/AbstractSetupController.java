package controller.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.catalina.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.CrudDAO;

public abstract class AbstractSetupController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * Common objectMapper shared across sub-class servlets.
	 */
	private ObjectMapper objectMapper;
	
	/**
	 * Service to be implemented by sub-class servlets.
	 */
	private Service service;
	
	/**
	 * Common dao required to be implemented in sub-class servlets.
	 */
	private CrudDAO dao;
	/**
	 * Old deprecated, to be deleted
	 * @return
	 */
	public CrudDAO getDao() {
		return dao;
	}
	
	/**
	 * Old deprecated, to be deleted
	 * @return
	 */
	public void setDao(CrudDAO dao) {
		this.dao = dao;
	}

	
	
	
	/**
	 * Return the service object injected to Controller
	 * @return service
	 */
	public Service getService() {
		return service;
	}
	/**
	 * Set new service for concrete Controller.
	 * @param service
	 */
	public void setService(Service service) {
		this.service = service;
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
