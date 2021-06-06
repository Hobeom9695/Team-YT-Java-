<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset= UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>로그인</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.js"></script>

<script>
function form_check() { alert("111")
	if($('#j_username').val().length == 0) {
		alert("아이디를 입력해주세요.");
		$('#j_username').focus();
		return;
	}
	
	if($('#j_password').val().length == 0) {
		alert("비밀번호를 입력해주세요.");
		$('#j_password').focus();
		return;
	}
	
	submit_ajax();
}

function submit_ajax() {
	<%
		String conPath = request.getContextPath();
	%>
	var queryString = $("#reg_frm").serialize();
	$.ajax({
		url: ${loginUrl},
		type: 'POST',
		data: queryString,
		dataType: 'text',
		success: function(json) {
			var result = JSON.parse(json);
			if(result.code=="success") {
				alert(result.desc)
				window.location.replace("/member/main2");
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

.font3 {
	font-color: black;
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
	
		<div class="container col-6">
			<c:url value="j_spring_security_check" var="loginUrl"/>
			<form action="${loginUrl}" method="post">
				<c:if test="${param.error != null}">
					<p>
						${error_message}
					</p>
				</c:if>
				<div class="input-group mb-3">
  					<span class="input-group-text col-2" id="basic-addon1">아이디</span>
					<input type="text" class="form-control" id="j_username" name="j_username" aria-label="아이디" aria-describedby="basic-addon1" value="${username}">
				</div>
				<div class="input-group mb-3">
  					<span class="input-group-text col-2" id="basic-addon1">비밀번호</span>
  					<input type="password" class="form-control" id="j_password" name="j_password" aria-label="비밀번호" aria-describedby="basic-addon1">
				</div>
				<br>
				<div>
					<input type="submit" class="btn btn-dark col" value="로그인">
					<br>
					<p class="row justify-content-center">
					<a href="/security/joinForm" class="font3">회원가입</a>&nbsp;&nbsp;
					<a href="" class="font3">고객센터</a>
					</p>
				</div>
				<div>
					<%
   						String clientId = "nior9v26kcvpaRLlFZCi";//애플리케이션 클라이언트 아이디값";
    					String redirectURI = URLEncoder.encode("http://localhost:8081/security/signup_sns", "UTF-8");
    					SecureRandom random = new SecureRandom();
    					String state = new BigInteger(130, random).toString();
    					String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    					apiURL += "&client_id=" + clientId;
    					apiURL += "&redirect_uri=" + redirectURI;
    					apiURL += "&state=" + state;
    					session.setAttribute("state", state);
 					%>
  					<a href="<%=apiURL%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
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