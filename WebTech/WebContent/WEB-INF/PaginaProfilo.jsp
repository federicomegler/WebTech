<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profilo - ${utente}</title>
<link href="CSS/ProfiloStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="topnav">
  		<a style="float: left;" href="Home">HOME</a>
  		<a style="float: right;" href="Logout">LOGOUT</a>
	</div>
	<br>
	<div class="container">
	<div class="immagine">
		<img alt="Immagine profilo"
			src="/Images/antilope.jpg" class="image">
			<div class="middle"><div class="text" onclick="CambiaImmagine()">Cambia Immagine</div></div>
			<form action="" >
				<input id="selezionaimmagine" type="file" name="nuovaimmagine" style="display: none" onchange="return confermaCambioImmagine()">
			</form>
	</div>
	</div>
	<br>
	
	<table>
	<tr><td>Nickname:</td><td><c:out value="${nickname}"></c:out></td></tr>
	<tr><td>Mail:</td><td><input type="text" value="${mail}" id="mail" disabled></td></tr>
	<tr><td>Esperienza:</td><td><c:out value="${esperienza}"></c:out></td></tr>
	<tr><td>Tipo Utente:</td><td><c:out value="${tipo}"></c:out></td></tr>
	</table>
	<br>
	<button onclick="CreaNuovaPassword()" id="BottoneNuovaPassword">Modifica password</button>
	<div style="display: none;" id="FormPassword">
		<form action="http:google.com" method="post" id="formpassword" novalidate>
			<input id="vecchia" type="password" placeholder="Vecchia Password" name="vecchiapassword" required><br><br>
			<input id="nuova1" type="password" placeholder="Nuova Password" name="nuovapassword1" required><br><br>
			<input id="nuova2" type="password" placeholder="Conferma Password" name="nuovapassword2" required><br><br>
			<label id="errore" style="display: none; color: red;">Le password non corrispondono</label><br>
			<input type="submit" value="Cambia Password" onclick="return ModificaPassword()"><br><br>
			<button onclick="AnnullaCambioPassword()"> Annulla</button>
		</form>
	</div>
</body>
</html>
<script>
	function CreaNuovaPassword(){
	  document.getElementById("FormPassword").style.display = "block";
	  document.getElementById("BottoneNuovaPassword").style.display = "none";
	}
	
	function AnnullaCambioPassword(){
		document.getElementById("FormPassword").style.display = "none";
		document.getElementById("BottoneNuovaPassword").style.display = "block";
	}
	function ModificaPassword(){
		if(document.getElementById("nuova1").value == document.getElementById("nuova2").value){
			document.getElementById("formpassword").submit();
		}
		else{
			document.getElementById("errore").style.display = "block";
			document.getElementById("nuova1").style.background = "#ffdddd";
			document.getElementById("nuova2").style.background = "#ffdddd";
			document.getElementById("nuova1").value = "";
			document.getElementById("nuova2").value = "";
			return false;
		}
	}
	
	function CambiaImmagine(){
		document.getElementById("selezionaimmagine").click();
	}
</script>