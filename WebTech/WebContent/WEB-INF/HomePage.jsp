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
  		<a style="float: left;" href="Profilo">PROFILO</a>
  		<a style="float: right;" href="Logout">LOGOUT</a>
	</div>
		
	<button id="create" class="Cbtn">Campagne create</button>
	<div id="containercreate" >
	<button id="nomecreata">Lista campagne vuota</button>
	<button id="sCr" class="left" >&#10094;</button>
    <button id="dCr" class="right">&#10095;</button>
	</div>
	
	<button id="avviate" class="Cbtn">Campagne avviate</button>
	<div id="containeravviate" >
	<button id="nomeavviata">Lista campagne vuota</button>
	<button id="sAv" class="left" >&#10094;</button>
    <button id="dAv" class="right">&#10095;</button>
	</div>
	
	<button id="chiuse" class="Cbtn">Campagne chiuse</button>
	<div id="containerchiuse" >
	<button id="nomechiusa">Lista campagne vuota</button>
	<button id="sCh" class="left" >&#10094;</button>
    <button id="dCh" class="right">&#10095;</button>
	</div>			

		<form id="dett" action="GetDettagli" method="get" style=" display: none">
            <input type="text" id="id" name="idcampagna">
			<input type="text" id="nome" name="nome">
            <input type="text" id="committente" name="committente">
            <input type="text" id="stato" name="stato">    
		</form>
		
	<div class="modulocreazione">
		<form action="CreaCampagna?stato=creata" method="post">
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
