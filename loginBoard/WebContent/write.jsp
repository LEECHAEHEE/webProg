<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
	a, li, ul, input, form , div {padding:0; margin:0;}
	
	.contentWrap{ width:50%; height:500px; margin:0 auto; border: 1px solid #ddd}
	.titleWrap { width:100%; height:40px; text-align:center; border-bottom:1px solid #ddd}
	.titleWrap input { width:90%; height:25px; margin:5px; }
	.contentInput { width:100%; height:400px; text-align:center;}
	.contentInput textarea { width:90%; height 100%; margin:5px; padding:5px;}
	.submitWrap { width:100%; height:50px; text-align:center;}
	.submitButton { width:65px; height:30px;}
</style>
</head>
<body>
User : ${nameSession }<br/><hr>
<form action="write.bdo" method="post">
	<input type="hidden" name="name" value="${nameSession }">
	
	<div class="contentWrap">
		<div class="titleWrap">
			<input type="text" name="title" placeholder="������ �Է��ϼ���"><br/>
		</div>
		<div class="contentInput">
			<textarea name="content" rows="24" cols="10" placeholder="������ �Է��ϼ���"></textarea>
		</div>
		<div class="submitWrap">
			<input class="submitButton" type="submit" value="Ȯ��"> <input type="button" class="submitButton" value="���" onclick="list.bdo"> 
		</div>
	</div>
</form>
</body>
</html>