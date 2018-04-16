<%@page import="com.javalec.ex.MemberDTO"%>
<%@page import="com.javalec.ex.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%!
	String id, pw;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");

	id = request.getParameter("id");
	pw = request.getParameter("pw");
	
	MemberDAO dao = MemberDAO.getInstance();
	int checkSum = dao.userCheck(id,pw);
	/*
		-1 아이디 없음 0 패스워드 틀림 1성공
	*/
	if(checkSum==-1){
%>
	<script language="javascript">
		alert("해당 아이디가 존재하지 않습니다");
		history.go(-1);
	</script>
<%
	}else if(checkSum==0){
%>
	<script language="javascript">
		alert("비밀번호가 일치하지 않습니다");
		history.go(-1);
	</script>
<%
	}else if(checkSum ==1){
		MemberDTO dto = dao.getMember(id);
		
		if(dto==null){
%>
	<script language="javascript">
		alert("존재하지 않는 회원입니다");
		history.go(-1);
	</script>
<%
	}else{
		String name = dto.getName();
		session.setAttribute("id", id);
		session.setAttribute("name", name);
		session.setAttribute("password", pw);
		session.setAttribute("ValidMem", "yes");
		response.sendRedirect("main.jsp");
		}
	}
%>
</body>
</html>