<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
</head>
<body>
<div id="wrap">
	
	<div id="main_bg">
		<div class="join_wrap">
			<div class="login_content">
				<div class="login_title">회원가입</div>
				<form method="post" id="join-form" name="join-form">
				<div class="input_area">
					<div class="left">
						<div class="top">
							<div class="text">아이디</div>
							<div class="input"><input type="text" name="username"></div>
						</div>
						<div>
							<div class="text">비밀번호</div>
							<div class="input"><input type="password" name="passwd"></div>
						</div>
						<div>
							<div class="text">비밀번호확인</div>
							<div class="input"><input type="password" name="passwdCheck"></div>
						</div>
						<div class="bottom">
							<div class="text">학년</div>
							<div class="input"><input type="text" name="grade" value="1"></div>
						</div>
					</div>
					<div class="right">
						<div class="join-form-submit">
						<input type="submit" value="가입"/>
						</div>
						<!-- <img src="images/login_btn.png" alt="조인" onclick="javascript:join-form.submit();" > -->
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
