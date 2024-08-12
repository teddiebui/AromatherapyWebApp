package authentication.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
				request.getRequestURI());
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Account account = getAccount(request);
		Map<String, Object> resultSet = service.authenticate(account);
		writeJsonToClient(resultSet, response);

	}

	private void writeJsonToClient(Map<String, Object> resultSet,
			HttpServletResponse response)
			throws StreamWriteException, DatabindException, IOException {
		objectMapper.writeValue(response.getOutputStream(), resultSet);

	}

	private Account getAccount(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		return account;
	}

}
