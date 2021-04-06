var a_dep = localStorage.getItem("dep");
var date_min = localStorage.getItem("min_d");
var date_max = localStorage.getItem("max_d");

function getServerData(type, url, fun){
    $.ajax({
		type: type,
		dataType: 'json',
		url:url
	}).done(fun)
}
function displayFlights(response){
	var data = JSON.parse(JSON.stringify(response));
	if(data.length == 0 || data == null){
		var noResultTemp = _.template($('#noResultTemplate').html());
		var html = noResultTemp();
		$("#flightList").append(html);
	}
	else{
		for(const elem of data){
			printFlight(elem);
		}
	}
	
}

function printFlight(f){
	var flightTemp = _.template($('#flightTemplate').html());
	var html = flightTemp({
		"departureAerodrom":JSON.stringify(f.departure),
		"arrivalAerodrom":JSON.stringify(f.arrival),
		"departureTime":new Date(f.date),
		"duration":JSON.stringify(f.duration),
		"price":JSON.stringify(f.price),
		"remainingSeats":JSON.stringify(f.nbremainningseats),
	});
	$("#flightList").append(html);
}

$(function(){
	$("#otherSearchButton").click(function(){
		document.location.href="http://localhost:8080/Search.html";
	});

	getServerData('GET', 'webservice/flight/searchFull/'+a_dep+'/'+date_min+'/'+date_max, displayFlights);

});