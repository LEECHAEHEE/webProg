<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${idResult==1 }">
			<h3>사용가능한 아이디 입니다.</h3>
			<input type="button" value="사용하기">&nbsp;&nbsp;&nbsp;<input type="button" value="다시검색">
		</c:when>
		<c:otherwise>
			<h3>이미 존재하는 아이디입니다.</h3>
			<input type="button" value="닫기">
		</c:otherwise>
	</c:choose>
</body>
</html>