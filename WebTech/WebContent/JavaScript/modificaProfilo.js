window.onload = function(){
    document.getElementById("cambia").addEventListener("click", function(){
        CambiaImmagine();
    },false);

    document.getElementById("elimina").addEventListener("click",function(){
        document.getElementById("alert").style.display = "block";
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
        document.getElementById("alert").style.display = "none";
        document.getElementById("infoelimina").value="true";
        document.getElementById("formimmagine").submit();
        
    },false);
    
    document.getElementById("annulla").addEventListener("click",function(){
        document.getElementById("alert").style.display = "none";
    },false);
    
    document.getElementById("ok").addEventListener("click", function(){
    	document.getElementById("confermaCambio").style.display = "none";
    	document.getElementById("infoelimina").value="false";
    	document.getElementById("formimmagine").submit();
    },false);
    
    document.getElementById("selezionaimmagine").addEventListener("change", function(){
    	document.getElementById("confermaCambio").style.display = "block";
    },false);
}

var email;

function CreaNuovaPassword(){
	document.getElementById("FormPassword").style.display = "block";
	document.getElementById("BottoneNuovaPassword").style.display = "none";
}

function AnnullaCambioPassword(){
	document.getElementById("FormPassword").style.display = "none";
	document.getElementById("BottoneNuovaPassword").style.display = "block";
	document.getElementById("formpassword").reset();
	document.getElementById("vecchia").style.background = "";
	document.getElementById("nuova1").style.background = "";
	document.getElementById("nuova2").style.background = "";
	document.getElementById("errore1").style.display = "none";
	document.getElementById("errore2").style.display = "none";
}

function ModificaPassword(){
	var passwordvecchia = document.getElementById("vecchia").value;
	var password1 = document.getElementById("nuova1").value;
	var password2 = document.getElementById("nuova2").value;
	if(password1 == password2 && password1 !== ""){
		//async
		
			var x = new XMLHttpRequest();
			x.onreadystatechange= function (){
				if(x.readyState==4 && x.status==200){
					var esito=JSON.parse(x.responseText);
					console.log(esito)
					console.log(esito[1])
					if(!(esito[0]||esito[1])){ // modifica esito positivo quindi true 	
						AnnullaCambioPassword()
					}
					else{
						
					  if(!esito[0]){
						    document.getElementById("errore1").style.display = "none";
						    document.getElementById("vecchia").style.background = "";
					  }
					  else{
						  mostraerrore1()
					  }
					  if(!esito[1]){
						    document.getElementById("errore2").style.display = "none";
						    document.getElementById("nuova1").style.background = "";
							document.getElementById("nuova2").style.background = "";
					  }
					  else{
						  mostraerrore2()
					  }
					  
					}
				}

			}
			
			x.open("POST", "\ModificaProfiloPassword",true)
			var param="p="+passwordvecchia+"&np1="+password1+"&np2="+password2
		    x.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		    x.send(param);
		}
	
	else{
		mostraerrore2();
	}
	
	}

function mostraerrore2(){
	document.getElementById("errore2").style.display = "block";
	document.getElementById("nuova1").style.background = "#ffdddd";
	document.getElementById("nuova2").style.background = "#ffdddd";
	document.getElementById("nuova1").value = "";
	document.getElementById("nuova2").value = "";
}

function mostraerrore1(){
	document.getElementById("errore1").style.display = "block";
	document.getElementById("vecchia").style.background = "#ffdddd";
	document.getElementById("vecchia").value = "";
}

function CambiaImmagine(){
	document.getElementById("selezionaimmagine").click();
}


function abilitaModificaEmail(){
	email = document.getElementById("mail").value;
	document.getElementById("modificaemail").style.display = "none";
	document.getElementById("confermaemail").style.display = "block";
	document.getElementById("annullaemail").style.display = "block";
	document.getElementById("modmail").style.display = "block";
	document.getElementById("descrmail").style.display = "none";
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
	document.getElementById("descrmail").innerHTML = "Mail: "+email;
	document.getElementById("modmail").style.display = "none";
	document.getElementById("descrmail").style.display = "block";
}

function confermaModificaEmail(){
	var nuovaemail = document.getElementById("mail").value;
	if(checkEmail(nuovaemail)){
		var x = new XMLHttpRequest();
		x.onreadystatechange= function (){
			
			if(x.readyState == 4 && x.status == 200){
				var infomail = x.responseText;
				if(infomail){ // modifica esito positivo quindi true 
					email=document.getElementById("mail").value
					annullaModificaEmail()
				}
				else{ 
					document.getElementById("errore mail").style.display = "block";
				}
				
			}
			
			
		}
		x.open("POST", "\ModificaProfiloMail",true)
		var param="nuovaemail="+nuovaemail
        
		  x.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		  
		  x.send(param);
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