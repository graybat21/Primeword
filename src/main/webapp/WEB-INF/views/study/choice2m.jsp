<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
</head>
<body>
<div id="wrap">
	
	<div id="main_bg5">
		<div class="middle_step">
		    <div class="step_left">
	    		<c:forEach var="item" items="${textbookList7 }" varStatus="status">
		    		<c:if test="${status.count eq 1 }">
		    		<div class="first"><a href="7/${item}">${item}</a></div>
		    		</c:if>
		    		<c:if test="${status.count gt 1 }">
		    		<div class="second"><a href="7/${item}">${item}</a></div>
		    		</c:if>
		    	</c:forEach>
		    	<!-- <div class="first"><a href="#">동아(김성곤)</a></div>
				<div class="second"><a href="#">동아(이병민)</a></div>
				<div class="second"><a href="#">지학사(양현권)</a></div>
				<div class="second"><a href="#">천재(김진완)</a></div>
				<div class="second"><a href="#">천재(이재영)</a></div>
				<div class="second"><a href="#">천재(김진완)</a></div>
				<div class="second"><a href="#">천재(정사열)</a></div>
				<div class="second"><a href="#">YBM(박준언)</a></div>
				<div class="second"><a href="#">YBM(신정현)</a></div> -->
		    </div>
		    <div class="step_middle">
		    	<c:forEach var="item" items="${textbookList8 }" varStatus="status">
		    		<c:if test="${status.count eq 1 }">
		    		<div class="first"><a href="8/${item}">${item}</a></div>
		    		</c:if>
		    		<c:if test="${status.count gt 1 }">
		    		<div class="second"><a href="8/${item}">${item}</a></div>
		    		</c:if>
		    	</c:forEach>
		    	<!-- <div class="first"><a href="#">동아(김성곤)</a></div>
				<div class="second"><a href="#">동아(이병민)</a></div>
				<div class="second"><a href="#">지학사(양현권)</a></div>
				<div class="second"><a href="#">천재(김진완)</a></div>
				<div class="second"><a href="#">천재(이재영)</a></div>
				<div class="second"><a href="#">천재(김진완)</a></div>
				<div class="second"><a href="#">천재(정사열)</a></div>
				<div class="second"><a href="#">YBM(박준언)</a></div>
				<div class="second"><a href="#">YBM(신정현)</a></div> -->
		    </div>
		    <div class="step_right">
		    	<c:forEach var="item" items="${textbookList9 }" varStatus="status">
		    		<c:if test="${status.count eq 1 }">
		    		<div class="first"><a href="9/${item}">${item}</a></div>
		    		</c:if>
		    		<c:if test="${status.count gt 1 }">
		    		<div class="second"><a href="9/${item}">${item}</a></div>
		    		</c:if>
		    	</c:forEach>
		    	<!-- <div class="first"><a href="#">동아(김성곤)</a></div>
				<div class="second"><a href="#">동아(이병민)</a></div>
				<div class="second"><a href="#">지학사(양현권)</a></div>
				<div class="second"><a href="#">천재(김진완)</a></div>
				<div class="second"><a href="#">천재(이재영)</a></div>
				<div class="second"><a href="#">천재(김진완)</a></div>
				<div class="second"><a href="#">천재(정사열)</a></div>
				<div class="second"><a href="#">YBM(박준언)</a></div>
				<div class="second"><a href="#">YBM(신정현)</a></div> -->
		    </div>		
		</div>
	</div>
</div>
</body>
</html>
