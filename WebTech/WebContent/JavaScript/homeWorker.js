/**
 * 
 */

window.onload = function(){

	document.getElementById("containeroptate").style.display = "none" ;
	document.getElementById("containernonoptate").style.display = "none" ;    //meglio nel css

	document.getElementById("optate").addEventListener("click",function(){
		var c = document.getElementById("containeroptate");
		if (c.style.display == "none") {
			c.style.display = "block"
				document.getElementById("containernonoptate").style.display = "none" ;
			getOptate() 
		} else { 
			c.style.display = "none" 
				c2=0
		}
	},false)

	document.getElementById("nonoptate").addEventListener("click",function(){
		var c = document.getElementById("containernonoptate");
		if (c.style.display == "none") {
			c.style.display = "block"
				document.getElementById("containeroptate").style.display = "none" ;
			getNonOptate() 
		} else { 
			c.style.display = "none" 
				c2=0
		}
	},false)

	
	document.getElementById("nomenonoptata").addEventListener("click",function(){
		if(listacreate.length == 0){return}
		
		document.getElementById("id").value = (listanonoptate[c2]).ID_campagna;
		document.getElementById("dett").submit()
		
	},false)
	
	document.getElementById("nomeoptata").addEventListener("click",function(){
		if(listacreate.length == 0){return}
		
		document.getElementById("id").value = (listaoptate[c1]).ID_campagna;
		document.getElementById("dett").submit();
		
	},false)
	
	document.getElementById("dNop").addEventListener("click",function(){
		showNonOptate(1);
	},false)

	document.getElementById("sNop").addEventListener("click",function(){
		showNonOptate(-1);
	},false)
	document.getElementById("dOp").addEventListener("click",function(){
		showOptate(1);
	},false)

	document.getElementById("sOp").addEventListener("click",function(){
		showOptate(-1);
	},false)
}

function getOptate (){

	var x = new XMLHttpRequest();
	x.onreadystatechange= function (){

		if(x.readyState==4 && x.status==200){
			listaoptate=JSON.parse(x.responseText)
			showOptate(0)
		}
	}
	x.open("GET", "\GetListaCampagneWorker?richiesta=optata",true)    
	x.send();
}


var c1=0;
var c2=0;
var listanonoptate, listaoptate;


function getNonOptate (){

	var x = new XMLHttpRequest();
	x.onreadystatechange= function (){

		if(x.readyState==4 && x.status==200){
			listanonoptate=JSON.parse(x.responseText)
			showNonOptate(0)
		}
	}
	x.open("GET", "\GetListaCampagneWorker?richiesta=nonoptata",true)    
	x.send();
}



function showOptate(n) {
	if(listaoptate.length > 0){	
		if (c1+n > listaoptate.length-1) {c1 = 0} 
		else if (c1+n < 0) {c1 = listaoptate.length-1} 
		else {
			c1 = c1+n;
		} 
		document.getElementById("nomeoptata").innerHTML = (listaoptate[c1]).nome;

	}
}

function showNonOptate(n) {
	if(listanonoptate.length > 0){	
		if (c2+n > listanonoptate.length-1) {c2 = 0} 
		else if (c2+n < 0) {c2 = listanonoptate.length-1} 
		else {
			c2 = c2+n;
		} 
		document.getElementById("nomenonoptata").innerHTML = (listanonoptate[c2]).nome;

	}
}
