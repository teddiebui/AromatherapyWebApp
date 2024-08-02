<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Set the page title as a request attribute --%>
<c:set var="pageTitle" value="Auromatherapy - Massage" />

<%-- Include the common header --%>
<c:include url="/WEB-INF/views/common/header.jsp" />

<div class="container mt-4">
    <h1>Welcome to Our Website</h1>
    <p>This is the home page. Here you can find a variety of content and services that we offer. Explore and enjoy your visit!</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris.</p>
    <p>Contact us at: contact@example.com</p>
    <p><a href="${pageContext.request.contextPath}/about">Learn more about us</a></p>
</div>

<%-- Include the common footer --%>
<c:include url="/WEB-INF/views/common/footer.jsp" />