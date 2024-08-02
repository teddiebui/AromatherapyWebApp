<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Set title  --%>
<c:set var="pageTitle" value="Price List" />
<c:set var="pageId" value="priceList" />
<%-- Static include header --%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="container-lg g-0 p-4" id="index">
	<div class="row">
		<div class="col-9" id="left">
			<div class="row g-0" id="service-price-lists">
				<h4>Price List</h4>
				<div class="row g-0 px-2">
					<div class="col-md-5">Treatment</div>
					<div class="col-md-6">Info</div>
					<div class="col-md-1">Price</div>
				</div>
			</div>

		</div>
		<%-- jsp include right --%>
		<%@ include file="/WEB-INF/views/common/right.jsp"%>

	</div>
</div>

<%-- Static include footer --%>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>