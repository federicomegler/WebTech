<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css?family=Lobster&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="CSS/MapStyle.css">
<link rel="shortcut icon" type="image/x-icon"
	href="docs/images/favicon.ico">
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css"
	integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ=="
	crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Lobster&display=swap" rel="stylesheet">
<script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet-src.js"
	integrity="sha512-IkGU/uDhB9u9F8k+2OsA6XXoowIhOuQL1NTgNZHY1nkURnqEGlDZq3GsfmdJdKFe1k1zOc6YU2K7qY+hF9AodA=="
	crossorigin="anonymous"></script>
<script src="JavaScript/mappage.js" type="text/javascript" defer></script>
</head>
<body background="/icone/sfondo.jpg">
	<div class="topnav">
			<a style="float: left;" href="GetDettagli?idcampagna=${idcampagna}"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>
		<a style="float: left;" href="Home"><i class="fa fa-home" aria-hidden="true"></i> HOME</a> <a style="float: left;"
			href="Profilo"><i class="fa fa-user" aria-hidden="true"></i> PROFILO</a> 
			<a style="float: right;" href="Logout"><i class="fa fa-sign-out" aria-hidden="true"></i> LOGOUT</a>
			
	</div>
	<div class="mappaimmagine">
		<div class="infobox">
			ID Campagna --> <label id="idcampagna">${idcampagna}</label>
		</div>
		<div id="mapareacontainer">
			<div id="mapid"></div>
		</div>

		Localita: <label id="datilocalita"></label>
		<label id="errore" style="color:red;display:none;">Errore caricamento immagini. Riprova!</label>
		<div id="immagini">
			<img alt="immagine" id="immagine" src="">
			<p id="datiimmagine"></p>
		</div>
					<button id="left">&#10094</button>
				
					<button id="right">&#10095</button>
	</div>

	<div id="annotazioni"></div>

</body>
</html>