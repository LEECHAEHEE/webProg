<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>회원가입</title>
	<style>
		html, body {width: 100%; height: 100%;}
		.container {width: 70%; height: 70%; margin: 40px auto;}
		.outer {display: table; width: 100%; height: 100%; }
		.inner {display:table-cell; vertical-align: middle; text-align: center;}
		.centered {position: relative; display: inline-block; width: 50%; height: 30%; padding: 1em; text-align:left;}

	</style>
	<script language="javascript" src="members.js" charset="utf-8"></script>
	
</head>
<body>
<div class="container">
		<div class="outer">
			<div class="inner">
				<div class="centered">
					<form action="joinOk.jsp" method="post" name="reg_frm">		
						<table>
							<tr height="40" >
								<td width="5%" align="left">*</td>
								<td width="15%">이름</td>
								<td><input type="text" name="name"/></td>
							</tr>
							<tr height="5">
								<td colspan="3"><hr/></td>
							</tr>
							<tr>
								<td width="5%" align="left">*</td>
								<td width="5px">아이디</td>
								<td><input type="text" name="id"></td>
								<td><input type="button" value="아이디 중복검사" onclick="idCheck()"></td>
							</tr>
							<tr height="5">
								<td colspan="3"><hr/></td>
							</tr>
							<tr height="5">
								<td width="5%" align="left">*</td>
								<td width="15%">비밀번호</td>
								<td><input type="password" name="pw" id="password" onkeyup="isSame()"/>&nbsp;&nbsp;<span id="pwValid"></span></td>
							</tr>
							<tr height="5">
								<td colspan="3"><hr/></td>
							</tr>
							<tr height="5">
								<td width="5%" align="left">*</td>
								<td width="15%">비밀번호 확인</td>
								<td><input type="password" name="chkpw" id="checkPassword" onkeyup="isSame()"/>&nbsp;&nbsp;<span id="same"></span></td>
							</tr>
							<tr height="5">
								<td colspan="3"><hr/></td>
							</tr>
							<tr height="5">
								<td width="5%" align="left">*</td>
								<td width="15%">이메일</td>
								<td><input type="text" name="email"/></td>
							</tr>
							<tr height="5">
								<td colspan="3"><hr/></td>
							</tr>
							<tr height="5">
								<td width="5%" align="left">*</td>
								<td width="15%">주소</td>
								<td><input type="text" name="address"/></td>
							</tr>
							<tr height="5">
								<td width="15%"><input type="button" value="회원가입" onclick="infoConfirm()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<td width="15%"><input type="button" value="취소" onclick="javascript:window.location='login.jsp'"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>