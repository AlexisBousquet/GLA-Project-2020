var args;

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

function sendNewPilot(type, url, fun){
    $.ajax({
		type: type,
		data: JSON.stringify(args),
		contentType: 'application/json',
		dataType: 'json',
		url:url
	}).done(fun)
}

function isUserValid(response){
	var data = JSON.parse(JSON.stringify(response));
	alert(JSON.stringify(data.message));
	if(data.id > 0){
		localStorage.setItem("pilotID",data.id);
		localStorage.removeItem("passengerID");
		document.location.href = "http://localhost:8080/PilotProfile.html";

	}
}

$(function(){
	isUserLoggedIn();
	
	$("#saveChangesButton").click(function(){
		args = {
			"firstName":				$('input[name="firstName"]').val(),
			"lastName":					$('input[name="lastName"]').val(),
			"mail":						$('input[name="email"]').val(),
			"password":					$('input[name="password"]').val(),
			"experience":				$('input[name="experience"]').val(),
			"qualification":			$('input[name="qualifications"]').val(),
			"number_of_flight_hours":	$('input[name="nbFlightHours"]').val()
		}
		sendNewPilot('PUT', 'webservice/pilot/signUp/', isUserValid);
	});
});