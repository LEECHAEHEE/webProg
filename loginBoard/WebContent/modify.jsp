<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- 아이디 중복 체크 하기 -->
<script>
</script>
</head>
<body>
회원정보 수정<br/><hr>
	<form action="modify.mdo" name=joinForm method="post">
		이름	<input type="text" name="name"><br/>
		아이디	<input type="text" id="id" name="id" value="${idSession }"><br/>
		비밀번호<input type="password" name="pw"><br/>
		이메일	<input type="text" name="email"><br/>
		주소	<input type="text" name="address"><br/>
		<input type="submit" value="수정완료">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="뒤로" onclick="javascript:window.history.back()">
	</form>
</body>
</html>