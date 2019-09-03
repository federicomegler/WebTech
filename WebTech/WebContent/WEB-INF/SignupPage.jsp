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
<link href="https://fonts.googleapis.com/css?family=Lobster&display=swap" rel="stylesheet">
</head>
<body background="/icone/sfondo.jpg">
	<div class="registrazione">
	<h1>Registrati</h1>
		<form action="Signup" id="form" method="Post">
			<div class="input">
				<input id="username" class="text" type="text" name="username" required="required">
				<span class="floatinglabel">Username</span>
				<div class="icona">
					<img id="esisteutente" src="">
				</div>
			</div>
			
			<div class="input">	  
			    <input id="mail" class="text" type="text" name="mail" required="required"> 
			    <span class="floatinglabel">Email</span>
				<div class="icona">
					<img id="esistemail" src="">
				</div>
			</div>	
			
			
				<div class="input">
					<input id="password1" class="text" type="password" name="password1" required="required">
					<span class="floatinglabel">Password</span>
				</div>
				
				<div class="input">
					<input id="password2" class="text" name="password2" type="password" required="required">
					<span class="floatinglabel">Conferma Password</span>
					<div class="icona">
						<img id="errorePassword" src="">
					</div> 
				</div>
				
			 <input type="checkbox" onclick="mostraPassword()" id="mostra" value="mostra password">
				<label id="mostraPassword">Mostra password</label>
				
				<h4>Tipo di account:</h4>
				
				<div class="radio">
			     	<input type="radio" name="tipo_account" checked="checked" value="manager">
			     	<span class="floatinglabel">Manager</span>
					<input type="radio" name="tipo_account" value="lavoratore">
					<span class="floatinglabel">Lavoratore</span>
				</div>
				
				
				<input type="submit" id="btnRegistrati" value="Registrati" onclick="return checkCredenziali()">
		</form>
		<div class="etichetta">
		 Hai gia' un account? <a href="Login">Login</a>
		</div>
	</div>
	<c:if test="${errore == true}">
		<label id="errore" style="color: red;">Errore nella creazione del profilo. Riprova!</label>
	</c:if>
</body>

</html>



