var a_dep = localStorage.getItem("dep");
var date_min = localStorage.getItem("min_d");
var date_max = localStorage.getItem("max_d");
var nbSeats = localStorage.getItem("nbSeats");
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

function getFlights(type, url, fun){
    $.ajax({
		type: type,
		dataType: 'json',
		url:url
	}).done(fun)
}
function displayFlights(response){
	flightList = JSON.parse(JSON.stringify(response));
	if(flightList.length == 0 || flightList == null){
		var noResultTemp = _.template($('#noResultTemplate').html());
		var html = noResultTemp();
		$("#flightList").append(html);
	}
	else{
		for(const elem of flightList){
			if(elem.nbremainningseats > 0){
				printFlight(elem);
			}
		}
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

$(function(){
	isUserLoggedIn();
	
	getFlights('GET', 'webservice/flight/searchFull/'+a_dep+'/'+date_min+'/'+date_max+'/'+nbSeats, displayFlights);

	$("#otherSearchButton").click(function(){
		document.location.href = "http://localhost:8080/Search.html";
		localStorage.removeItem("dep");
		localStorage.removeItem("min_d");
		localStorage.removeItem("max_d");
		localStorage.removeItem("nbSeats");
	});

	$(document).on('click','.flight',function(event){
		localStorage.setItem("flightID",flightList[$(this).index()].id)
		document.location.href = "http://localhost:8080/Flight.html";
	});
});