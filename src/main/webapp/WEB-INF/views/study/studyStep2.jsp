<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<script src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>

<script>
	$.noConflict();
	var J=jQuery;
	
	function sessionCreate(){
		pageLink(1);
		if(${alreadyFinishedThisLesson} == true){
			if(confirm("이미 모두 공부한 과목입니다. 다시 공부 하시겠습니까?") == true){
				/* 초기화 */
				document.getElementById("knownWords").value = "";
				/* resetList.submit(); */
			}else{
				
			}
		}
		sessionStorage.setItem("session_words", document.getElementById("knownWords").value);
		/* alert(sessionStorage.getItem("session_words")); */
	}

	function knownDisappeal(){
		sessionStorage.setItem("session_words","");
	}
	function tellSession(){
		alert(sessionStorage.getItem("session_words"));
	}
	
	function pageLink(p){
		J('tr').css('display','none');
		J('tr:eq(0)').css('display','');
		J('tr').filter(function (index){
			return ((index / 10) <= p) && ((index / 10) > p-1);
		}).css('display','');
	}
	
	function disappealAndRestore(s){
		var ses = sessionStorage.getItem("session_words");
		var sCw = s + "_word";
		var sCm = s + "_meaning"
		var local_word
		if(document.getElementById(sCw).value != ''){
			localStorage.setItem(sCw,document.getElementById(sCw).value); 
			localStorage.setItem(sCm,document.getElementById(sCm).value);
			if(ses == ',' || ses == '' || ses == null){
				ses = ","+document.getElementById(sCw).value+",";
			}else{
				ses = ses + document.getElementById(sCw).value+",";				
			}
			sessionStorage.setItem("session_words", ses);
			var strArray = sessionStorage.getItem("session_words").trim().split(',');
			var widthPercent = Math.round((strArray.length - 2) / ${totalCount} * 100);
			document.getElementById("graph").style.width = widthPercent + '%';
			J("#graph").html(widthPercent+'%');
			document.getElementById(sCw).value='';
			document.getElementById(sCm).value='';
			document.getElementById("knownWords").value = sessionStorage.getItem("session_words");
			alert(ses);
		}else if(document.getElementById(sCw).value == ''){
			var local_word = localStorage.getItem(sCw);
			var l = ","+local_word+",";
			var local_meaning = localStorage.getItem(sCm);
			/* ses = sessionStorage.getItem("session_words"); */
			ses = ses.replace(l, ',');
			sessionStorage.setItem("session_words", ses);
			var strArray = sessionStorage.getItem("session_words").trim().split(',');
			var widthPercent = Math.round((strArray.length - 2) / ${totalCount} * 100);
			document.getElementById("graph").style.width = widthPercent +'%';
			J("#graph").html(widthPercent+'%');
			document.getElementById(sCw).value=local_word;
			document.getElementById(sCm).value=local_meaning;
			document.getElementById("knownWords").value = sessionStorage.getItem("session_words");
			alert(ses);
		}
	}
</script>
<script>
/* 사이드 lesson 선택 마우스움직이에 따라 보이는게 다름 */
J(function() {
	J(".first_img").attr("src",J(".first_img").attr("src").replace("_off","_on"));
	J(".left_menu li img").mouseover(function() {
		J(this).attr("src",J(this).attr("src").replace("_off","_on"));
	});
	J(".left_menu li img").mouseout(function() {
		J(this).attr("src",J(this).attr("src").replace("_on","_off"));
	});
})
</script>
<style>
/* 가림판 */
#main_bg4 .content_area .main_content .shade {
	position: relative;
	top: -500px;
	left: 434px;
	width: 318px;
	height: 460px;
	border: 1px solid #214296;
	border-radius: 10px;
	background: #214296;
	z-index: 999;
}
</style>
</head>
<body onload="sessionCreate();">
	<div id="wrap">

		<div id="main_bg4">
			<div class="content_area">
			
				<div class="sidebar">
					<div class="title">단어암기</div>
					<div class="line"></div>
					<div class="left_menu">
						<ul>
							<c:forEach begin="1" end="${howManyLesson }" var="x">
								<li><a
									href="<c:url value="/Study/${grade}/${textbook}/${x }/ready1"/>">
										<c:if test="${x eq lesson }">
											<img
												src="<c:url value="/images/left_middle_sub${x}_off.png"/>"
												alt="과목" class="first_img">
										</c:if> <c:if test="${x ne lesson }">
											<img
												src="<c:url value="/images/left_middle_sub${x}_off.png"/>"
												alt="과목">
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
							<th class="last" onclick="tellSession();"></th>
						</tr>
						<c:forEach var="item" items="${list }" varStatus="status" >
							<tr id="${status.count}_tr" style="display:none;">
								<td class="first">${status.count }</td>
								<td><input type="text" id="${status.count}_word"
									value="${item.word }" class="input_01"
									onclick="disappealAndRestore(${status.count});" readonly></td>
								<td><input type="text" id="${status.count}_meaning"
									name="${item.no }" value="${item.meaning }" class="input_02"
									onclick="disappealAndRestore(${status.count});" readonly></td>
								<td class="last">
								<a href="https://translate.google.com/translate_tts?q=${item.word }&tl&tl=en-us&client=tw-ob">
								<img src="<c:url value="/images/speaker_on.png"/>" alt=""></a></td>
							</tr>
							<%-- <div class="shade" id="shade${status.index}"></div> --%>
						</c:forEach>
					</table>
					
					<div class="btn_area">
						<div class="graph">
							<div class="graph_bg">
								<div id="graph" class="graph_ratio" style="width: ${(1 - (list.size() / totalCount)) * 100}%">
								</div>
							</div>
						</div>

						<!-- paging -->
						<c:forEach begin="1" end="${list.size()%10 == 0 ? list.size()/10 : list.size()/10 +1}" step="1" varStatus="status">
							<input type="button" value="${status.count }" onclick="return pageLink(${status.count})">
						</c:forEach>
						
						<!-- 공부할 양이 남아있을경우 다음페이지로, 없을경우 다음스텝으로 -->		
						<div class="btn">
							
							<%-- <form action="<c:url value="/Study/${grade}/${textbook}/${lesson }/step1" />" name="resetList">
								<input type="hidden" name="knownWords" id="knownWords" value="${knownWords }" />
							</form> --%>
							<form action="<c:url value="/Study/${grade}/${textbook}/${lesson }/ready2" />" name="goStep2">
								<input type="hidden" name="knownWords" id="knownWords" value="${knownWords }" />
							</form>
							
							<!-- 발음읽어주는 버튼 start -->
							<a href="javascript:pronounceRepeat();"> <img
								src="<c:url value="/images/start_btn_02.png"/>" alt="">
							<!-- 다음스텝으로 가는 버튼 done -->
							</a> <a href="javascript:goStep2.submit();"><img
								src="<c:url value="/images/done_btn.png"/>" alt=""></a>
						</div>
					</div>
					
				</div>
				<!-- <div class="shade" id="shade"></div>
				<div class="shade" id="shade" style="top:-561px;"></div>
	    		 <div class="shade" style="top:-558px;"></div>
	    		<div class="shade" style="top:-460px;"></div>
	    		<div class="shade" style="top:-457px;"></div> -->
			</div>
		</div>
	</div>
</body>
</html>
