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
			<h3>��밡���� ���̵� �Դϴ�.</h3>
			<input type="button" value="����ϱ�">&nbsp;&nbsp;&nbsp;<input type="button" value="�ٽð˻�">
		</c:when>
		<c:otherwise>
			<h3>�̹� �����ϴ� ���̵��Դϴ�.</h3>
			<input type="button" value="�ݱ�">
		</c:otherwise>
	</c:choose>
</body>
</html>