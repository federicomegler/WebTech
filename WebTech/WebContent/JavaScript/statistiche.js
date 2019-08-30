/**
 * 
 */
window.onload = function(){
	var img = document.getElementsByTagName("img");
	if(img.length > 0){
		for(var i=0; i<img.length; ++i){
			img[i].addEventListener("click", (e) => {


				var x = new XMLHttpRequest();
				x.onreadystatechange= function (){

					if(x.readyState==4 && x.status==200){
						obj = JSON.parse(x.responseText);
						var listaAnn = obj[0];
						var loc = obj[1];
						document.getElementById("localitaimmagine").innerHTML = loc.nome + ", " + loc.comune + ", " + loc.regione + ", " + loc.stato;
						document.getElementById("immagineZoom").src = e.target.src;
						showAnn(listaAnn);
					}
				}
				x.open("GET", "\GetInfoStatistiche?idcampagna=" + document.getElementById("idcampagna").innerHTML+"&idimmagine="+e.target.id ,true); 
				x.send();
			}, false);
		}

		var e = document.createEvent("HTMLEvents");
		e.initEvent("click",false,true);
		img[0].dispatchEvent(e);
	}
}


function showAnn(listaann){
	var container = document.getElementById("annotazioni");
	while (container.firstChild) {
	    container.removeChild(container.firstChild);
	  }
	for(var i=0; i<listaann.length; ++i){
		var c_utente = document.createElement("div"); // container utente
		c_utente.innerHTML = listaann[i].proprietario + " Exp: " + listaann[i].fiducia;
		container.appendChild(c_utente);
		c_note = document.createElement("div");
		c_note.innerHTML = listaann[i].note;  // container note
		container.appendChild(c_note);
		var c_info = document.createElement("div"); //&#9830
		var c_validita = document.createElement("div");
		if(listaann[i].validita){
			c_validita.style.backgroundColor = "green";

		}
		else{
			c_validita.style.backgroundColor = "red";
		}
		c_info.appendChild(c_validita);
		c_info.innerHTML = " data creazione: " + listaann[i].data_creazione;
		container.appendChild(c_info);
	}
}
