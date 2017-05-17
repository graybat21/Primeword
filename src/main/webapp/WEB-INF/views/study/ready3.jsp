<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
</head>
<body>
<div id="wrap">
	
	<div id="main_bg4">
	    <div class="content_area">
	    	<div class="sidebar">
	    		<div class="title">단어암기</div>
	    		<div class="line"></div>
	    	</div>
	    	
	    	<div class="main_content">
	    		<p class="first"><span>발음+뜻</span> 페이지 입니다.</p>
	    		<p class="second">3번 2번 1번 암기 후<span>에</span></p>
	    		<p class="third">암기되는 단어를 <span>1</span><span>초</span> 이내로 <span>클릭</span><span>하세요.</span></p>
	    		<p class="four">(그 외 단어는 발음과 암기가 될때까지 스피커를 눌러 반복합니다.)</p>
	    		<a href="${pageContext.request.contextPath}/Study/${grade}/${textbook}/${lesson}/step3">
				<div class="start_btn"><img src="<c:url value="/images/start_btn.png"/>" alt=""></div></a>
	    	</div>
	    	
	    </div>		
	</div>

</div>
</body>
</html>
