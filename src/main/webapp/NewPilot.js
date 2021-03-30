var firstName;
var lastName;
var email;
var password;
var experience;
var qualifications;
var nbFlightHours;
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
	if(data.ID > 0){
		localStorage.setItem("ID",data.ID);
		setTimeout(function(){document.location.href="http://localhost:8080/Homepage.html";}, 2000);

	}
}

function callDone(response){
	var templateExample = _.template($('#templateExample').html());
	var html = templateExample({
		"attribute":"Your pilot account has been created !"
	});
	$("#result").append(html);
}

$(function(){
	$("#saveChangesButton").click(function(){

		firstName = $('input[name="firstName"]').val();
		lastName = $('input[name="lastName"]').val();
		email = $('input[name="email"]').val();
		password = $('input[name="password"]').val();
		experience = $('input[name="experience"]').val();
		qualifications = $('input[name="qualifications"]').val();
		nbFlightHours = $('input[name="nbFlightHours"]').val();

		args = {
			"firstName":firstName,
			"lastName":lastName,
			"email":email,
			"password":password,
			"experience":experience,
			"qualifications":qualifications,
			"nbFlightHours":nbFlightHours
		}
		getServerData('PUT', 'webservice/pilot/signUp/', callDone);
	});
});