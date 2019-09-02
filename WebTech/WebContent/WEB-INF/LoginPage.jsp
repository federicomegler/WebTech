<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="CSS/LoginStyle.css">
<link href="https://fonts.googleapis.com/css?family=Lobster&display=swap" rel="stylesheet">
</head>
<body background="/icone/sfondo.jpg">
	<div class="container">
	<h1>Login</h1>
		<div class="form">
			<form action="CheckLogin" method="post">
				<div class="input">
				<i class="fa fa-user" aria-hidden="true"></i>
					<input type="text" name="username" class="text" required>
					<span class="floating-label">Email o Username</span>
				</div>
				<div class="input">
					<i class="fa fa-lock"  aria-hidden="true"></i>
					<input type="password" name="password" class="text" required>
					<span class="floating-label">Password</span>
				</div>
				<input type="submit" value="Login" id="button">
			</form>
		</div>
		<div class="link">
		Non hai un account? <a href="Signup"> registrati qui. </a>
		
		<c:choose>
			<c:when test="${errore == 1}">
				<p style="color: red;">Username o password errate!</p>
			</c:when>
		</c:choose>
		</div>
	</div>
</body>
</html>