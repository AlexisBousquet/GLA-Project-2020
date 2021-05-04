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
		$("#logInButton").hide();
	}
	else{
		var ConnectTemp = _.template($('#ConnectTemplate').html());
		var html = ConnectTemp();
		$("#accountSpace").append(html);
	}
}

$(function(){
	isUserLoggedIn();
	
	$("#logInButton").click(function(){
		document.location.href = "http://localhost:8080/LoginPassenger.html";
	});

	$("#searchFlightButton").click(function(){
		var departure = $('input[name="departureAirport"]').val();
		var date_min = $('input[name="date_min"]').val();
		var date_max = $('input[name="date_max"]').val();
		var nbSeats = $('input[name="nbSeats"]').val();

		localStorage.setItem("dep",departure);
		localStorage.setItem("min_d",date_min);
		localStorage.setItem("max_d",date_max);
		localStorage.setItem("nbSeats",nbSeats);

		document.location.href = "http://localhost:8080/SearchResults.html"
	});
});