<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${idSession==null }">
			<form action="login_check.mdo" method="post">
				아이디 <input type="text" name="id"><br>
				비밀번호 <input type="password" name="pw"><br>
				<input type="submit" value="로그인"><input type="button" value="회원가입" onclick="javascript:window.location='register.jsp'">
			</form>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				location.href="main.jsp";
			</script>	
		</c:otherwise>
	</c:choose>
	
</body>
</html>