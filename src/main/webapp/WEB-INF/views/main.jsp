<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<link rel="stylesheet" href="${contextPath}/css/main.css">
<script src="${contextPath}/js/test.js"></script>
</head>
<body>
	<h1 id="head">메인 페이지</h1>
	<h2>${msg}</h2>
	<img src="${contextPath}/images/dalmatian.jpg" alt="달마시안"><br>  <!-- contextPath => static 폴더 -->
	<input type="button" value="클릭해보세요" onclick="message()">
</body>
</html>