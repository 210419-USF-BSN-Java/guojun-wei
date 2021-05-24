function initMenu(){
	let token = sessionStorage.getItem("token");
	console.log(token);
	let data = [];
	if(!token){
		window.location.href="http://localhost:8080/ReimbursmentSystem/login";
	} else {
		// In this case our token contains the user id for convenience
		let tokenArr = token.split(":");
		if ("1"==tokenArr[1]) {
			data = [
				{key:"Employee Home Page",icon:"home",path:"home"},
				{key:"Personal Info",icon:"file",path:"EmplyPersonalInfo"},
				{key:"Edit Personal Info",icon:"shopping-cart",path:"EmplyEditPersonalInfo"},
				{key:"Reimbursement Request",icon:"users",path:"EmplyRequest"},
				{key:"View Request Status",icon:"bar-chart-2",path:"EmplyViewRequest"},
				];
		}else if("2"==tokenArr[1]){
			data = [
				{key:"Manager Home Page",icon:"home",path:"ManagerHome"},
				{key:"View All Employee",icon:"file",path:"ManagerViewEmployee"},
				{key:"View Resolved Request",icon:"shopping-cart",path:"ManagerViewResolvedRequest"},
				];
		}
	}
	
	
	let menu = '';
	data.forEach(function(item, index){
		menu = menu + '<li class="nav-item">' + 
		'<a class="nav-link" href="'+item.path+'">' + 
		'<span data-feather="'+item.icon+'"></span>' +
				item.key +
			'</a>' + 
		'</li>';
	})
	document.getElementById("menu").innerHTML = menu;
}
initMenu();

function logout(){
	sessionStorage.clear();
	window.location.href="http://localhost:8080/ReimbursmentSystem/login";
}