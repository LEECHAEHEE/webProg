<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function idCheck(id){
		location.href="idCheck.mdo?id=ergaerg";
	}
</script>
</head>
<body>
	<form action="join.mdo" method="post">
		이름	<input type="text" name="name"><br/>
		아이디	<input type="text" id="id" name="id"><input type="button" value="아이디 중복 확인" onclick="javascript:window.location='idCheck.mdo?id=document.getElementById(id).value'"><br/>
		비밀번호<input type="password" name="pw"><br/>
		이메일	<input type="text" name="email"><br/>
		주소	<input type="text" name="address"><br/>
		<input type="submit" value="회원가입">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="뒤로" onclick="javascript:window.location='login.mdo'">
	</form>
</body>
</html>