/**
 * 
 */
function checkPassword(){
		var form = document.forms["form"];
		if(form.elements[2].value === form.elements[3].value){
			document.getElementById("errorePassword").innerHTML = "";
			return true;
		}
		else{
			document.getElementById("errorePassword").innerHTML = "Le password non coincidono";
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
	    	document.getElementById("erroreMail").innerHTML =  "";
	    	return true;	    	
	    }
	    else{
	    	document.getElementById("erroreMail").innerHTML =  "Mail non valida";
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