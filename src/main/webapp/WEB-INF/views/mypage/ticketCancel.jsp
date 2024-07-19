<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>이용권 취소하기</title>
    <!--<link rel="stylesheet" href="${contextPath}/css/mypage/mypageStyle.css">-->
    <!--<script src="${contextPath}/js/mypage/mypage.js"></script>-->
</head>
<body>
	<h2>이용권을 취소 하시겠습니까?</h2>
	<form action="#">
	    <input type="submit" value="이용권 취소">
	    <input type="reset" value="취소">
	</form>
</body>
</html>
