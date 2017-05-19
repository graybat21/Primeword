<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<script src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
<script>
/* function changes1Step(v){
	$.ajax({
		url : 'textbookCondition.prime',
		dataType : 'json',
		data : params,
		type : 'POST',
		success : function(data){
			if(data == null){
				data = 0;
			}
			for(var i=0; i<data.length; i++){
				$('#textbook').append("<option value='" + data[i])
			}
		}
	})
}
 */
/* $(function() {
	var select = "<option>:: 선택 ::</option>"; 
	$("#grade").change(function() {			
		if($("#grade").val() == "") { // select의 value가 ""이면, "선택" 메뉴만 보여줌.
			$("#textbook").find("option").remove().end().append(select);
		} else {
			console.log('aa');
			comboChange($(this).val());
		}
	});

	function comboChange() {
		$.ajax({
			type:"post",
			url:"textbookCondition.prime",
			datatype: "json",
			data: $("#productForm").serialize(),
			success: function(data) {
				
				if(data.textbookList.length > 0) {
					$("#textbook").find("option").remove().end().append(select);
					$.each(data.textbookList, function(key, value) {
						$("#textbook").append("<option>" + value + "</option>"); 
					});
				} else {
					$("#textbook").find("option").remove().end().append("<option>-- No TextBook --</option>");
					return;
				}
			},
			error: function(x, o, e) {
				var msg = "페이지 호출 중 에러 발생 \n" + x.status + " : " + o + " : " + e; 
				alert(msg);
			}				
		});
	}	
}); */
/* $('#searchBtn').on("click",function(event){
	var searchOption = $("select[name=searchOption]").val();
	var searchKeyword = $("input[name=searchKeyword]").val();
	self.location = "wordsList.prime&searchOption=" + searchOption + "&searchKeyword=" + searchKeyword;
}); */
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
								<th>단어명</th>
								<th>뜻</th>
								<th>grade</th>
								<th>교과서</th>
								<th>레슨</th>
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
						<form action="wordsList.prime">
						<select id="searchOption" name="searchOption">
							<option value="grade">grade</option>
							<option value="textbook">textbook</option>
							<option value="lesson">lesson</option>
						</select>
					<%--<form method="post" id="productForm">
						 <select id="grade" name="grade">
							<option>:: 선택 ::</option>
							<c:forEach var="item1" items="${gradeList }">
								<option value="${item1 }">${item1 }</option>
							</c:forEach>
						</select> 
						<select id="textbook" name="textbook" onchange='changes2Step(value)'>
							<option>:: 선택 ::</option>
							<c:forEach var="item2" items="${textbookList }">
								<option value="${item2 }">${item2 }</option>
							</c:forEach>
						</select> 
						<select id="lesson" name="lesson">
							<option>:: 선택 ::</option>
							<c:forEach var="item3" items="${lessonList }">
								<option value="${item3 }">${item3 }</option>
							</c:forEach>
						</select> --%>
						<input type="text" id="searchKeyword" name="searchKeyword">
						<button type="submit" id="searchBtn">검색</button>
						</form>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
