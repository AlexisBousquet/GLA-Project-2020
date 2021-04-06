var firstName;
var lastName;
var email;
var password;
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
		setTimeout(function(){document.location.href="http://localhost:8080/Search.html";}, 2000);

	}
}

$(function(){
	$("#saveChangesButton").click(function(){

		firstName = $('input[name="firstName"]').val();
		lastName = $('input[name="lastName"]').val();
		email = $('input[name="email"]').val();
		password = $('input[name="password"]').val();

		args = {
			"firstName":firstName,
			"lastName":lastName,
			"email":email,
			"password":password,
		}
		getServerData('PUT', 'webservice/passenger/signUp/', isUser);
	});
});