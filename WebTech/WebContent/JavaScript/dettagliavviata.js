/**
 * 
 */

window.onload = function(){
	document.getElementById("btnChiudiCampagna").addEventListener("click", (e) => {
		  
		  var x = new XMLHttpRequest();
		  
		  x.onreadystatechange = function(){
			  if(x.readyState == 4 && x.status == 200){
				  var ris = JSON.parse(x.responseText);
				  if(ris){
					  location.reload();
				  }
				  else{
					  document.getElementById("erroreChiusura").style.display = "block";
				  }
			  }
		  }
		  var id = document.getElementById("idcampagna").innerHTML;
		  x.open("POST", "\ChiudiCampagna",true);
		  var param = "id="+id;
		  x.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		  x.send(param);
	  },false);
}