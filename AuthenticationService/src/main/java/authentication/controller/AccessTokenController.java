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

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import authentication.service.AuthenticationService;


@WebServlet("/authen/refreshToken")
public class AccessTokenController extends HttpServlet {
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
		String refreshToken = getTokenFromCookie(request);
		String accessToken = getTokenFromHeader(request);
		Map<String, Object> resultSet = service.generateAccessToken(refreshToken, accessToken);

		if ((int) resultSet.get("code") == 200) {
			String redirect = (String) request.getParameter("redirect");
			resultSet.put("redirect", redirect);
		}
		
		if ((boolean) resultSet.get("forceLogOut")) {
			clearRefreshTokenFromCookie(request, response);
		}
		writeToJson(resultSet, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		writeToJson(generateNotAllow(), resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		writeToJson(generateNotAllow(), resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		writeToJson(generateNotAllow(), resp);
	}
	
	
	private void writeToJson(Map<String, Object> resultSet,
			HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
		response.setStatus((int) resultSet.get("code"));
		objectMapper.writeValue(response.getOutputStream(), resultSet);
	}
	
	private String getTokenFromCookie(HttpServletRequest httpRequest) {
		Cookie[] cookies = httpRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName()
						.equals(LoginController.JWT_REFERSH_TOKEN)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}
	
	private Map<String, Object> generateNotAllow() {
		Map<String, Object> resultSet = new HashMap<>();
		
		resultSet.put("code", HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		resultSet.put("message", "Not allowed.");
		return resultSet;
	}
	
	private String getTokenFromHeader(HttpServletRequest req) {
		String value = req.getHeader("Authorization");
		String token = null;
		
		if (value != null && !value.isEmpty()) {
			token = value.substring(7); 
		}
		
		return token;
	} 
	
	private void clearRefreshTokenFromCookie(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie invalidateToken = null;
		for (Cookie cookie: request.getCookies()) {
			if (cookie.getName().equalsIgnoreCase("jwt-refresh-token")) {
				invalidateToken = new Cookie(
						cookie.getName(),
						cookie.getValue());
				invalidateToken.setPath(cookie.getPath());
				invalidateToken.setMaxAge(0);
				response.addCookie(invalidateToken);
			}
		}
		
	}


	

}
