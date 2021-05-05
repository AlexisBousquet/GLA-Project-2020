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


function sendNewFlight(type, url, fun){
    $.ajax({
		type: type,
		data: JSON.stringify(args),
		contentType: 'application/json',
		dataType: 'json',
		url:url
	}).done(fun)
}

function isFlightValid(response){
	var data = JSON.parse(JSON.stringify(response));
	alert(JSON.stringify(data.message));
	if(data.id > 0){
		document.location.href = "http://localhost:8080/PilotProfile.html";
	}
}

$(function(){
	isUserLoggedIn();
	
	$("#addFlightButton").click(function(){
		args = {
			"arrival":				$('input[name="arrivalAirport"]').val(),
			"departure":			$('input[name="departureAirport"]').val(),
			"date":					$('input[name="departureDate"]').val(),
			"duration":				$('input[name="duration"]').val(),
			"price":				$('input[name="price"]').val(),
			"nbremainningseats":	$('input[name="nbPassengers"]').val(),
			"informations":			$.trim($("textarea").val()),
			"pilot":				null,
			"passengers":			null
		}
		sendNewFlight('PUT', 'webservice/flight/add/'+localStorage.getItem("pilotID"), isFlightValid);
	});
});