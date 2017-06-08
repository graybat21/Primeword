<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<script src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script>
	function deleteMem() {
		return confirm("선택한 회원을 탈퇴시키겠습니까?");
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
			
<%@ include file="/WEB-INF/views/layout/adminSide.jsp" %>
				<div class="main_content">
					<table>
						<thead>
							<tr>
								<th>번호</th>
								<th>id</th>
								<th>pw</th>
								<th>이름</th>
								<th>birth</th>
								<th>phone</th>
								<th>소속</th>
								<th>학교</th>
								<th>기타</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${userList }" varStatus="status">
								<c:if test="${item.belong == sessionScope.USER.belong || sessionScope.USER.no == 1}">
								<tr>
									<c:url var="userDetail" value="/admin/userDetail.prime">
										<c:param name="no" value="${item.no}" />
									</c:url>
									<td><a href="${userDetail }">${item.no}</a></td>
									<td><a href="${userDetail }">${item.username }</a></td>
									<td><a href="${userDetail }">${item.passwd }</a></td>
									<td><a href="${userDetail }">${item.realname }</a></td>
									<td>${item.birth }</td>
									<td>${item.phone }</td>
									<td>${item.belong}</td>
									<td>${item.school == '1' ? '*' : item.school }
									<td>
									<c:if test="${USER.school == '1' && USER.no == 1}">
										<c:url var="deleteMem" value="/admin/userDelete.prime">
											<c:param name="no" value="${item.no }" />
										</c:url> <a href="${deleteMem }"> <input type="button" value="탈 퇴"
											onclick="return deleteMem()">
									</a></c:if>
									</td>
								</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>

					<form action="userManagement.prime">
						<select name="o">
							<option value="username"
								${param.o eq "username" ? "selected" : "" }>id</option>
							<option value="birth" ${param.o eq "birth" ? "selected" : "" }>생일</option>
							<option value="phone" ${param.o eq "phone" ? "selected" : "" }>phone</option>
							<option value="belong" ${param.o eq "belong" ? "selected" : "" }>소속</option>
							<option value="school" ${param.o eq "school" ? "selected" : "" }>학교</option>
						</select> <input type="text" name="k" value="${searchKeyword }"> <input
							type="submit" value="검색">
					</form>
					
					<!-- 페이징 -->
						<ul class="pagination">
							<c:if test="${pageMaker.prev }">
								<c:if test="${searchKeyword != null }">
									<c:url var="adminUserList" value="userManagement.prime">
										<c:param name="page" value="${pageMaker.start - 1}" />
										<c:param name="o" value="${searchOption }"></c:param>
										<c:param name="k" value="${searchKeyword }"></c:param>
									</c:url>
								</c:if>
								<c:if test="${searchKeyword == null }">
									<c:url var="adminUserList" value="userManagement.prime">
										<c:param name="page" value="${pageMaker.start - 1}" />
									</c:url>
								</c:if>
								<li><a href="${adminUserList }">이전</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.start }" end="${pageMaker.end}"
								var="idx">
								<c:if test="${searchKeyword != null }">
									<c:url var="adminUserList" value="userManagement.prime">
										<c:param name="page" value="${idx}" />
										<c:param name="o" value="${searchOption }"></c:param>
										<c:param name="k" value="${searchKeyword }"></c:param>
									</c:url>
								</c:if>
								<c:if test="${searchKeyword == null }">
									<c:url var="adminUserList" value="userManagement.prime">
										<c:param name="page" value="${idx}" />
									</c:url>
								</c:if>
								<li
									class='<c:out value="${idx == pageMaker.page ? 'current' : ''}"/>'>
									<a href='${adminUserList }'>${idx}</a>
								</li>

							</c:forEach>
							<c:if test="${pageMaker.next }">
								<c:if test="${searchKeyword != null }">
									<c:url var="adminUserList" value="userManagement.prime">
										<c:param name="page" value="${pageMaker.end + 1}" />
										<c:param name="o" value="${searchOption }"></c:param>
										<c:param name="k" value="${searchKeyword }"></c:param>
									</c:url>
								</c:if>
								<c:if test="${searchKeyword == null }">
									<c:url var="adminUserList" value="userManagement.prime">
										<c:param name="page" value="${pageMaker.end + 1}" />
									</c:url>
								</c:if>
								<li><a href="${adminUserList }">다음</a></li>
							</c:if>
						</ul>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
