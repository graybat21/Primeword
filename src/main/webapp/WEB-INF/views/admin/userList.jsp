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
				    	<th>번호</th><th>이름</th><th>pw</th><th>birth</th><th>기타</th>
				    </tr>
				    </thead>
				    <tbody>
				    	<c:forEach var="item" items="${userList }" varStatus="status">
				    	<tr>
				    		<td>${status.count }</td>
				    		<td>${item.username }</td>
				    		<td>${item.passwd }</td>
				    		<td>${item.birth }</td>
				    		<td>
				    			<c:url var="deleteMem" value="/admin/userDelete.prime">
									<c:param name="username" value="${item.username }" />
								</c:url>								
								<a href="${deleteMem }">
								<input type="button" value="탈 퇴" onclick="return deleteMem()">
								</a>
							</td>
				    	</tr>
				    	</c:forEach>
				    </tbody>
	    		</table>
	    		
	    		<script type="text/javascript">		
					function deleteMem() {
						return confirm("선택한 회원을 탈퇴시키겠습니까?");
					}
				</script>
				
	    	</div>
	    </div>		
	</div>

</div>
</body>
</html>
