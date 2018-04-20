<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
	.dtable{
		width:50%;
	}
</style>

</head>
<body>
	<form action="reply.do" method="post">
		<input type="hidden" name="bId" value="${reply_content.bId }">
		<input type="hidden" name="bGroup" value="${reply_content.bGroup }">
		<input type="hidden" name="bStep" value="${reply_content.bStep }">
		<input type="hidden" name="bIndent" value="${reply_content.bIndent }">
		<input type="text" value="[답변] ${reply_content.bTitle }" name=bTitle><br/>
		<textarea name="bContent" cols="50" rows="10"></textarea><br/>
		<input type="submit" value="댓글달기">
		<input type="button" value="취소" onclick="javascript:window.location='content_view.do?bId=${reply_content.bId}'">
	</form>
</body>
</html>