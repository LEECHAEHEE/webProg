<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	html,body,h1,h2,h3,h4,h5,h6,p,ul,ol,dl,p,dd, input{margin: 0; padding: 0;}
	ul {list-style: none}
	.loginWrap { width:300px; height:300px; margin:0 auto; border:1px dotted powderblue; font-family:"NanumGothic"}
	.loginHeader { width:250px; height:50px; text-align: center; margin:0 auto; border-bottom: 1px dotted powderblue}
	.loginHeader span{margin:0 auto; line-height: 50px; font-size: 20px; font-weight: bold;}
	
	.loginContent { width:300px; height:180px; border-bottom:1px dotted powderblue; text-align: center;}
	
	.loginInputWrap { width:250px; height:150px; margin:auto;padding:auto}
	.loginInputWrap span { display: block; margin-bottom:5px;}
	.loginInputWrap input { position:relative; width:80%; height:20px; border:none; border-bottom:1px solid #dedede; }
	
	.idWrap{ position:relative; width:250px; height 75px; top:25px;}
	.pwWrap{ position:relative; width:250px; height 75px; top:45px;}
	
	.loginFooter { width:300px; height:70px }
	.loginFooter ul { width:200px; height:40px; margin:auto;}
	.loginFooter ul li { position:relative; float:left; padding:10px; margin-top:6px;}
	.loginFooter ul li input { width:80px; height:30px; background-color: transparent; border:none; border-bottom:1px solid #dedede }
</style>
</head>
<body>
<form action="loginCheck.mdo" method="post">
	<div class="loginWrap">
		<div class="loginHeader">
			<span>로그인</span>
		</div>
		<div class="loginContent">
			<div class="loginInputWrap">
					<div class="idWrap">
						<c:choose>
							<c:when test="${sessionScope.idSession != null }">
								<span>아이디</span><input type="text" value="${sessionScope.idSession }" name="id" placeholder="아이디"><br>
							</c:when>
							<c:otherwise>
								<span>아이디</span><input type="text" name="id" placeholder="아이디">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="pwWrap">
							<span>비밀번호</span> <input type="password" name="pw" placeholder="비밀번호">
					</div>
				
			</div>
		</div>
		<div class="loginFooter">
			<ul>
				<li><input type="submit" value="로그인"></li>
				<li><input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'"></li>
			</ul>
		</div>
</div>
</form>
</body>
</html>