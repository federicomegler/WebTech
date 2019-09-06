<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<link href="https://fonts.googleapis.com/css?family=Lobster&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="CSS/HomePageStyle.css">
<script src="JavaScript/homeWorker.js" type="text/javascript" defer></script>
</head>
<body>

  
  
	<div class="topnav">
		<a style="float: left;" href="Profilo">PROFILO</a> <a
			style="float: right;" href="Logout">LOGOUT</a>
	</div>
<div class="container">
  <h1>Home</h1>
	<button id="optate" class="Cbtn">Mostra campagne optate</button>
	<button id="nonoptate" class="Cbtn">Mostra campagne non optate</button>
	
	<div id="containernonoptate">
		<button id="nomenonoptata" class="elementlist">Lista campagne vuota</button>
		<button id="sNop" class="left">&#10094;</button>
		<button id="dNop" class="right">&#10095;</button>
	</div>
	
	<div id="containeroptate">
		<button id="nomeoptata" class="elementlist">Lista campagne vuota</button>
		<button id="sOp" class="left">&#10094;</button>
		<button id="dOp" class="right">&#10095;</button>
	</div>

	<form id="dett" action="GetDettagliWorker" method="post" style="display: none">
		<input type="text" id="id" name="idcampagna">
	</form>
	<c:if test="${errore == 1}">
			<label style="color: red;">Errore nella richiesta</label>
	</c:if>
</div>



</body>
</html>