<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle != null ? pageTitle : "Default Title"}</title>
    <!-- Bootstrap CSS from CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-r4NyZt2VuCo3EM2C0kA7JqkhClPjHq1yW6V3oMvV7yM29x5xEgFbWTAc/6l7XfE" crossorigin="anonymous">
    <!-- Custom CSS (linked via URL rewriting) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/style.css">
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Brand Name</a>
            <!-- Add more navigation items here -->
        </nav>
    </header>
    <main role="main" class="container">