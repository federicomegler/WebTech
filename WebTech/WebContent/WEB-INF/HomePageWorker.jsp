<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<link rel="stylesheet" type="text/css" href="CSS/HomePageStyle.css">
<script src="JavaScript/homeWorker.js" type="text/javascript" defer></script>
</head>
<body>

	<div class="topnav">
		<a style="float: left;" href="Profilo">PROFILO</a> <a
			style="float: right;" href="Logout">LOGOUT</a>
	</div>

	<button id="optate" class="Cbtn">Mostra campagne optate</button>
	<button id="nonoptate" class="Cbtn">Mostra campagne non optate</button>
	
	<div id="containernonoptate">
		<button id="nomenonoptata">Lista campagne vuota</button>
		<button id="sNop" class="left">&#10094;</button>
		<button id="dNop" class="right">&#10095;</button>
	</div>
	
	<div id="containeroptate">
		<button id="nomeoptata">Lista campagne vuota</button>
		<button id="sOp" class="left">&#10094;</button>
		<button id="dOp" class="right">&#10095;</button>
	</div>

	<form id="dett" action="GetDettagli" method="post"
		style="display: none">
		<input type="text" id="id" name="id"> <input type="text"
			id="nome" name="nome"> <input type="text" id="committente"
			name="committente"> <input type="text" id="stato"
			name="stato">
	</form>
</body>
</html>