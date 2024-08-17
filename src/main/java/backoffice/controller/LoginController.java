package backoffice.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import authentication.model.Account;
import authentication.service.AuthenticationService;
import authentication.util.JWTUtil;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/backoffice/login")
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
		request.getRequestDispatcher("/WEB-INF/views/backoffice/login.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Account account = getAccount(request);
		
		Map<String, Object> resultSet = service.authenticate(account);

		boolean loginResult = (boolean) resultSet.get("result");

		if (loginResult) {
			account = (Account) resultSet.get("retrievedAccount");
			String refreshToken = resultSet.containsKey("refreshToken")
					? (String) resultSet.get("refreshToken")
					: null;
			if (loginResult && refreshToken != null || refreshToken.isEmpty()) {
				setRefreshTokenCookie(refreshToken, response);
			}
			resultSet.remove("refreshToken");

			// generate access token based on account + refresh token
			String accessToken = JWTUtil.getInstance().generateAccessToken(
					account.getUsername(), account.getRole());
			
			resultSet.put("token", accessToken);
		}
		writeJsonToClient(resultSet, response);

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
				req.getRequestURI());
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
				req.getRequestURI());
	}

	private void writeJsonToClient(Map<String, Object> resultSet,
			HttpServletResponse response)
			throws StreamWriteException, DatabindException, IOException {
		objectMapper.writeValue(response.getOutputStream(), resultSet);

	}

	private Account getAccount(HttpServletRequest request)
			throws StreamReadException, DatabindException, IOException {
		return objectMapper.readValue(request.getInputStream(), Account.class);
	}

	private void setRefreshTokenCookie(String refreshToken, HttpServletResponse response) {

		Cookie cookie = new Cookie("refreshToken", refreshToken);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(12*60*60); // 12 hours
		cookie.setPath("/back-office/login");
		cookie.setPath("/back-office/logout");
		cookie.setPath("/refreshToken");
		response.addCookie(cookie);

		// Manually set the SameSite attribute by adding it to the response
		// header
		response.addHeader("Set-Cookie",
				cookie.getName() + "=" + cookie.getValue() + "; Max-Age="
						+ cookie.getMaxAge() + "; Path=" + cookie.getPath()
						+ "; HttpOnly; Secure; SameSite=Strict");

	}

}
