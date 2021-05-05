var seats;

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

function printFlight(response){
	$("#departureAirport").append(response.departure);
	$("#arrivalAirport").append(response.arrival);
	$("#departureDate").append(new Date(response.date).toDateString());
	$("#departureTime").append(new Date(response.date).toLocaleTimeString());
	$("#price").append(response.price);
	$("#totalPrice").append(seats*response.price);
}

function updatePage(response){
	if(response.status == 0){
		$("#reservationStatus").append("waiting for confirmation");
	}
	else{
		$("#reservationStatus").append("confirmed");
	}
	
	$("#nbPassengers").append(response.seats);
	seats = response.seats;
	getServerData('GET', 'webservice/flight/getFlightInformation/'+response.flight_id, printFlight);
	
}

$(function(){
	isUserLoggedIn();
	
	getServerData('GET', 'webservice/reservation/getReservationInfo/'+localStorage.getItem("reservationID"), updatePage);
});