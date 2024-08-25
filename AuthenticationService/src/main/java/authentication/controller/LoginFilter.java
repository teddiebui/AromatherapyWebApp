package authentication.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebFilter("/authen/login")
public class LoginFilter extends HttpFilter implements Filter {
       
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ObjectMapper objectMapper;
	
	public void init(FilterConfig fConfig) throws ServletException {
		objectMapper = (ObjectMapper) fConfig.getServletContext().getAttribute("objectMapper");

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String token = getTokenFromHeader(httpRequest);
		if (token == null || token.isEmpty()) {
			token = getTokenFromCookie(httpRequest);
		}

		if (token == null || token.isEmpty()) {
			token = getTokenFromCookie(httpRequest);
			chain.doFilter(request, response);
		} else {
			writeToJson(generateAlreadyAuthenticated(httpRequest), httpResponse);
		}
		
	}

	private Map<String, Object> generateAlreadyAuthenticated(HttpServletRequest req) {
		Map<String, Object> resultSet = new HashMap<>();
		resultSet.put("result", false);
		resultSet.put("redirect", "/index");
		resultSet.put("message", "You have been authenticed, please redirect to index");
		resultSet.put("code", HttpServletResponse.SC_FORBIDDEN);
		return resultSet;
	}

	private void writeToJson(Map<String, Object> resultSet,
			HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
		response.setStatus((int) resultSet.get("code"));
		objectMapper.writeValue(response.getOutputStream(), resultSet);
	}

	private String getTokenFromHeader(HttpServletRequest httpRequest) {
		String header = httpRequest.getHeader("Authorization");
		if (header != null && !header.isEmpty()) {
			return header.substring(7);
		}
		return "";
	}
	
	private String getTokenFromCookie(HttpServletRequest httpRequest) {
		Cookie[] cookies = httpRequest.getCookies();
		if (cookies != null ) {
			for (Cookie cookie: cookies) {
				if (cookie.getName().equals(LoginController.JWT_REFERSH_TOKEN)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}
	
}
