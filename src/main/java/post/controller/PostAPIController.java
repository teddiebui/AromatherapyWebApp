package post.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.ObjectMapper;

import post.model.Post;
import post.service.impl.PostServiceImpl;

@WebServlet("/api/post/*")
public class PostAPIController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Explicitly set PostDao for this servlet.
	 */

	private PostServiceImpl postService;
	private ObjectMapper objectMapper;

	@Override
	public void init() throws ServletException {
		super.init();
		postService = (PostServiceImpl) getServletContext()
				.getAttribute("postService");
		objectMapper = (ObjectMapper) getServletContext()
				.getAttribute("objectMapper");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getPathInfo().substring(1).toLowerCase();
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
			resp.sendError(HttpServletResponse.SC_NOT_FOUND,
					req.getRequestURI());
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);

	}

	private void processCreate(HttpServletRequest req, HttpServletResponse resp)
			throws StreamReadException, IOException, ServletException {
		Post post = null;
		Map<String, Object> result = new HashMap<>();
		post = mapRequestQuery(post, req);
		System.out.println(post);
		result = postService.create(post);

		writeToJson(result, resp);
	}

	private Post mapRequestQuery(Post post, HttpServletRequest req) {
		if (post == null) {
			post = new Post();
		}

		String postId = req.getParameter("postId");
		String employeeId = req.getParameter("employeeId");
		String postStatus = req.getParameter("postStatus");
		String postLastUpdateEmployee = req
				.getParameter("postLastUpdateEmployee");
		String postLastUpdateTime = req.getParameter("postLastUpdateTime");
		String postTitle = req.getParameter("postTitle");
		String postContent = req.getParameter("postContent");
		String postSlug = req.getParameter("postSlug");
		String postIntroImgSrc = req.getParameter("postIntroImgSrc");
		String postExcerpt = req.getParameter("postExcerpt");
		String postExcerptImgSrc = req.getParameter("postExcerptImgSrc");
		String postCreateTime = req.getParameter("postCreateTime");

		post.setPostId(postId == null || postId.isEmpty() ? 0
				: Integer.parseInt(postId));
		post.setEmployeeId(employeeId == null || employeeId.isEmpty() ? 0
				: Integer.parseInt(employeeId));
		post.setPostStatus(postStatus == null || postStatus.isEmpty() ? 0
				: Integer.parseInt(postStatus));
		post.setPostLastUpdateEmployee(postLastUpdateEmployee == null
				|| postLastUpdateEmployee.isEmpty() ? 0
						: Integer.parseInt(postLastUpdateEmployee));
		post.setPostLastUpdateTime(
				postLastUpdateTime == null || postLastUpdateTime.isEmpty()
						? new Timestamp(0)
						: new Timestamp(Long.parseLong(postLastUpdateTime)));
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);
		post.setPostSlug(postSlug);
		post.setPostIntroImgSrc(postIntroImgSrc);
		post.setPostExcerpt(postExcerpt);
		post.setPostExcerptImgSrc(postExcerptImgSrc);
		post.setPostCreateTime(
				postCreateTime == null || postCreateTime.isEmpty()
						? new Timestamp(0)
						: new Timestamp(Long.parseLong(postCreateTime)));

		return post;
	}

	private void processDelete(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String idParameter = req.getParameter("id");
		Map<String, Object> result = new HashMap<>();

		if (idParameter == null || idParameter.isEmpty()) {
			result.put("status", false);
			result.put("message", "Bad request id is null");
		} else {
			int id = Integer.parseInt(idParameter);
			result = postService.delete(id);
		}

		writeToJson(result, resp);

	}

	private void processUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws StreamReadException, IOException, ServletException {
		Post post = null;
		Map<String, Object> result = new HashMap<>();

		post = mapRequestQuery(post, req);
		result = postService.update(post);

		writeToJson(result, resp);

	}

	private void processGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idParameter = req.getParameter("id");
		Map<String, Object> result;
		if (idParameter == null || idParameter.isEmpty()) {
			result = postService.getAll();

		} else {
			int id = Integer.parseInt(idParameter);
			result = postService.findById(id);
		}

		writeToJson(result, resp);
	}

	private void writeToJson(Map<String, Object> result,
			HttpServletResponse resp) throws ServletException, IOException {
		setJsonHeader(resp);
		this.objectMapper.writeValue(resp.getOutputStream(), result);
	}

	private void setJsonHeader(HttpServletResponse resp) {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
	}

}
