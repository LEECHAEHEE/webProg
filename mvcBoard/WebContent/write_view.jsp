<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Calendar cal = Calendar.getInstance();
	Timestamp curTime = Timestamp.valueOf(sdf.format(cal.getTime()));
 %>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
	
	table{
		width:300;
		border-collapse:collapse;
	}
	
	tr,td{
		border-bottom:1px solid powderblue; 	
	}
</style>
</head>
<body>
	<form action="write.do" method="post">
		<table>
			<tr>
				<th>이름 <input type="text" name="bName"></th>
				<th>제목 <input type="text" name="bTitle"></th>
			</tr>
			<tr>
				<td colspan ="3">
					<textarea name="bContent" cols="120" rows="10"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="right">
					<input type="submit" value="글쓰기"> &nbsp;&nbsp; <input type="button" value="취소" onclick="javascript:window.location='list.do'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>