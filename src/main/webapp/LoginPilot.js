var mail;
var password;
var args;

function isUserLoggedIn(){
	if(localStorage.getItem("pilotID")!= null){
		var PilotAccountTemp = _.template($('#PilotAccountTemplate').html());
		var html = PilotAccountTemp();
		$("#accountSpace").append(html);
		return 2;
	}
	else if(localStorage.getItem("passengerID")!= null){
		var PassengerAccountTemp = _.template($('#PassengerAccountTemplate').html());
		var html = PassengerAccountTemp();
		$("#accountSpace").append(html);
		return 1;
	}
	else{
		var ConnectTemp = _.template($('#ConnectTemplate').html());
		var html = ConnectTemp();
		$("#accountSpace").append(html);
		return 0;
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
		localStorage.setItem("pilotID",data.id);
		localStorage.removeItem("passengerID");
		document.location.href = "http://localhost:8080/PilotProfile.html";
	}
}

$(function(){
	var userCategory = isUserLoggedIn();
	if(userCategory == 2){
		document.location.href = "http://localhost:8080/PilotProfile.html";
	}

	$("#logInButton").click(function(){
		mail = $('input[name="emailInput"]').val();
		password = $('input[name="passwordInput"]').val();
		
		getServerData('GET', 'webservice/pilot/logIn/'+mail+'/'+password, isUser);
	});
	$("#signUpButton").click(function(){
		document.location.href = "http://localhost:8080/NewPilot.html";
	});
});