<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="/images/member/icecream.png" type="image/x-icon">
<link rel="icon" href="/images/member/icecream.png" type="image/x-icon">
<title>이지집 공식웹사이트</title>
<!-- jQuery 로드 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" crossorigin="annonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="/resources/bootstrap-5.3.3-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="/css/layout/header.css">
<!-- Google Fonts 로드 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Anton+SC&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Anton+SC&family=Arsenal+SC:ital,wght@0,400;0,700;1,400;1,700&family=Bona+Nova+SC:ital,wght@0,400;0,700;1,400&family=Playwrite+GB+S:ital,wght@0,100..400;1,100..400&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
<style>
.welcome-message {
	color: white;
	font-size: 1em; /* 폰트 크기를 줄임 */
	text-align: center; /* 가운데 정렬 */
	display: inline-block;
	margin-left: 20px; /* 왼쪽에 약간의 마진을 추가 */
}
</style>
</head>

<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
		<a class="navbar-brand" href="/main.do">EasyGym</a>
		<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			<li class="nav-item active"><a class="nav-link" href="">공지사항</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${contextPath }/freeboard/fboardList.do">커뮤니티</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdownProgram"
				role="button" data-bs-toggle="dropdown" aria-expanded="false">시설찾기</a>
				<ul class="dropdown-menu">
					<li><a class="dropdown-item" href="health">헬스</a></li>
					<li><a class="dropdown-item" href="pilates">필라테스</a></li>
					<li><a class="dropdown-item" href="boxing">복싱</a></li>
				</ul></li>
		</ul>
		<div id="authButtons">
			<ul class="nav">
				<c:choose>
					<c:when
						test="${sessionScope.isLogOn eq true and sessionScope.member ne null}">
						<li class="nav-item"><a class="btn btn-info btn-sm"
							href="/mypage/mypageMain.do">마이페이지</a></li>
						<li class="nav-item"><a class="btn btn-info btn-sm"
							href="/member/logout.do">로그아웃</a></li>
						<li class="nav-item">
							<h1 class="welcome-message">
								환영합니다, <span>${sessionScope.member.memberName}님!</span>
							</h1>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="btn btn-info btn-sm"
							href="/member/loginSelect.do">로그인</a></li>
						<li class="nav-item"><a class="btn btn-info btn-sm"
							href="/member/joinSelect.do">회원가입</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
	<div>
	<!-- 부트스트랩 JS 로드 -->
	<script
		src="/resources/bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>