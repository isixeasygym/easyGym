<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<script>
    var contextPath = "${contextPath}";
</script>
<link rel="stylesheet" href="${contextPath}/css/mypage/mypageMain.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${contextPath}/js/mypage/mypageMain.js"></script>

<div class="container">
    <div class="header">
        <h1>
            <c:choose>
                <c:when test="${not empty sessionScope.member}">
                    <img src="/images/member/catgym.png" alt="Profile Image" width="100">
                    ${sessionScope.member.memberName}님
                </c:when>
                <c:otherwise>
                    로그인을 해주세요.
                </c:otherwise>
            </c:choose>
        </h1>
    </div>
    <div class="nav">
        <button class="nav-btn active" data-target="my-info">내 정보</button>
        <button class="nav-btn" data-target="searchHistory">내역조회</button>
        <button class="nav-btn" data-target="update-info">정보수정</button>
    </div>
    <div class="content">
        <div class="sidebar">
            <button class="sidebar-btn active" data-target="using-products">이용중인 상품</button>
            <button class="sidebar-btn" data-target="dibs-list" onclick="fn_dibsList();">찜 목록</button>
			<button class="sidebar-btn" data-target="points">포인트</button>
        </div>
        <div class="main-content">
			<!-- 내 정보 탭 안에 사이드바 -->
            <div id="using-products" class="section active">
                <h2>이용중인 상품</h2>
                <!-- 이용 중인 상품 목록이 여기에 동적으로 로드됩니다 -->
            </div>
            <div id="dibs-list" class="section">
                <h2>찜 목록</h2>
                <!-- mypageMain.js 파일에 finction 및 테이블 구조 만들어져 있음 -->
            </div>
			<div id="points" class="section">
                <h2>포인트 적립/사용 내역</h2>
                <img src="${contextPath}/images/payform/ana.png" alt="커밍 쑨!">
            </div>
			
			<!-- 내역조회 탭 안에 사이드바 -->
            <div id="purchaseHistory" class="section">
                <h2>구매내역</h2>
                <img src="${contextPath}/images/payform/ana.png" alt="커밍 쑨!">
            </div>
			<div id="reportHistory" class="section">
                <h2>신고내역</h2>
                <img src="${contextPath}/images/payform/ana.png" alt="커밍 쑨!">
            </div>
			<div id="reviewHistory" class="section">
                <h2>리뷰내역</h2>
                <img src="${contextPath}/images/payform/ana.png" alt="커밍 쑨!">
            </div>
            
			<!-- 정보수정 탭 -->
            <div id="update-info" class="section">
                <div align="center" id="password-check">
                    <h2>비밀번호 확인</h2>
                    <form id="password-check-form">
                        <input type="password" id="password" placeholder="비밀번호 확인">
                        <input type="hidden" id="memberNo" value="${member.memberNo}"> <!-- 멤버 번호를 위한 숨겨진 입력 필드 -->
                        <button type="button" id="password-check-btn">확인</button> <!-- 폼 제출을 방지하기 위해 type="button" 사용 -->
                    </form>
                </div>
                <div align="center" id="update-form" style="display:none;">
                    <h2>회원정보 수정</h2>
                    <form method="post" action="${contextPath}/mypage/memberUpdate.do" id="update-form">
                        <input type="hidden" value="${member.memberNo}">
                        <p>이름: <input type="text" value="${member.memberName}" disabled></p>
                        <p>아이디: <input type="text" value="${member.memberId}" disabled></p>
                        <p>성별:
                            <input type="radio" name="sex" value="남" ${member.memberGender == '1' ? 'checked' : ''} disabled>남
                            <input type="radio" name="sex" value="여" ${member.memberGender == '2' ? 'checked' : ''} disabled>여
                        </p>
                        <p>비밀번호 : <input type="password" id="memberPwd" value="${member.memberPwd}"></p>
                        <p>비밀번호 확인 : <input type="password" id="memberPwdConfirm" value="${member.memberPwd}"></p>
                        <p>전화번호: <input type="tel" id="memberPhone" value="${member.memberPhone}"></p>
                        <p>이메일 주소: <input type="email" id="memberEmail" value="${member.memberEmail}"></p>
                        <button type="button" id="update-btn">수정하기</button>
                    </form>
                    <button id="cancel-btn"><a href="${contextPath}/mypage/mypageMain.do">취소</a></button>
                    <form id="withdraw-form">
                        <input type="hidden" id="memberNo" value="${memberNo}">
                        <button type="button" id="withdraw-btn">회원탈퇴</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>