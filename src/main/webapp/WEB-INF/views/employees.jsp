<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Set title  --%>
<c:set var="pageTitle" value="Employee List" />
<c:set var="pageId" value="employeeList" />
<%-- Static include header --%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="container-lg g-0 p-4" id="index">
	<div class="row">
		<div class="col-9" id="left">
			<div class="row g-0" id="employee-lists">
			</div>

		</div>
		<!-- jsp include right -->
		<%@ include file="/WEB-INF/views/common/right.jsp"%>

	</div>
</div>

<%-- Static include footer --%>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>