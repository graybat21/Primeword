<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/nanumbarungothic.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="content" />
	<%-- <tiles:insertAttribute name="footer" /> --%>
</body>
</html>
