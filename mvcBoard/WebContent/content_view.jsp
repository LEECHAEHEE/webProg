<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script>
	function delchk(){
		return confirm("삭제하시겠습니까");
	}
</script>
<style>
	
	table{
		
		font-family: 'Nanum Gothic', sans-serif;
		font-size=0.7rem;
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
		font-size: 0.7rem;
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
</head>
<body>
	<table>
		<tr class="bottomLine smallText">
			<td class="leftAlign">글번호</td>
			<td>${content.bId }</td>
			<td class="leftAlign">제목</td>
			<td colspan="3">${content.bTitle }</td>
		</tr>
		<tr class="smallText bottomLine">
			<td >작성자</td>
			<td>${content.bName }</td>
			<td>작성일</td>
			<td>${content.bDate }</td>
			<td>조회수</td>
			<td>${content.bHit }</td>
		</tr>
		<tr>
			<td colspan="6" class="content">${content.bContent }<td>
		</tr>
		<tr class="menu">
			<td colspan="6">
				<a href="reply_view.do?bId=${content.bId }">댓글</a>&nbsp;&nbsp;&nbsp;<a href="modify.do?bId=${content.bId }">수정</a>&nbsp;&nbsp;&nbsp;<a href="deleteConfirm.do?bId=${content.bId }" onclick="return delchk();">삭제</a>&nbsp;&nbsp;&nbsp;<a href="list.do">뒤로</a>
			</td>	
		</tr>
	</table>
</body>
</html>