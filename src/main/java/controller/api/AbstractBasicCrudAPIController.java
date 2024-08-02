package controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AbstractSetupController;
import model.Model;

public abstract class AbstractBasicCrudAPIController
		extends AbstractSetupController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String requestPath = req.getRequestURI();
		String[] splittor = requestPath.split("/");
		String operation = splittor[splittor.length - 1];
		switch (operation) {

		case "get":
			int modelId = req.getParameter("id") == null ? 0
					: Integer.parseInt(req.getParameter("id"));
			if (modelId > 0) {
				processGetById(modelId, req, resp);
			} else {
				processGet(req, resp);
			}
			break;
		default:
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, requestPath);
		}
	}

	protected void processGetById(int id, HttpServletRequest req,
			HttpServletResponse resp) {
		Model model = null;
		try {
			model = getDao().findById(id);
			if (model == null) {
				HashMap<String, Object> result = new HashMap<>();
				result.put("result", false);
				result.put("message", "Model not found");
				result.put("model", new Object[] {});
				writeJsonResponse(resp, result);
			} else {
				HashMap<String, Object> result = new HashMap<>();
				result.put("result", true);
				result.put("message", "Success");
				result.put("model", model);
				writeJsonResponse(resp, result);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void processGet(HttpServletRequest req,
			HttpServletResponse resp) {
		List<? extends Model> list;
		try {
			list = getDao().getAll();
			writeJsonResponse(resp, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void writeJsonResponse(HttpServletResponse resp,
			Map<String, Object> result) throws IOException {
		String jsonBody = getObjectMapper().writeValueAsString(result);
		// set context-type, encoding, and send to client
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().append(jsonBody);
	}

	protected void writeJsonResponse(HttpServletResponse resp,
			List<? extends Model> list) throws IOException {
		String jsonBody = getObjectMapper().writeValueAsString(list);
		// set context-type, encoding, and send to client
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().append(jsonBody);
	}

}
