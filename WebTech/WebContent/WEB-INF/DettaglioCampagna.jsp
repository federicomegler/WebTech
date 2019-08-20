<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>	
<title>Dettagli Campagna</title>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="CSS/DettagliCampagnaStyle.css">
<link rel="shortcut icon" type="image/x-icon" href="docs/images/favicon.ico">
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css" integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ==" crossorigin="anonymous" >
<script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet-src.js" integrity="sha512-IkGU/uDhB9u9F8k+2OsA6XXoowIhOuQL1NTgNZHY1nkURnqEGlDZq3GsfmdJdKFe1k1zOc6YU2K7qY+hF9AodA==" crossorigin="anonymous"></script>	
</head>
<body>

	<div class="topnav">
  		<a style="float: left;" href="Home">HOME</a>
  		<a style="float: left;" href="Profilo">PROFILO</a>
  		<a style="float: right;" href="Logout">LOGOUT</a>
	</div>
	
	<div class="container">
        <div class="details">
        	id campagna <p id="idcampagna">${id}</p>
            nome <p id="nomecampagna" >${nomecampagna}</p>
            committente <p id="committente">${committente}</p>
            stato <p id="stato">${stato}</p>
        </div>
        
        <button id="btnAvviaCampagna"> Avvia Campagna </button>
        <label id="erroreAvvio" style="display:none; color:red;"> Impossibile avviare campagna! </label><br>
        
       <form action="GetMap" method="post" id="VisualizzaMappa">
       		<input type="submit" value="Visualizza Mappa">
       </form>
        <br>
        
        
      <c:if test="${stato=='creata'}">  
      <script src="JavaScript/dettagli.js" type="text/javascript" defer></script>
        <div id="wizard">
            
            <form id="addelement" action="GestioneDatiWizard" method="post" enctype='multipart/form-data'>

     		<input type="text" id="id" name="idcampagna" style=" display: none" value="${idcampagna}">
            <!-- step1 con localita esistente o si crea nuova-->
            <div class="step">
            <div id="formmappa">
                
              <div id="mapareacontainer">
                <div id="mapid" ></div>
              </div> 
                <div id="formareacontainer">
                 <p><input type="text" id="locazione" placeholder="Località" name="localita" ></p>
                 <p><input type="text" id="C" placeholder="Comune" name="comune"  readonly></p>
                 <p><input type="text" id="R" placeholder="Regione" name="regione" readonly></p>
                 <p><input type="text" id="S" placeholder="Stato" name="Stato" readonly></p>
                 <p><input type="text" id="lat" placeholder="Latitudine" name="lat"  readonly></p>
                 <p><input type="text" id="lon" placeholder="Longitudine" name="lon" readonly></p>
                </div>
               </div>
            </div>
            <!-- step2 inserimento dell'immagine-->
            <div class="step">
              <input id="i" type="file" oninput="this.className = ''" name="file">
              <div class="imagecontainer">
                <img src="" style="display: none" height="200" height="300" id="image">
              </div>
            </div>
            <!-- step3-->
            <div class="step">
                <p><input type="text" id="provenienza" placeholder="Provenienza" name="provenienza"></p>
                <p><input type="date"  id="data" placeholder="Data di recupero" name="datarecupero"></p>
                <p><input type="text" id="risoluzione" placeholder="Risoluzione" name="risoluzione"></p>
            </div>
             <br><br>
                <div class="buttons">
        <!-- invio alla funzione di quanto devo spostarmi in base a allo step che voglio vedere-->
                    <button type="button" id="precedente">Precedenete</button>
                    <button type="button" id="successivo">Successivo</button>
                    <button type= "button" id="cancella">Clear</button>
                </div>
                <c:if test="${errore}">
                <label>errore dettaglio campagna</label>
                </c:if>
            </form>
        </div>
        </c:if>
    </div>
</body>
</html>