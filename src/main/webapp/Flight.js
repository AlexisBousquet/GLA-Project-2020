var price;
var nbSeats;
function isUserLoggedIn(){
	if(localStorage.getItem("pilotID")!= null){
		var PilotAccountTemp = _.template($('#PilotAccountTemplate').html());
		var html = PilotAccountTemp();
		$("#accountSpace").append(html);
		$("#nbPassengers").hide();
		$("#bookFlight").hide();
		$("#totalPriceDiv").hide();
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

function getFlightInformation(type, url, fun){
	$.ajax({
		type: type,
		dataType: 'json',
		url:url
	}).done(fun)
}

function bookFlight(type, url, fun){
	$.ajax({
		type:type,
		dataType:'json',
		url:url
	}).done(fun);
}

function updatePage(response){
	$("#departureAirport").append(response.departure);
	$("#arrivalAirport").append(response.arrival);
	$("#departureDate").append(new Date(response.date).toDateString());
	$("#departureTime").append(new Date(response.date).toLocaleTimeString());
	$("#duration").append(response.duration);
	$("#price").append(response.price);
	price = response.price;
	$("#nbRemainingSeats").append(response.nbremainningseats);
	$("#information").append(response.informations);
	$("#pilot").append(response.pilot.id);
	$("#nbPassengers").append(localStorage.getItem("nbSeats"));
	$("#totalPrice").append(localStorage.getItem("nbSeats")*price);
}

$(function(){
	isUserLoggedIn();
	
	getFlightInformation('GET', 'webservice/flight/getFlightInformation/'+localStorage.getItem("flightID"), updatePage);

	$("#bookFlightButton").click(function(){
		$("#bookFlightButton").hide();
		if(localStorage.getItem("passengerID") != null){
			var reservTemp = _.template($('#reservationTemplate').html());
			var html = reservTemp();
			$("#container").append(html);
			localStorage.removeItem("isBooking");
			bookFlight('GET', 'webservice/flight/bookFlight/'+localStorage.getItem("flightID")+'/'+localStorage.getItem("passengerID")+'/'+localStorage.getItem("nbSeats"),null);
		}
		else{
			alert("Please log in before booking a flight");
			localStorage.setItem("isBooking",1);
			document.location.href = "http://localhost:8080/LoginPassenger.html";
		}
	});

	$(document).on('click','#homepageButton',function(event){
		document.location.href = "http://localhost:8080";
	});
});