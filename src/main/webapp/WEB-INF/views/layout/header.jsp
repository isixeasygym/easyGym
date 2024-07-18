<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>이지집 공식웹사이트</title>
<!-- jQuery 로드 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"  crossorigin="anonymous"></script>
<!-- Bootstrap CSS 로드 -->
<link rel="stylesheet" type="text/css"
	href="/resources/bootstrap-5.3.3-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="/css/layout/header.css">
<!-- Google Fonts 로드 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Anton+SC&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Anton+SC&family=Arsenal+SC:ital,wght@0,400;0,700;1,400;1,700&family=Bona+Nova+SC:ital,wght@0,400;0,700;1,400&family=Playwrite+GB+S:ital,wght@0,100..400;1,100..400&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<a class="navbar-brand" href="/main.do"> EasyGym</a>
		<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			<li class="nav-item active"><a class="nav-link" href="#">About</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdownProgram"
				role="button" data-bs-toggle="dropdown" aria-expanded="false">
					Program </a>
				<ul class="dropdown-menu" aria-labelledby="navbarDropdownProgram">
					<li><a class="dropdown-item" href="health">헬스</a></li>
					<li><a class="dropdown-item" href="pilates">필라테스·요가</a></li>
					<li><a class="dropdown-item" href="boxing">복싱</a></li>
				</ul></li>
			<li class="nav-item"><a class="nav-link" href="/freeboard/fboardList.do">Community</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Gym</a></li>
		</ul>
		<form id="searchForm" action="${contextPath}/search/results" method="GET">
			<input type="text" name="searchTxt" id="searchTxt" placeholder="검색어를 입력하세요" value="${param.searchTxt}">
			<button type="submit" class="btn btn-info btn-sm">검색</button>
		</form>
		<div id="authButtons">
<ul class="nav">
    <c:choose>
        <c:when test="${sessionScope.isLogOn eq true and sessionScope.member ne null}">
            <li class="nav-item"><a class="btn btn-info btn-sm" href="#">마이페이지</a></li>
            <li class="nav-item"><a class="btn btn-info btn-sm" href="/member/logout.do">로그아웃</a></li>
        </c:when>
        <c:otherwise>
            <li class="nav-item"><a class="btn btn-info btn-sm" href="/member/loginForm.do">로그인</a></li>
            <li class="nav-item"><a class="btn btn-info btn-sm" href="/member/joinSelect.do">회원가입</a></li>
        </c:otherwise>
    </c:choose>
</ul>
<c:if test="${not empty memberName}">
    <h1>환영합니다, <span>${memberName}님!</span></h1>
</c:if>
		</div>
	</nav>
	<!-- 부트스트랩 JS 로드 -->
	<script src="/resources/bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>
