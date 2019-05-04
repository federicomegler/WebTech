<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignUp</title>
</head>
<body>

	<form action="Signup" method="Post">
		<input placeholder="Nome Utente" type="text" name="username" required="required"> <br>
		<input placeholder="Mail" type="text" name="mail" required="required"><br>
		<input placeholder="Password" type="password" name="password" required="required"><br>
		<input placeholder="Reinserisci password" type="password" required="required"><br><br>
		Manager <input type="radio" name="tipo_account" checked="checked" value="manager"><br>
		Lavoratore <input type="radio" name="tipo_account" value="lavoratore"><br>
		<input type= "submit" value="Registrati">
	</form>
</body>

</html>