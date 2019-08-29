<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="CSS/MapStyle.css">
<link rel="shortcut icon" type="image/x-icon"
	href="docs/images/favicon.ico">
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css"
	integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ=="
	crossorigin="anonymous">
<script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet-src.js"
	integrity="sha512-IkGU/uDhB9u9F8k+2OsA6XXoowIhOuQL1NTgNZHY1nkURnqEGlDZq3GsfmdJdKFe1k1zOc6YU2K7qY+hF9AodA=="
	crossorigin="anonymous"></script>
<script src="JavaScript/mappage.js" type="text/javascript" defer></script>
</head>
<body>
	<div class="topnav">
		<a style="float: left;" href="Home">HOME</a>
		<a style="float: right;" href="Profilo">PROFILO</a> 
		<a style="float: right;" href="Logout">LOGOUT</a>
	</div>
	
	ID Campagna -->  <p id="idcampagna">${idcampagna}</p>
	
	<div id="mapareacontainer">
		<div id="mapid"></div>
	</div>
	
	<p id="datilocalita"></p>
	
	<div id="immagini" >
		<img alt="immagine" id="immagine" src="">
		<p id="datiimmagine"></p>
		<button id="left">&#10094</button>
		<button id="right">&#10095</button>
	</div>
	
	<div id="annotazioni"></div>
	
</body>
</html>