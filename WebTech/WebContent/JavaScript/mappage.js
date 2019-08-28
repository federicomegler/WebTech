window.onload = function(){
	
	init(); //prova marker
	
	//event listener dei pulsanti che richiedono i dati delle relative foto
	
}
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




var mymap = L.map('mapid').setView([45.7802507654344,9.199769496808585], 13);

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
var popup = L.popup();
var x
function onMapout(e){
	var marker= L.marker(x).addTo(mymap)
	.bindPopup(document.getElementById("locazione").value).openPopup();
	marker.valueOf()._icon.style.color = 'green'
	marker.on('click', onClick_Marker)
}

function onClick_Marker(e) {
	var marker = e.target;
	x=marker.getLatLng();
	document.getElementById("lat").value=x.lat;
	document.getElementById("lon").value=x.lng;
	reverseGeocode(x);
}

function addMarkers (loc){
	console.log(loc);
	for(let i=0; i<loc.length; ++i){
		var marker;
		switch (loc[i].colore) {
		case "yellow":
			marker = L.marker([loc[i].latitudine,loc[i].longitudine], {icon: yellowIcon}).addTo(mymap)
			.bindPopup("<b>"+loc[i].nome+"</b>").openPopup(); 
			break;
		case "green":
			marker = L.marker([loc[i].latitudine,loc[i].longitudine], {icon: greenIcon}).addTo(mymap)
			.bindPopup("<b>"+loc[i].nome+"</b>").openPopup();
			break;
		case "red":
			marker = L.marker([loc[i].latitudine,loc[i].longitudine], {icon: redIcon}).addTo(mymap)
			.bindPopup("<b>"+loc[i].nome+"</b>").openPopup(); 
			break;
		}
		marker.on('click', onClick_Marker)
		console.log(loc);
	}
}
