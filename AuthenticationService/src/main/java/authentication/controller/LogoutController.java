package authentication.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import authentication.service.AuthenticationService;
import authentication.util.JWTUtil;

@WebServlet("/authen/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ObjectMapper objectMapper;
	
	private AuthenticationService service;

	@Override
	public void init() throws ServletException {
		super.init();
		objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
		service = (AuthenticationService) getServletContext().getAttribute("authenticationService");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = getTokenFromCookie(request);
		if (token == null || token.isEmpty()) {
			writeToJson(generateNotYetAuthenticated(request), response);
			return;
		}
		
		DecodedJWT decodedToken = JWTUtil.getInstance().verifyRefreshToken(token);
		String username = decodedToken.getSubject();
		Map<String, Object> resultSet = service.logout(username);
		
		if ((boolean) resultSet.get("result")) {
			resultSet.put("redirect", "/index");
			//clear token from header and cookie
			clearTokenFromHeader(response);
			clearTokenFromCookie(request, response);
		}
		
		writeToJson(resultSet, response);
	}
	private void clearTokenFromCookie(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie: cookies) {
			if (cookie.getName().equalsIgnoreCase(LoginController.JWT_REFERSH_TOKEN)) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				break;
			}
		}
	}
	private void clearTokenFromHeader(HttpServletResponse response) {
		response.setHeader("Authorization", null);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		writeToJson(generateMethodNotAllowed(), response);
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
	
	private Map<String, Object> generateMethodNotAllowed() {
		Map<String, Object> resultSet = new HashMap<>();
		resultSet.put("code", 405);
		resultSet.put("message", "Method not allowed.");
		return resultSet;
	}

	private void writeToJson(Map<String, Object> resultSet,
			HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
		response.setStatus((int) resultSet.get("code"));
		objectMapper.writeValue(response.getOutputStream(), resultSet);
		
	}
	
	private String getTokenFromCookie(HttpServletRequest httpRequest) {
		Cookie[] cookies = httpRequest.getCookies();
		if (cookies != null ) {
			for (Cookie cookie: cookies) {
				System.out.println(String.format("Cookie %s=%s", cookie.getName(), cookie.getValue()));
				if (cookie.getName().equals(LoginController.JWT_REFERSH_TOKEN)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}
	
	private Map<String, Object> generateNotYetAuthenticated(HttpServletRequest req) {
		Map<String, Object> resultSet = new HashMap<>();
		resultSet.put("result", false);
		resultSet.put("redirect", "/index");
		resultSet.put("code", HttpServletResponse.SC_UNAUTHORIZED);
		resultSet.put("message", "Not yet authenticated");
		return resultSet;
	}

}
