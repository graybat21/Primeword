<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<script src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<!-- <script type="text/javascript" src="/js/studyUtils.js"></script>
<script>
window.onload = function(){
	var doubleSelect1 = new DoubleSelect(
		'grade', 'textbook', 'selectCategory.jsp', '&parent=grade');
	var doubleSelect2 = new DoubleSelect(
		'textbook', 'lesson', 'selectCategory.jsp', '&parent=textbook');
}
</script> -->
<script>
	function deleteWord() {
		return confirm("선택한 단어를 삭제하겠습니까?");
	}
</script>
<style>
ul.pagination {  
    text-align:center;  
}  
ul.pagination li {  
    display:inline;  
    vertical-align:middle;  
}  
ul.pagination li a {  
    display:-moz-inline-stack;  /*FF2*/  
    display:inline-block;  
    vertical-align:top;  
    padding:4px;  
    margin-right:3px;  
    width:15px !important;  
    color:#000;  
    font:bold 12px tahoma;  
    border:1px solid #eee;  
    text-align:center;  
    text-decoration:none;  
    width /**/:26px;    /*IE 5.5*/  
}  
ul.pagination li a.now {  
    color:#fff;  
    background-color:#f40;  
    border:1px solid #f40;  
}  
ul.pagination li a:hover, ul.pagination li a:focus {  
    color:#fff;  
    border:1px solid #f40;  
    background-color:#f40;  
}  
</style>
</head>
<body>
	<div id="wrap">

		<div id="main_bg4">
			<div class="content_area">
				<div class="sidebar">
					<div class="title">관리 항목</div>
					<div class="line"></div>
					<div class="left_menu">
						<ul>
							<a href="/Primeword/admin/userManagement.prime">
								<li>User 관리</li>
							</a>
							<a href="/Primeword/admin/wordsManagement.prime">
								<li>Words 관리</li>
							</a>
							<a href="/Primeword">
								<li>기타 관리</li>
							</a>
						</ul>
					</div>
				</div>

				<div class="main_content">
					<span>&nbsp;총&nbsp;${totalCount }&nbsp;개</span>
					<table>
						<thead>
							<tr>
								<th>단어명</th>
								<th>뜻</th>
								<th>grade</th>
								<th>교과서</th>
								<th>레슨</th>
								<th>기타</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${wordsList }" varStatus="status">
								<tr>
									<td>${item.word }</td>
									<td>${item.meaning}</td>
									<td>${item.grade }</td>
									<td>${item.textbook }</td>
									<td>${item.lesson }</td>
									<td><c:url var="deleteWord" value="/admin/wordDelete.prime">
											<c:param name="no" value="${item.no}" />
										</c:url> <a href="${deleteWord }"> <input type="button" value="단어삭제"
											onclick="return deleteWord()">
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<form action="wordsManagement.prime">
						<select name="o">
							<option value="word" ${param.o eq "word" ? "selected" : "" }>word</option>
							<option value="meaning" ${param.o eq "meaning" ? "selected" : "" }>meaning</option>
							<option value="grade" ${param.o eq "grade" ? "selected" : "" }>grade</option>
							<option value="textbook" ${param.o eq "textbook" ? "selected" : "" }>textbook</option>
						</select> <input type="text" name="k" value="${searchKeyword }"> <input
							type="submit" value="검색">
					</form>
					
					<!-- 페이징 -->
						<ul class="pagination">
							<c:if test="${pageMaker.prev }">
								<c:if test="${searchKeyword != null }">
									<c:url var="adminWordList" value="wordsManagement.prime">
										<c:param name="page" value="${pageMaker.start - 1}" />
										<c:param name="o" value="${searchOption }"></c:param>
										<c:param name="k" value="${searchKeyword }"></c:param>
									</c:url>
								</c:if>
								<c:if test="${searchKeyword == null }">
									<c:url var="adminWordList" value="wordsManagement.prime">
										<c:param name="page" value="${pageMaker.start - 1}" />
									</c:url>
								</c:if>
								<li><a href="${adminWordList }">이전</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.start }" end="${pageMaker.end}"
								var="idx">
								<c:if test="${searchKeyword != null }">
									<c:url var="adminWordList" value="wordsManagement.prime">
										<c:param name="page" value="${idx}" />
										<c:param name="o" value="${searchOption }"></c:param>
										<c:param name="k" value="${searchKeyword }"></c:param>
									</c:url>
								</c:if>
								<c:if test="${searchKeyword == null }">
									<c:url var="adminWordList" value="wordsManagement.prime">
										<c:param name="page" value="${idx}" />
									</c:url>
								</c:if>
								<li
									class='<c:out value="${idx == pageMaker.page ? 'now' : ''}"/>'>
									<a href='${adminWordList }'>${idx}</a>
								</li>

							</c:forEach>
							<c:if test="${pageMaker.next }">
								<c:if test="${searchKeyword != null }">
									<c:url var="adminWordList" value="wordsManagement.prime">
										<c:param name="page" value="${pageMaker.end + 1}" />
										<c:param name="o" value="${searchOption }"></c:param>
										<c:param name="k" value="${searchKeyword }"></c:param>
									</c:url>
								</c:if>
								<c:if test="${searchKeyword == null }">
									<c:url var="adminWordList" value="wordsManagement.prime">
										<c:param name="page" value="${pageMaker.end + 1}" />
									</c:url>
								</c:if>
								<li><a href="${adminWordList }">다음</a></li>
							</c:if>
						</ul>
					
				</div>
			</div>
		</div>

	</div>
</body>
</html>

<%-- 						<form action="wordsList.prime">
						<!-- <select id="searchOption" name="searchOption">
							<option value="grade">grade</option>
							<option value="textbook">textbook</option>
							<option value="lesson">lesson</option>
						</select> -->
						<select id="grade" name="grade" onchange="findTextbook(value)">
							<option>:: 선택 ::</option>
							<c:forEach var="item1" items="${gradeList }">
								<option value="${item1 }">${item1 }</option>
							</c:forEach>
						</select> &gt; 
						<select id="textbook" name="textbook">
							<option>:: 선택 ::</option>
							<c:forEach var="item2" items="${textbookList }">
								<option value="${item2 }">${item2 }</option>
							</c:forEach>
						</select> &gt;
						<select id="lesson" name="lesson">
							<option>:: 선택 ::</option>
							<c:forEach var="item3" items="${lessonList }">
								<option value="${item3 }">${item3 }</option>
							</c:forEach>
						</select>
						<input type="text" id="searchKeyword" name="searchKeyword">
						<button type="submit" id="searchBtn">검색</button>
						</form> --%>