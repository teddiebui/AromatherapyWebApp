package employee.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import employee.model.Employee;
import employee.service.impl.EmployeeServiceImpl;

@WebServlet("/api/employee/*")
public class EmployeeAPIController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private EmployeeServiceImpl employeeService;
	private ObjectMapper objectMapper;

	/**
	 * Explicitly set EmployeeDao for this servlet.
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		employeeService = (EmployeeServiceImpl) getServletContext()
				.getAttribute("employeeService");

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
			resp.sendError(HttpServletResponse.SC_NOT_FOUND,
					req.getRequestURI());
		}
	}

	private void processDelete(HttpServletRequest req,
			HttpServletResponse resp) {
		String idParameter = req.getParameter("id");
		int id = idParameter == null ? 0 : Integer.parseInt(idParameter);
		Map<String, Object> result = employeeService.delete(id);
		writeToJson(result, resp);

	}

	private void processUpdate(HttpServletRequest req,
			HttpServletResponse resp) {
		Map<String, Object> result;
		Employee employee = null;
		employee = mapRequestParam(employee, req);
		result = employeeService.update(employee);
		writeToJson(result, resp);

	}

	private void processCreate(HttpServletRequest req,
			HttpServletResponse resp) {
		Map<String, Object> result;
		Employee employee = null;
		employee = mapRequestParam(employee, req);
		result = employeeService.create(employee);
		writeToJson(result, resp);

	}

	private void processGet(HttpServletRequest req, HttpServletResponse resp) {
		String idParameter = req.getParameter("id");
		Map<String, Object> result;
		if (idParameter == null || idParameter.isBlank()) {
			result = employeeService.getAll();
		} else {
			int id = idParameter == null ? 0 : Integer.parseInt(idParameter);
			result = employeeService.findById(id);
		}

		writeToJson(result, resp);

	}

	private Employee mapRequestParam(Employee employee,
			HttpServletRequest req) {
		// TODO Auto-generated method stub
		if (employee == null) {
			employee = new Employee();
		}

		String employeeId = req.getParameter("employeeId");
		String employeeName = req.getParameter("employeeName");
		String employeeTitle = req.getParameter("employeeTitle");
		String employeeInfo = req.getParameter("employeeInfo");
		String employeeImgSrc = req.getParameter("employeeImgSrc");
		String employeeJoinDate = req.getParameter("employeeJoinDate");

		employee.setEmployeeId(employeeId == null || employeeId.isEmpty() ? 0
				: Integer.parseInt(employeeId));
		employee.setEmployeeName(employeeName);
		employee.setEmployeeTitle(employeeTitle);
		employee.setEmployeeInfo(employeeInfo);
		employee.setEmployeeImgSrc(employeeImgSrc);
		employee.setEmployeeJoinDate(
				employeeJoinDate == null || employeeJoinDate.isEmpty()
						? new Timestamp(0)
						: new Timestamp(Long.parseLong(employeeJoinDate)));

		return employee;

	}

	private void writeToJson(Map<String, Object> result,
			HttpServletResponse resp) {
		try {
			objectMapper.writeValue(resp.getOutputStream(), result);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
}
