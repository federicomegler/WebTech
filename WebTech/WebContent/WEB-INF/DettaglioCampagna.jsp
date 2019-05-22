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
<script src="JavaScript/dettagli.js" type="text/javascript" defer></script>
</head>
<body>

<br>
<div class="container">
        <div class="details">
            <p id="nome">nome</p>
            <p id="committente">committente</p>
            <p id="stato">stato</p>
        </div>
        
        <div class="wizard">
            
            <form id="addelement" action="ClearAll()" method="post">
                       
            
            <!-- step1 con localita esistente o si crea nuova-->
            <div class="step">
            <div id="formmappa">
                
              <div id="mapareacontainer">
                <div id="mapid" ></div>
              </div> 
                <div id="formareacontainer">
                 <p><input  id="locazione" placeholder="Località" oninput="this.className = ''" name="localita" oninput="this.className = ''" onchange="cambiapopup()"></p>
                 <p><input  id="C" placeholder="Comune" oninput="this.className = ''" name="comune"  oninput="this.className = ''" disabled></p>
                 <p><input  id="R" placeholder="Regione" oninput="this.className = ''" name="regione" oninput="this.className = ''" disabled></p>
                 <p><input  id="S" placeholder="Stato" oninput="this.className = ''" name="stato" oninput="this.className = ''" disabled></p>
                 <p><input  id="lat" placeholder="Latitudine" oninput="this.className = ''" name="latitudine"  oninput="this.className = ''" disabled></p>
                 <p><input  id="lon" placeholder="Longitudine" oninput="this.className = ''" name="longitudine" oninput="this.className = ''" disabled></p>
                </div>
               </div>
            </div>
            <!-- step2 inserimento dell'immagine-->
            <div class="step">
              <input id="i" type="file" oninput="this.className = ''">
              <div class="imagecontainer">
                <img src="" style="display: none" height="200" height="300" id="image">
              </div>
            </div>
            <!-- step3-->
            <div class="step">
                <p><input placeholder="Provenienza" oninput="this.className = ''" name="provenienza" oninput="this.className = ''"></p>
                <p><input type="date" placeholder="Data di recupero" oninput="this.className = ''" name="datarecupero" oninput="this.className = ''"></p>
                <p><input placeholder="Risoluzione" oninput="this.className = ''" name="risoluzione" oninput="this.className = ''"></p>
            </div>
             <div style="overflow:auto;">
                <div style="float:right;">
        <!-- invio alla funzione di quanto devo spostarmi in base a allo step che voglio vedere-->
                    <button type="button" id="precedente" onclick="Spostapos(-1)">Precedenete</button>
                    <button type="button" id="successivo" onclick="Spostapos(1)">Successivo</button>
                    <button type= "button" id="cancella"  onclick="ClearAll()">Clear</button>
                </div>
               </div>
            </form>
        </div>
    </div>
</body>
</html>