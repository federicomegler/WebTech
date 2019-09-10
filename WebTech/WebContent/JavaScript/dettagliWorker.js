window.onload = function(){

	init(); //prova marker

	document.getElementById("invia").addEventListener("click", (e) => {
		nuovaAnnotazione();
	},true);
	
	document.getElementById("left").addEventListener("click",(e)=>{
		showImm(-1);
		getAnn();
	}, false);

	document.getElementById("right").addEventListener("click",(e)=>{
		showImm(1);
		getAnn();
	}, false);

	// event listener src immagine get annotazioni
}
var idlocalita;
var listaImm;
var c=0;

var mymap = L.map('mapid').setView([45.7802507654344,9.199769496808585], 13);

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
	noWrap: true,
	maxZoom: 18,
	minZoom:1,
	attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
	'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
	'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
	id: 'mapbox.streets'
}).addTo(mymap);

var bounds = new L.LatLngBounds();

function init(){

	var x = new XMLHttpRequest();
	x.onreadystatechange= function (){

		if(x.readyState==4 && x.status==200){
			var ris = JSON.parse(x.responseText);
			addMarkers(ris);
		}
	}
	x.open("GET", "\GetLocalitaWorker?idcampagna=" + document.getElementById("idcampagna").innerHTML,true)    
	x.send();

}

function reverseGeocode(c) {
	fetch('http://nominatim.openstreetmap.org/reverse?format=json&lon=' + c.lng+ '&lat=' + c.lat)
	.then(function(response) {
		return response.json();
	}).then(function(json) {
		setInfoLocalita(json)			
	});

}

function setInfoLocalita(daticitta){

	if(daticitta.address){
		if(daticitta.address.city){
			document.getElementById("datilocalita").innerHTML = daticitta.address.city;
		}
		else if(daticitta.address.village){
			document.getElementById("datilocalita").innerHTML = daticitta.address.village;
		}
		else if(daticitta.address.town){
			document.getElementById("datilocalita").innerHTML = daticitta.address.town;
		}
		else{					
			document.getElementById("datilocalita").innerHTML = "unknown";
		}
		if(daticitta.address.state){
			document.getElementById("datilocalita").innerHTML = document.getElementById("datilocalita").innerHTML + ", " + daticitta.address.state;
		}
		else if(daticitta.address.county){
			document.getElementById("datilocalita").innerHTML = document.getElementById("datilocalita").innerHTML + ", " + daticitta.address.county;
		}
		else{
			document.getElementById("datilocalita").innerHTML = document.getElementById("datilocalita").innerHTML + ", unknown";
		}
		if(daticitta.address.country){
			document.getElementById("datilocalita").innerHTML = document.getElementById("datilocalita").innerHTML + ", " + daticitta.address.country;
		}
		else{
			document.getElementById("datilocalita").innerHTML = document.getElementById("datilocalita").innerHTML + ", unknown";
		}
	}
	else{
		document.getElementById("datilocalita").innerHTML = "UNKNOWN";
	}
}

function showAnn(listaann){
	var container = document.getElementById("annotazioni");
	while (container.firstChild) {
		container.removeChild(container.firstChild);
	}
	var titolo = document.createElement("h1");
	titolo.innerHTML = "Commenti";
	container.appendChild(titolo);
	for(var i=0; i<listaann.length; ++i){
		var c_container = document.createElement("div");
		c_container.id = "container";
		var c_utente = document.createElement("div"); // container utente
		c_utente.id = "nomeutente";
		c_utente.innerHTML = listaann[i].proprietario + ",       Exp: " + listaann[i].fiducia;
		c_container.appendChild(c_utente);
		c_note = document.createElement("div");
		c_note.id = "commento";
		c_note.innerHTML = listaann[i].note;  // container note
		c_container.appendChild(c_note);
		var c_info = document.createElement("div"); //&#9830
		c_info.id = "informazioni";
		var c_validita = document.createElement("div");
		c_validita.id = "colore";
		if(listaann[i].validita){
			c_validita.style.backgroundColor = "green";
		}
		else{
			c_validita.style.backgroundColor = "red";
		}
		c_info.appendChild(c_validita);
		c_info.innerHTML = c_info.innerHTML + " data creazione: " + listaann[i].data_creazione;
		c_container.appendChild(c_info);
		container.appendChild(c_container);
	}
}


var popup = L.popup();
var x
function onMapout(e){
	var marker= L.marker(x).addTo(mymap).bindPopup(document.getElementById("locazione").value).openPopup();
	marker.valueOf()._icon.style.color = 'green';
	marker.on('click', onClick_Marker);
}

function nuovaAnnotazione(){
	var param = "falso";
	var validita = document.getElementsByName("validita");
	if(validita[0].checked){
		param = "vero";
	}
	var x = new XMLHttpRequest();
	x.onreadystatechange= function (){

		if(x.readyState==4 && x.status==200){
			esito = JSON.parse(x.responseText);
			if(!esito){
				document.getElementById("errore").style.display = "block";
			}
			else{
				document.getElementById("errore").style.display = "none";
			}
			getAnn();
		}
	}
	x.open("POST", "\CreaCommento",true); 
	x.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	x.send("idcampagna=" + document.getElementById("idcampagna").innerHTML+"&idimmagine="+listaImm[c].id + "&nota=" + document.getElementById("nota").value + "&validita=" + param + "&idlocalita=" + idlocalita);
}

function showImm(n) {
	if(listaImm.length > 0){

		if (c+n > listaImm.length-1) {c = 0} 
		else if (c+n < 0) {c = listaImm.length-1} 
		else {
			c = c+n;
		} 
		document.getElementById("immagine").src = "/ImmaginiCampagna/" + listaImm[c].id + listaImm[c].formato;
		document.getElementById("datiimmagine").innerHTML ="provenienza: " + listaImm[c].provenienza + ", " + listaImm[c].data_recupero + ", risoluzione: " + listaImm[c].risoluzione;
	}}

function getAnn(){
	var x = new XMLHttpRequest();
	x.onreadystatechange= function (){

		if(x.readyState==4 && x.status==200){
			var ris = JSON.parse(x.responseText);
			if(ris[1]){
				document.getElementById("formAnnotazione").style.display = "none";
			}
			else{
				document.getElementById("formAnnotazione").style.display = "block";
				document.getElementById("nota").value="";
			}
			showAnn(ris[0]);
		}
	}
	x.open("GET", "\GetAnnotazioniWorker?idimmagine=" + listaImm[c].id + "&idlocalita=" + idlocalita + "&idcampagna=" + document.getElementById("idcampagna").innerHTML,true);   
	x.send();
}


function onClick_Marker(e) {
	var marker = e.target;
	coordinate = marker.getLatLng();
	idlocalita = marker.id;
	reverseGeocode(coordinate);
	var x = new XMLHttpRequest();
	x.onreadystatechange= function (){

		if(x.readyState==4 && x.status==200){
			listaImm = JSON.parse(x.responseText);
			c=0;
			showImm(0);
			getAnn();
		}
	}
	x.open("GET", "\GetDatiImmaginiWorker?idcampagna=" + document.getElementById("idcampagna").innerHTML+"&idlocalita="+idlocalita ,true); 
	x.send();
}

function addMarkers (loc){
	var maxlat=-360;
	var ref;
	for(let i=0; i<loc.length; ++i){
		var marker;
		marker = L.marker([loc[i].latitudine,loc[i].longitudine]).addTo(mymap)
		.bindPopup("<b>"+loc[i].nome+"</b>"); 

		marker.id=loc[i].ID_localita;
		marker.nome=loc[i].nome;
		marker.on('click', onClick_Marker)
		if(maxlat<loc[i].latitudine){
			maxlat=marker.getLatLng().lat;
			ref=marker.getLatLng().lng;
		}
		bounds.extend(marker.getLatLng());
		if(i == loc.length-1){
			var latlng = new L.latLng();
			latlng.lat=maxlat+0.02;
			latlng.lng=ref;
			bounds.extend(latlng);
			marker.fire("click");
			marker.openPopup();
		}
	}
	 mymap.fitBounds(bounds);
}
