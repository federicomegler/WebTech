/**
 * 
 */
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
	return false;
}
function ModificaPassword(){
	if(document.getElementById("nuova1").value == document.getElementById("nuova2").value){
		document.getElementById("formpassword").submit();
	}
	else{
		document.getElementById("errore").style.display = "block";
		document.getElementById("nuova1").style.background = "#ffdddd";
		document.getElementById("nuova2").style.background = "#ffdddd";
		document.getElementById("nuova1").value = "";
		document.getElementById("nuova2").value = "";
		return false;
	}
}

function CambiaImmagine(){
	document.getElementById("selezionaimmagine").click();
}

function confermaCambioImmagine(){
	document.getElementById("formimmagine").submit();
	return true;
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
