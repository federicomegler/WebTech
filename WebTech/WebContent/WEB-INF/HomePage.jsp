<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<a><c:out value="${nomeutente}"></c:out></a>
	<a href="Logout">Logout</a>
	<table>
		<c:forEach var="campagna" items="${listacampagna}">
			<tr>
				<td><a> <c:out value="${campagna}"></c:out>
				</a></td>
			</tr>
		</c:forEach>
	</table>

	<div>
		<form action="" method="post">
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