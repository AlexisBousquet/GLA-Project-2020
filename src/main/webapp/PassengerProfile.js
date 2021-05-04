var args;
var nbSeats=0;
var reservationList;

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

function printFlight(f){
	var flightTemp = _.template($('#flightTemplate').html());
	var html = flightTemp({
		"departureAirport":	f.departure,
		"arrivalAirport":		f.arrival,
		"departureTime":		new Date(f.date).toDateString()+"    "+new Date(f.date).toLocaleTimeString().split(":")[0]+":"+new Date(f.date).toLocaleTimeString().split(":")[1],
		"duration":				f.duration,
		"price":				nbSeats*f.price,
		"seats":				nbSeats
	});
	$("#flightList").append(html);
}

function updatePage(response){
	document.querySelector('input[name="firstName"]').value = 	response.firstName;
	document.querySelector('input[name="lastName"]').value = 	response.lastName;
	document.querySelector('input[name="email"]').value = 		response.mail;
	document.querySelector('input[name="password"]').value = 	response.password;

	reservationList = response.list_of_reservations;
	if(reservationList.length == 0 || reservationList == null){
		var noResultTemp = _.template($('#noResultTemplate').html());
		var html = noResultTemp();
		$("#flightList").append(html);
	}
	else{
		for(const r of reservationList){
			nbSeats = r.seats;
			getServerData('GET', 'webservice/flight/getFlightInformation/'+r.flight_id, printFlight);
		}
	}
}

function getServerData(type, url, fun){
	$.ajax({
		type: type,
		dataType: 'json',
		url:url
	}).done(fun)
}

function modifyPassenger(type, url, fun){
	$.ajax({
		type: type,
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(args),
		url:url
	}).done(fun)
}

function modified(response){
	location.reload();
}

$(function(){
	isUserLoggedIn();
	
	getServerData('GET', 'webservice/passenger/getPassengerInformation/'+localStorage.getItem("passengerID"), updatePage);

	$("#saveChangesButton").click(function(){
		args = {
			"firstName":	$('input[name="firstName"]').val(),
			"lastName":		$('input[name="lastName"]').val(),
			"mail":			$('input[name="email"]').val(),
			"password":		$('input[name="password"]').val(),
		}
		modifyPassenger('POST', 'webservice/passenger/modify/'+localStorage.getItem("passengerID"), modified);
	});

	$(document).on('click','.flight',function(event){
		localStorage.setItem("reservationID",reservationList[$(this).index()].flight_id);
		document.location.href = "http://localhost:8080/Reservation.html";
	});

	$("#logOutButton").click(function(){
		localStorage.removeItem("passengerID");
		localStorage.removeItem("isBooking");
		localStorage.removeItem("dep");
		localStorage.removeItem("min_d");
		localStorage.removeItem("max_d");
		localStorage.removeItem("flightID");
		localStorage.removeItem("reservationID");
		localStorage.removeItem("nbSeats");
		document.location.href = "http://localhost:8080";
	});
});