<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Backoffice Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="<curl value="/backoffice/main/css/style.css"/>">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Backoffice</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#">Dashboard</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Manage Posts</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Manage Services</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Manage Users</a>
            </li>
        </ul>
    </div>
    <form id="logoutForm" class="form-inline my-2 my-lg-0">
        <button type="button" class="btn btn-outline-danger my-2 my-sm-0" onclick="submitLogout()">Logout</button>
    </form>
</nav>

<div class="container mt-5">
    <div class="jumbotron">
        <h1 class="display-4">Welcome, <c:out value="${username}" />!</h1>
        <p class="lead">This is your backoffice dashboard. Here you can manage all aspects of the application.</p>
        <hr class="my-4">
        <p>Use the navigation bar above to access different sections of the backoffice.</p>
    </div>
</div>

<!-- Include jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    function submitLogout() {
        const username = "<c:out value='${username}'/>";

        // Create JSON object
        const logoutData = {
            "username": username
        };

        // Send AJAX request
        $.ajax({
            url: 'logout',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(logoutData),
            success: function(response) {
                // Redirect to login page after successful logout
                window.location.href = '/login.jsp';
            },
            error: function(error) {
                console.error('Error during logout:', error);
            }
        });
    }
</script>
<script src="<curl value="/backoffice/main/js/script.js"/>" ></script>

</body>
</html>
