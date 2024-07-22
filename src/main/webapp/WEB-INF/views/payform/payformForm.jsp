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
    <script>
        function PaymentMockup() {
            let fp = document.getElementById('finalPrice').textContent;
            fp = fp.replace(/[^\d.-]/g, '');
            window.open("${contextPath}/payform/payformCredit.do?memberNo=${payform[0].memberNo}&detailNo=${payform[1].detailNo}&detailNa=${payform[1].detailBusinessName}&name=${payform[0].memberName}&subscriptionMonths="+parseInt(document.getElementById('subscriptionMonths').value)+"&payformPayment="+parseInt(document.getElementById('payformPayment').value)+"&phoneNumber=${payform[0].memberPhone}&price=" + fp, "_blank");
        }
    </script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/payform/payformForm.css">
</head>
<body>
<div class="bg-image"></div>
<button type="button" id="paymentProcess" onclick="PaymentMockup()">토스 결제 구현</button>
<form id="payment_form" action="${contextPath}/payform/payformProcess.do" target="_blank" method="GET">
    <div class="container">
        <div class="receipt_info">
            <span class="hidden">멤버 번호: <input id="memberNo" name="memberNo" value="${payform[0].memberNo}"></span>
            <span class="hidden">헬스장 번호: <input id="detailNo" name="detailNo" value="${payform[1].detailNo}"></span>
        </div>

        <h1><span id="detailName">${payform[1].detailBusinessName}</span> 헬스장 구매 폼</h1>
        <input type="hidden" name="detailNa" id="detailNa" value="${payform[1].detailBusinessName}">

        <h2>구매자 정보</h2>
        <div class="form_group">
            <label for="name">이름:</label>
            <input type="text" id="name" name="name" placeholder="이름을 입력해주세요." value="${payform[0].memberName}" readonly required>
        </div>
        <div class="form_group">
            <label for="phoneNumber">휴대폰 번호:</label>
            <input type="tel" id="phoneNumber" name="phoneNumber" placeholder="번호를 입력해주세요." value="${payform[0].memberPhone}"
                   pattern="\d{3}-\d{3,4}-\d{4}" readonly required>
        </div>

        <h2>구독 정보</h2>
        <div class="form_group">
            <label for="subscriptionMonths">구독 개월:</label>
            <select id="subscriptionMonths" name="subscriptionMonths" required>
                <option value="1">1개월</option>
                <option value="3">3개월</option>
                <option value="6">6개월</option>
            </select>
            <span id="discountRate"></span>
        </div>

        <div class="form_group">
            <label for="originalPrice">원래 금액:</label>
            <div id="originalPrice"><span id="oriPrice"></span>원</div>
            <input type="hidden" id="onePrice" value="${payform[1].detailMonthlyPrice}">
        </div>

        <div class="form_group">
            <label for="payformPayment">결제방법:</label>
            <select id="payformPayment" name="payformPayment" required>
                <option value="0">신용/체크카드</option>
                <option value="1">계좌이체</option>
                <option value="2">가상계좌</option>
                <option value="3">휴대폰</option>
                <option value="4">문화상품권</option>
                <option value="5">도서문화상품권</option>
                <option value="6">게임문화상품권</option>

            </select>
        </div>

        <div class="form_group">
            <label for="finalPrice">최종 결제 금액:</label>
            <div id="final"><span id="finalPrice"></span>원</div>
            <input type="hidden" name="price" id="price">
        </div>
        <button type="submit" id="paymentButton">구매하기</button>
    </div>
</form>

</body>
</html>