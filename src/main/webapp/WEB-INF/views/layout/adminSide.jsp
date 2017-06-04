<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sidebar">
	<div class="title">관리 항목</div>
	<div class="line"></div>
	<div class="left_menu">
	<c:if test="${USER.school == '1' }">
	<ul>
	<a href="/Primeword/admin/userManagement.prime">
	<li>User 관리</li></a>
	<a href="/Primeword/admin/wordsManagement.prime">
	<li>Words 관리</li></a>
	<a href="/Primeword/admin/wordsGroupManagement.prime">
	<li>Words 그룹 관리</li></a>
	</ul>
	</c:if>
	</div>
</div>