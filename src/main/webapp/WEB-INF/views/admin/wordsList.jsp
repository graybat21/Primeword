<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	    		<div class="title">관리 항목</div>
	    		<div class="line"></div>
	    		<div class="left_menu">
	    		<ul>
	    		<a href="/Primeword/admin/userManagement.prime">
	    		<li>User 관리</li></a>
	    		<a href="/Primeword/admin/wordsManagement.prime">
	    		<li>Words 관리</li></a>
	    		<a href="/Primeword">
	    		<li>기타 관리</li></a>
	    		</ul>
	    		</div>
	    	</div>
	    	
	    	<div class="main_content">
	    		<table>
				    <thead>
				    <tr>
				    	<th>번호</th><th>단어명</th><th>뜻</th><th>grade</th><th>교과서</th><th>레슨</th>
				    </tr>
				    </thead>
				    <tbody>
				    	<c:forEach var="item" items="${wordsList }" varStatus="status">
				    	<tr>
				    		<td>${status.count }</td>
				    		<td>${item.word }</td>
				    		<td>${item.meaning}</td>
				    		<td>${item.grade }</td>
				    		<td>${item.textbook }</td>
				    		<td>${item.lesson }</td>
				    	</tr>
				    	</c:forEach>
				    </tbody>
	    		</table>
	    		
				<form method="post">
					<select name="searchOption">
						<option>grade</option>
						<option>textbook</option>
					</select>
					<input type="text" name="searchKeyword">
				</form>
	    	</div>
	    </div>		
	</div>

</div>
</body>
</html>
