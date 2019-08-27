/**
 * 
 */
window.onload= function(){
	document.getElementById("containercreate").style.display = "none" ; //meglio nel css
	document.getElementById("containerchiuse").style.display = "none" ;
	document.getElementById("containeravviate").style.display = "none" ;
	document.getElementById("create").addEventListener("click",function(){
		var c = document.getElementById("containercreate");
		if (c.style.display == "none") {
			c.style.display = "block"
			document.getElementById("containerchiuse").style.display = "none" ;
			document.getElementById("containeravviate").style.display = "none" ;
			getCreate()
		} else { 
				  c.style.display = "none" 
				  c1=0  
			  }
			
		
	},false)
	
	document.getElementById("avviate").addEventListener("click",function(){
		var c = document.getElementById("containeravviate");
		  if (c.style.display == "none") {
			  c.style.display = "block"
				document.getElementById("containercreate").style.display = "none" ;
				document.getElementById("containerchiuse").style.display = "none" ;
			   getAvviate() 
			  } else { 
				  c.style.display = "none" 
				  c2=0
			  }
	},false)
		
	document.getElementById("chiuse").addEventListener("click",function(){
		
		var c = document.getElementById("containerchiuse");
		  if (c.style.display == "none") {
			  	c.style.display = "block"
			  	document.getElementById("containercreate").style.display = "none" ;
				document.getElementById("containeravviate").style.display = "none" ;
			  	getChiuse()
			  } else { 
				c.style.display = "none" 
			    c3=0
			  }
	},false)
	
	document.getElementById("nomecreata").addEventListener("click",function(){
		if(!listacreate){return}
		
		document.getElementById("id").value = (listacreate[c1]).ID_campagna;
		document.getElementById("dett").submit()
		
	},false)	

	document.getElementById("nomeavviata").addEventListener("click",function(){
		if(!listaavviate){return}
		document.getElementById("id").value = (listaavviate[c2]).ID_campagna;
		document.getElementById("dett").submit()
	},false)
	
	document.getElementById("nomechiusa").addEventListener("click",function(){
		if(!listachiuse){return}
		document.getElementById("id").value = (listachiuse[c3]).ID_campagna;
		document.getElementById("dett").submit()
		
	},false)
	
	document.getElementById("dCr").addEventListener("click",function(){
		showCreate(1)
	},false)
	
	document.getElementById("sCr").addEventListener("click",function(){
		showCreate(-1)
	},false)	
	
		document.getElementById("dAv").addEventListener("click",function(){

			showAvviate(1)
	},false)
	
	document.getElementById("sAv").addEventListener("click",function(){

		showAvviate(-1)
		
	},false)		
	
	
	document.getElementById("dCh").addEventListener("click",function(){

		 showChiuse(1) 
		
	},false)
	
	document.getElementById("sCh").addEventListener("click",function(){

		 showChiuse(-1) 
		
	},false)		
	
}
var listacreate
var listaavviate
var listachiuse
var c1=0,c2=0,c3=0

function getCreate (){
	
	var x = new XMLHttpRequest();
	x.onreadystatechange= function (){
		
		if(x.readyState==4 && x.status==200){
			listacreate=JSON.parse(x.responseText)
			showCreate(0)
		}
	}
	x.open("GET", "\GetListeCampagne?stato=creata",true)    
	x.send();
	
	
}

function getAvviate (){
	
	var x = new XMLHttpRequest();
	x.onreadystatechange= function (){
		
		if(x.readyState==4 && x.status==200){
			listaavviate=JSON.parse(x.responseText)
			showAvviate(0)
		}
	}
	x.open("GET", "\GetListeCampagne?stato=avviata",true)    
	x.send();
	
}

function getChiuse (){
	
	var x = new XMLHttpRequest();
	x.onreadystatechange= function (){
		
		if(x.readyState==4 && x.status==200){
	
			listachiuse=JSON.parse(x.responseText)
			showChiuse(0)
			
		}
	}
	x.open("GET", "\GetListeCampagne?stato=chiusa",true)    
	x.send();
	
}

function showCreate(n) {
	if(listacreate){	
	  if (c1+n > listacreate.length-1) {c1 = 0} 
	  else if (c1+n < 0) {c1 = listacreate.length-1} 
	  else {
		  c1 = c1+n;
		  } 
	  document.getElementById("nomecreata").innerHTML = (listacreate[c1]).nome;
	  	  
	}
	
	}

function showAvviate(n) {
	if(listaavviate){
		
		  if (c2+n > listaavviate.length-1) {c2 = 0} 
		  else if (c2+n < 0) {c2 = listaavviate.length-1} 
		  else {
			  c2 = c2+n;
			  }	  
		document.getElementById("nomeavviata").innerHTML = (listaavviate[c2]).nome;
		
		}
	}

function showChiuse(n) {
	if(listachiuse){
		
		  if (c3+n > listachiuse.length-1) {c3 = 0} 
		  else if (c3+n < 0) {c3 = listachiuse.length-1} 
		  else {
			  c3 = c3+n;
			  } 
		  document.getElementById("nomechiusa").innerHTML = (listachiuse[c3]).nome;
			
		}}
