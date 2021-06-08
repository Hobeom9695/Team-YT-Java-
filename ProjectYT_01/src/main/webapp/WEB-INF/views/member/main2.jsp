<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset= UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>와이티YT</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.js"></script>

<script>
window.addEventListener('load', function () {
	naverLogin.getLoginStatus(function (status) {
		if (status) {
			/* (6) 로그인 상태가 "true" 인 경우 로그인 버튼을 없애고
			   사용자 정보를 출력합니다. */
			setLoginStatus();
		}
	});
});
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
	font-color: white;
	font-weight: bold;
	font-size: 1.2em;
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
		<a class="navbar-brand" href="#">YT</a>

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
		<div class="visible font3">
			<sec:authentication property="name"/>&nbsp;
			<c:url value="/logout" var="logoutUrl" />
			<a href="${logoutUrl}" type="button" class="btn btn-secondary">로그아웃</a>&nbsp;
		</div>
		<div class="invisible">
			<tr>
				<td class="button">
					<button type="button" class="btn btn-secondary" onclick="javascript:window.location='/loginForm'">로그인</button>&nbsp;
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
		
		<div class="d-flex justify-content-center">
			<div class="border border-3">
				<img src="img/Youtube_logo.png" alt="logo"><a href=""></a>
			</div>&nbsp;&nbsp;&nbsp;
			<div class="border border-3">
				<img src="img/Twitch_logo.png" alt="logo"><a href=""></a>
			</div>
		</div>
		
		<hr>
	
		<table class="table table-hover table-dark">
			<thead>
				<tr>

				</tr>
			</thead>
			<tbody>

			</tbody>
			<tr class="col-12">
				<td class="button">
					<button type="button" class="btn btn-primary btn-lg" onclick="">버튼</button>
				</td>
			</tr>
		</table>
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