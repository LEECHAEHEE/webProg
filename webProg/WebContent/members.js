/**
 * 
 */
function idCheck(){
	alert("아이디 체크");
}

function isSame(){
	var pw = document.getElementById("password").value;
	
	var chkpw = document.getElementById("checkPassword").value;
	if(pw.length < 8 || pw.legnth > 16){
		document.getElementById("pwValid").innerHTML = "8자리이상 16자리 이하";
		document.getElementById("pwValid").style.color="red";
	}else{
		document.getElementById("pwValid").innerHTML = "사용가능합니다.";
		document.getElementById("pwValid").style.color="blue";
	}
	if(document.getElementById("password").value !="" && document.getElementById("checkPassword").value!=""){
		if(document.getElementById("password").value == document.getElementById("checkPassword").value){
			document.getElementById("same").innerHTML = "비밀번호가 일치합니다.";
			document.getElementById("same").style.color="blue";
		}else{
			document.getElementById("same").innerHTML = "비밀번호가 일치하지 않습니다.";
			document.getElementById("same").style.color="red";
		}
	}
}

function infoConfirm(){
	
	if(document.reg_frm.name.value==""){
		alert("이름 입력하세요.");
		reg_frm.name.focus();
		return;
	}
	
	if(document.reg_frm.id.value==""){
		alert("아이디를 입력하세요.");
		reg_frm.id.focus();
		return;
	}
//	if(document.reg_frm.id.length==0){
//		alert("아이디를 입력하세요.");
//		reg_frm.id.focus();
//		return;
//	}
	
	if(document.reg_frm.id.legnth < 4){
		alert("아이디는 한글, 숫자 조합 4자 이상이어야 합니다.");
		reg_frm.id.focus();
		return;
	}
	
	if(document.reg_frm.pw.value == ""){
		alert("패스워드를 입력하세요");
		reg_frm.pw.focus();
		return;
	}
	if(document.reg_frm.chkpw.value == ""){
		alert("패스워드 확인란을 입력하세요");
		reg_frm.pw.focus();
		return;
	}
	
	if(document.reg_frm.email.value==""){
		alert("이메일을 입력하세요");
		reg_frm.email.focus();
		return;
	}
	
	document.reg_frm.submit();
}

function updateInfoConfirm(){
	if(document.reg_frm.pw.value == ""){
		alert("패스워드를 입력하세요");
		reg_frm.pw.focus();
		return;
	}
	if(document.reg_frm.chkpw.value == ""){
		alert("패스워드 확인란을 입력하세요");
		reg_frm.pw.focus();
		return;
	}
	
	if(document.reg_frm.email.value==""){
		alert("이메일을 입력하세요");
		reg_frm.email.focus();
		return;
	}
	document.reg_frm.submit();
}