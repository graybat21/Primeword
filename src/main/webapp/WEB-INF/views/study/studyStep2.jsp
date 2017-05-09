<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
</head>
<body>
	<div id="wrap">
		<table>
			<c:forEach var="item" items="${list }" varStatus="status">
				<tr>
					<td class="first">${status.count }</td>
					<td><input type="text" name="" value="${item.word }"
						class="input_01"></td>
					<td><input type="text" name="" value="${item.meaning }"
						class="input_02"></td>
					<td class="last"><img src="images/speaker_on.png" alt=""></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
