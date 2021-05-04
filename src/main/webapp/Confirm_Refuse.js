function GetURLParameter(str)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == str) 
        {
            return sParameterName[1];
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

function printFlight(response){
	$("#departureAirport").append(response.departure);
	$("#arrivalAirport").append(response.arrival);
	$("#departureDate").append(new Date(response.date).toDateString());
	$("#departureTime").append(new Date(response.date).toLocaleTimeString());
	$("#price").append(response.price);
	$("#totalPrice").append(seats*response.price);
}

function printName(response){
	$("#customer").append(response.firstName+"   "+response.lastName);
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
	getServerData('GET', 'webservice/passenger/getPassengerInformation/'+response.passenger_id, printName);
	
}

function callDone(response){
	document.location.href = "http://localhost:8080";
}

$(function(){
	var idReservation = GetURLParameter("id");
	getServerData('GET', 'webservice/reservation/getReservationInfo/'+idReservation, updatePage);
	
	$("#confirmButton").click(function(){
		getServerData('PUT', 'webservice/reservation/confirmReservation/'+idReservation, callDone);
	});
	$("#refuseButton").click(function(){
		getServerData('PUT', 'webservice/reservation/refuseReservation/'+idReservation, callDone);
	});
});