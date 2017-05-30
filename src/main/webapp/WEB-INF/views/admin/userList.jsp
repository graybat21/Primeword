<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<script>
	function deleteMem() {
		return confirm("선택한 회원을 탈퇴시키겠습니까?");
	}
</script>
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
								<th>기타</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${userList }" varStatus="status">
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
									<td><c:url var="deleteMem" value="/admin/userDelete.prime">
											<c:param name="no" value="${item.no }" />
										</c:url> <a href="${deleteMem }"> <input type="button" value="탈 퇴"
											onclick="return deleteMem()">
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<form action="userManagement.prime">
						<select name="o">
							<option value="username"
								${param.o eq "username" ? "selected" : "" }>username</option>
							<option value="birth" ${param.o eq "birth" ? "selected" : "" }>birth</option>
							<option value="phone" ${param.o eq "phone" ? "selected" : "" }>phone</option>
							<option value="belong" ${param.o eq "belong" ? "selected" : "" }>belong</option>
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
