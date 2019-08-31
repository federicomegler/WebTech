<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dettaglio Campagna Worker</title>
<link rel="shortcut icon" type="image/x-icon"
	href="docs/images/favicon.ico">
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css"
	integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ=="
	crossorigin="anonymous">
<script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet-src.js"
	integrity="sha512-IkGU/uDhB9u9F8k+2OsA6XXoowIhOuQL1NTgNZHY1nkURnqEGlDZq3GsfmdJdKFe1k1zOc6YU2K7qY+hF9AodA=="
	crossorigin="anonymous"></script>

</head>
<body>
	<div class="topnav">
		<a style="float: left;" href="Home">HOME</a> <a style="float: left;"
			href="Profilo">PROFILO</a> <a style="float: right;" href="Logout">LOGOUT</a>
	</div>

	<div class="container">
		<div class="details">
			id campagna
			<p id="idcampagna">${idcampagna}</p>
			nome
			<p id="nomecampagna">${nomecampagna}</p>
			committente
			<p id="committente">${committente}</p>
		</div>

		<c:if test="${iscritto == true}">
			<script src="JavaScript/dettagliWorker.js" type="text/javascript" defer></script>
			<div id="mapareacontainer">
				<div id="mapid"></div>
			</div>

			<p id="datilocalita"></p>

			<div id="immagini">
				<img alt="immagine" id="immagine" src="">
				<p id="datiimmagine"></p>
				<button id="left">&#10094</button>
				<button id="right">&#10095</button>
			</div>

			<div id="annotazioni"></div>

		</c:if>
		<c:if test="${iscritto == false}">
			<form action="IscrizioneCampagna" method="post">
				<input type="text" value="${idcampagna}" style="display:none;" name="idcampagna">
				<input type="submit" value="Iscriviti">
			</form>
		</c:if>
</body>
</html>