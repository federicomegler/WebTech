<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="CSS/SignupStyle.css" type="text/css" rel="stylesheet">
<title>SignUp</title>
</head>
<body>
	<div class="registrazione">
	<form action="Signup" id="form" method="Post">
		<input placeholder="Nome Utente" type="text" name="username" required="required"> <br><br>
		<input placeholder="Mail" id="mail" type="text" name="mail" required="required"> <label id="erroreMail" style="color: red"></label><br><br>
		<input id="password1" placeholder="Password" type="password" name="password" required="required"><br><br>
		<input id="password2" placeholder="Reinserisci password" type="password" required="required"><label id="errorePassword" style="color: red;"></label><br><br>
		<input type="checkbox" onclick="mostraPassword()" id="mostra" value="mostra password"><label id="mostraPassword">Mostra password</label> <br><br>
		Manager <input type="radio" name="tipo_account" checked="checked" value="manager"><br>
		Lavoratore <input type="radio" name="tipo_account" value="lavoratore"><br><br>
		<input type= "submit" value="Registrati" onclick="return checkCredenziali()">
	</form>
	<br>
	Hai gia' un account? <a href="Login">Login</a>
	</div>
</body>

</html>

<script type="text/javascript">

	function checkPassword(){
		var form = document.forms["form"];
		if(form.elements[2].value === form.elements[3].value){
			document.getElementById("errorePassword").innerHTML = "";
			return true;
		}
		else{
			document.getElementById("errorePassword").innerHTML = "Le password non coincidono";
			return false;
		}
	}	
	
	function checkCredenziali(){
		
		var cp = checkPassword();
		var cm = checkEmail();
		return cp && cm;
	}
	
	function checkEmail() {
	    var form = document.getElementById("form");
	    var email = form.elements[1].value;
		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    if(re.test(email)){
	    	document.getElementById("erroreMail").innerHTML =  "";
	    	return true;	    	
	    }
	    else{
	    	document.getElementById("erroreMail").innerHTML =  "Mail non valida";
			return false;
	    }
	}
	
	function mostraPassword(){
		if(document.getElementById("password1").getAttribute("type") === "password"){
	        document.getElementById("password1").setAttribute("type","text");
	        document.getElementById("password2").setAttribute("type","text");
	        document.getElementById("mostraPassword").innerHTML = "Nascondi password";
	    }
	    else{
	        document.getElementById("password1").setAttribute("type","password");
	        document.getElementById("password2").setAttribute("type","password");
	        document.getElementById("mostraPassword").innerHTML = "Mostra password";
	    }
	}
	
</script>


