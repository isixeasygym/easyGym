<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>EasyGym</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/css/member/main.css" />
<link rel="stylesheet" href="/css/member/noscript.css" />
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
	#logo a {
    font-size: 1.7rem; /* 원하는 글자 크기로 설정 (예: 2rem) */
    color: #ffffff; /* 글자 색상을 하얀색으로 설정 */
    text-decoration: none; /* 밑줄 제거 */
    border: 0;
  }
  @font-face {
    font-family: 'neurimboGothicRegular';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/neurimboGothicRegular.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'KorailRoundGothicBold';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2212@1.0/KorailRoundGothicBold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

#logo{
	font-family: 'neurimboGothicRegular';
}
  </style>
</head>
<body class="index is-preload">
	<div id="page-wrapper">
		<header id="header" class="alt">
			<div id="logo" >
				<a href="/main.do">EasyGym </a>
			</div>
			
			<nav id="nav">
				<ul class="nav">
				<c:choose>
				
               <c:when test="${sessionScope.isLogOn eq true and sessionScope.member ne null}">
               	  <li class="current"><a href="/main.do">Home</a></li>
					<li class="current"><a href="${contextPath }/freeboard/fboardList.do">Community</a></li>
					<li class="current"><a href="${contextPath }/notice/noticeList.do">Notice</a></li>
                  <li class="submenu">
                  
					<a href="${contextPath}/detail/search.do?query="><span>▼</span>Find My Gym</a>
						<ul>
							<li><a href="${contextPath}/detail/search.do?query=&detailClassification=health">Fitness</a></li>
							<li><a href="${contextPath}/detail/search.do?query=&detailClassification=pilates">Pilates</a></li>
							<li><a href="${contextPath}/detail/search.do?query=&detailClassification=boxing">Boxing</a></li>
						</ul>
					</li>
                  <li class="current"><a href ="/mypage/mypageMain.do">My Page</a></li>
                  <li class="current"><a href ="/member/logout.do">Sign out</a></li>
                  <li class="nav-item">
                     <h1 class="welcome-message">
                        환영합니다, <span>${sessionScope.member.memberName}님!</span>
                     </h1>
               </c:when>
               
               <c:when test="${sessionScope.isLogOn eq true and sessionScope.operator ne null}">
               	  <li class="current"><a href="/main.do">Home</a></li>
					<li class="current"><a href="${contextPath }/freeboard/fboardList.do">Community</a></li>
					<li class="current"><a href="${contextPath }/notice/noticeList.do">Notice</a></li>
                  <li class="current"><a href="/detail/registration.do">업체 등록하기</a></li>
                  <li class="current"><a href="/member/logout.do">로그아웃</a></li>
                  <li class="current">
                     <h1 class="welcome-message">
                        환영합니다, 사업자 <span>${sessionScope.operator.operatorName}님!</span>
                     </h1>
               </c:when>
     
               <c:otherwise>
                  <li class="current"><a href="/main.do">Home</a></li>
					<li class="current"><a href="${contextPath }/freeboard/fboardList.do">Community</a></li>
					<li class="current"><a href="${contextPath }/notice/noticeList.do">Notice</a></li>
                  <li class="submenu">
					<a href="${contextPath}/detail/search.do?query=">Find My Gym</a>
						<ul>
							<li><a href="${contextPath}/detail/search.do?query=&detailClassification=health">Fitness</a></li>
							<li><a href="${contextPath}/detail/search.do?query=&detailClassification=pilates">Pilates</a></li>
							<li><a href="${contextPath}/detail/search.do?query=&detailClassification=boxing">Boxing</a></li>
						</ul>
					</li>
					
					<li><a href="/member/loginForm.do" class="button primary">Sign Up</a></li>
               </c:otherwise>
            </c:choose>
            
					
			</nav>
			</header>
			</div>
			
	
	</div>
	</body>
