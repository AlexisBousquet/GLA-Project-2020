$(function(){
	$("#logInButton").click(function(){
		document.location.href="http://localhost:8080/LoginPassenger.html";
	});

	$("#searchFlightButton").click(function(){
		departure = $('input[name="departureAerodrom"]').val();
		date_min = $('input[name="date_min"]').val();
		date_max = $('input[name="date_max"]').val();

		localStorage.setItem("dep",departure);
		localStorage.setItem("min_d",date_min);
		localStorage.setItem("max_d",date_max);

		document.location.href = "http://localhost:8080/SearchResults.html"
	});
});