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
		<c:when test="${modifyResult==1 }">
			<script>
				alert("수정완료");
				location.href="list.bdo";
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("예상치 못한 오류 발생");
				location.go(-1);
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>