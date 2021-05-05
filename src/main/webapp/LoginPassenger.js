var mail;
var password;

function isUserLoggedIn(){
	if(localStorage.getItem("pilotID")!= null){
		var PilotAccountTemp = _.template($('#PilotAccountTemplate').html());
		var html = PilotAccountTemp();
		$("#accountSpace").append(html);
	}
	else if(localStorage.getItem("passengerID")!= null){
		var PassengerAccountTemp = _.template($('#PassengerAccountTemplate').html());
		var html = PassengerAccountTemp();
		$("#accountSpace").append(html);
	}
	else{
		var ConnectTemp = _.template($('#ConnectTemplate').html());
		var html = ConnectTemp();
		$("#accountSpace").append(html);
	}
}

function getServerData(type, url, fun){
    $.ajax({
		type: type,
		dataType: 'json',
		url:url
	}).done(fun)
}

function isUser(response){
	var data = JSON.parse(JSON.stringify(response));
	alert(JSON.stringify(data.message));
	if(data.id > 0){
		localStorage.setItem("passengerID",data.id);
		localStorage.removeItem("pilotID");
		
		if(localStorage.getItem("isBooking") == 1){
			alert("You will be redirected to your current reservation");
			document.location.href = "http://localhost:8080/Flight.html";
		}
		else{
			document.location.href = "http://localhost:8080/PassengerProfile.html";
		}
	}
}

$(function(){
	isUserLoggedIn();
	
	$("#logInButton").click(function(){
		mail = $('input[name="emailInput"]').val();
		password = $('input[name="passwordInput"]').val();
		getServerData('GET', 'webservice/passenger/logIn/'+mail+'/'+password, isUser);
	});
	$("#signUpButton").click(function(){
		document.location.href = "http://localhost:8080/NewPassenger.html";
	});
});