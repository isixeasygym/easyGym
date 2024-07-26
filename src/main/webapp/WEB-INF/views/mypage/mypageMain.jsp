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
	<!--<script src="${contextPath}/js/mypage/mypageDibs.js"></script>-->
	
	
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
            <button class="nav-btn" data-target="points-coupons">포인트&쿠폰</button>
            <button class="nav-btn" data-target="update-info">정보수정</button>
        </div>
        <div class="content">
            <div class="sidebar">
                <button class="sidebar-btn active" data-target="using-products">이용중인 상품</button>
                <button class="sidebar-btn" data-target="dibs-list" onclick="fn_dibsList();">찜 목록</button>
                <button class="sidebar-btn" data-target="purchase-history">구매내역</button>
            </div>
            <div class="main-content">
                <div id="using-products" class="section active">
                    <h2>이용중인 상품</h2>
                    <!-- 여기에 이용중인 상품 정보를 추가 -->
					<c:forEach var="product" items="${dibsMap.detailList}">
                        <div class="item">
                            <div class="info">
                                <p>${product.detailKoClassification}</p>  <!-- 프로그램명 불러오기 -->
                                <p>이용권</p>
                            </div>
                            <div class="details">
                                <p>업체명 : ${product.detailBusinessName}</p>  <!-- 업체명 불러오기 -->
                                <p>프로그램명 : ${product.detailKoClassification}</p>  <!-- 프로그램명 불러오기 -->
                                <p>이용기간 : ${product.detailDate}</p>  <!-- 이용기간 불러오기 -->
                            </div>
                            <div class="actions">
                                <button onclick="location.href='${contextPath}/mypage/ticketCancel.do'">취소하기</button>
                                <button onclick="location.href='${contextPath}/mypage/ticketRefund.do'">환불하기</button>
                            </div>
                        </div>
                    </c:forEach>
					<div class="item">
                        <div class="info">
                            <p>요가</p>  <!-- 프로그램명 불러오기 -->
                            <p>이용권</p>
                        </div>
                        <div class="details">
                            <p>업체명 : 인도요가</p>  <!-- 업체명 불러오기 -->
                            <p>프로그램명 : 요가</p>  <!-- 프로그램명 불러오기 -->
                            <p>이용기간 : 2024.02.17 ~ 2024.10.26</p>  <!-- 이용기간 불러오기 -->
                        </div>
                        <div class="actions">
                            <button onclick="location.href='${contextPath}/mypage/ticketCancel.do'">취소하기</button>
                        </div>
                    </div>
                </div>
                <div id="dibs-list" class="section">
                    <h2>찜 목록</h2>
                    <!-- mypageMain.js 파일에 finction 및 테이블 구조 만들어져 있음 -->
                </div>
                <div id="purchase-history" class="section">
                    <h2>구매내역</h2>
                    <table>
                        <tr>
                            <th>번호</th>
                            <th>업체명</th>
                            <th>이용권</th>
                            <th>금액</th>
                            <th>구매일자</th>
                            <th>상태</th>
                        </tr>
                        <!-- 구매내역 항목을 여기에 동적으로 추가 -->
                        <tr>
                            <td>2</td>
                            <td>업체명2</td>
                            <td>이용권2</td>
                            <td>100,000원</td>
                            <td>2024-07-01</td>
                            <td>완료</td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>업체명1</td>
                            <td>이용권1</td>
                            <td>200,000원</td>
                            <td>2024-06-30</td>
                            <td>취소</td>
                        </tr>
                        <!-- 더 많은 항목들... -->
                    </table>
                </div>
                <div id="points" class="section">
                    <h2>포인트 적립/사용 내역</h2>
                    <!-- 포인트 적립 및 사용 내역을 여기에 추가 -->
					<div class="points-total">
						총 적립 포인트: ${pointsList.sum(point -> point.memberPoint)} <!-- 전체 포인트 합계 표시 -->
					</div>
				    <table class="points-table">
			            <tr>
			                <th>날짜</th>
			                <th>포인트</th>
			                <th>내역</th>
			            </tr>
			            <!-- 포인트 내역 항목을 여기에 동적으로 추가 -->
						<c:forEach var="point" items="${pointsList}">
				            <tr>
				                <td>${point.memberPointDate}</td>
				                <td class="${point.memberPoint < 0 ? 'negative' : ''}">${point.memberPoint}</td>
				                <td>${point.memberPointDes}</td>
				            </tr>
				        </c:forEach>
				    </table>
                </div>
                <div id="coupons" class="section">
                    <h2>My 쿠폰</h2>  <!-- 사용하거나 기간이 지나면 자동 삭제 -->
                    <!-- 쿠폰 적립 및 사용 내역을 여기에 추가 -->
					<table class="coupons-table">
			            <tr>
			                <th>유효기간</th>
			                <th>쿠폰</th>
			                <th>내역</th>
			            </tr>
			            <!-- 쿠폰 내역 항목을 여기에 동적으로 추가 -->
						<c:forEach var="coupon" items="${couponsList}">
				            <tr>
				                <td>${coupon.expiryDate}</td>
				                <td>${coupon.couponName}</td>
				                <td>${coupon.description}</td>
				            </tr>
				        </c:forEach>
				    </table>
                </div>
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
				                <input type="radio" name="sex" value="남" ${member.memberGender == '남' ? 'checked' : ''} disabled>남
				                <input type="radio" name="sex" value="여" ${member.memberGender == '여' ? 'checked' : ''} disabled>여
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

