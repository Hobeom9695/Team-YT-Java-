<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset= UTF-8">
<title>회원가입</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
function form_check() {
	if($('#userId').val().length == 0) {
		alert("아이디는 필수사항입니다.");
		$('#userId').focus();
		return;
	}
	
	if($('#userId').val().length < 4) {
		alert("아이디는 4글자 이상이어야 합니다.");
		$('#id').focus();
		return;
	}
	
	if($('#userPw').val().length == 0) {
		alert("비밀번호는 필수사항입니다.");
		$('#pw').focus();
		return;
	}
	
	if($('#userPw').val() != $('#pw_check').val()) {
		alert("비밀번호가 일치하지 않습니다.");
		$('#pw').focus();
		return;
	}
	
	if($('#userName').val().length == 0) {
		alert("이름은 필수사항입니다.");
		$('#name').focus();
		return;
	}
	
	if($('#eMail').val().length == 0) {
		alert("이메일은 필수사항입니다.");
		$('#eMail').focus();
		return;
	}
	
	if($('#mobile').val().length == 0) {
		alert("전화번호는 필수사항입니다.");
		$('#mobile').focus();
		return;
	}
	submit_ajax();
}

function submit_ajax() {
	var queryString = $("#reg_frm").serialize();
	$.ajax({
		url: 'BodSiteJoinCommand.do',
		type: 'POST',
		data: queryString,
		dataType: 'text',
		success: function(json) {
			var result = JSON.parse(json);
			if(result.code=="success") {
				alert(result.desc)
				window.location.replace("login.jsp");
			} else {
				alert(result.desc);
			}
		}
	});
}
</script>
</head>
<body>
	<form id="reg_frm" name="reg_frm">
		아이디 : <input type="text" id="userId" name="userId" size="20"><br>
		비밀번호 : <input type="password" id="userPw" name="userPw" size="20"><br>
		비밀번호 확인 : <input type="password" id="pw_check" name="pw_check" size="20"><br>
		이름 : <input type="text" id="userName" name="userName" size="20"><br>
		이메일 : <input type="text" id="eMail" name="eMail" size="20"><br>
		전화번호 : <input type="number" id="mobile" name="mobile" size="20"><br>
		닉네임 : <input type="text" id="nickName" name="nickName" size="20"><br><p>
		<input type="button" value="취소" onclick="javascript:window.location='main.jsp'">&nbsp;&nbsp;&nbsp;
		<input type="button" value="회원가입" onclick="form_check()">&nbsp;&nbsp;&nbsp;
		<input type="button" value="로그인" onclick="javascript:window.location='login.jsp'">
	</form>
</body>
</html>