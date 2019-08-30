<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statistiche</title>
<link rel="stylesheet" type="text/css" href="CSS/StatisticheStyle.css">
</head>
<body>
	<div class="topnav">
		<a style="float: left;" href="Home">HOME</a> <a style="float: left;"
			href="Profilo">PROFILO</a> <a style="float: right;" href="Logout">LOGOUT</a>
	</div>
	
	<p id="campagna">Campagna: ${idcampagna}</p>
	<p id="numerolocalita">Numero totale Località: ${totLocalita}</p>
	<p id="numeroimmagini">Numero totale Immagini: ${totImmagini}</p>
	<p id="numeroannotazioni">Numero totale Annotazioni:
		${totAnnotazioni}</p>

	<p id="mediaImmaginiLoc">Media Immagini per Località:
		${totImmagini/totLocalita}</p>
	<p id="mediaAnnotazioniImm">Media Annotazioni per Immagini:
		${totAnnotazioni/totImmagini}</p>


	<p id="numeroconflitti">Numero conflitti: ${listaImmagini.size()}</p>
	<div id="elencoImmagini">
		<c:forEach var="imm" items="${listaImmagini}">
			<div id="${imm.id}">
				<img alt="Cliccare per info"
					src="/ImmaginiCampagna/${imm.id}${imm.formato}">
			</div>
		</c:forEach>
	</div>
</body>
</html>