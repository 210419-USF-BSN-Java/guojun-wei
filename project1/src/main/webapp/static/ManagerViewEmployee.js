// Retrieving the token we received from our Servlets
let token = sessionStorage.getItem("token");
console.log(token);
let tokenArr = token.split(":");
let baseUrl = "http://localhost:8080/ReimbursmentSystem/api/manager";
sendAjaxGet(baseUrl, display);


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

function display(xhr){
	let data = JSON.parse(xhr.response);
	let result = "";
	data.forEach(function(item,index){
		let tr = 
		`<tr>
		      <td>${item.reimbType}</td>
			  <td>${item.reimbAuthorName}</td>
		      <td>${item.reimbDescription}</td>
		      <td>${item.reimbStatus}</td>
		      <td>
		      <button onclick="accept(${item.reimbId})" type="button" class="btn btn-link">Accept</button>
		      <button onclick="reject(${item.reimbId})" type="button" class="btn btn-link">Reject</button>
		      </td>
		    </tr>
	    `;
		result += tr	
	})
	document.getElementById("dataBody").innerHTML = result;
	
}

function accept(id){
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/ReimbursmentSystem/api/manager";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			alert("Action Successfully!");
			sendAjaxGet(baseUrl, display);
		} 
		else if (xhr.readyState == 4){
			// TODO fail
		}
	}
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Authorization",token);
	let requestBody = `type=accept&reimbId=${id}`;
	xhr.send(requestBody);
}
