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
			<td>�۹�ȣ <p>${content.bId }<p></td>
			<td>���� <p>${content.bTitle }<p></td>
		</tr>
		<tr>
			<td>�ۼ���<p>${content.bName }</p></td>
			<td>�ۼ���<p>${content.bDate }</p></td>
			<td>��ȸ��<p>${content.bHit }</p></td>
		</tr>
		<tr>
			<td colspan="3"><p style="text-align:justify">${content.bContent }</p><td>
		</tr>
		<tr>
			<td>
				<a href="reply.do">���</a> &nbsp;&nbsp;&nbsp; <a href="modify.do">����</a>&nbsp;&nbsp;&nbsp; <a href="delete.do">����</a>&nbsp;&nbsp;&nbsp;<a href="list.do">�ڷ�</a>
			</td>	
		</tr>
	</table>
</body>
</html>