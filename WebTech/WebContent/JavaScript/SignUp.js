/**
 * 
 */
window.onload = function(){
	document.getElementById("username").addEventListener("keyup", (e) => {
		if(document.getElementById("username").value != ""){
			esisteUsername(document.getElementById("username").value);
		}
	}, false);
	
	document.getElementById("mail").addEventListener("keyup", (e) => {
		if(document.getElementById("mail").value != ""){
			esisteMail(document.getElementById("mail").value);
		}
	}, false);
	
	document.getElementById("password1").addEventListener("keyup",(e)=>{
		
		checkPassword()
		
	},false)

		document.getElementById("password2").addEventListener("keyup",(e)=>{
		
		checkPassword()
		
	},false)
	
}

function esisteUsername(username){
	var x = new XMLHttpRequest();
	
	x.onreadystatechange = function(){
		if(x.readyState == 4 && x.status == 200){
			var esito = JSON.parse(x.responseText);
			if(esito){
				document.getElementById("esisteutente").style.diplay = "block";
				document.getElementById("esisteutente").src = "/icone/cancel.png";
			}
			else{
				document.getElementById("esisteutente").style.diplay = "block";
				document.getElementById("esisteutente").src = "/icone/checked.png";
				}
		}
	}
	x.open("GET","\GetUtente?username=" + username, true);
	x.send();
}


function esisteMail(email){
	var x = new XMLHttpRequest();
	x.onreadystatechange = function(){
		if(x.readyState == 4 && x.status == 200){
			var esito = JSON.parse(x.responseText);
			if(esito || !checkEmail()){
				document.getElementById("esistemail").style.diplay = "block";
				document.getElementById("esistemail").src = "/icone/cancel.png";
			}
			else{
				document.getElementById("esistemail").style.diplay = "block";
				document.getElementById("esistemail").src = "/icone/checked.png";
			}
		}
	}
	x.open("GET","\GetEmail?email=" + email, true);
	x.send();
}


function checkPassword(){
		var password1 = document.getElementById("password1") ;
		var password2 = document.getElementById("password2") ;

		if(password1.value === password2.value){
			document.getElementById("errorePassword").style.diplay = "block";
			document.getElementById("errorePassword").src = "/icone/checked.png";

			return true;
		}
		else{
			document.getElementById("errorePassword").style.diplay = "block";
			document.getElementById("errorePassword").src = "/icone/cancel.png";

			return false;
		}
	}	
	
	function checkCredenziali(){
		
		var cp = checkPassword();
		var cm = checkEmail();
		return cp && cm
		
	}
	
	function checkEmail() {
	    var form = document.getElementById("form");
	    var email = form.elements[1].value;
		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    if(re.test(email)){
	    	document.getElementById("esistemail").innerHTML =  "";
	    	return true;	    	
	    }
	    else{
	    	document.getElementById("esistemail").innerHTML =  "Mail non valida";
	    	document.getElementById("esistemail").style.color = "red";
			return false;
	    }
	}
	
	function mostraPassword(){
		if(document.getElementById("password1").getAttribute("type") === "password"){
	        document.getElementById("password1").setAttribute("type","text");
	        document.getElementById("password2").setAttribute("type","text");
	        document.getElementById("mostraPassword").innerHTML = "Nascondi password";
	    }
	    else{
	        document.getElementById("password1").setAttribute("type","password");
	        document.getElementById("password2").setAttribute("type","password");
	        document.getElementById("mostraPassword").innerHTML = "Mostra password";
	    }
	}