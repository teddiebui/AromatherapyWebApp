package controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

public abstract class AbstractBasicCrudAPIController
		extends AbstractSetupController {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Do process for each specific method "get", "delete"
	 * "update", will return 404 for any other methods or
	 * invalid strings.
	 * 
	 * @param req object injected by Servlet Container
	 * @param resp object injected by Servlet Container
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String requestPath = req.getRequestURI();
		String[] splittor = requestPath.split("/");
		String operation = splittor[splittor.length - 1];
		switch (operation) {

		case "get":
			String idParameter = req.getParameter("id");
			int modelId = idParameter == null ? 0
					: Integer.parseInt(idParameter);
			
			if (modelId > 0) {
				processGetById(modelId, req, resp);
			} else {
				processGet(req, resp);
			}
			break;
			
		default:
			resp.sendError(
					HttpServletResponse.SC_NOT_FOUND,
					requestPath);
		}
	}
	
	/**
	 * Post request by default will be handled as Get request.
	 * @param request object injected by Servlet Container
	 * @param response object injected by Servlet Container
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {

		doGet(request, response);
	}
	
	/**
	 * Find  model by id and returns result as JSON.
	 * 
	 * @param id of the requested model
	 * @param req object injected by Servlet Container
	 * @param resp object injected by Servlet Container
	 */
	protected void processGetById(int id, HttpServletRequest req,
			HttpServletResponse resp) {
		Model model = null;
		HashMap<String, Object> result = new HashMap<>();
		try {
			model = getDao().findById(id);
			if (model == null) {
				result.put("result", false);
				result.put("message", "Model not found");
				result.put("model", new Object[] {});
				writeJsonResponse(resp, result);
			} else {
				result.put("result", true);
				result.put("message", "Success");
				result.put("model", model);
				writeJsonResponse(resp, result);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets all model and returns result as JSON.
	 * 
	 * @param req object injected by Servlet Container
	 * @param resp object injected by Servlet Container
	 */
	
	protected void processGet(HttpServletRequest req,
			HttpServletResponse resp) {
		List<? extends Model> list;
		
		try {
			list = getDao().getAll();
			writeJsonResponse(resp, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Parses Map typed result set as JSON and writes to client.
	 * 
	 * @param result set
	 * @param resp object injected by Servlet Container
	 * @throws IOException in case of network error
	 */
	protected void writeJsonResponse(HttpServletResponse resp,
			Map<String, Object> result) throws IOException {
		String jsonBody = getObjectMapper().writeValueAsString(result);
		resp = setAPIContentType(resp);
		resp.getWriter().append(jsonBody);
	}
	
	/**
	 * Parses List data set as JSON and writes to client.
	 * 
	 * @param list of data set
	 * @param resp object injected by Servlet Container
	 * @throws IOException in case of network error
	 */
	protected void writeJsonResponse(HttpServletResponse resp,
			List<? extends Model> list) throws IOException {
		String jsonBody = getObjectMapper().writeValueAsString(list);
		resp = setAPIContentType(resp);
		resp.getWriter().append(jsonBody);
	}
	
	/**
	 * Set HTTP response content-type & char encoding.
	 * 
	 * @param resp object injected by Servlet Container
	 * @return HttpServletResponse 
	 */
	protected HttpServletResponse setAPIContentType(
			HttpServletResponse resp) {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		return resp;
	}

}
