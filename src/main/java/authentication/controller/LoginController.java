package authentication.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import authentication.model.Account;
import authentication.service.AuthenticationService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AuthenticationService service;
	private ObjectMapper objectMapper;

	@Override
	public void init() throws ServletException {
		super.init();
		service = (AuthenticationService) getServletContext()
				.getAttribute("authenticationService");
		objectMapper = (ObjectMapper) getServletContext()
				.getAttribute("objectMapper");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login/login.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Account account = getAccount(request);
		System.out.println(account);
		Map<String, Object> resultSet = service.authenticate(account);
		writeJsonToClient(resultSet, response);

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
				req.getRequestURI());
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
				req.getRequestURI());
	}

	private void writeJsonToClient(Map<String, Object> resultSet,
			HttpServletResponse response)
			throws StreamWriteException, DatabindException, IOException {
		objectMapper.writeValue(response.getOutputStream(), resultSet);

	}

	private Account getAccount(HttpServletRequest request) throws StreamReadException, DatabindException, IOException {
		return objectMapper.readValue(request.getInputStream(), Account.class);
	}

}
