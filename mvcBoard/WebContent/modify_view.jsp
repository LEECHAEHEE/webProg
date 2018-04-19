<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
	
	table{
		font-family: 'Nanum Gothic', sans-serif;
		font-size=1rem;
		width:50%;
		border: 2px solid powderblue;
	}
	tr:hover {background-color: #f5f5f5;}
	
	.leftAlign{
		text-align: center;
		background-color: powderblue;
	}
	
	.smallText :nth-child(2n-1){
		background-color: powderblue;
		text-align:center;
		font-size: 0.5rem;
	}
	
	.menu{
		align-items: right;
	}
	
	.bottomLine{
		border-bottom: 1px solid powderblue;
	}
	
	.content {
		height:100px;
		border-top:2px solid powderblue;
		border-bottom:2px solid powderblue;
		text-align: justify;
	}
</style>
<body>
	<table>
		<tr class="bottomLine">
			<td class="leftAlign">글번호</td>
			<td>${content.bId }</td>
			<td class="leftAlign">제목 <input type="text" value=${content.bTitle } name="bTitle"></td>
			<td colspan="3">${content.bTitle }</td>
		</tr>
		<tr class="smallText bottomLine">
			<td>작성자  <input type="text" value=${content.bName } name="bName"></td>
			<td>작성일 </td>
			<td>${content.bDate }</td>
			<td>조회수</td>
			<td>${content.bHit }</td>
		</tr>
		<tr>
			<td colspan="6" class="content"><textarea name="bContent" rows="3" cols="50">${content.bContent }</textarea><td>
		</tr>
		<tr class="menu">
			<td colspan="6">
				<a href="modifyClick.do">수정</a>
			</td>	
		</tr>
	</table>
</body>
</html>