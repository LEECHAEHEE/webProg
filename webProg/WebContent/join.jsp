<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>ȸ������</title>
	<script language="javascript" src="members.js" charset="utf-8"></script>
</head>
<body>
	<form action="joinOk.jsp" method="post" name="reg_frm">		
		<table>
			<tr height="5">
				<td width="5%" align="left">*</td>
				<td width="15%">�̸�</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr height="5">
				<td colspan="3"><hr/></td>
			</tr>
			<tr>
				<td width="5%" align="left">*</td>
				<td width="5px">���̵�</td>
				<td><input type="text" name="id"></td>
				<td><input type="button" value="���̵� �ߺ��˻�" onclick="idCheck()"></td>
			</tr>
			<tr height="5">
				<td colspan="3"><hr/></td>
			</tr>
			<tr height="5">
				<td width="5%" align="left">*</td>
				<td width="15%">��й�ȣ</td>
				<td><input type="password" name="pw" id="password" onkeyup="isSame()"/>&nbsp;&nbsp;<span id="pwValid"></span></td>
			</tr>
			<tr height="5">
				<td colspan="3"><hr/></td>
			</tr>
			<tr height="5">
				<td width="5%" align="left">*</td>
				<td width="15%">��й�ȣ Ȯ��</td>
				<td><input type="password" name="chkpw" id="checkPassword" onkeyup="isSame()"/>&nbsp;&nbsp;<span id="same"></span></td>
			</tr>
			<tr height="5">
				<td colspan="3"><hr/></td>
			</tr>
			<tr height="5">
				<td width="5%" align="left">*</td>
				<td width="15%">�̸���</td>
				<td><input type="text" name="email"/></td>
			</tr>
			<tr height="5">
				<td colspan="3"><hr/></td>
			</tr>
			<tr height="5">
				<td width="5%" align="left">*</td>
				<td width="15%">�ּ�</td>
				<td><input type="text" name="address"/></td>
			</tr>
			<tr height="5">
				<td width="15%"><input type="button" value="ȸ������" onclick="infoConfirm()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td width="15%"><input type="button" value="���" onclick="javascript:window.location='login.jsp'"></td>
			</tr>
		</table>
	</form>
</body>
</html>