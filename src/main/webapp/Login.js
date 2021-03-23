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

function callDone(result){
	var templateExample = _.template($('#templateExample').html());

	var html = templateExample({
		"attribute":JSON.stringify(result)
	});

	$("#result").append(html);
}

$(function(){
	$("#logInButton").click(function(){
		username = $('input[name="emailInput"]').val();
		password = $('input[name="passwordInput"]').val();
		args = {
			'username':username,
			'password':password
		}
		getServerData('GET', 'webservice/pilot/logIn/'+username+'/'+password, callDone);
	});
	$("#signUpButton").click(function(){
		username = $('input[name="emailInput"]').val();
		password = $('input[name="passwordInput"]').val();
		args = {
			'username':username,
			'password':password
		}
		getServerData('GET', 'webservice/pilot/signUp/'+username+'/'+password, callDone);
	});
});