<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="CSS/LoginStyle.css">
</head>
<body>
	<div class="box">
		<div class="form">
			<form action="CheckLogin" method="post">
				<div class="input">
				<br>
					<input type="text" name="username" class="text" required>
					<span class="floating-label">Email o Username:</span>
				</div>
				<br>
				<div class="input">
				<br>
					<input type="password" name="password" class="text" required>
					<span class="floating-label">Password:</span>
				</div>
				<br>
				<div class="submit">
				<input type="submit" value="Login" id="button">
				</div>
			</form>
			<br>
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