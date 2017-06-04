<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<div id="wrap">
	<div id="main_bg2">
		<div class="main_btn_area">
			<ul>
				<li><a href="#"><img src="images/main_btn_01.png" alt="레벨테스트"></a></li>
				<li><a href="/Primeword/Study"><img src="images/main_btn_02.png" alt="단어암기"></a></li>
				<li><a href="#"><img src="images/main_btn_03.png" alt="나의학습"></a></li>
				<c:if test="${USER.school == '1' }">
				<li><a href="/Primeword/admin/main.prime"><img src="images/main_btn_04.png" alt="관리자페이지"></a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
</body>
</html>
