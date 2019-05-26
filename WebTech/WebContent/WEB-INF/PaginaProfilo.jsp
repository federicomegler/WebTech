<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profilo - ${utente}</title>
<link href="CSS/ProfiloStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="topnav">
  		<a style="float: left;" href="Home">HOME</a>
  		<a style="float: right;" href="Logout">LOGOUT</a>
  		<a style="float: right;" href="">MODIFICA PROFILO</a>
	</div>
	<br>
	<div class="immagine">
		<img alt="Immagine profilo"
			src="http://localhost:8081/Images/${immagine}">
	</div>
	<br>
	<table>
	<tr><td>Nickname:</td><td><c:out value="${nickname}"></c:out></td></tr>
	<tr><td>Mail:</td><td><c:out value="${mail}"></c:out></td></tr>
	<tr><td>Esperienza:</td><td><c:out value="${esperienza}"></c:out></td></tr>
	<tr><td>Tipo Utente:</td><td><c:out value="${tipo}"></c:out></td></tr>
	</table>
	<br>
</body>
</html>