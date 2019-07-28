/**
 * 
 */

window.onload = function(){
    document.getElementById("cambia").addEventListener("click", function(){
        CambiaImmagine();
    },false);

    document.getElementById("elimina").addEventListener("click",function(){
        document.getElementById("principale").className = "principaleOff";
        document.getElementById("alert").className = "alert";
    },false);

    document.getElementById("modificaemail").addEventListener("click",function(){
        abilitaModificaEmail();
    },false);

    document.getElementById("confermaemail").addEventListener("click",function(){
        confermaModificaEmail();
    },false);

    document.getElementById("annullaemail").addEventListener("click",function(){
        annullaModificaEmail();
    },false);

    document.getElementById("BottoneNuovaPassword").addEventListener("click",function(){
        CreaNuovaPassword();
    },false);

    document.getElementById("cambiaPassword").addEventListener("click",function(){
        ModificaPassword();
    },false);

    document.getElementById("annullaCambioPassword").addEventListener("click",function(){
    	AnnullaCambioPassword();
    },false);
    
    document.getElementById("conferma").addEventListener("click",function(){
    	document.getElementById("principale").className = "";
        document.getElementById("alert").className = "alertOff";
        eleminaImmagine();
    },false);
    
    document.getElementById("annulla").addEventListener("click",function(){
    	document.getElementById("principale").className = "";
        document.getElementById("alert").className = "alertOff";
    },false);
    
    document.getElementById("ok").addEventListener("click", function(){
    	confermaCambioImmagine();
    	document.getElementById("confermaCambio").className = "confermaCambioOff";
    },false);
    
    document.getElementById("selezionaimmagine").addEventListener("change", function(){
    	document.getElementById("confermaCambio").className = "confermaCambioOn";
    },false);
}

var email;
function CreaNuovaPassword(){

}
function CreaNuovaPassword(){
	document.getElementById("FormPassword").style.display = "block";
	document.getElementById("BottoneNuovaPassword").style.display = "none";
}

function AnnullaCambioPassword(){
	document.getElementById("FormPassword").style.display = "none";
	document.getElementById("BottoneNuovaPassword").style.display = "block";
	document.getElementById("formpassword").reset();
}
function ModificaPassword(){
	var password1 = document.getElementById("nuova1").value;
	var password2 = document.getElementById("nuova2").value;
	if(password1 == password2 && password1 !== ""){
		document.getElementById("formpassword").submit();
	}
	else{
		document.getElementById("formpassword").reset();
		document.getElementById("errore").style.display = "block";
		document.getElementById("nuova1").style.background = "#ffdddd";
		document.getElementById("nuova2").style.background = "#ffdddd";
		document.getElementById("nuova1").value = "";
		document.getElementById("nuova2").value = "";
	}
}

function CambiaImmagine(){
	document.getElementById("selezionaimmagine").click();
}

function confermaCambioImmagine(){
		document.getElementById("formimmagine").submit();
}

function abilitaModificaEmail(){
	email = document.getElementById("mail").value;
	document.getElementById("modificaemail").style.display = "none";
	document.getElementById("confermaemail").style.display = "block";
	document.getElementById("annullaemail").style.display = "block";
	document.getElementById("mail").disabled = false;
	email = document.getElementById("mail").value;
}

function annullaModificaEmail(){
	document.getElementById("modificaemail").style.display = "block";
	document.getElementById("confermaemail").style.display = "none";
	document.getElementById("annullaemail").style.display = "none";
	document.getElementById("errore mail").style.display = "none";
	document.getElementById("mail").disabled = true;
	document.getElementById("mail").value = email;
}

function confermaModificaEmail(){
	var nuovaemail = document.getElementById("mail").value;
	if(checkEmail(nuovaemail)){
		//submit
	}
	else{
		document.getElementById("errore mail").style.display = "block";
	}
}

function checkEmail(nuovaemail) {
	var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(re.test(nuovaemail)){
    	return true;	    	
    }
    else{
		return false;
    }
}

function eliminaImmagine(){
	
}
