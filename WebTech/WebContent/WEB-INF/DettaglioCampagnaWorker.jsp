<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dettaglio Campagna Worker</title>
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
				<input type="submit" value="Iscriviti">
			</form>
		</c:if>
</body>
</html>