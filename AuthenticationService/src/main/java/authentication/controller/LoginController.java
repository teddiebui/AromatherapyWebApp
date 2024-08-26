package authentication.controller;

import java.io.IOException;
import java.util.HashMap;
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


@WebServlet("/authen/login")
public class LoginController extends HttpServlet {
	public static final String JWT_REFERSH_TOKEN = "jwt-refresh-token";

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
		request.getRequestDispatcher("/WEB-INF/views/backoffice/login.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Account account = getAccount(request);
		Map<String, Object> resultSet = service.authenticate(account);
		int httpCode = (int) resultSet.get("code");
		if (httpCode == 200) {
			resultSet.put("redirect", "/index");
			
			//set access token to "Authorization" header
			String accessToken = (String) resultSet.get("accessToken");
			setAccessTokenToHeader(accessToken, response);
			
			//set refresh token to httpOnly sameSite cookie
			String refreshToken = (String) resultSet.get("refreshToken");
			setRefreshTokenToCookie(refreshToken, request, response);
		}
		
		writeToJson(resultSet, response);

	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		writeToJson(generateMethodNotAllowed(), resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		writeToJson(generateMethodNotAllowed(), resp);
	}





	private void setRefreshTokenToCookie(String refreshToken,
			HttpServletRequest request,
			HttpServletResponse response) {
		String headerValue = String.format("%s=%s; Path=%s; HttpOnly; Max-Age=%d; SameSite=Strict", 
				JWT_REFERSH_TOKEN, 
				refreshToken,
				request.getContextPath() + "/authen",
				12*60*60);
		response.addHeader("Set-Cookie", headerValue);
		
	}

	private void setAccessTokenToHeader(String accessToken,
			HttpServletResponse response) {
		response.setHeader("Authorization", String.format("Bearer %s", accessToken));
	}


	private Account getAccount(HttpServletRequest request) throws StreamReadException, DatabindException, IOException {
		return objectMapper.readValue(request.getInputStream(), Account.class);
	}
	
	private Map<String, Object> generateMethodNotAllowed() {
		Map<String, Object> resultSet = new HashMap<>();
		resultSet.put("code", 405);
		resultSet.put("message", "Method not allowed");
		return resultSet;
	}

	private void writeToJson(Map<String, Object> resultSet,
			HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
		response.setStatus((int) resultSet.get("code"));
		objectMapper.writeValue(response.getOutputStream(), resultSet);
		
	}
}
