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
		<c:when test="${writeResult==1 }">
			<script type="text/javascript">
				alert("��ϵǾ����ϴ�");
				location.href="list.bdo";
			</script>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				alert("����ġ ���� ���� �߻�");
				location.go(-1);			
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>