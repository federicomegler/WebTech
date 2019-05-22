<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profilo - ${utente}</title>
</head>
<body>
	<div class="menu">
		
		<a href="Logout"> Logout </a>
	
	</div>
	
	<div class="info">
		<img alt="Immagine profilo" src="">
		Nickname:   <c:out value="${nickname}"></c:out>
		Mail:	<c:out value="${mail}"></c:out>
		Tipo profilo:   <c:out value="${tipo profilo}"></c:out>
	</div>
</body>
</html>