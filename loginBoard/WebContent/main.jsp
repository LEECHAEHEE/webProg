<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	HI! ${ nameSession}<br/><hr>
	<input type="button" value="게시판" onclick="javascript:window.location='list.bdo?curPage=1'">
	<input type="button" value="회원정보 수정" onclick="javascript:window.location='modify.jsp'">
	<input type="button" value="로그아웃" onclick="javascript:window.location='logout.mdo'">
	<input type="button" value="회원탈퇴" onclick="javascript:window.location='leave.mdo'">
</body>
</html>