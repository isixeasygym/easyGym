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
    <title>마이페이지</title>
    <link rel="stylesheet" href="${contextPath}/css/mypage/mypageStyle.css">
    <!--<script src="${contextPath}/js/mypage/mypage.js"></script>-->
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            // 탭 버튼 클릭 이벤트
            document.querySelectorAll('.nav-btn').forEach(button => {
                button.addEventListener('click', () => {
                    document.querySelectorAll('.nav-btn').forEach(btn => btn.classList.remove('active'));
                    button.classList.add('active');

                    document.querySelectorAll('.section').forEach(section => section.classList.remove('active'));

                    if (button.dataset.target === 'my-info') {
                        document.querySelector('.sidebar').style.display = 'block';
                        document.querySelector('.main-content').style.width = '70%';
                        document.querySelector('.sidebar').innerHTML = `
                            <button class="sidebar-btn active" data-target="using-products">이용중인 상품</button>
                            <button class="sidebar-btn" data-target="purchase-history">구매내역</button>
                        `;
                        document.querySelectorAll('.sidebar-btn').forEach(btn => btn.addEventListener('click', sidebarBtnClickHandler));
                        document.getElementById('using-products').classList.add('active');
                    } else if (button.dataset.target === 'points-coupons') {
                        document.querySelector('.sidebar').style.display = 'block';
                        document.querySelector('.main-content').style.width = '70%';
                        document.querySelector('.sidebar').innerHTML = `
                            <button class="sidebar-btn active" data-target="points">포인트</button>
                            <button class="sidebar-btn" data-target="coupons">쿠폰</button>
                        `;
                        document.querySelectorAll('.sidebar-btn').forEach(btn => btn.addEventListener('click', sidebarBtnClickHandler));
                        document.getElementById('points').classList.add('active');
                    } else {
                        document.querySelector('.sidebar').style.display = 'none';
                        document.querySelector('.main-content').style.width = '100%';
                        document.getElementById(button.dataset.target).classList.add('active', 'fullscreen');
                    }
                });
            });

            // 사이드바 버튼 클릭 이벤트
            document.querySelectorAll('.sidebar-btn').forEach(button => {
                button.addEventListener('click', sidebarBtnClickHandler);
            });

            function sidebarBtnClickHandler() {
                document.querySelectorAll('.sidebar-btn').forEach(btn => btn.classList.remove('active'));
                this.classList.add('active');
                
                document.querySelectorAll('.section').forEach(section => section.classList.remove('active'));
                document.getElementById(this.dataset.target).classList.add('active');
            }

            // 비밀번호 확인 이벤트
            document.getElementById('password-check-btn').addEventListener('click', function() {
                var password = document.getElementById('password').value;
                if (password === "password") { // 실제로는 서버와의 통신이 필요합니다.
                    document.getElementById('password-check').style.display = 'none';
                    document.getElementById('update-form').style.display = 'block';
                } else {
                    alert("비밀번호가 올바르지 않습니다.");
                }
            });

            // 포인트 내역 필터링 이벤트
            document.getElementById('filter-points-btn').addEventListener('click', function() {
                var startDate = document.getElementById('start-date').value;
                var endDate = document.getElementById('end-date').value;
                alert('조회기간: ' + startDate + ' ~ ' + endDate);
            });

            // 수정하기 버튼 클릭 이벤트
            document.getElementById('update-btn').addEventListener('click', function() {
                // 여기에 수정 로직을 추가합니다.
                alert("수정되었습니다.");
            });

            // 취소 버튼 클릭 이벤트
            document.getElementById('cancel-btn').addEventListener('click', function() {
                location.href = '${contextPath}/mypage.jsp'; // 마이페이지 첫 화면으로 이동
            });
        });
    </script>
</head>
<body>
    <div class="container">
        <div class="header">
            <img src="#" alt="Profile Image">
            <h1>${member.memberName}</h1>
        </div>
        <div class="nav">
            <button class="nav-btn active" data-target="my-info">이용권</button>
            <button class="nav-btn" data-target="points-coupons">포인트&쿠폰</button>
            <button class="nav-btn" data-target="update-info">정보수정</button>
        </div>
        <div class="content">
            <div class="sidebar">
                <button class="sidebar-btn active" data-target="using-products">이용중인 상품</button>
                <button class="sidebar-btn" data-target="purchase-history">구매내역</button>
            </div>
            <div class="main-content">
                <div id="using-products" class="section active">
                    <h2>이용중인 상품</h2>
                    <!-- 여기에 이용중인 상품 정보를 추가 -->
                </div>
                <div id="purchase-history" class="section">
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
                <div id="points" class="section">
                    <h2>포인트</h2>
                    <!-- 포인트 적립 및 사용 내역을 여기에 추가 -->
                </div>
                <div id="coupons" class="section">
                    <h2>쿠폰</h2>
                    <!-- 쿠폰 적립 및 사용 내역을 여기에 추가 -->
                </div>
                <div id="update-info" class="section">
                    <div id="password-check">
                        <h2>비밀번호 확인</h2>
                        <input type="password" id="password" placeholder="비밀번호 확인">
                        <button id="password-check-btn">확인</button>
                    </div>
                    <div id="update-form" style="display:none;">
                        <h2>회원정보 수정</h2>
                        <p>아이디: <input type="text" value="${member.memberId}" disabled></p>
                        <p>프로필 이미지 변경: <input type="file" accept="image/*"></p>
                        <p>이름: <input type="text" value="${member.memberName}"></p>
                        <p>성별: 
							<input type="radio" name="sex">남자
							<input type="radio" name="sex">여자
						</p>
                        <p>생년월일: <input type="date" value="${member.memberBirthdate}"></p>
                        <p>휴대폰번호: <input type="tel" value="${member.memberPhone}"></p>
                        <p>이메일 주소: <input type="email" value="${member.memberEmail}"></p>
                        <button id="update-btn">수정하기</button>
                        <button id="cancel-btn">취소</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
