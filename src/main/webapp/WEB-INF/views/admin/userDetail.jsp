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
							<th>번호</th>
							<th>이름</th>
							<th>pw</th>
							<th>birth</th>
							<th>phone</th>
							<th>소속</th>
							<th>기타</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${user.no}</td>
							<td>${user.username }</td>
							<td>${user.passwd }</td>
							<td>${user.birth }</td>
							<td>${user.phone}</td>
							<td>${user.belong }</td>
							<td><c:url var="deleteMem" value="/admin/userDelete.prime">
									<c:param name="no" value="${user.no }" />
								</c:url> <a href="${deleteMem }"> <input type="button" value="탈 퇴"
									onclick="return deleteMem()">
							</a></td>
						</tr>
					</tbody>
	    		</table>
	    		
	    		<table width="100%">
	    			<thead><tr>
	    				<th>grade</th>
	    				<th>textbook</th>
	    				<th>lesson</th>
	    				<th>진행률</th>
	    				<th width="70%">학습단어</th>
	    				<th>기타</th>
	    			</tr></thead>
	    			<tbody>
	    				<c:forEach var="item" items="${remember}">
	    				<tr>
	    					<td>${item.grade }</td>
	    					<td>${item.textbook }</td>
	    					<td>${item.lesson }</td>
	    					<td></td>
	    					<td>${item.words }</td>
	    					<td></td>
	    				</tr>
	    				</c:forEach>
	    			</tbody>
	    		</table>
	    	</div>
	    </div>		
	</div>

</div>
</body>
</html>
