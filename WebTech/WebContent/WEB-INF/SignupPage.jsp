<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="CSS/SignupStyle.css" type="text/css" rel="stylesheet">
<title>SignUp</title>
<script src="JavaScript/SignUp.js" type="text/javascript" defer></script>
</head>
<body>
	<div class="registrazione">
		<form action="Signup" id="form" method="Post">
			<input id="username" placeholder="Nome Utente" type="text" name="username"
				required="required">
				
				<img id="esisteutente" src="">
				
				  <br>
			<br> <input placeholder="Mail" id="mail" type="text" name="mail"
				required="required"> 
				
				<img id="esistemail" src="">
				
				
				<br>
			<br> <input id="password1" placeholder="Password"
				type="password" name="password1" required="required"><br>
			<br> <input id="password2" name="password2"placeholder="Reinserisci password"
				type="password" required="required">
				
				<img id="errorePassword" src=""> <br>
			<br> <input type="checkbox" onclick="mostraPassword()"
				id="mostra" value="mostra password"><label
				id="mostraPassword">Mostra password</label> <br>
			<br> Manager <input type="radio" name="tipo_account"
				checked="checked" value="manager"><br> Lavoratore <input
				type="radio" name="tipo_account" value="lavoratore"><br>
			<br> <input type="submit" id="btnRegistrati" value="Registrati" onclick="return checkCredenziali()">
		</form>
		<br> Hai gia' un account? <a href="Login">Login</a>
	</div>
	<c:if test="${errore == true}">
		<label id="errore" style="color: red;">Errore nella creazione del profilo. Riprova!</label>
	</c:if>
</body>

</html>



