<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dettaglio Campagna Worker</title>
<link href="https://fonts.googleapis.com/css?family=Lobster&display=swap" rel="stylesheet">
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css"
	integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ=="
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="CSS/DettagliCampagnaWorkerStyle.css">
<script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet-src.js"
	integrity="sha512-IkGU/uDhB9u9F8k+2OsA6XXoowIhOuQL1NTgNZHY1nkURnqEGlDZq3GsfmdJdKFe1k1zOc6YU2K7qY+hF9AodA=="
	crossorigin="anonymous"></script>

</head>
<body background="/icone/sfondo.jpg">
	<div class="topnav">
		<a style="float: left;" href="Home"><i class="fa fa-home" aria-hidden="true"></i> HOME</a> <a style="float: left;"
			href="Profilo"><i class="fa fa-user" aria-hidden="true"></i> PROFILO</a> 
			<a style="float: right;" href="Logout"><i class="fa fa-sign-out" aria-hidden="true"></i> LOGOUT</a>
	</div>
	<div class="containerprincipale">
		<div class="container">
			<div class="details">
				<h2>ID Campagna</h2>
				<p id="idcampagna">${idcampagna}</p>
				<h2>Nome</h2>
				<p id="nomecampagna">${nomecampagna}</p>
				<h2>Committente</h2>
				<p id="committente">${committente}</p>
			</div>
		</div>
		<c:if test="${iscritto == true}">
			<script src="JavaScript/dettagliWorker.js" type="text/javascript" defer></script>
			<h2>Mappa:</h2>
			<div class="mappaimmagine">
				<div id="mapareacontainer">
					<div id="mapid"></div>
				</div>

				<p id="datilocalita"></p>
				<h2>Immagine:</h2>
				<div id="immagini">
					<img alt="immagine" id="immagine" src="">
					<p id="datiimmagine"></p>
					<button id="left" class="left">&#10094</button>
					<button id="right" class="right">&#10095</button>
				</div>

				<div id="annotazioni"></div>

				<div id="formAnnotazione">
					
					<h4>Scrivi un commento:</h4>
					<input type="radio" name="validita" id="validita" checked>
					Vero <input type="radio" name="validita" id="validita">
					Falso <br> 
					
					<textarea maxlength="256" id="nota" draggable="false" placeholder="inserisci un commento"></textarea>
					<br><button id="invia" class="button">Invia</button>
				</div>
				<label id="errore" style="display: none; color: red;">Impossibile inviare il commento</label>	
			</div>
		</c:if>
		<c:if test="${iscritto == false}">
			<form action="IscrizioneCampagna" method="post">
				<input type="text" value="${idcampagna}" style="display: none;"
					name="idcampagna"> 
					<input class="button" type="submit" value="Iscriviti">
			</form>
		</c:if>
	</div>
</body>
</html>