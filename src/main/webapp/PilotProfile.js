var args;
var flightList;

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
		"departureTime":		new Date(f.date).toDateString()+"    "+new Date(f.date).toLocaleTimeString(),
		"duration":				f.duration,
		"price":				f.price,
		"remainingSeats":		f.nbremainningseats,
	});
	$("#flightList").append(html);
}

function updatePage(response){
	flightList = response.list_of_flights;
	document.querySelector('input[name="firstName"]').value = response.firstName;
    document.querySelector('input[name="lastName"]').value = response.lastName;
    document.querySelector('input[name="email"]').value = response.mail;
    document.querySelector('input[name="password"]').value = response.password;
    document.querySelector('input[name="experience"]').value = response.experience;
    document.querySelector('input[name="qualifications"]').value = response.qualification;
    document.querySelector('input[name="nbFlightHours"]').value = response.number_of_flight_hours;
	

	if(flightList.length == 0 || flightList == null){
		var noResultTemp = _.template($('#noResultTemplate').html());
		var html = noResultTemp();
		$("#flightList").append(html);
	}
	else{
		for(const f of flightList){
			printFlight(f);
		}
	}
}

function getPilotInformation(type, url, fun){
	$.ajax({
		type: type,
		dataType: 'json',
		url:url
	}).done(fun)
}

function modifyPilot(type, url, fun){
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
	
	getPilotInformation('GET', 'webservice/pilot/getPilotInformation/'+localStorage.getItem("pilotID"), updatePage);
	
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
		modifyPilot('POST', 'webservice/pilot/modify/'+localStorage.getItem("pilotID"), modified);
	});

	$("#addFlightButton").click(function(){
		document.location.href = "http://localhost:8080/AddFlight.html";
	});

	$(document).on('click','.flight',function(event){
		localStorage.setItem("flightID",flightList[$(this).index()].id)
		document.location.href = "http://localhost:8080/Flight.html";
	});

	$("#logOutButton").click(function(){
		localStorage.removeItem("pilotID");
		document.location.href = "http://localhost:8080";
	});

});