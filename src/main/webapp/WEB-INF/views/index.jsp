<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Set title  --%>
<c:set var="pageTitle" value="Auromatherapy - Massage" />
<c:set var="pageId" value="index" />
<%-- Static include header --%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="container-lg g-0 p-4" id="index">
	<div class="row">
		<div class="col-9" id="left">
			<div id="banner">
				<div class="img-container">
					<img src="${pageContext.request.contextPath}/resources/static/img/welcome/i283445314524568867._szw1280h1280_.jpg "
						alt="" class="img-fluid" />
				</div>
			</div>
			<section id="welcome-post">
				<h3>Articles</h3>
				<div id="post-list" class="row row-columns-3"></div>
			</section>
			<section id="welcome-courses">
				<h3>Courses</h3>
				<div id="course-list"></div>
			</section>
			<section id="make-appointment">
				<h3>Make an appointment</h3>
				<div id="contact-list">
					<p>Tel: 123-123-1234</p>
					<p>Email: health@123health.com</p>
					<p>We are commited to your well-being. If you have any comments, questions, or concerns feel free to contact
						us.</p>

					<div id="employee-signature" class="row">
						<div class="col-md-3 avt-img">
							<img src="" alt="" />
						</div>
						<div class="col-md-9">
							<p>Kind regards,</p>
							<p>
								<strong id="signature"></strong>
							</p>
						</div>
					</div>


				</div>
			</section>

		</div>
		<!-- jsp include right -->
		<%@ include file="/WEB-INF/views/common/right.jsp"%>
		
	</div>
</div>

<%-- Static include footer --%>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>