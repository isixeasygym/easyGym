<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" href="${contextPath}/css/mypage/mypageMain.css">
    <script src="${contextPath}/js/mypage/mypageMain.js"></script>
</head>
<body>
    <div class="container">
        <div class="header">
            <img src="/images/1.png" alt="Profile Image">
            <h1>${member.memberName}</h1>
        </div>
        <div class="nav">
            <button class="nav-btn active" data-target="my-info">내 정보</button>
            <button class="nav-btn" data-target="points-coupons">포인트&쿠폰</button>
            <button class="nav-btn" data-target="update-info">정보수정</button>
        </div>
        <div class="content">
            <div class="sidebar">
                <button class="sidebar-btn active" data-target="using-products">이용중인 상품</button>
                <button class="sidebar-btn" data-target="dibs-list">찜 목록</button>
                <button class="sidebar-btn" data-target="purchase-history">구매내역</button>
            </div>
            <div class="main-content">
                <div id="using-products" class="section active">
                    <h2>이용중인 상품</h2>
                    <!-- 여기에 이용중인 상품 정보를 추가 -->
                </div>
                <div id="dibs-list" class="section">
                    <h2>찜 목록</h2>
                    <!-- 여기에 찜 목록 정보를 추가 -->
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
                    <h2>포인트 적립/사용 내역</h2>
                    <!-- 포인트 적립 및 사용 내역을 여기에 추가 -->
                </div>
                <div id="coupons" class="section">
                    <h2>쿠폰 적립/사용 내역</h2>
                    <!-- 쿠폰 적립 및 사용 내역을 여기에 추가 -->
                </div>
                <div id="update-info" class="section">
                    <div align="center" id="password-check">
                        <h2>비밀번호 확인</h2>
                        <input type="password" id="password" placeholder="비밀번호 확인">
                        <button id="password-check-btn">확인</button>
                    </div>
                    <div align="center" id="update-form" style="display:none;">
                        <h2>회원정보 수정</h2>
                        <p>아이디: <input type="text" value="${member.memberId}" disabled></p>
                        <p>비밀번호 : <button id="update-pwd">비밀번호 변경하기</button></p>
                        <p>이름: <input type="text" value="${member.memberName}"></p>
                        <p>성별: 
                            <input type="radio" name="sex">남자
                            <input type="radio" name="sex">여자
                        </p>
                        <p>생년월일: <input type="date" value="${member.memberBirthdate}"></p>
                        <p>휴대폰번호: <input type="tel" value="${member.memberPhone}"></p>
                        <p>이메일 주소: <input type="email" value="${member.memberEmail}"></p>
                        <p>프로필 이미지 변경: <input type="file" accept="image/*"></p>
                        <button id="update-btn">수정하기</button>
                        <button id="cancel-btn">취소</button>
                        <button id="withdraw-btn"><a href="${contextPath}/mypage/withdraw.do">회원탈퇴</a></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
</html>
