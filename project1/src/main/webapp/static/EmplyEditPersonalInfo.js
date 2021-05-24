// Retrieving the token we received from our Servlets
let token = sessionStorage.getItem("token");
console.log(token);
let tokenArr = token.split(":");
let baseUrl = "http://localhost:8080/ReimbursmentSystem/api/users/";
sendAjaxGet(baseUrl+tokenArr[0], displayName);


/*
 * if we are not redirected when checking for the token, a request will be made to 
 * the url for that particular user 
 */

function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			callback(this);
		} else if (this.readyState===4){
			//window.location.href="http://localhost:8080/ReimbursmentSystem/login";
		}
	}
	//Here the token is included into the request being made
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

function displayName(xhr){
	let user = JSON.parse(xhr.response);
	console.log(user);
	document.getElementById("username").value = user.username;
	document.getElementById("email").value = user.email;
	document.getElementById("firstName").value = user.firstName;
	document.getElementById("lastName").value = user.lastName;
	
}

function save(){
	

	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;
	let email = document.getElementById("email").value;
	let firstName = document.getElementById("firstName").value;
	let lastName = document.getElementById("lastName").value;
	let tokenArr = token.split(":");
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/ReimbursmentSystem/api/users";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			alert("Save Successfully!");
		} 
		else if (xhr.readyState == 4){
			// TODO fail
		}
	}
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Authorization",token);
	let requestBody = `username=${username}&password=${password}&email=${email}&firstName=${firstName}&lastName=${lastName}&userId=${tokenArr[0]}`;
	xhr.send(requestBody);
}