<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src='https://code.responsivevoice.org/responsivevoice.js'></script>
<script>
	$.noConflict();
	
	var J=jQuery;
	var everyWords = new Array();
	var repeatEveryWords = "";
	var totalCount = ${totalCount};
	var endCount = ${removeTotalCount};
	var endPage = Math.ceil(endCount/15);
	var page = 1;
	var changeHeight = 0;
	
	function sessionCreate(){
		responsiveVoice.setDefaultVoice("US English Female");
		
		for(var i = 1; i< endCount + 1; i++){
			everyWords.push(document.getElementById(i + "_word").value);
		}
		
		if(${alreadyFinishedThisLesson} == true){
			if(confirm("이미 모두 공부한 과목입니다. 다시 공부 하시겠습니까?") == true){
				/* 초기화 */
				document.getElementById("knownWords").value = "";
			}else{
			}
		}
		sessionStorage.setItem("session_words", document.getElementById("knownWords").value);
		pageLink(1);
		graphChange();
	}
	
	function pageLink(p){
		page = p;
		J('.transform').removeClass('transform-active');
		if(endPage == page){
			changeHeight = 48 * (endCount - (page - 1) * 15);
		 	J('.transform').css("-webkit-transition-duration","0s");
		 	J('#box').css("height", changeHeight + "px");
		 	/* J('.transform-active').css("transformY","192px");
		 	J('.transform-active').css("background-color","#fff"); */
		}else{
		 	J('.transform').css("-webkit-transition-duration","0s");
		 	J('#box').css("height", "480px");
		 	/* J('.transform-active').css("transform","translate(0px, 480px)"); */
		}
		
		J('tr').css('display','none');
		J('tr:eq(0)').css('display','');
		J('tr').filter(function (index){
			return ((index / 15) <= p) && ((index / 15) > p-1) && ( index < 15 * (p-1) + 11 );
		}).css('display','');
		
		makeRepeatWords(p);
	}
	
	function makeRepeatWords(p){
		repeatEveryWords = "";
		var standardNum = 15 * (p-1);
		var end = (standardNum+10) > endCount ? endCount : standardNum+10;
		for(var i = standardNum ; i< end; i++){
			if(everyWords[i] != ""){
			repeatEveryWords = repeatEveryWords + everyWords[i]+";"+ everyWords[i]+";"+ everyWords[i] + ";";
			}
		}
		for(var i = standardNum ; i< end; i++){
			if(everyWords[i] != ""){
			repeatEveryWords = repeatEveryWords + everyWords[i]+";"+ everyWords[i]+";";
			}
		}
		for(var i = standardNum ; i< end; i++){
			if(everyWords[i] != ""){
			repeatEveryWords = repeatEveryWords + everyWords[i]+";";
			}
		}
	}
	
	function disappealAndRestore(s){
		var ses = sessionStorage.getItem("session_words");
		var sCw = s + "_word";
		var sCm = s + "_meaning";
		if(document.getElementById(sCw).value != ''){
			localStorage.setItem(sCw,document.getElementById(sCw).value); 
			localStorage.setItem(sCm,document.getElementById(sCm).value);
			if(ses == ';' || ses == '' || ses == null){
				ses = ";" + document.getElementById(sCw).value + ";";
			}else{
				ses = ses + document.getElementById(sCw).value + ";";				
			}
			everyWords[s-1] = "";
			sessionStorage.setItem("session_words", ses);

			graphChange();
			
			document.getElementById(sCw).value='';
			document.getElementById(sCm).value='';
			document.getElementById("knownWords").value = sessionStorage.getItem("session_words");
		}else if(document.getElementById(sCw).value == ''){
			var local_word = localStorage.getItem(sCw);
			var l = ";" + local_word + ";";
			var local_meaning = localStorage.getItem(sCm);
			ses = ses.replace(l, ';');
			everyWords[s-1] = local_word;
			sessionStorage.setItem("session_words", ses);
			
			graphChange();
			
			document.getElementById(sCw).value=local_word;
			document.getElementById(sCm).value=local_meaning;
			document.getElementById("knownWords").value = sessionStorage.getItem("session_words");
		}
		
		makeRepeatWords(page);
	}
	
	function graphChange(){
		var ses = sessionStorage.getItem("session_words");
		/* var regExp = /;/gi; */
		var knownWordsArr = ses.split(';');
		var knownWordsCount = knownWordsArr.length;
		var widthPercent = Math.round((knownWordsCount - 1) / ${totalCount} * 100);
		if(widthPercent <= 0){
			document.getElementById("graph").style.width = '0%';
			J("#graph").html('0%');
		}else{
			document.getElementById("graph").style.width = widthPercent +'%';
			J("#graph").html(widthPercent+'%');
		}
	}
	
	function tellWord(word){
		responsiveVoice.speak(word, "US English Female", {rate: 0.7});
	}
	
	function stopSpeak(){
		responsiveVoice.cancel();
	}
	
	function knownDisappeal(){
		sessionStorage.setItem("session_words","");
	}
	
	function tellSession(){
		var sessionWords = sessionStorage.getItem("session_words");
		alert(sessionWords);
		/* responsiveVoice.speak(sessionWords); */
	}
</script>
<style>
/* 가림판 */
#main_bg4 .content_area .main_content .box {
  position:absolute;
  background-color: #218D9B;
  height: 480px;
  width: 305px;
  z-index:999;
  float:right;
  border-radius:20px;
  margin:53px 0px 0px 440px;
}

#main_bg4 .content_area .main_content .transform {
    -webkit-transition-duration:10s;
    transition-timing-function:linear;
	height: 480px;
}

#main_bg4 .content_area .main_content .transform-active { 
	transform: translateY(480px);
  	background-color: #45CEE0;
	/* height: 0px; */
} 
</style>

<script>
	function changebox(){
		J('.transform').css("-webkit-transition-duration","13s");
		J('.transform').addClass('transform-active');
		J('.transform-active').css("height","0px");
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
				
				<div id="box" class="box transform" onclick="changebox()"></div>
				
					<table>
						<colgroup>
							<col width="100">
							<col>
							<col width="318">
							<col width="76">
						</colgroup>
						<tr>
							<th class="first" onclick="tellSession();">번 호</th>
							<th>단 어</th>
							<th>뜻</th>
							<th class="last" onclick="stopSpeak();"></th>
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
								<img src="<c:url value="/images/speaker_on.png"/>" onclick='tellWord("${item.word}");'>
								</td>
								<c:if test="${status.last == true }">
								<input type="hidden" id="endCount" value="${status.count }">
								</c:if>
							</tr>
						</c:forEach>
						
					</table>
					
					<div class="btn_area">
						<div class="graph">
							<div class="graph_bg">
								<div id="graph" class="graph_ratio">
								</div>
							</div>
						</div>

						<!-- paging -->
						<input type="button" value="이전" onclick="return pageLink(page-1 < 1 ? 1 : page-1)">
						<c:forEach begin="1" end="${list.size()%15 == 0 ? list.size()/15 : list.size()/15 + 1}" step="1" varStatus="status">
							<input type="button" value="${status.count }" onclick="return pageLink(${status.count})">
							<c:if test="${status.last == true }">
								<c:set var="lastPage" value="${status.count }"></c:set>
							</c:if>
						</c:forEach>
						<input type="button" value="다음" onclick="return pageLink(${lastPage} > page+1 ? page+1 : ${lastPage })">
						
						<!-- 공부할 양이 남아있을경우 다음페이지로, 없을경우 다음스텝으로 -->		
						<div class="btn">
							
							<form action="<c:url value="/Study/${grade}/${textbook}/${lesson }/ready2" />" name="goStep2">
								<input type="hidden" name="knownWords" id="knownWords" value="${knownWords }" />
							</form>
							
							<!-- 가림판 줄어드는 버튼 start -->
							<a href="javascript:changebox();"> 
							<img src="<c:url value="/images/start_btn_02.png"/>"></a> 
							<!-- 다음스텝으로 가는 버튼 done -->
							<a href="javascript:goStep2.submit();">
							<img src="<c:url value="/images/done_btn.png"/>"></a>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
