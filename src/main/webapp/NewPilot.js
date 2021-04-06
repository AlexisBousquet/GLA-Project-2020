var firstName;
var lastName;
var mail;
var password;
var experience;
var qualification;
var number_of_flight_hours;
var args;

function getServerData(type, url, fun){
    $.ajax({
		type: type,
		data: JSON.stringify(args),
		contentType: 'application/json',
		dataType: 'json',
		url:url
	}).done(fun)
}

function isUser(response){
	var data = JSON.parse(JSON.stringify(response));
	var templateExample = _.template($('#templateExample').html());
	var html = templateExample({
		"attribute":JSON.stringify(data.message)
	});
	$("#result").append(html);
	if(data.id > 0){
		localStorage.setItem("ID",data.id);
		setTimeout(function(){document.location.href="http://localhost:8080/PilotProfile.html";}, 2000);

	}
}

$(function(){
	$("#saveChangesButton").click(function(){

		firstName = $('input[name="firstName"]').val();
		lastName = $('input[name="lastName"]').val();
		mail = $('input[name="email"]').val();
		password = $('input[name="password"]').val();
		experience = $('input[name="experience"]').val();
		qualification = $('input[name="qualifications"]').val();
		number_of_flight_hours = $('input[name="nbFlightHours"]').val();

		args = {
			"firstName":firstName,
			"lastName":lastName,
			"mail":mail,
			"password":password,
			"experience":experience,
			"qualification":qualification,
			"number_of_flight_hours":number_of_flight_hours
		}
		getServerData('PUT', 'webservice/pilot/signUp/', isUser);
	});
});