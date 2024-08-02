<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Set title  --%>
<c:set var="pageTitle" value="Contact Us" />
<c:set var="pageId" value="contact_us" />
<%-- Static include header --%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="container-lg g-0 p-4" id="index">
	<div class="row">
		<div class="col-9" id="left">
			<div class="row g-0" id="contact-us-page">
				<h3>Contact us</h3>
		        <div class="row">
		            <div class="col-md-6 d-flex flex-column">
		                <h4>Aromatherapy - Massage</h4>
		                <span>Address:</span>
		                <span>City:</span>
		                <span>Country:</span>
		                <span>Tel: 12345</span>
		                <span>Email: your-email@your-email.com</span>
	                </div>
		            <div class="col-md-6 d-flex flex-column">
		                <h4>Opening hours:</h4>
		                <span>Monday: 9:00 - 15:00</span>
		                <span>Tuesday: 9:00 - 15:00</span>
		                <span>Wednesday: Closed</span>
		                <span>Thursday: 9:00 - 15:00</span>
		                <span>Friday: 9:00 - 14:00</span>
		            </div>
		        </div>
				<iframe id="google-map-location"
					src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d6211.603561500693!2d-77.13370755434877!3d38.8827766931033!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89b7c6de5af6e45b%3A0xd6e28ec00254a198!2zT2EteGluaC10xqFuLCBRdeG6rW4gQ29sdW1iaWEsIEhvYSBL4buz!5e0!3m2!1svi!2s!4v1722573912026!5m2!1svi!2s"
					width="" height="" style="border: 0;" allowfullscreen="" loading="lazy"
					referrerpolicy="no-referrer-when-downgrade"></iframe>
			</div>

		</div>
		<%-- jsp include right --%>
		<%@ include file="/WEB-INF/views/common/right.jsp"%>

	</div>
</div>

<%-- Static include footer --%>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>