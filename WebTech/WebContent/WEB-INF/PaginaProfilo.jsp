<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Cache-Control" content="no-store" />
<title>Profilo - ${nickname}</title>
<link href="CSS/ProfiloStyle.css" rel="stylesheet" type="text/css">
<script src="JavaScript/modificaProfilo.js" type="text/javascript" defer></script>
</head>
<body>

	<div class="topnav">
		<a style="float: left;" href="Home">HOME</a> <a style="float: right;"
			href="Logout">LOGOUT</a>
	</div>
	<br>
	<div class="container">
		<div class="immagine">
			<img alt="Immagine profilo" src="/ImmaginiUtente/${immagine}"
				class="image">
			<div class="middle">
				<div class="cambia" id="cambia">Cambia Immagine</div>
				<div class="elimina" id="elimina">Elimina
					Immagine</div>
			</div>
			<form action="CambiaImmagineProfilo" method="post"
				enctype="multipart/form-data" id="formimmagine">
				<input id="selezionaimmagine" type="file" name="nuovaimmagine"
					style="display: none" onchange="return confermaCambioImmagine()">
			</form>
		</div>
	</div>
	<br>

	<table>
		<tr>
			<td>Nickname:</td>
			<td><c:out value="${nickname}"></c:out></td>
		</tr>
		<tr>
			<td>Mail:</td>
			<td><input type="text" value="${mail}" id="mail" disabled></td>
			<td><button id="modificaemail">Modifica
					Email</button>
				<button id="confermaemail" style="display: none;">Conferma</button>
				<button id="annullaemail" style="display: none;">Annulla</button></td>
			<td id="errore mail" style="color: red; display: none;">Email
				non valida</td>
		</tr>
		<tr>
			<td>Esperienza:</td>
			<td><c:out value="${esperienza}"></c:out></td>
		</tr>
		<tr>
			<td>Tipo Utente:</td>
			<td><c:out value="${tipo}"></c:out></td>
		</tr>
	</table>
	<br>
	<button id="BottoneNuovaPassword">Modifica
		password</button>
	<div style="display: none;" id="FormPassword">
		<form action="http:google.com" method="post" id="formpassword"
			novalidate>
			<input id="vecchia" type="password" placeholder="Vecchia Password"
				name="vecchiapassword" required><br>
			<br> <input id="nuova1" type="password"
				placeholder="Nuova Password" name="nuovapassword1" required><br>
			<br> <input id="nuova2" type="password"
				placeholder="Conferma Password" name="nuovapassword2" required><br>
			<br> <label id="errore" style="display: none; color: red;">Le
				password non corrispondono</label><br> 
				<input type="submit"
				value="Cambia Password" id="cambiaPassword"><br>
			<br>
			<button id="annullaCambioPassword">Annulla</button>
		</form>
	</div>
</body>
</html>