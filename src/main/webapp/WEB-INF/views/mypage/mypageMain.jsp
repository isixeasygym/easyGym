<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <title>마이페이지</title>
	<link rel="stylesheet" href="${contextPath}/CSS/mypage/mypageReport.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 70%;
            margin: 30px auto;
            padding: 20px;
        }
        .header {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 50px;
        }
        .header img {
            margin-right: 20px;
        }
        .nav {
            display: flex;
            justify-content: center;
            border-bottom: 2px solid #f0f0f0;
            margin-bottom: 30px;
        }
        .nav button {
            background-color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            margin: 0 60px;
            border-bottom: 2px solid transparent;
            font-size: 18px;
        }
        .nav button.active {
            border-bottom: 2px solid #007bff;
            color: #007bff;
        }
        .content {
            display: flex;
        }
        .sidebar {
            width: 20%;
            padding: 20px;
            border-right: 1px solid #ccc;
        }
        .sidebar button {
            display: block;
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            cursor: pointer;
            text-align: left;
            font-size: 16px;
        }
        .sidebar button.active {
            background-color: #007bff;
            color: #fff;
        }
        .main-content {
            width: 70%;
            padding: 10px;
        }
        .section {
            display: none;
            padding-left: 20px;
        }
        .section.active {
            display: block;
        }
        .section.fullscreen {
            width: 100%;
            padding-left: 0;
        }
        .item {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 10px;
            display: flex;
            justify-content: space-between;
        }
        .item button {
            margin-top: 10px;
        }
        .filter {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: center;
        }
        th {
            background-color: #f0f0f0;
        }
    </style>
	<script src="${contextPath}/js/mypage/mypage.js"></script>
</head>
<body>
    <div class="container">
        <div class="header">
            <img src="/images/12.png" alt="Profile Image">
            <h1>닉네임</h1>
        </div>
        <div class="nav">
            <button class="nav-btn active" data-target="my-info">내 정보</button>
            <button class="nav-btn" data-target="inquiry-history">구매내역</button>
            <button class="nav-btn" data-target="report-history">문의내역</button>
            <button class="nav-btn" data-target="purchase-history">신고내역</button>
        </div>
        <div class="content">
            <div class="sidebar">
                <button class="sidebar-btn active" data-target="using-products">이용중인 상품</button>
                <button class="sidebar-btn" data-target="update-info">정보수정</button>
            </div>
            <div class="main-content">
                <div id="using-products" class="section active">
                    <h2>헬스장 이용권</h2>
                    <img src="" alt="">
                </div>
                <div id="update-info" class="section">
                    <div id="password-check">
                        <h2>비밀번호 확인</h2>
                        <input type="password" id="password" placeholder="비밀번호 확인">
                        <button onclick="checkPassword()">확인</button>
                    </div>
                    <div id="update-form" style="display:none;">
                        <h2>회원정보 수정</h2>
                        <p>아이디: 사용자아이디</p>
                        <p>비밀번호: <button>비밀번호 변경하기</button></p>
                        <p>이름: <input type="text" value="사용자이름"></p>
                        <p>성별: <input type="text" value="성별"></p>
                        <p>생년월일: <input type="date"></p>
                        <p>휴대폰번호: <input type="tel"></p>
                        <p>이메일 주소: <input type="email"></p>
                    </div>
                </div>
                <div id="inquiry-history" class="section fullscreen">
                    <h2>구매내역</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>업체명</th>
                                <th>이용권</th>
                                <th>금액</th>
                                <th>구매일자</th>
                                <th>상태</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- 구매내역 항목을 여기에 동적으로 추가 -->
                            <tr>
                                <td>1</td>
                                <td>업체명1</td>
                                <td>이용권1</td>
                                <td>100,000원</td>
                                <td>2024-07-01</td>
                                <td>완료</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>업체명2</td>
                                <td>이용권2</td>
                                <td>200,000원</td>
                                <td>2024-06-30</td>
                                <td>취소</td>
                            </tr>
                            <!-- 더 많은 항목들... -->
                        </tbody>
                    </table>
                </div>
				
                <div id="report-history" class="section fullscreen">
                    <h2>문의내역</h2>
                </div>
				
                <div id="purchase-history" class="section fullscreen">
                    <h2>신고내역</h2>
					<table>
	                    <tr>
	                        <th>No</th><th>제목</th><th>글쓴이</th><th>작성시간</th>
	                    </tr>
	                    <c:choose>
	                        <c:when test="${empty reportsList}">  
	                            <tr>
	                                <td colspan="4" align="center">등록된 글이 없습니다.</td>
	                            </tr>
	                        </c:when>
	                        <c:when test="${!empty reportsList}">
	                            <c:forEach var="report" items="${reportsList}">
	                                <tr align="center">
	                                    <td width="10%">${report.reportNo}</td>
	                                    <td align="left" width="50%">
	                                        <span style="padding-left:10px"></span>
	                                        <a href="${contextPath}/board/viewArticle.do?articleNo=${article.articleNo}">${report.reportTitle}</a>
	                                    </td>  
	                                    <td width="15%">${member.memberId}</td>
	                                    <td width="15%">${report.reportDate}</td>
	                                </tr>
	                            </c:forEach>
	                        </c:when>
	                    </c:choose>
	                </table>
                </div>
            </div>
        </div>
    </div>
    <script>
        document.querySelectorAll('.nav-btn').forEach(button => {
            button.addEventListener('click', () => {
                document.querySelectorAll('.nav-btn').forEach(btn => btn.classList.remove('active'));
                button.classList.add('active');

                document.querySelectorAll('.section').forEach(section => section.classList.remove('active'));
                
                if (button.dataset.target === 'inquiry-history' || button.dataset.target === 'report-history' || button.dataset.target === 'purchase-history') {
                    document.querySelector('.sidebar').style.display = 'none';
                    document.querySelector('.main-content').style.width = '100%';
                    document.getElementById(button.dataset.target).classList.add('active', 'fullscreen');
                } else {
                    document.querySelector('.sidebar').style.display = 'block';
                    document.querySelector('.main-content').style.width = '70%';
                    document.querySelectorAll('.section').forEach(section => section.classList.remove('fullscreen'));
                    
                    if (button.dataset.target === 'my-info') {
                        document.getElementById('using-products').classList.add('active');
                        document.querySelector('.sidebar-btn[data-target="using-products"]').classList.add('active');
                    } else {
                        document.getElementById(button.dataset.target).classList.add('active');
                    }
                }
            });
        });

        document.querySelectorAll('.sidebar-btn').forEach(button => {
            button.addEventListener('click', () => {
                document.querySelectorAll('.sidebar-btn').forEach(btn => btn.classList.remove('active'));
                button.classList.add('active');
                
                document.querySelectorAll('.section').forEach(section => section.classList.remove('active'));
                document.getElementById(button.dataset.target).classList.add('active');
            });
        });

        function checkPassword() {
            // 비밀번호 확인 로직 (여기서는 단순히 비밀번호를 "password"로 체크)
            var password = document.getElementById('password').value;
            if (password === "password") {
                document.getElementById('password-check').style.display = 'none';
                document.getElementById('update-form').style.display = 'block';
            } else {
                alert("비밀번호가 올바르지 않습니다.");
            }
        }

        function filterPoints() {
            // 조회기간에 따른 포인트 내역 필터링 로직
            var startDate = document.getElementById('start-date').value;
            var endDate = document.getElementById('end-date').value;
            // 여기에 필터링 로직 추가 (예: Ajax 호출로 서버에서 데이터 가져오기)
            alert('조회기간: ' + startDate + ' ~ ' + endDate);
        }
    </script>
</body>
</html>
