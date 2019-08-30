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
</head>
<body>
	<div class="topnav">
		<a style="float: left;" href="Home">HOME</a> <a style="float: left;"
			href="Profilo">PROFILO</a> <a style="float: right;" href="Logout">LOGOUT</a>
	</div>
	
	ID Campagna: <p id="idcampagna">${idcampagna}</p>
	<p id="nomecampagna">Nome campagna: ${nome}</p>
	<p id="numerolocalita">Numero totale Località: ${totLocalita}</p>
	<p id="numeroimmagini">Numero totale Immagini: ${totImmagini}</p>
	<p id="numeroannotazioni">Numero totale Annotazioni:
		${totAnnotazioni}</p>

	<p id="mediaImmaginiLoc">Media Immagini per Località:
		${totImmagini/totLocalita}</p>
	<p id="mediaAnnotazioniImm">Media Annotazioni per Immagini:
		${totAnnotazioni/totImmagini}</p>


	<p id="numeroconflitti">Numero conflitti: ${listaImmagini.size()}</p>

	<c:if test="${listaImmagini.size() > 0}">
		<div id="elencoImmagini">
			<c:forEach var="imm" items="${listaImmagini}">
				<div>
					<img id="${imm.id}" alt="Cliccare per info"
						src="/ImmaginiCampagna/${imm.id}${imm.formato}">
				</div>
			</c:forEach>
		</div>

		<div id="containerdettagli">
			<div id="IMG">
				<p id="localitaimmagine"></p>
				<img id="immagineZoom" alt="" src="">
			</div>
			<div id="annotazioni"></div>
		</div>
	</c:if>
</body>
</html>