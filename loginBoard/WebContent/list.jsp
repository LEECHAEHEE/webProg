<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<style>
	tr:hover {background-color: #f5f5f5;}
	a{text-decoration:none; color:#ddd}
	a:hover{color:black}
	table { width:50%; margin: 0 auto; text-align:left;}
	input { margin-right:10px;}
	th{
		background-color:#00e68a;
		color:white;
	}
	th, td{
		font-family: 'Nanum Gothic', sans-serif;
		font-size:1rem;
		padding:8px;	
		border-bottom: 1px solid lightgray;
	}
	

</style>
</head>
<body>
User : ${nameSession }<br/><hr>
<%-- 	<select name="contentNum" onchange="list.do?contentNum=${contentNum }"> --%>
<!-- 		<option value="10">10</option> -->
<!-- 		<option value="20">20</option> -->
<!-- 		<option value="30">30</option> -->
<!-- 	</select> -->
	<table>
		<thead>
			<tr>
				<th style="width:70px;">글번호</th>
				<th style="width:80px;">작성자</th>
				<th style="width:200px;">제목</th>
				<th style="width:150px;">작성일</th>
				<th style="width:50px;">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="iter" items="${dtos }">
				<tr>
					<td>${iter.num }</td>
					<td>${iter.name }</td>
					<td><a href="content.bdo?num=${iter.num }">${iter.title }</a></td>
					<td>${iter.rDate }</td>
					<td>${iter.hit }</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<input type="button" value="작성" onclick="javascript:window.location='write.jsp'">
					<input type="button" value="메인" onclick="javascript:window.location='main.jsp'">
				</td>
			</tr>
		</tfoot>
	</table>
	
</body>
</html>