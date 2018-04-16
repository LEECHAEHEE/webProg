<%@page import="com.javalec.ex.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="dto" class="com.javalec.ex.MemberDTO" scope="page"/>
<jsp:setProperty name="dto" property="*" />
<%!
	String id;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	id = (String)session.getAttribute("id");
	dto.setId(id);
	
	MemberDAO dao = MemberDAO.getInstance();
	int ri = dao.updateMember(dto);
	
	if(ri == 1){
%>
	<script language="javascript">
		alert("수정 되었습니다.")
		documenet.location.href="main.jsp";
	</script>
<%
}else{
%>
	<script language="javascript">
		alert("정보수정 실패");
		history.go(-1);
	</script>
<%
}
%>
</body>
</html>