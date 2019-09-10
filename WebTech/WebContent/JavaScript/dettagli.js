	document.getElementById("left").addEventListener("click",(e)=>{
		showImm(-1);
	}, false);


	document.getElementById("right").addEventListener("click",(e)=>{
		showImm(1);
	}, false);
	
	document.getElementById("dL").addEventListener("click",function(){
		showLoc(1);
		getImm();
	},false)
	
	document.getElementById("sL").addEventListener("click",function(){
		showLoc(-1);
		getImm();
	},false)

var currentimm=0;
var currentloc=0;
var listaloc;
var listaImm;
init();

function init(){
	  
	var x = new XMLHttpRequest();
	x.onreadystatechange= function (){
		
		if(x.readyState==4 && x.status==200){
			listaloc = JSON.parse(x.responseText);
			showLoc(0);
			getImm();
		}
	}
	x.open("GET", "\GetLocalita?idcampagna=" + document.getElementById("idcampagna").innerHTML,true)    
	x.send();
	
}



function showImm(n) {
	
	if(listaImm){
	if(listaImm.length > 0){

		document.getElementById("immagine").style.display= "block";
		if (currentimm+n > listaImm.length-1) {currentimm = 0} 
		else if (currentimm+n < 0) {currentimm = listaImm.length-1} 
		else {
			currentimm = currentimm+n;
		} 
		document.getElementById("immagine").src = "/ImmaginiCampagna/" + listaImm[currentimm].id + listaImm[currentimm].formato;
		document.getElementById("datiimmagine").innerHTML ="provenienza: " + listaImm[currentimm].provenienza + ", " + listaImm[currentimm].data_recupero + ", risoluzione: " + listaImm[currentimm].risoluzione;
	}
	}
}



function showLoc(n) {
	if(listaloc){
	if(listaloc.length > 0){	
	  if (currentloc+n > listaloc.length-1) {currentloc = 0} 
	  else if (currentloc+n < 0) {currentloc = listaloc.length-1} 
	  else {
		  currentloc = currentloc+n;
		  } 
	  document.getElementById("nomelocalita").innerHTML = (listaloc[currentloc]).nome;
	  	  }
	}
}

function getImm (){
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
				currentimm=0;
				showImm(0);
			}
		}
	}
	x.open("GET", "\GetDatiImmagine?idcampagna=" + document.getElementById("idcampagna").innerHTML+"&idlocalita="+listaloc[currentloc].ID_localita ,true); 
	x.send();
	
}
