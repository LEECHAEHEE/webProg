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
		-1 ���̵� ���� 0 �н����� Ʋ�� 1����
	*/
	if(checkSum==-1){
%>
	<script language="javascript">
		alert("�ش� ���̵� �������� �ʽ��ϴ�");
		history.go(-1);
	</script>
<%
	}else if(checkSum==0){
%>
	<script language="javascript">
		alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�");
		history.go(-1);
	</script>
<%
	}else if(checkSum ==1){
		MemberDTO dto = dao.getMember(id);
		
		if(dto==null){
%>
	<script language="javascript">
		alert("�������� �ʴ� ȸ���Դϴ�");
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