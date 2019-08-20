<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<link rel="stylesheet" type="text/css" href="CSS/HomePageStyle.css">
<script src="JavaScript/home.js" type="text/javascript" defer></script>
</head>
<body>

	<div class="topnav">
		<a style="float: left;" href="Profilo">PROFILO</a> <a
			style="float: right;" href="Logout">LOGOUT</a>
	</div>

	<button id="avviate" class="Cbtn">Campagne avviate</button>
	<div id="containeravviate">
		<button id="nomeavviata">Lista campagne vuota</button>
		<button id="sAv" class="left">&#10094;</button>
		<button id="dAv" class="right">&#10095;</button>
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