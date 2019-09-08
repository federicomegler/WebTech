window.onload = function(){

	init(); //prova marker
	document.getElementById("left").addEventListener("click",(e)=>{
		showImm(-1);
		getAnn();
	}, false);



	document.getElementById("right").addEventListener("click",(e)=>{
		showImm(1);
		getAnn();

	}, false);
}
var idlocalita;
var listaImm;
var c=0;
var greenIcon = L.icon({
	iconUrl: '/icone/green.png',
	shadowUrl: '/icone/ombra.png',

	iconSize:    [25, 41],
	iconAnchor:  [12, 41],
	popupAnchor: [1, -34],
	tooltipAnchor: [16, -28],
	shadowSize:  [41, 41]
});

var yellowIcon = L.icon({
	iconUrl: '/icone/yellow.png',
	shadowUrl: '/icone/ombra.png',

	iconSize:    [25, 41],
	iconAnchor:  [12, 41],
	popupAnchor: [1, -34],
	tooltipAnchor: [16, -28],
	shadowSize:  [41, 41]
});

var redIcon = L.icon({
	iconUrl: '/icone/red.png',
	shadowUrl: '/icone/ombra.png',

	iconSize:    [25, 41],
	iconAnchor:  [12, 41],
	popupAnchor: [1, -34],
	tooltipAnchor: [16, -28],
	shadowSize:  [41, 41]
});




var mymap = L.map('mapid');

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
	maxZoom: 18,
	attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
	'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
	'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
	id: 'mapbox.streets'
}).addTo(mymap);

function init(){

	var x = new XMLHttpRequest();
	x.onreadystatechange= function (){

		if(x.readyState==4 && x.status==200){
			var ris = JSON.parse(x.responseText);
			addMarkers(ris);
		}
	}
	x.open("GET", "\GetLocalita?idcampagna=" + document.getElementById("idcampagna").innerHTML,true)    
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

var bounds = new L.LatLngBounds();
var popup = L.popup();
var x
function onMapout(e){
	var marker= L.marker(x).addTo(mymap).bindPopup(document.getElementById("locazione").value).openPopup();
	marker.valueOf()._icon.style.color = 'green';
	marker.on('click', onClick_Marker);
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
			showAnn(ris);
		}
	}
	x.open("GET", "\getAnnotazioni?idimmagine=" + listaImm[c].id + "&idlocalita=" + idlocalita + "&idcampagna=" + document.getElementById("idcampagna").innerHTML,true);   
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
			var obj = JSON.parse(x.responseText);
			var errore = obj[1];
			if(errore){
				document.getElementById("errore").style.display = "block";
			}
			else{
				document.getElementById("errore").style.display = "none";
				listaImm = obj[0];
				c=0;
				showImm(0);
				getAnn();
			}
		}
	}
	x.open("GET", "\GetDatiImmagine?idcampagna=" + document.getElementById("idcampagna").innerHTML+"&idlocalita="+idlocalita ,true); 
	x.send();
}

function addMarkers (loc){
	var maxlat=-360;
	for(let i=0; i<loc.length; ++i){
		var marker;
		switch (loc[i].colore) {
		case "yellow":
			marker = L.marker([loc[i].latitudine,loc[i].longitudine], {icon: yellowIcon}).addTo(mymap)
			.bindPopup("<b>"+loc[i].nome+"</b>"); 
			break;
		case "green":
			marker = L.marker([loc[i].latitudine,loc[i].longitudine], {icon: greenIcon}).addTo(mymap)
			.bindPopup("<b>"+loc[i].nome+"</b>");
			break;
		case "red":
			marker = L.marker([loc[i].latitudine,loc[i].longitudine], {icon: redIcon}).addTo(mymap)
			.bindPopup("<b>"+loc[i].nome+"</b>"); 
			break;
		}
		marker.id=loc[i].ID_localita;
		marker.nome=loc[i].nome;
		marker.on('click', onClick_Marker)
		var coord=marker.getLatLng();
		if(maxlat<loc[i].latitudine){
			coord.lat=coord.lat+0.001;
			maxlat=coord.lat;
		}
		bounds.extend(coord);
		if(i == loc.length-1){
			marker.fire("click");
			marker.openPopup();
		}
	}
	 mymap.fitBounds(bounds);
}
