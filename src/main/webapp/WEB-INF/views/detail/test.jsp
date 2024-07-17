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
	     .location-button {
	         margin: 10px;
	     }
	     .location-button img {
	         width: 100px;
	         height: 100px;
	         cursor: pointer;
	     }
	
</style>

</head>
<body>
	<form action="${contextPath}/detail/showAll.do" method="get">
		<input type=hidden name="detailClassification" value="health">
		<button type="submit">더 보기</button>
	</form>
	<form action="${contextPath}/detail/showAll.do" method="get">
			<input type=hidden name="detailClassification" value="boxing">
			<input type=hidden name="detailStatus" value="popular">
			<button type="submit">더 보기</button>
	</form>
	
	 <a href="${contextPath}/detail/detail.do?detailNo=1">
	        <img src="이미지파일.jpg" alt="장소 이미지">
	 </a>
</body>
</html>