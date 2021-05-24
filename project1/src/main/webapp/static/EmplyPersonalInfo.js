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
	document.getElementById("username").innerHTML = user.username;
	document.getElementById("email").innerHTML = user.email;
	document.getElementById("firstName").innerHTML = user.firstName;
	document.getElementById("lastName").innerHTML = user.lastName;
	
}

