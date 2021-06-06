<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset= UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>회원가입</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
function form_check() {
	if($('#userId').val().length == 0) {
		alert("아이디를 입력해주세요.");
		$('#userId').focus();
		return;
	}
	
	if($('#userId').val().length < 6) {
		alert("아이디는 6글자 이상이어야 합니다.");
		$('#userId').focus();
		return;
	}
	
	if($('#userPwd').val().length == 0) {
		alert("비밀번호를 입력해주세요.");
		$('#userPwd').focus();
		return;
	}
	
	if($('#userPwd').val().length < 8) {
		alert("비밀번호는 8자리 이상이어야 합니다.");
		$('#userPwd').focus();
		return;
	}
	
	if($('#userPwd').val() != $('#password_check').val()) {
		alert("비밀번호가 일치하지 않습니다.");
		$('#userPwd').focus();
		return;
	}
	
	if($('#userName').val().length == 0) {
		alert("이름을 입력해주세요.");
		$('#userName').focus();
		return;
	}
	
	if($('#nickName').val().length == 0) {
		alert("닉네임(별명)을 입력해주세요.");
		$('#nickName').focus();
		return;
	}
	
	if($('#nickName').val().length < 3) {
		alert("닉네임(별명)은 3글자 이상이어야 합니다.");
		$('#nickName').focus();
		return;
	}
	
	if($('#eMail_id').val().length == 0) {
		alert("이메일 아이디를 입력해주세요.");
		$('#eMail_id').focus();
		return;
	}
	
	if($('#eMail_url').val().length == 0) {
		alert("이메일 주소를 입력해주세요.");
		$('#eMail_url').focus();
		return;
	}
	
	if($('#userTelNum').val().length == 0) {
		alert("전화번호를 입력해주세요.");
		$('#user_telNum').focus();
		return;
	}
	submit_ajax();
}

function check_id() {
	var id = "userId="+$('#userId').val();
	$.ajax({
		url: '/security/checkId',
		type: 'POST',
		data: id,
		dataType: 'text',
		success: function(json) {
			var result = JSON.parse(json);
			if(result.code=="success") {
				alert(result.desc);
			} else {
				alert(result.desc);
			}
		}
	});
}

function check_nick() {
	var nickName = "nickName="+$('#nickName').val();
	$.ajax({
		url: '/security/checkNick',
		type: 'POST',
		data: nickName,
		dataType: 'text',
		success: function(json) {
			var result = JSON.parse(json);
			if(result.code=="success") {
				alert(result.desc);
			} else {
				alert(result.desc);
			}
		}
	});
}

function submit_ajax() {
	<%
		String conPath = request.getContextPath();
	%>
	var queryString = $("#reg_frm").serialize();
	$.ajax({
		url: '<%=conPath%>/security/join',
		type: 'POST',
		data: queryString,
		dataType: 'text',
		success: function(json) {
			var result = JSON.parse(json);
			if(result.code=="success") {
				alert(result.desc)
				window.location.replace("/loginForm");
			} else {
				alert(result.desc);
			}
		}
	});
}
</script>
<style>
.font {
	font-color: white;
	font-weight: bold;
	font-size: 1.5em;
	text-align: center;
}

.font2 {
	font-color: white;
	font-size: 0.8em;
	text-align: center;
}


.button {
	float: right;
}

.right {
	float: right;
	text-align: right;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo03"
			aria-controls="navbarTogglerDemo03" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="/">YT</a>

		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active"><a class="nav-link" href="main.jsp">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">공지사항</a>
				</li>
				<li class="nav-item active"><a class="nav-link" href="#">인플루언서</a>
				</li>
				<li class="nav-item active"><a class="nav-link" href="#">구인구직</a>
				</li>
				<li class="nav-item active"><a class="nav-link" href="#">굿즈</a>
				</li>
				<li class="nav-item active"><a class="nav-link" href="#">고객센터</a>
				</li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>&nbsp;
			</form>
		</div>
		<div>
			<tr>
				<td class="button">
					<button type="button" class="btn btn-secondary" onclick="javascript:window.location='/loginForm'">로그인</button>
				</td>
			</tr>
		</div>
		<div id="navbarNavDarkDropdown">
			<ul class="navbar-nav">
				<li class="nav-item dropdown">
					<a	class="nav-link dropdown-toggle" href="#"
						id="navbarDarkDropdownMenuLink" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 메뉴 </a>
					<ul class="dropdown-menu dropdown-menu-dark"
						aria-labelledby="navbarDarkDropdownMenuLink">
						<li><a class="dropdown-item" href="#">Action</a></li>
						<li><a class="dropdown-item" href="#">Another action</a></li>
						<li><a class="dropdown-item" href="#">Something else here</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	
	<hr>

	<div class="container">
		<div class="page-header col aling-self-center font">
			<header>
				<h1>
					<a href="">YT</a>
				</h1>
			</header>
		</div>
	
		<hr>
	
		<div class="container col-8">
			<form id="reg_frm" name="reg_frm">
				<div class="input-group mb-3">
  					<span class="input-group-text col-2" id="basic-addon1">아이디</span>
					<input type="text" class="form-control" id="userId" name="userId" placeholder="* 6자리 이상" aria-label="아이디" aria-describedby="basic-addon1">&nbsp;
					<button type="button" id="btn_checkid" class="btn btn-dark" onclick="check_id()">아이디 중복확인</button>
				</div>
				<div class="input-group mb-3">
  					<span class="input-group-text col-2" id="basic-addon1">비밀번호</span>
  					<input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="* 8자리 이상" aria-label="비밀번호" aria-describedby="basic-addon1">
				</div>
				<div class="input-group mb-3">
  					<span class="input-group-text col-2" id="basic-addon1">비밀번호 확인</span>
  					<input type="password" class="form-control" id="password_check" name="password_check" aria-label="비밀번호 확인" aria-describedby="basic-addon1">
				</div>
				<div class="input-group mb-3">
  					<span class="input-group-text col-2" id="basic-addon1">이름</span>
  					<input type="text" class="form-control" id="userName" name="userName"  aria-label="이름" aria-describedby="basic-addon1">
				</div>
				<div class="input-group mb-3">
  					<span class="input-group-text col-2" id="basic-addon1">휴대폰</span>
  					<input type="text" class="form-control" id="userTelNum" name="userTelNum" aria-label="휴대폰" aria-describedby="basic-addon1">
				</div>
				<div class="input-group mb-3">
  					<span class="input-group-text col-2" id="basic-addon1">닉네임</span>
  					<input type="text" class="form-control" id="nickName" name="nickName" placeholder="* 3자리 이상" aria-label="닉네임" aria-describedby="basic-addon1">&nbsp;
  					<button type="button" class="btn btn-dark" onclick="check_nick()">닉네임 중복확인</button>
				</div>
				<div class="input-group mb-3">
					<span class="input-group-text col-2" id="basic-addon1">이메일</span>
  					<input type="text" class="form-control" id="eMail_id" name="eMail_id" aria-label="eMail_id">
  					<span class="input-group-text">@</span>
  					<input type="text" class="form-control" id="eMail_url" name="eMail_url" aria-label="eMail_url">
				</div>
				<div class="input-group mb-3">
  					<span class="input-group-text col-2" id="basic-addon1">소속회사</span>
  					<input type="text" class="form-control" id="company" name="company" aria-label="소속회사" aria-describedby="basic-addon1">
				</div>
				<br><p>
				<div class="d-flex justify-content-center">
					<input type="button" class="btn btn-dark d-flex p-2" value="취소" onclick="javascript:window.location='main1.jsp'">&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn btn-dark d-flex p-2" value="회원가입" onclick="form_check()">
				</div>
			</form>
		</div>
	</div>

	<hr>

	<div class="p-3 mb-2 bg-dark text-white font2">
		<div class="container">
			<footer>
				이메일<br> 전화번호<br> 주소<br> <a href="">고객센터</a>
			</footer>
		</div>
	</div>

</body>
</html>