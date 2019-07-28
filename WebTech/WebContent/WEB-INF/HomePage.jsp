<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<link rel="stylesheet" type="text/css" href="CSS/HomePageStyle.css">
</head>
<body>

	<div class="topnav">
  		<a style="float: left;" href="Profilo">PROFILO</a>
  		<a style="float: right;" href="Logout">LOGOUT</a>
	</div>
	
	<table>
		<c:forEach var="campagna" items="${listacampagna}">
			<tr>
				<td><a href=""> <c:out value="${campagna}"></c:out>
				</a></td>
			</tr>
		</c:forEach>
	</table>
	
	
	<div>
	<form action="GetDettagli" method="get"><input type="submit" value="Dettaglio"></form>
				
	</div>
	

	<div class="modulocreazione">
		<form action="CreaCampagna" method="post">
			<table>
				<tr>
					<td>Nome campagna:</td>
					<td><input type="text" name="nome" required="required">
						<br></td>
				</tr>
				<tr>
					<td>Committente:</td>
					<td><input type="text" name="committente" required="required">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="Crea campagna"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>