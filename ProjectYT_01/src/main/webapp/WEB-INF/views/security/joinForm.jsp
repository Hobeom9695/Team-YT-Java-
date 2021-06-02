<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset= UTF-8">
<title>회원가입</title>
</head>
<body>
<form action="join" method="post">
		아이디 : <input type="text" id="userid" name="userid" size="20"><br>
		비밀번호 : <input type="password" id="password" name="password" size="20"><br>
		이름 : <input type="text" id="user_name" name="user_name" size="20"><br>
		전화번호 : <input type="text" id="user_telNum" name="user_telNum" size="20"><br>
		닉네임 : <input type="text" id="nickname" name="nickname" size="20"><br>
		이메일 : <input type="text" id="eMail" name="eMail" size="20"><br>
		소속회사 : <input type="text" id="company" name="company" size="20"><br><p>
		<input type="button" value="취소">&nbsp;&nbsp;&nbsp;
		<input type="submit" value="회원가입">&nbsp;&nbsp;&nbsp;
		<input type="button" value="로그인">
</form>
</body>
</html>