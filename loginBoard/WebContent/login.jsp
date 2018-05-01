<%@page import="java.io.PrintWriter"%>
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
	<form action="loginCheck.mdo">
		<c:choose>
			<c:when test="${sessionScope.idSession !=null }">
				아이디 <input type="text"  value="${sessionScope.idSession }" name="id"><br>
			</c:when>
			<c:otherwise>
				아이디 <input type="text" name="id"><br>
			</c:otherwise>
		</c:choose>
		비밀번호 <input type="password" name="pw"><br>
		<input type="submit" value="로그인">
		<input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'">
	</form>
</body>
</html>