const signupButton = document.querySelector(".signup-button");
const signinButton = document.querySelector(".signin-button");
const inputs = document.querySelectorAll("input");

let checkUsernameFlag = false;

signinButton.onclick = () => {
	location.href = "/auth/signin";
}

inputs[2].onblur = () => {
	$.ajax({
		async:false,
		type: "get",
		url: "/api/v1/auth/signup/validation/username",
		data: {username: inputs[2].value},
		dataType: "json",
		success: (response) => {
			checkUsernameFlag = response.data;
			
			if(response.data) {
				alert("사용가능한 아이디입니다.");
			}else {
				alert("이미 가입된 아이디입니다.")
			}
		},
		error: errorMsg
	});
}

signupButton.onclick = () => {
	
	let signupData = {
		name: inputs[0].value,
		email: inputs[1].value,
		username: inputs[2].value,
		password: inputs[3].value,
		"checkUsernameFlag":checkUsernameFlag
	}
	
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/auth/signup",
		contentType: "application/json",
		data: JSON.stringify(signupData),
		dataType: "json",
		success: (response) => {
			if(response.data) {
				alert("회원가입완료");
				location.replace("/auth/signin");
			}
		},
		error: errorMsg
	});
}

	function errorMsg(error) {
	if(error.status == 400) {
		alert(JSON.stringify(error.responseJSON.data));
	}else {
		console.log("요청실패")
		console.log(error)
	}
}