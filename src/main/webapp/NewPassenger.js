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

function getServerData(type, url, fun){
    $.ajax({
		type: type,
		data: JSON.stringify(args),
		contentType: 'application/json',
		dataType: 'json',
		url:url
	}).done(fun)
}

function isPassengerValid(response){
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
	
	$("#saveChangesButton").click(function(){
		args = {
			"firstName":	$('input[name="firstName"]').val(),
			"lastName":		$('input[name="lastName"]').val(),
			"password":		$('input[name="password"]').val(),
			"mail":			$('input[name="email"]').val()
		}
		getServerData('PUT', 'webservice/passenger/signUp/', isPassengerValid);
	});
});