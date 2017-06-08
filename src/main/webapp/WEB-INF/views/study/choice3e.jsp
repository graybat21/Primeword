<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
</head>
<body>
<div id="wrap">
	
	<div id="main_bg5">
		<div class="elementary_step">
		    <div class="step_left">
				<c:forEach begin="1" end="${howManyLessonBasic }" var="x">
		    		<div class="first">
		    		<a href="${pageContext.request.contextPath}/Study/초등/입문/${x}/ready1">${x}강</a>
		    		</div>
		    		<%-- <div class="second"><a href="#">${x}강</a></div> --%>
				</c:forEach>
		    </div>	
		    <div class="step_right">
		    	<c:forEach begin="1" end="${howManyLessonAdvanced }" var="x">
		    		<div class="first">
		    		<a href="${pageContext.request.contextPath}/Study/초등/도약/${x}/ready1">${x}강</a>
		    		</div>
		    	</c:forEach>
		    </div>		
		</div>
	</div>
</div>
</body>
</html>
