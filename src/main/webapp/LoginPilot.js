var username;
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
	if(data.ID > 0){
		localStorage.setItem("ID",data.ID);
		setTimeout(function(){document.location.href="http://localhost:8080/Homepage.html";}, 2000);

	}
}

$(function(){
	$("#logInButton").click(function(){
		username = $('input[name="emailInput"]').val();
		password = $('input[name="passwordInput"]').val();
		args = {
			"username":username,
			"password":password
		}
		getServerData('GET', 'webservice/pilot/logIn/'+username+'/'+password, isUser);
	});
	$("#signUpButton").click(function(){
		document.location.href="http://localhost:8080/NewPilot.html";
	});
});