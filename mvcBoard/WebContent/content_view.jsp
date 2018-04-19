<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>글번호 <p>${content.bId }<p></td>
			<td>제목 <p>${content.bTitle }<p></td>
		</tr>
		<tr>
			<td>작성자<p>${content.bName }</p></td>
			<td>작성일<p>${content.bDate }</p></td>
			<td>조회수<p>${content.bHit }</p></td>
		</tr>
		<tr>
			<td colspan="3"><p style="text-align:justify">${content.bContent }</p><td>
		</tr>
		<tr>
			<td>
				<a href="reply.do">댓글</a> &nbsp;&nbsp;&nbsp; <a href="modify.do">수정</a>&nbsp;&nbsp;&nbsp; <a href="delete.do">삭제</a>&nbsp;&nbsp;&nbsp;<a href="list.do">뒤로</a>
			</td>	
		</tr>
	</table>
</body>
</html>