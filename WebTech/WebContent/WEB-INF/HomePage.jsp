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
<body background="/icone/sfondo.jpg">

	<div class="topnav">
		<a style="float: left;" href="Profilo">PROFILO</a> <a
			style="float: right;" href="Logout">LOGOUT</a>
	</div>
	<div class="container">
		<h1>Home</h1>

		<button id="create" class="Cbtn">Campagne create</button>
		<button id="avviate" class="Cbtn">Campagne avviate</button>
		<button id="chiuse" class="Cbtn">Campagne chiuse</button>
		<div id="containercreate">
			<button id="nomecreata" class="elementlist">Lista campagne
				vuota</button>
			<button id="sCr" class="left">&#10094;</button>
			<button id="dCr" class="right">&#10095;</button>
		</div>

		
		<div id="containeravviate">
			<button id="nomeavviata" class="elementlist">Lista campagne
				vuota</button>
			<button id="sAv" class="left">&#10094;</button>
			<button id="dAv" class="right">&#10095;</button>
		</div>

		
		<div id="containerchiuse">
			<button id="nomechiusa" class="elementlist">Lista campagne
				vuota</button>
			<button id="sCh" class="left">&#10094;</button>
			<button id="dCh" class="right">&#10095;</button>
		</div>

		<form id="dett" action="GetDettagli" method="post"
			style="display: none">
			<input type="text" id="id" name="idcampagna">
		</form>
		<h2>Crea una nuova campagna</h2>
		<div class="form">
		
			<form action="CreaCampagna?stato=creata" method="post">
				<div class="input">
					<i class="fa fa-user" aria-hidden="true"></i> <input type="text"
						name="nome" class="text" required> <span
						class="floating-label">Nome</span>
				</div>

				<div class="input">
					<i class="fa fa-user" aria-hidden="true"></i> <input type="text"
						name="committente" class="text" required> <span
						class="floating-label">Committente</span>
				</div>

				<input type="submit" value="Crea campagna" class="btn">
			</form>
			
		</div>
		<c:if test="${errore == 1}">
			<label style="color: red;">Errore nella richiesta</label>
		</c:if>
	</div>
</body>
</html>
