<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
</head>
<body>
<div id="wrap">
	
	<div id="main_bg7">
		<div class="high_step">
			<div class="grade"><div class="grade_text">${grade }</div></div>
			<div class="publish"><div class="publish_text">${textbook }</div></div>
			<div class="menu_btn">
				<ul class="top">
					<c:forEach begin="1" end="${howManyLesson }" var="x">
						<li><a href="${pageContext.request.contextPath}/Study/${grade}/${textbook}/${x}/ready1">
						<img src="${pageContext.request.contextPath}/images/high_menu_${x}.png" alt=""></a></li>
					</c:forEach>
					<%-- <li><a href="${pageContext.request.contextPath}/Study/${grade}/${textbook}/2/start">
					<img src="${pageContext.request.contextPath}/images/high_menu_2.png" alt=""></a></li>
					<li><a href="${pageContext.request.contextPath}/Study/${grade}/${textbook}/3/start">
					<img src="${pageContext.request.contextPath}/images/high_menu_3.png" alt=""></a></li>
					<li><a href="${pageContext.request.contextPath}/Study/${grade}/${textbook}/4/start">
					<img src="${pageContext.request.contextPath}/images/high_menu_4.png" alt=""></a></li> --%>					
				</ul>
				<%-- <ul class="bottom">
					<li><a href="#"><img src="${pageContext.request.contextPath}/images/high_menu_5.png" alt=""></a></li>
					<li><a href="#"><img src="${pageContext.request.contextPath}/images/high_menu_6.png" alt=""></a></li>
					<li><a href="#"><img src="${pageContext.request.contextPath}/images/high_menu_7.png" alt=""></a></li>
					<li><a href="#"><img src="${pageContext.request.contextPath}/images/high_menu_8.png" alt=""></a></li>
				</ul> --%>
			</div>
		</div>    
	</div>
</div>
</body>
</html>
