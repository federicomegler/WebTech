<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>

<meta charset="ISO-8859-1">
<title>Login</title>
<link href="CSS/LoginStyle.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="form">
	<form action="CheckLogin" method="post">
		<input placeholder="Username o Email" name="username" type="text" required="required"> <br><br>
		<input placeholder="Password" name="password" type="password" required="required"><br><br>
		<input type="submit" value="login">
	</form><br>
</div>
	
	Non hai un account? <a href="Signup"> registrati qui. </a>
	
	<c:choose>
		<c:when test="${errore == 1}">
			<p style="color:red;">Username o password errate!</p>
		</c:when>
	</c:choose>
	
</body>
</html>