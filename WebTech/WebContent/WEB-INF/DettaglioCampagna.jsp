<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Dettagli Campagna</title>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<link href="https://fonts.googleapis.com/css?family=Lobster&display=swap" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="CSS/DettagliCampagnaStyle.css">
<link rel="shortcut icon" type="image/x-icon"
	href="docs/images/favicon.ico">
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css"
	integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ=="
	crossorigin="anonymous">
<script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet-src.js"
	integrity="sha512-IkGU/uDhB9u9F8k+2OsA6XXoowIhOuQL1NTgNZHY1nkURnqEGlDZq3GsfmdJdKFe1k1zOc6YU2K7qY+hF9AodA=="
	crossorigin="anonymous"></script>
</head>
<body background="/icone/sfondo.jpg">
	<div class="topnav">
		<a style="float: left;" href="Home"><i class="fa fa-home" aria-hidden="true"></i> HOME</a> <a style="float: left;"
			href="Profilo"><i class="fa fa-user" aria-hidden="true"></i> PROFILO</a> 
			<a style="float: right;" href="Logout"><i class="fa fa-sign-out" aria-hidden="true"></i> LOGOUT</a>
	</div>

	<div class="container">
	<div class="top">
	
		<div class="details">
			<h2>ID Campagna</h2>
			<p id="idcampagna">${idcampagna}</p>
			<h2>Nome</h2>
			<p id="nomecampagna">${nomecampagna}</p>
			<h2>Committente</h2>
			<p id="committente">${committente}</p>
			<h2>Stato</h2>
			<p id="stato">${stato}</p>
		</div>
		<div class="details">
		<h2>Località</h2>
				<div id="containercreate">
			<button id="nomelocalita" class="elementlist">Lista localita
				vuota</button>
			<button id="sL" class="left">&#10094;</button>
			<button id="dL" class="right">&#10095;</button>
		</div>
				<label id="errore" style="color:red;display:none;">Errore caricamento immagini. Riprova!</label>
		<div id="immagini">
			<img alt="immagine" id="immagine" src="">
			<p id="datiimmagine"></p>
		</div>
					<button id="left">&#10094</button>
				
					<button id="right">&#10095</button>
		</div>
	</div>
		<br>
		<c:if test="${stato=='avviata' || stato == 'chiusa'}">
			<form action="GetMap" method="post" id="VisualizzaMappa">

				<input type="text" value="${idcampagna}" name="idcampagna"
					style="display: none"> <input type="submit"
					value="Visualizza Mappa" class="button">
			</form>
			
			<form action="GetStatistiche" method="post" id="VisualizzaStatistiche">

				<input type="text" value="${idcampagna}" name="idcampagna"
					style="display: none">
					 <input type="submit"
					value="Visualizza Statistiche" class="button">
			</form>
		</c:if>

		<c:if test="${stato=='avviata'}">
		<script src="JavaScript/dettagliavviata.js" type="text/javascript" defer></script>
			<button id="btnChiudiCampagna">Chiudi Campagna</button>
			<label id="erroreChiusura" style="display: none; color: red;">
			 Ops... Qualcosa non va </label>
			<br>
		</c:if>


		<c:if test="${stato=='creata'}">
			<button id="btnAvviaCampagna">Avvia Campagna</button>
			<label id="erroreAvvio" style="display: none; color: red;">
				Impossibile avviare campagna! </label>
			<br>

			<br>
			
			<h1>Inserisci una nuova località:</h1>
			<br>
			<script src="JavaScript/dettaglicreata.js" type="text/javascript" defer></script>
			<div id="wizard">

				<form id="addelement" action="GestioneDatiWizard" method="post"
					enctype='multipart/form-data'>

					<input type="text" id="id" name="idcampagna" style="display: none"
						value="${idcampagna}">
					<!-- step1 con localita esistente o si crea nuova-->
					<div class="step">
						<div id="formmappa">

							<div id="mapareacontainer">
								<div id="mapid"></div>
							</div>
							<div id="formareacontainer">
								<div class="input">
									<input type="text" id="locazione" placeholder="Località"
										name="localita">
								</div>
								<div class="input">
									<input type="text" id="C" placeholder="Comune" name="comune"
										readonly>
								</div>
								<div class="input">
									<input type="text" id="R" placeholder="Regione" name="regione"
										readonly>
								</div>
								<div class="input">
									<input type="text" id="S" placeholder="Stato" name="Stato"
										readonly>
								</div>
								<div class="input">
									<input type="text" id="lat" placeholder="Latitudine" name="lat"
										readonly>
								</div>
								<div class="input">
									<input type="text" id="lon" placeholder="Longitudine"
										name="lon" readonly>
								</div>
							</div>
						</div>
					</div>
					<!-- step2 inserimento dell'immagine-->
					<div class="step">
					
					<div class="c_input">
						<input id="i" type="file" accept="image/*" oninput="this.className = ''"
							name="file">
					</div>
						<div class="imagecontainer">
							<img src="" style="display: none; height:200px; width:300px;" id="image">
						</div>
					</div>
					<!-- step3-->
					<div class="step">
					<div class="c_input">
						<p>
							<input type="text" id="provenienza" placeholder="Provenienza"
								name="provenienza">
						</p>
						<p>
							<input type="date" id="data" placeholder="Data di recupero"
								name="datarecupero">
						</p>
						<p>
							<input type="text" id="risoluzione" placeholder="Risoluzione"
								name="risoluzione">
						</p>
					</div>	
					</div>
					<br>
					<br>
					<div class="buttons">
						<!-- invio alla funzione di quanto devo spostarmi in base a allo step che voglio vedere-->
						<button type="button" id="precedente">Precedenete</button>
						<button type="button" id="successivo">Successivo</button>
						<button type="button" id="cancella">Clear</button>
					</div>
					<c:if test="${errore}">
						<label>errore dettaglio campagna</label>
					</c:if>
				</form>
			</div>
		</c:if>
	</div>
<script src="JavaScript/dettagli.js" type="text/javascript" defer></script>
</body>
</html>