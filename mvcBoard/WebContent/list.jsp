<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
	a{text-decoration:none;}
	
	
	th{
		background-color:#00e68a;
		color:white;
	}
	th, td{
		font-family: 'Nanum Gothic', sans-serif;
		font-size:1rem;
		padding:8px;	
		text-align:center;
		border-bottom: 1px solid lightgray;
	}
	
	.paging a{color:#ddd;}
	.paging a:hover{color:black;}
	
	.tableWrap{font-family: 'Nanum Gothic', sans-serif;
		width:700px;
		border-top:1px solid powderblue;
		border-collapse: collapse; }
	.tableWrap .tableContent{
		width:700px;
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
<%-- 	<select name="contentNum" onchange="list.do?contentNum=${contentNum }"> --%>
<!-- 		<option value="10">10</option> -->
<!-- 		<option value="20">20</option> -->
<!-- 		<option value="30">30</option> -->
<!-- 	</select> -->
	<table style="width:50;">
		<thead>
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="dto">
				<tr>
					<td>${dto.bId }</td>
					<td>${dto.bName }</td>
					<td>
						<c:forEach begin="1" end="${dto.bIndent }">[답변]</c:forEach>
						<a href="content_view.do?bId=${dto.bId }&currpage=${page.currPage}">${dto.bTitle }</a>
					</td>
					<td>${dto.bDate }</td>
					<td>${dto.bHit }</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5" style="text-align:center;">
					<c:if test="${page.prev }">
						<a href="list.do?page=1">처음</a>
						<a href="list.do?page=${page.getStartPage()-1 }">이전페이지</a>
					</c:if>
					
					<c:forEach var="idx" begin="${page.getStartPage() }" end="${page.getEndPage() }">
						<a href="list.do?page=${idx }">${idx }</a>
					</c:forEach>
					
					<c:if test="${page.next }">
						<a href="list.do?page=${page.getEndPage()+1 }">다음페이지</a>
						<a href="list.do?page=${page.getTotalPage(page.getTotalCount(), page.getContentNum()) }">마지막</a>
					</c:if>
				</td>
			</tr>
		</tfoot>
	</table>
	<input type="button" value="글쓰기"  id="writeButton" onclick="javascript:window.location='write_view.do'">
</body>
</html>