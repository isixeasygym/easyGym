<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="scripts.js" defer></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsVc/y3m5+g3ZbH1v4UQnA9Vs2K7ftwCk4vE9sIQ3Z"
	crossorigin="anonymous"></script>
<title>이지집 공식웹사이트</title>
<link rel="stylesheet" type="text/css"
	href="/resources/bootstrap-5.3.3-dist/css/bootstrap.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Anton+SC&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Anton+SC&family=Arsenal+SC:ital,wght@0,400;0,700;1,400;1,700&family=Bona+Nova+SC:ital,wght@0,400;0,700;1,400&family=Playwrite+GB+S:ital,wght@0,100..400;1,100..400&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

<style>
@font-face {
	font-family: 'HancomMalangMalang-Regular';
	src:
		url('https://fastly.jsdelivr.net/gh/projectnoonnu/2406-1@1.0/HancomMalangMalang-Regular.woff2')
		format('woff2');
	font-weight: 400;
	font-style: normal;
}

.buttons {
	text-align: right;
	border: 4px solid #fff; /* 흰색 보더 추가 */
}

.navbar-brand {
  font-family: 'HancomMalangMalang-Regular';
  font-weight: 700;
  font-size: 22px;
  display: inline-block; /* 요소를 인라인 블록 요소로 설정하여 내용에 맞게 크기가 조정되도록 함 */
  padding: 0.25rem 0.5rem; /* 내부 여백을 추가하여 알약 모양을 만듦 */
  color: #fff; /* 글자색 지정 */
  border-radius: 1rem; /* 알약 모양을 위한 border-radius 설정 */
  border: 3px solid #fff; /* 흰색 보더 추가 */
}

.navbar-nav { 
  font-family: 'HancomMalangMalang-Regular';
  font-weight: 700;
  font-style: normal;
  font-size: 20px;
}

/* 드롭다운 메뉴 스타일 */
.navbar-nav .dropdown-menu {
    border: none;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    border-radius: 8px;
}

.navbar-nav .dropdown-item {
    font-weight: 500;
    font-size: 18px;
}

</style>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<a class="navbar-brand" href="main"> EasyGym</a>
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="#">About</a></li>
			<li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="every" id="navbarDropdownProgram" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Program
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdownProgram">
                    <li><a class="dropdown-item" href="health">헬스</a></li>
                    <li><a class="dropdown-item" href="pilates">필라테스</a></li>
                    <li><a class="dropdown-item" href="yoga">요가</a></li>
                    <li><a class="dropdown-item" href="boxing">복싱</a></li>
                </ul>
            </li>
			<li class="nav-item"><a class="nav-link" href="#">Community</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Gym</a></li>
		</ul>
		<ul class="navbar-nav ms-auto">
            <li class="nav-item"><a class="btn btn-info btn-sm me-2" href="login">로그인</a></li>
            <li class="nav-item"><a class="btn btn-info btn-sm" href="joinSelect">회원가입</a></li>
        </ul>
	</nav>
	<div>
</body>
</html>
