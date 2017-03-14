<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 부트스트랩 적용 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>학습시작</title>
</head>
<body>

<div class="container">
  <table class="table">
    <thead>
      <tr>
      	<th>NO</th>
        <th>WORD</th>
        <th>MEANING</th>
      </tr>
    </thead>
    <tbody>
    
	<s:iterator value="%{list}" status="stat" >
	
      <tr>
      	<td><s:property value="no"/></td>
      	<!-- 누르면 사라지게하고 그 단어의 파라미터 불린값 1로 바꿔줌? -->
        <td><s:property value="word"/></td>
        	<!-- 안보이다가 누르면 보여지게 하기. -->
        <td><s:property value="meaning"/></td>
      </tr>
     </s:iterator>
    </tbody>
  </table>
</div>
	
</body>
</html>