<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	ul,ol,li,p,a {padding:0; margin:0;}
	ul{ list-style: none}
	input {margin-right:10px;}
	
	.contentWrap{font-family: "NanumGothic"; width:60%; height:600px; border: 1px solid #ddd; margin:0 auto;}
	.titleBar {width:100%; height:50px; border-bottom:1px dotted gray;}
	.titleBar input {width:90%; height:30px; position:relative; top:10px; left:30px;}
	.contentInfo { width:100%; height:40px }
	.contentInfo ul{position:relative; top:6px; left:30px; }
	.contentInfo li{float:left; font-size: 0.8rem; padding-right :20px; margin-right:10px; border-right:solid 1px #ddd}
	.content {width: 90%; height:450px; margin:0 auto;}
	.content textarea { width:90%; height 100%; margin:5px; padding:5px;}
	.footMenu{width:100%; height:50px; }
	.footMenu ul { position:relative;; top:10px; left:30px;}
	.footMenu li { float:left; fontsize:0.8rem; padding-right: 20px; margin:right:10px;} 
</style>
</head>
<body>
USER : ${idSession  } / ${bDto.id }<br/><hr>

<div class="contentWrap">
	<form action="modify.bdo" method="post">
		<input type="hidden" name="num" value="${bDto.num }">
		
		<div class="titleBar">
			<input type="text" name="title" value="${bDto.title }">
		</div>
		<div class="contentInfo">
			<ul>
				<li>${bDto.name }</li>
				<li>조회  ${bDto.hit }</li>
				<li>${bDto.rDate }</li>
			</ul>	
		</div>
		<div class="content">
			<textarea name="content" rows="24" cols="10" placeholder="내용을 입력하세요">${bDto.content }</textarea>
		</div>
		<div class="footMenu">
			<ul>
				<li><input type="submit" value="완료" ></li>
				<li><input type="button" value="뒤로" onclick="javascript:window.location='list.bdo'"></li>
			</ul>
		</div>
	</form>
</div>
</body>
</html>