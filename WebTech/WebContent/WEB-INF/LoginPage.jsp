<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action="CheckLogin" method="post">
		<input placeholder="Username" name="username" type="text" required="required"> <br><br>
		<input placeholder="Password" name="password" type="password" required="required"><br><br>
		<input type="submit" value="login">
	</form><br>
	
	
	Non hai un account? <a href="Signup"> registrati qui. </a>
	
</body>
</html>