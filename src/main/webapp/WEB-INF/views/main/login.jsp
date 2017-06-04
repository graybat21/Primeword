<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="_csrf_header" content="X-CSRF-TOKEN" />
<meta name="${_csrf.parameterName}" content="${_csrf.token}" />
<script type="text/javascript">
	function submit(){
		document.getElementById('login-form').submit();
	}
</script>
</head>
<body>
<div id="wrap">
	
	<div id="main_bg">
		<div class="login_wrap">
			<div class="login_content">
				<div class="login_title">로그인</div>
				<form method="post" id="login-form" name="login-form">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<div class="input_area">
					<div class="left">
						<div class="top">
							<div class="text">아이디</div>
							<div class="input"><input type="text" name="username" value="${USER.username }"></div>
						</div>
						<div class="bottom">
							<div class="text">비밀번호</div>
							<div class="input"><input type="password" name="passwd"></div>
						</div>
					</div>
					<div class="right">
						<img src="images/login_btn.png" onclick="submit();" alt="로그인">
					</div>
				</div>
				</form>
				<div class="link">
					<a href="#">아이디 찾기</a>
					<span>|</span>
					<a href="#">비밀번호 찾기</a>
					<span>|</span>
					<a href="/Primeword/join.prime">회원 가입</a>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
