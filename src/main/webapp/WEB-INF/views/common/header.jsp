<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><c:out value="${pageTitle}" /></title>
    <!-- Bootstrap CSS from CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- fontawe some -->
    <script src="https://kit.fontawesome.com/1c04025fcb.js" crossorigin="anonymous"></script>
    <!-- Link to the CSS file using context path -->
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/style.css">
</head>
<body data-page="${pageId}">
    <header class="my-1">
    	<div class="container-lg g-0 px-1 text-center">
	    	<h1>Aromatherapy - Massage</h1>
	    	<h3>
	    		<i>Welcome to this website</i>
	    	</h3>
	    	
	    	<nav class="container-lg g-0 px-1 d-flex justify-content-start" id="top-menu">
	   			<a href="<c:url value="/index" />">Welcome</a>
	   			<a href="<c:url value="/therapy_massage"/>">Therapy and Massage</a>
	   			<a href="<c:url value="/price_list"/>">Price List</a>
	   			<a href="<c:url value="/employees"/>">Employees</a>
	   			<a href="<c:url value="/contact_us"/>">Contact us</a>
	   		</nav>
    	</div>

    </header>
    <main role="main" class="container-lg g-0 px-1">