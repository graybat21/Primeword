<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<div id="wrap">
	<div id="top"></div>
	<div id="header">
		<div class="header_content">
			<div class="logo"><a href="/Primeword">
			<img src="${pageContext.request.contextPath}/images/logo.png" alt="로고"></a></div>
			<div class="nav_area">
				<c:if test="${USER.username == null }">
				<div class="top_nav">
					<a href="/Primeword/join.prime">회원가입</a>
					<span>|</span>
					<a href="/Primeword/login.prime">로그인</a>
				</div>
				</c:if>
				<c:if test="${USER.username != null }">
				<div class="top_nav">
					<a href="">${USER.username }&nbsp;님</a>
					<span>|</span>
					<a href="/Primeword/logout.prime">로그아웃</a>
				</div>
				</c:if>
				<div class="bottom_nav">
					<a href="#">레벨TEST</a>
					<span>|</span>
					<a href="/Primeword/Study">단어암기</a>
					<span>|</span>
					<a href="#">나의학습</a>
					<span>|</span>
					<a href="/Primeword/admin/main.prime">관리자PAGE</a>
				</div>
			</div>
		</div>
	</div></div></body></html>