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
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>이지짐 회원권 구매</title>
    <script src="${contextPath}/JS/payform/payformForm.js"></script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/payform/payformForm.css">
</head>
<body>
<div class="bg-image"></div>

<form id="membership_form" action="${contextPath}/tosspay.do" method="GET">
    <div class="container">
        <div class="receipt_info">
            <span class="hidden">주문번호: <span id="payformNo">${payform.payformNo}</span></span>
            <span class="hidden">멤버 번호: <span id="memberNo">${payform.memberNo}</span></span>
            <span class="hidden">헬스장 번호: <span id="wholeNo">${payform.detailNo}</span></span>
        </div>

        <h1><span id="wholeName">${payform.detailBusinessName}</span> 헬스장 구매 폼</h1>

        <h2>구매자 정보</h2>
        <div class="form_group">
            <label for="name">이름:</label>
            <input type="text" id="name" name="name" placeholder="이름을 입력해주세요." value="${payform.memberName}" required>
        </div>
        <div class="form_group">
            <label for="phoneNumber">휴대폰 번호:</label>
            <input type="tel" id="phoneNumber" name="phoneNumber" placeholder="번호를 입력해주세요." value="${payform.memberPhone}"
                   pattern="\d{3}-\d{3,4}-\d{4}" required>
        </div>

        <h2>구독 정보</h2>
        <div class="form_group">
            <label for="subscriptionMonths">구독 개월:</label>
            <select id="subscriptionMonths" name="subscriptionMonths" required>
                <option value="1" ${payform.payformSub == 1 ? 'selected' : ''}>1개월</option>
                <option value="3" ${payform.payformSub == 3 ? 'selected' : ''}>3개월</option>
                <option value="6" ${payform.payformSub == 6 ? 'selected' : ''}>6개월</option>
            </select>
            <span id="discountRate"></span>
        </div>

        <div class="form_group">
            <label for="originalPrice">원래 금액:</label>
            <span id="originalPrice">${payform.wholeMonthlyTicket}원</span>
        </div>

        <div class="form_group">
            <label for="paymentMethod">결제방법:</label>
            <select id="paymentMethod" name="paymentMethod" required>
                <option value="0" ${payform.payformPayment == 0 ? 'selected' : ''}>신용/체크카드</option>
            </select>
        </div>

        <div class="form_group">
            <label for="finalPrice">최종 결제 금액:</label>
            <div id="finalPrice">${payform.payformPrice}원</div>
        </div>
        <button type="submit" id="paymentButton">구매하기</button>

    </div>
</form>

</body>
</html>