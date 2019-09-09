<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Cache-Control" content="no-store" />
<title>Profilo - ${nickname}</title>
<link
	href="https://fonts.googleapis.com/css?family=Lobster&display=swap"
	rel="stylesheet">
<link href="CSS/ProfiloStyle.css" rel="stylesheet" type="text/css">
<script src="JavaScript/modificaProfilo.js" type="text/javascript" defer></script>
</head>
<body>
<body background="/icone/sfondo.jpg">

	<div class="over" style="display: none;" id="confermaCambio">
		<div class="c_alert">
			<p>Verrà cambiata l'immagine di profilo!</p>
			<br>
			<div class="div_btn" style="width: auto;" id="ok">OK</div>
		</div>
	</div>

	<div class="over" style="display: none;" id="alert">
		<div class="c_alert">
			<p>Sei sicuro di voler eliminare l'immagine?</p>
			<br>
			<div class="div_btn" style="width: auto;" id="conferma">CONFERMA</div>
			<div class="div_btn" style="width: auto;" id="annulla">ANNULLA</div>
		</div>
	</div>

	<div class="topnav">
		<a style="float: left;" href="Home"><i class="fa fa-home" aria-hidden="true"></i> HOME</a> <a style="float: right;"
			href="Logout"><i class="fa fa-sign-out" aria-hidden="true"></i> LOGOUT</a>
	</div>

	<div class="container" id="principale">
		<h1>Profilo</h1>

		<div class="immagine">
			<img id="immprofilo" alt="Immagine profilo"
				src="/ImmaginiUtente/${immagine}" class="image">
			<div class="middle">
				<div class="div_btn" id="cambia">Cambia Immagine</div>
				<div class="div_btn" id="elimina">Elimina Immagine</div>
			</div>
			<form action="CambiaImmagineProfilo" method="post"
				enctype="multipart/form-data" id="formimmagine">
				<input id="selezionaimmagine" type="file" accept="image/*" name="nuovaimmagine"
					style="display: none"> <input id="infoelimina" type="text"
					name="eliminafoto" style="display: none">
			</form>
		</div>

		<div class="c_cont">

			<h4>
				Nickname:
				<c:out value="${nickname}"></c:out>
			</h4>

			<c:if test="${tipo != 'Manager' }">
				<h4>
					Esperienza:
					<c:out value="${esperienza}"></c:out>
				</h4>
			</c:if>

			<h4>
				Tipo Utente:
				<c:out value="${tipo}"></c:out>
			</h4>

			<h4 id="descrmail">Mail: "${mail}"</h4>

			<div id="modmail" style="display: none;">
				<div class="input">
					<i class="fa fa-user" aria-hidden="true"></i> <input type="text"
						id="mail" name="username" class="text" value="${mail}" disabled>
				</div>
				<label id="errore mail" class="err">Email non valida</label> <br>
			</div>
			<div class="c_cont">

				<button id="confermaemail" style="display: none; float: right;">Conferma</button>
				<button id="annullaemail" style="display: none; float: right;">Annulla</button>
				<button id="modificaemail" style="float: right;">Modifica
					Email</button>
				<br> <br>
				<button id="BottoneNuovaPassword" style="float: right;">Modifica
					password</button>

			</div>
		</div>



		<div class="c_cont" style="display: none;" id="FormPassword">
			<form id="formpassword" novalidate>
				<div class="input">
					<i class="fa fa-lock" aria-hidden="true"></i> <input id="vecchia"
						class="text" type="password" name="vecchiapassword" required>
					<span class="floating-label">Vecchia Password</span>
				</div>

				<label id="errore1" class="err">Password errata</label> <br>

				<div class="input">
					<i class="fa fa-lock" aria-hidden="true"></i> <input id="nuova1"
						class="text" type="password" name="nuovapassword1" required>
					<span class="floating-label">Nuova Password</span>
				</div>
				<br>
				<div class="input">
					<i class="fa fa-lock" aria-hidden="true"></i> <input id="nuova2"
						class="text" type="password" name="nuovapassword2" required>
					<span class="floating-label">Conferma Password</span>

				</div>
				<label id="errore2" class="err">Le password non
					corrispondono</label> <br><br>

			</form>

			<button id="annullaCambioPassword" style="float: right">Annulla</button>
			<button style="float: right" id="cambiaPassword">Cambia
				Password</button>

		</div>

	</div>
</body>
</html>