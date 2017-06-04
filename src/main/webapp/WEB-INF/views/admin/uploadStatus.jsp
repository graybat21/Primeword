<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="wrap">
	<div id="main_bg4">
		<div class="content_area">

			<%@ include file="/WEB-INF/views/layout/adminSide.jsp"%>

			<div class="main_content">

				<h1>업로드 상황</h1>
				${message}
				<br><hr>
				<c:forEach var="item" items="${errorNo }">
					${item } 행 입력 안됨<br>
				</c:forEach>
			</div>
		</div>
	</div>
</div>