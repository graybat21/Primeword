<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<script src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script>
	function deleteWordsGroup() {
		return confirm("선택한 그룹을 삭제하겠습니까?");
	}

	$(window).load(function() {    
	     $('#loading').hide();  
    });

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
#loading {
 width: 100%;  
 height: 100%;  
 top: 0px;
 left: 0px;
 position: fixed;  
 display: block;  
 opacity: 0.7;  
 background-color: #fff;  
 z-index: 999;  
 text-align: center; } 
  
#loading-image {  
 position: absolute;  
 top: 50%;  
 left: 50%; 
 z-index: 1000; }

</style>
</head>
<body>
<div id="wrap">
	<div id="main_bg4">
	    <div class="content_area">
	    
<%@ include file="/WEB-INF/views/layout/adminSide.jsp" %>
	    	
	    	<div class="main_content">
	    	<form action="insertExcel.prime" method="post" enctype="multipart/form-data">
	    		<input type="file" name="file" value="엑셀파일 업로드">
	    		<input type="submit" value="업로드">
	    	</form>
	    	<div id="loading"><img id="loading-image" src="${pageContext.request.contextPath}/images/loading.gif" alt="Loading..." /></div>
	    	<span>${message }</span><br>
	    	<hr>
	    	
	    		<span>&nbsp;총&nbsp;${totalCount }&nbsp;개 그룹</span>
					<table>
						<thead>
							<tr>
								<th>grade</th>
								<th>교과서</th>
								<th>레슨</th>
								<th>action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${wordsGroupList }" varStatus="status">
								<tr>
									<td>${item.grade }</td>
									<td>${item.textbook }</td>
									<td>${item.lesson }</td>
									<td><c:url var="deleteWordsGroup" value="/admin/wordsGroupDelete.prime">
											<c:param name="grade" value="${item.grade}" />
											<c:param name="textbook" value="${item.textbook}" />
											<c:param name="lesson" value="${item.lesson}" />
										</c:url> <a href="${deleteWordsGroup }"> <input type="button" value="단원삭제"
											onclick="return deleteWordsGroup()">
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<form action="wordsGroupManagement.prime">
						<select name="o">
							<option value="grade" ${param.o eq "grade" ? "selected" : "" }>grade</option>
							<option value="textbook" ${param.o eq "textbook" ? "selected" : "" }>textbook</option>
						</select> <input type="text" name="k" value="${searchKeyword }"> <input
							type="submit" value="검색">
					</form>
					
					<!-- 페이징 -->
					<ul class="pagination">
						<c:if test="${pageMaker.prev }">
							<c:if test="${searchKeyword != null }">
								<c:url var="adminGroupWordList" value="wordsGroupManagement.prime">
									<c:param name="page" value="${pageMaker.start - 1}" />
									<c:param name="o" value="${searchOption }"></c:param>
									<c:param name="k" value="${searchKeyword }"></c:param>
								</c:url>
							</c:if>
							<c:if test="${searchKeyword == null }">
								<c:url var="adminGroupWordList" value="wordsGroupManagement.prime">
									<c:param name="page" value="${pageMaker.start - 1}" />
								</c:url>
							</c:if>
							<li><a href="${adminGroupWordList }">이전</a></li>
						</c:if>
						<c:forEach begin="${pageMaker.start }" end="${pageMaker.end}"
							var="idx">
							<c:if test="${searchKeyword != null }">
								<c:url var="adminGroupWordList" value="wordsGroupManagement.prime">
									<c:param name="page" value="${idx}" />
									<c:param name="o" value="${searchOption }"></c:param>
									<c:param name="k" value="${searchKeyword }"></c:param>
								</c:url>
							</c:if>
							<c:if test="${searchKeyword == null }">
								<c:url var="adminGroupWordList" value="wordsGroupManagement.prime">
									<c:param name="page" value="${idx}" />
								</c:url>
							</c:if>
							<li
								class='<c:out value="${idx == pageMaker.page ? 'now' : ''}"/>'>
								<a href='${adminGroupWordList }'>${idx}</a>
							</li>

						</c:forEach>
						<c:if test="${pageMaker.next }">
							<c:if test="${searchKeyword != null }">
								<c:url var="adminGroupWordList" value="wordsGroupManagement.prime">
									<c:param name="page" value="${pageMaker.end + 1}" />
									<c:param name="o" value="${searchOption }"></c:param>
									<c:param name="k" value="${searchKeyword }"></c:param>
								</c:url>
							</c:if>
							<c:if test="${searchKeyword == null }">
								<c:url var="adminGroupWordList" value="wordsGroupManagement.prime">
									<c:param name="page" value="${pageMaker.end + 1}" />
								</c:url>
							</c:if>
							<li><a href="${adminGroupWordList }">다음</a></li>
						</c:if>
					</ul>
					
	    	
	    	
	    	</div>
	    </div>		
	</div>

</div>
</body>
</html>
