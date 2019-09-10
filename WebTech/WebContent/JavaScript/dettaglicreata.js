var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();
if(dd<10){
	dd='0'+dd
} 
if(mm<10){
	mm='0'+mm
} 

today = yyyy+'-'+mm+'-'+dd;
document.getElementById("data").setAttribute("max", today);
var stepcorrente=0;
mostrastep(stepcorrente)


function mostrastep(s){

	var step = document.getElementsByClassName("step")
	step[s].style.display="block"
		if(s==0){
			document.getElementById("precedente").style.display="none"
		}
		else{
			document.getElementById("precedente").style.display="inline"
		}
	if(s==step.length-1){
		document.getElementById("successivo").innerHTML="Conferma"
	}
	else{
		document.getElementById("successivo").innerHTML="Successivo"
	}
}


function Spostapos(pos){
	//recupero tutti gli elementi da vedere mettendoli in steps
	var steps=document.getElementsByClassName("step")
	if(pos==1 && !validateForm()){
		return false}
	steps[stepcorrente].style.display="none"
		stepcorrente=stepcorrente+pos
		if(stepcorrente==steps.length){
			document.getElementById("addelement").submit();
		}
		else{
			mostrastep(stepcorrente)
		}

}

function validateForm() {
	// This function deals with validation of the form fields
	var x, y, i, valid = true;
	x = document.getElementsByClassName("step");
	y = x[stepcorrente].getElementsByTagName("input");
	// A loop that checks every input field in the current tab:
	for (i = 0; i < y.length; i++) {
		// If a field is empty...
		if (y[i].value == "") {
			// add an "invalid" class to the field:
			if( y[i].className == "invalid"){}
			else  y[i].className += "invalid";
			// and set the current valid status to false:
			valid = false;
		}
	}
	return valid
}

function ClearAll(){
	document.getElementById("image").style.display="none";
	document.getElementById("addelement").reset()
	Spostapos(-stepcorrente)
	mymap.closePopup();
}

var loadFile= function (){

	if(this.files && this.files[0]){

		var obj = new FileReader();
		obj.onload = function(data){
			var image= document.getElementById("image");
			image.src = data.target.result;
			image.style.display="block";
		}
		obj.readAsDataURL(this.files[0]);

	}
	else{
		var image= document.getElementById("image");
		image.src = "";
		image.style.display="none";
	}

}

//mappa


var mymap = L.map('mapid').setView([45.7802507654344,9.199769496808585], 15);

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
	maxZoom: 18,
	attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
	'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
	'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
	id: 'mapbox.streets'
}).addTo(mymap);

var bounds = new L.LatLngBounds();



function addMarkers (loc){
	
	if(loc){
		if(loc.lenght>0){
	var maxlat=-360;
	var ref;
	for(let i=0; i<loc.length; ++i){
		var marker;
		marker = L.marker([loc[i].latitudine,loc[i].longitudine]).addTo(mymap)
		.bindPopup("<b>"+loc[i].nome+"</b>").openPopup(); 
		marker.id=loc[i].ID_localita;
		marker.nome=loc[i].nome;
		marker.on('click', onClick_Marker);
		if(maxlat<loc[i].latitudine){
			maxlat=marker.getLatLng().lat;
			ref=marker.getLatLng().lng
		}
		bounds.extend(marker.getLatLng());
	}
	var latlng = new L.latLng();
	latlng.lat=maxlat+0.01;
	latlng.lng=ref;
	bounds.extend(latlng);
	mymap.fitBounds(bounds);
}}
}



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
init()

function cambiapopup(){

	if(document.getElementById("lat").value!="" && document.getElementById("lon").value!="" ){
		popup
		.setLatLng(x)
		.setContent(document.getElementById("locazione").value)
		.openOn(mymap);
	}

}

function onMapClick(e) {
	if(document.getElementById("locazione").readonly == true){
		ClearAll();
		document.getElementById("locazione").readonly = false;
	}

	if(document.getElementById("locazione").value!=""){
		x=e.latlng   
		document.getElementById("lat").value=x.lat;
		document.getElementById("lat").className = "";
		document.getElementById("lon").value=x.lng
		document.getElementById("lon").className = "";
		reverseGeocode(x)
		cambiapopup()
	}
}


function onMapout(e){

	var marker= L.marker(x).addTo(mymap)
	.bindPopup(document.getElementById("locazione").value).openPopup();
	marker.on('click', onClick_Marker)
}

mymap.on('click', onMapClick);

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
			document.getElementById("C").value=daticitta.address.city
			document.getElementById("C").className = "";
		}
		else if(daticitta.address.village){
			document.getElementById("C").value=daticitta.address.village
			document.getElementById("C").className = "";
		}
		else if(daticitta.address.town){
			document.getElementById("C").value=daticitta.address.town
			document.getElementById("C").className = "";
		}
		else{					
			document.getElementById("C").value=''
				document.getElementById("C").className = "";
		}

		if(daticitta.address.country){
			document.getElementById("S").value=daticitta.address.country
			document.getElementById("S").className = "";
		}
		else{
			document.getElementById("S").value='';
				document.getElementById("S").className = "";
		}

		if(daticitta.address.state){
			document.getElementById("R").value=daticitta.address.state
			document.getElementById("R").className = "";
		}
		else if(daticitta.address.county){
			document.getElementById("R").value=daticitta.address.county
			document.getElementById("R").className = "";
		}
		else{
			document.getElementById("R").value=''
				document.getElementById("R").className = "";
		}
	}
	else{
		document.getElementById("C").value=''
			document.getElementById("C").className = "";
		document.getElementById("R").value=''
			document.getElementById("R").className = "";
		document.getElementById("S").value=''
			document.getElementById("S").className = "";
	}


}

function onClick_Marker(e) {
	var marker = e.target;
	console.log(marker.idloc)
	x=marker.getLatLng();
	document.getElementById("lat").value=x.lat;
	document.getElementById("lat").className = "";
	document.getElementById("lon").value=x.lng;
	document.getElementById("lon").className = "";
	document.getElementById("locazione").value=marker.nome;
	document.getElementById("locazione").readonly= true;
	document.getElementById("locazione").className = "";
	reverseGeocode(x);
}

window.onload = function (){

	if(document.getElementById("stato").innerHTML != "creata"){
		document.getElementById("btnAvviaCampagna").style.display = "none";
	}


	document.getElementById("locazione").addEventListener("input",function(e){
		this.className = "";
	},false);

	document.getElementById("i").addEventListener("input",function(e){
		this.className = "";
	},false);

	document.getElementById("provenienza").addEventListener("input",function(e){
		this.className = "";
	},false);

	document.getElementById("data").addEventListener("input",function(e){
		this.className = "";
	},false);

	document.getElementById("risoluzione").addEventListener("input",function(e){
		this.className = "";
	},false);

	document.getElementById("precedente").addEventListener("click", (e) => {
		Spostapos(-1);
	},false);
	document.getElementById("successivo").addEventListener("click", (e) => {
		Spostapos(1);
	},false);
	document.getElementById("cancella").addEventListener("click", (e) => {
		ClearAll();
	},false);

	document.getElementById("i").addEventListener("change",loadFile)

	document.getElementById("btnAvviaCampagna").addEventListener("click", (e) => {
		//avvia la campagna

		var x = new XMLHttpRequest();

		x.onreadystatechange = function(){
			if(x.readyState == 4 && x.status == 200){
				var ris = JSON.parse(x.responseText);
				if(ris){
					location.reload();
				}
				else{
					document.getElementById("erroreAvvio").style.display = "block";
				}
			}
		}
		var id = document.getElementById("idcampagna").innerHTML;
		x.open("POST", "\AvviaCampagna",true);
		var param = "id="+id;
		x.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		x.send(param);
	},false);
}