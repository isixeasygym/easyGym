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
<style>
	#one {
		width:300px;
		height:300px;
	}
	.three{
		width:300px;
		height:300px;
	}
</style>

</head>
<body>
	<form action="${contextPath}/detail/showAll.do" method="get">
		<input type=hidden name="wholeClassification" value="health">
		<button type="submit">더 보기</button>
	</form>
	<form action="${contextPath}/detail/showAll.do" method="get">
			<input type=hidden name="wholeClassification" value="boxing">
			<input type=hidden name="wholeStatus" value="popular">
			<button type="submit">더 보기</button>
	</form>

</body>
</html>