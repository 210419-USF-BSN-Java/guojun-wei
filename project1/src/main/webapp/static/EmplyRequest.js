let token = sessionStorage.getItem("token");

function save(){
	let reimbAmount = document.getElementById("reimbAmount").value;
	let reimbDescription = document.getElementById("reimbDescription").value;
	let reimbTypeId = document.getElementById("reimbTypeId").value;
	let tokenArr = token.split(":");
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/ReimbursmentSystem/api/reimb";
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
	let requestBody = `reimbAmount=${reimbAmount}&reimbDescription=${reimbDescription}&reimbTypeId=${reimbTypeId}&userId=${tokenArr[0]}`;
	xhr.send(requestBody);
}