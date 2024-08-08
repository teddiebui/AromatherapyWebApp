package course.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import course.model.Course;
import course.service.impl.CourseServiceImpl;


@WebServlet("/api/course/*")
public class CourseAPIController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ObjectMapper objectMapper;
	private CourseServiceImpl courseService;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		objectMapper = (ObjectMapper) getServletContext()
				.getAttribute("objectMapper");
		

		courseService = (CourseServiceImpl) getServletContext()
				.getAttribute("courseService");
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
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, req.getRequestURI());
			
		}

	}

	private void processDelete(HttpServletRequest req,
			HttpServletResponse resp) {
		Map<String, Object> result;
		String idParameter = req.getParameter("id");
		int id = 0;
		if (idParameter != null && !idParameter.isEmpty()) {
			id = Integer.parseInt(idParameter);
		}
		
		result = courseService.delete(id);
		writeToJson(result, resp);
		
	}

	private void processUpdate(HttpServletRequest req,
			HttpServletResponse resp) {
		Map<String, Object> result;
		Course course = null;
		course = mapRequestParams(course, req);
		result = courseService.update(course);
		writeToJson(result, resp);
		
	}

	private void processCreate(HttpServletRequest req,
			HttpServletResponse resp) {
		Map<String, Object> result;
		Course course = null;
		course = mapRequestParams(course, req);
		result = courseService.create(course);
		writeToJson(result, resp);
		
	}

	private void processGet(HttpServletRequest req, HttpServletResponse resp) {
		String idParameter = req.getParameter("id");
		Map<String, Object> result;
		if (idParameter == null || idParameter.isEmpty()) {
			result = courseService.getAll();
		} else {
			int id = Integer.parseInt(idParameter);
			result = courseService.findById(id);
		}
		
		writeToJson(result, resp);
		
	}

	private void writeToJson(Map<String, Object> result,
			HttpServletResponse resp) {
		try {
			objectMapper.writeValue(resp.getOutputStream(), result);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	private Course mapRequestParams(Course course, HttpServletRequest req) {
		// TODO Auto-generated method stub
		if (course == null) {
			course = new Course();
		}
		
		String courseId = req.getParameter("courseId");
		String employeeId = req.getParameter("employeeId");
		String courseTitle = req.getParameter("courseTitle");
		String courseContent = req.getParameter("courseContent");
		String courseCreateDate = req.getParameter("courseCreateDate");
		
		course.setCourseId(courseId == null ? 0 : Integer.parseInt(courseId));
		course.setEmployeeId(employeeId == null ? 0 : Integer.parseInt(employeeId));
		course.setCourseTitle(courseTitle);
		course.setCourseContent(courseContent);
		course.setCourseCreateDate(courseId == null ? new Date(0) : new Date(Long.parseLong(courseCreateDate)));
		
		return course;
	}

       
	
}
