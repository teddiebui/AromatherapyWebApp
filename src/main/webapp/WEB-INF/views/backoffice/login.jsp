<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        /* Modal styles */
        .modal-backdrop {
            z-index: 1040 !important;
        }
        .modal-content {
            margin: 2rem auto;
            z-index: 1100 !important;
        }
    </style>
    <script>
        function validateForm() {
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            // Regex for username and password validation
            const usernameRegex = /^[a-zA-Z][a-zA-Z0-9]{3,15}$/;
            const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()])[A-Za-z\d!@#$%^&*()]{6,16}$/;

            // Validate username
            if (!usernameRegex.test(username)) {
                showModal("Invalid username. Must be 4-16 characters, start with a letter, and contain only letters and digits.");
                return false;
            }

            // Validate password
            if (!passwordRegex.test(password)) {
                showModal("Invalid password. Must be 6-16 characters, include upper & lowercase letters, digits, and at least 1 special character: !@#$%^&*().");
                return false;
            }

            // If validation passes, submit the form data as JSON
            submitFormAsJSON(username, password);
            return false;  // Prevent default form submission
        }

        function submitFormAsJSON(username, password) {
            const loginData = {
                "username": username,
                "password": password
            };

            $.ajax({
                url: 'login',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(loginData),
                success: function(response) {
                	response = JSON.parse(response);
                	console.log(response);
                	window.location.href = response.redirect;
                },
                error: function(response) {
                	console.log(response);
                	response = JSON.parse(response.responseText);
                	console.log(response);
                	if (response.validationErrors) {
                        let errorMessages = '';
                        if (response.validationErrors.username) {
                            errorMessages += 'Username: ' + response.validationErrors.username + '<br>';
                        }
                        if (response.validationErrors.password) {
                            errorMessages += 'Password: ' + response.validationErrors.password + '<br>';
                        }
                        showModal(errorMessages);
                    } else {
                        showModal(response.message);
                    }
                	
                }
            });
        }

        function showModal(message) {
            document.getElementById("modal-message").innerHTML = message;
            $('#errorModal').modal('show');
        }
    </script>
</head>
<body>

<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-4">
            <h3>Login</h3>
            <form id="loginForm" onsubmit="return validateForm();">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Login</button>
            </form>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="errorModalLabel">Login Error</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p id="modal-message"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
