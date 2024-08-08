package service.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.model.Service;
import service.service.impl.ServiceServiceImpl;

@WebServlet("/api/service/*")
public class ServiceAPIController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServiceServiceImpl serviceService;
	private ObjectMapper objectMapper;

	@Override
	public void init() throws ServletException {
		super.init();
		serviceService = (ServiceServiceImpl) getServletContext()
				.getAttribute("serviceService");
		objectMapper = (ObjectMapper) getServletContext()
				.getAttribute("objectMapper");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getPathInfo();
		String method = path.substring(1);

		switch (method) {
		case "get":
			processGet(req, resp);
			break;
		case "create":
			processCreate(req, resp);
			break;
		case "update":
			processUpdate(req, resp);
			break;
		case "delete":
			processDelete(req, resp);
			break;
		default:
			resp.sendError(404, req.getRequestURI());
			break;
		}
	}

	private void processDelete(HttpServletRequest req,
			HttpServletResponse resp) {
		Map<String, Object> resultSet;
		String idParameter = req.getParameter("id");
		int id = idParameter == null ? 0 : Integer.parseInt(idParameter);
		Service service = null;
		service = mapRequestParams(service, req);
		resultSet = serviceService.delete(id);
		writeToJson(resultSet, resp);
	}

	private void processUpdate(HttpServletRequest req,
			HttpServletResponse resp) {
		Map<String, Object> resultSet;
		Service service = null;
		try {
			service = mapRequestParams(service, req);
			resultSet = serviceService.update(service);
		} catch (Exception exception) {
			resultSet = new HashMap<>();
			exception.printStackTrace();
		}

		writeToJson(resultSet, resp);

	}

	private void processCreate(HttpServletRequest req,
			HttpServletResponse resp) {
		Map<String, Object> resultSet;
		Service service = null;
		service = mapRequestParams(service, req);
		resultSet = serviceService.create(service);
		writeToJson(resultSet, resp);

	}

	private void processGet(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> resultSet;
		String idParameter = req.getParameter("id");

		if (idParameter == null || idParameter.isEmpty()) {
			resultSet = serviceService.getAll();
		} else {
			int id = Integer.parseInt(idParameter);
			resultSet = serviceService.findById(id);
		}

		writeToJson(resultSet, resp);

	}

	private Service mapRequestParams(Service service, HttpServletRequest req) {
		if (service == null) {
			service = new Service();
		}

		String serviceId = req.getParameter("serviceId");
		String employeeId = req.getParameter("employeeId");
		String serviceTitle = req.getParameter("serviceTitle");
		String serviceInfo = req.getParameter("serviceInfo");
		String serviceImgSrc = req.getParameter("serviceImgSrc");
		String servicePrice = req.getParameter("servicePrice");
		String serviceCreateTime = req.getParameter("serviceCreateTime");

		service.setServiceId(serviceId == null || serviceId.isEmpty() ? 0
				: Integer.parseInt(serviceId));
		service.setEmployeeId(employeeId == null || employeeId.isEmpty() ? 0
				: Integer.parseInt(employeeId));
		service.setServicePrice(
				servicePrice == null || servicePrice.isEmpty() ? 0
						: Float.parseFloat(servicePrice));
		service.setServiceTitle(serviceTitle);
		service.setServiceInfo(serviceInfo);
		service.setServiceImgSrc(serviceImgSrc);
		service.setServiceCreateTime(
				serviceCreateTime == null || serviceCreateTime.isEmpty()
						? new Date(0)
						: new Date(Long.parseLong(serviceCreateTime)));
		return service;
	}

	private void writeToJson(Map<String, Object> resultSet,
			HttpServletResponse resp) {
		try {
			objectMapper.writeValue(resp.getOutputStream(), resultSet);
		} catch (Exception exception) {
			exception.printStackTrace();
			try {
				resp.sendError(500);
			} catch (IOException exception1) {
				exception1.printStackTrace();
			}
		}

	}

}
