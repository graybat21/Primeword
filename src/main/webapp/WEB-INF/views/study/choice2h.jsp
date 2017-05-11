<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
</head>
<body>
<div id="wrap">
	
	<div id="main_bg6">
		<div class="high_step">
		    <div class="step_left">
		    	<c:forEach var="item" items="${textbookList10 }" varStatus="status">
		    		<c:if test="${status.count eq 1 }">
		    		<div class="first"><a href="10/${item}">${item}</a></div>
		    		</c:if>
		    		<c:if test="${status.count gt 1 }">
		    		<div class="second"><a href="10/${item}">${item}</a></div>
		    		</c:if>
		    	</c:forEach>
				<!-- <div class="second"><a href="#">금성(김경한)</a></div>
				<div class="second"><a href="#">능률(이찬승)</a></div>
				<div class="second"><a href="#">미래엔(양형권)</a></div>
				<div class="second"><a href="#">비상(홍민표)</a></div>
				<div class="second"><a href="#">천재(김진완)</a></div>
				<div class="second"><a href="#">천재(이창봉)</a></div>
				<div class="third"><a href="#">YBM(박준언)</a></div>
				<div class="second"><a href="#">금성(김경한)</a></div>
				<div class="second"><a href="#">능률(이찬승)</a></div>
				<div class="second"><a href="#">동아(김성곤)</a></div>
				<div class="second"><a href="#">미래엔(양형권)</a></div>
				<div class="second"><a href="#">천재(김진완)</a></div>
				<div class="second"><a href="#">천재(이창봉)</a></div> -->
		    </div>
		    <div class="step_middle">
		    	<c:forEach var="item" items="${textbookList11 }" varStatus="status">
		    		<c:if test="${status.count eq 1 }">
		    		<div class="first"><a href="11/${item}">${item}</a></div>
		    		</c:if>
		    		<c:if test="${status.count gt 1 }">
		    		<div class="second"><a href="11/${item}">${item}</a></div>
		    		</c:if>
		    	</c:forEach>
		    	<!-- <div class="first"><a href="#">YBM(신정현)</a></div>
				<div class="second"><a href="#">금성(김경한)</a></div>
				<div class="second"><a href="#">능률(이찬승)</a></div>
				<div class="second"><a href="#">미래엔(양형권)</a></div>
				<div class="second"><a href="#">비상(홍민표)</a></div>
				<div class="second"><a href="#">천재(김진완)</a></div>
				<div class="second"><a href="#">천재(이창봉)</a></div>
				<div class="third"><a href="#">YBM(박준언)</a></div>
				<div class="second"><a href="#">금성(김경한)</a></div>
				<div class="second"><a href="#">능률(이찬승)</a></div>
				<div class="second"><a href="#">동아(김성곤)</a></div>
				<div class="second"><a href="#">천재(김진완)</a></div>
				<div class="second"><a href="#">천재(이창봉)</a></div> -->
		    </div>
		    <div class="step_right">
		    	<c:forEach var="item" items="${textbookList12 }" varStatus="status">
		    		<c:if test="${status.count eq 1 }">
		    		<div class="first"><a href="12/${item}">${item}</a></div>
		    		</c:if>
		    		<c:if test="${status.count gt 1 }">
		    		<div class="second"><a href="12/${item}">${item}</a></div>
		    		</c:if>
		    	</c:forEach>
		    	<!-- <div class="first"><a href="#">YBM(박준언)</a></div>
				<div class="second"><a href="#">금성(이의갑)</a></div>
				<div class="second"><a href="#">능률(이찬승)</a></div>
				<div class="second"><a href="#">천재(김진완)</a></div>
				<div class="four"><a href="#">YBM(신정현)</a></div>
				<div class="second"><a href="#">금성(이의갑)</a></div>
				<div class="second"><a href="#">능률(이찬승)</a></div>				
				<div class="second"><a href="#">천재(김진완)</a></div> -->
		    </div>		
		</div>
	</div>
</div>
</body>
</html>
