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
    <title>정보수정완료</title>
    <!--<link rel="stylesheet" href="${contextPath}/css/mypage/mypageStyle.css">-->
    <!--<script src="${contextPath}/js/mypage/mypage.js"></script>-->
</head>
<body>
	<h2>회원정보 수정이 완료되었습니다.</h2>
	<form action="/main.do">
	    <input type="submit" value="홈페이지로 돌아가기">
	</form>
</body>
</html>
