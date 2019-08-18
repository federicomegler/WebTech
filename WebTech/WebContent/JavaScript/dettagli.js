/**
 * 
 */
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
   mostrastep(stepcorrente)
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

function init(){
	
	  if(document.getElementById("stato").innerHTML != "creata"){
		  
			 document.getElementById("wizard").style.display= "none";
			  
		  }
	  
    var marker=L.marker([45.7802507654344,9.199769496808585]).addTo(mymap)
		.bindPopup("<b>Hello world!</b><br />I am a popup.").openPopup();
marker.on('click', onClick_Marker)}
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
	if(document.getElementById("locazione").value!=""){
         x=e.latlng   
         document.getElementById("lat").value=x.lat      
         document.getElementById("lon").value=x.lng
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
            newfunction(json)
						
        });
	
}
		
function newfunction(daticitta){

if(daticitta.address){
					if(daticitta.address.city){
			   document.getElementById("C").value=daticitta.address.city
				 }
				 else if(daticitta.address.village){
					document.getElementById("C").value=daticitta.address.village
				 }
				 else if(daticitta.address.town){
					document.getElementById("C").value=daticitta.address.town
				 }
				 else{					
					document.getElementById("C").value=''
				 }
				 
				 if(daticitta.address.country){document.getElementById("S").value=daticitta.address.country}
         else{document.getElementById("S").value=''
				 }
				 
				  if(daticitta.address.state){document.getElementById("R").value=daticitta.address.state}
					else if(daticitta.address.county){
						document.getElementById("R").value=daticitta.address.county
					}
         else{document.getElementById("R").value=''
				 }
}
else{
	document.getElementById("C").value=''
	document.getElementById("R").value=''
	document.getElementById("S").value=''
}


		}
		
function onClick_Marker(e) {
	    var marker = e.target;
	    x=marker.getLatLng();
         document.getElementById("lat").value=x.lat;
         document.getElementById("lon").value=x.lng;
	 reverseGeocode(x);
}

window.onload = function (){
	document.getElementById("locazione").addEventListener("input",function(e){
        this.className = "";
    },false);

    document.getElementById("locazione").addEventListener("change",function(e){
        cambiapopup();
    },false);

    document.getElementById("C").addEventListener("input", function(e){
        this.className = "";
    },false);

    document.getElementById("R").addEventListener("input",function(e){
        this.className = "";
    },false);

    document.getElementById("S").addEventListener("input",function(e){
        this.className = "";
    },false);

    document.getElementById("lat").addEventListener("input",function(e){
        this.className = "";
    },false);

    document.getElementById("lon").addEventListener("input",function(e){
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
	  
	  
	  
	  if(document.getElementById("stato").innerHTML != "creata"){
		  document.getElementById("VisualizzaMappa").style.display = "none";
	  }
	  else{
		  document.getElementById("VisualizzaMappa").style.display = "block";
	  }
	}