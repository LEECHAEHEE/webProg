<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<style>
	tr:hover {background-color: #f5f5f5;}
	th{
		background-color:#00e68a;
		color:white;
	}
	th, td{
		font-family: 'Nanum Gothic', sans-serif;
		font-size:1rem;
		padding:8px;	
		text-align:center;
	}
	table{
		width:50%;
		border-top:1px solid powderblue;
		border-collapse: collapse;
	}	
	#writeButton{
		width:80px;
		height:30px;
		font-size:1rem;
		font-family: 'Nanum Gothic', sans-serif;
		background-color:white;
		border:1.5px solid powderblue;
		border-radius: 3px;
	}

</style>
</head>
<body>
		<table>
			<tr>
				<th>�۹�ȣ</th>
				<th>�ۼ���</th>
				<th>����</th>
				<th>�ۼ���</th>
				<th>��ȸ��</th>
			</tr>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.bId}</td>
					<td>${dto.bName}</td>
					<c:forEach begin="1" end="${dto.bIndent}">��</c:forEach>
					<td><a href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a></td>
					<td>${dto.bDate}</td>
					<td>${dto.bHit}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan=5 style="text-align:right"><input id="writeButton" type="button" value="�۾���" onclick="javascript:window.location='write_view.do'"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>	
	</table>
</body>
</html>