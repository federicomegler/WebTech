var mymap = L.map('mapid').setView([45.7802507654344,9.199769496808585], 13);

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
	maxZoom: 18,
	attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
	'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
	'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
	id: 'mapbox.streets'
}).addTo(mymap);

function init(){
	var marker=L.marker([45.7802507654344,9.199769496808585]).addTo(mymap)
	.bindPopup("<b>Hello world!</b><br />I am a popup.").openPopup();
	marker.on('click', onClick_Marker)}
var popup = L.popup();
var x
init()

function onMapout(e){
	var marker= L.marker(x).addTo(mymap)
	.bindPopup(document.getElementById("locazione").value).openPopup();
	marker.on('click', onClick_Marker)
}

function onClick_Marker(e) {
	var marker = e.target;
	x=marker.getLatLng();
	document.getElementById("lat").value=x.lat;
	document.getElementById("lon").value=x.lng;
	reverseGeocode(x);
}