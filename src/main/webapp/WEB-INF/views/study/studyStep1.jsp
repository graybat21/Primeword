<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<script src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
<script>
$(function() {
	$(".first_img").attr("src",$(".first_img").attr("src").replace("_off","_on"));
	$(".left_menu li img").mouseover(function() {
		$(this).attr("src",$(this).attr("src").replace("_off","_on"));
	});
	$(".left_menu li img").mouseout(function() {
		$(this).attr("src",$(this).attr("src").replace("_on","_off"));
	});
})
</script>
<script>
	function knownDisappeal(){
		/* sessionStorage.removeItem("known"); */
		sessionStorage.setItem("known","");
	}
	function disappealAndRestore(statusCount){
		var ses = sessionStorage.getItem("known") +","+ document.getElementById(statusCount).value;
		sessionStorage.setItem("known", ses);
		document.getElementById(statusCount).value='';
		alert(sessionStorage.getItem("known"));
	}
</script>
<style>
/* 가림판 */
#main_bg4 .content_area .main_content .shade {
	position:relative;
	top:100px;
	left:434px;
	width:318px;
	height:46px;
	border:1px solid #214296;
	border-radius:10px;
	background:#214296;
	z-index:999;
}
</style>
</head>
<body>
<div id="wrap">
	
	<div id="main_bg4">
	    <div class="content_area">
	    	<div class="sidebar">
	    		<div class="title">단어암기</div>
	    		<div class="line"></div>
	    		<div class="left_menu">
	    			<ul>
	    				<c:forEach begin="1" end="${howManyLesson }" var="x">
	    				<li><a href="<c:url value="/Study/${grade}/${textbook}/${x }"/>">
	    				<c:if test="${x eq lesson }">
	    				<img src="<c:url value="/images/left_middle_sub${x}_off.png"/>" alt="과목" class="first_img">
	    				</c:if>
	    				<c:if test="${x ne lesson }">
	    				<img src="<c:url value="/images/left_middle_sub${x}_off.png"/>" alt="과목">
	    				</c:if>
	    				</a></li>
	    				</c:forEach>
	    			</ul>
	    		</div>
	    	</div>
	    	<div class="main_content">
	    		<table>
	    		    <colgroup>
	    		    	<col width="100">
	    		    	<col>
	    		    	<col width="318">
	    		    	<col width="76">
	    		    </colgroup>
	    			<tr>
	    				<th class="first" onclick="knownDisappeal();">번 호</th>
	    				<th>단 어</th>
	    				<th>뜻</th>
	    				<th class="last"></th>
	    			</tr>
	    			<c:forEach var="item" items="${list }" varStatus="status">
	    			<c:if test="${status.count lt 11}">
	    			<tr>
	    				<td class="first">${status.count }</td>
	    				<td><input type="text" id="${status.count}" value="${item.word }" class="input_01" onfocus="disappealAndRestore(${status.count});"></td>
	    				<td><input type="text" name="${item.no }" value="${item.meaning }" class="input_02"></td>
	    				<td class="last">
	    				<img src="<c:url value="/images/speaker_on.png"/>" alt="">
	    				</td>
	    			</tr>
						<%-- <div class="shade" id="shade${status.index}"></div> --%>
	    			</c:if>
	    			</c:forEach>
	    		</table>
	    		<div class="btn_area">
	    		    <div class="graph"><div class="graph_bg"><div class="graph_ratio" style="width:35%">35%</div></div></div>
	    			<div class="btn">
	    			<a href="studyStep2.prime"><img src="<c:url value="/images/start_btn_02.png"/>" alt=""></a> 
	    			<a href="#"><img src="<c:url value="/images/done_btn.png"/>" alt=""></a>
	    			</div>
	    		</div>
	    		<!--<div class="shade" id="shade0"></div>
	    		<div class="shade" id="shade1" style="top:-561px;"></div>
	    		 <div class="shade" style="top:-558px;"></div>
	    		<div class="shade" style="top:-460px;"></div>
	    		<div class="shade" style="top:-457px;"></div> -->
	    	</div>
	    </div>		
	</div>
</div>
</body>
</html>
