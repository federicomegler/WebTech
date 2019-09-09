<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statistiche</title>
<link rel="stylesheet" type="text/css" href="CSS/StatisticheStyle.css">
<script src="JavaScript/statistiche.js" type="text/javascript" defer></script>
<link href="https://fonts.googleapis.com/css?family=Lobster&display=swap" rel="stylesheet">

</head>
<body background="/icone/sfondo.jpg">
	<div class="topnav">
			<a style="float: left;" href="GetDettagli?idcampagna=${idcampagna}"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
		<a style="float: left;" href="Home">HOME</a> <a style="float: left;"
			href="Profilo">PROFILO</a> <a style="float: right;" href="Logout">LOGOUT</a>
	</div>
	<div class="container">
	<h2>ID Campagna:</h2> <p id="idcampagna">${idcampagna}</p>
	
	<h2>Nome Campagna:</h2><p id="nomecampagna">${nome}</p>
	<h2>Numero totale Località:</h2><p id="numerolocalita">${totLocalita}</p>
	<h2>Numero totale Immagine:</h2><p id="numeroimmagini">${totImmagini}</p>
	<h2>Numero totale Annotazioni:</h2><p id="numeroannotazioni">${totAnnotazioni}</p>

	<h2>Media Immagini per Località</h2><p id="mediaImmaginiLoc">${totImmagini/totLocalita}</p>
	<h2>Media Annotazioni per Immagini</h2><p id="mediaAnnotazioniImm">${totAnnotazioni/totImmagini}</p>


	<h2>Numero conflitti:</h2><p id="numeroconflitti">${listaImmagini.size()}</p>

	<c:if test="${listaImmagini.size() > 0}">
	<h3>Immagini con Conflitti:</h3>
		<div id="elencoImmagini">
			<c:forEach var="imm" items="${listaImmagini}">
				<div class="immagini">
					<img id="${imm.id}" alt="Cliccare per info"
						src="/ImmaginiCampagna/${imm.id}${imm.formato}" class="img">
				</div>
			</c:forEach>
		</div>

		<div id="containerdettagli">
			<label id="errore" style="color:red;display:none;">Errore nel caricamento dati. Riprova!</label>
			<div id="IMG">
				<p id="localitaimmagine"></p>
				<img id="immagineZoom" alt="" src="">
			</div>
			<div id="annotazioni"></div>
		</div>
	</c:if>
	</div>
</body>
</html>