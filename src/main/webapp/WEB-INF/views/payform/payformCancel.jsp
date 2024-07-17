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
    <title>이지짐 회원권 취소</title>
    <script src="${contextPath}/resources/static/JS/payform/payformCancel.js"></script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/static/CSS/payform/payformCancel.css">
</head>
<body>
<div class="bg-image"></div>
<div class="container">
    <div class="receipt_info">
        <span class="hidden">주문취소번호: <span id="cancelNo">${cancelInfo.cancelNo}</span></span>
        <span class="hidden">주문번호: <span id="payformNo">${cancelInfo.payformNo}</span></span>
        <span class="hidden">멤버 번호: <span id="memberNo">${cancelInfo.memberNo}</span></span>
        <span class="hidden">헬스장 번호: <span id="wholeNo">${cancelInfo.wholeNo}</span></span>
    </div>

    <h1><span id="wholeName">${cancelInfo.wholeName}</span> 헬스장 구매 취소 폼</h1>

    <h2>환불 정보</h2>
    <form id="payCheck_form">
        <div class="form_group">
            <div class="paymentInfo">
                <div class="form_group">
                    <label for="payName">구매자:</label>
                    <input type="text" id="payName" name="payName" value="${cancelInfo.payName}" readonly required>
                </div>
                <div class="form_group">
                    <label for="userTel">휴대전화:</label>
                    <input type="text" id="userTel" name="userTel" value="${cancelInfo.userTel}" readonly required>
                </div>
                <div class="form_group">
                    <label for="bisName">헬스장 이름:</label>
                    <input type="text" id="bisName" name="bisName" value="${cancelInfo.bisName}" readonly required>
                </div>
                <div class="form_group">
                    <p>구매일로부터<input type="text" id="cancelDay" name="cancelDay" width="10px" value="${cancelInfo.cancelDay}" readonly required>일 지나서 환불이 <span id="cancelAble"></span>합니다</p>
                </div>
            </div>

            <label for="finalPrice">환불 금액:</label>
            <div id="finalPrice">${cancelInfo.finalPrice}원</div>
        </div>
        <button type="submit" id="cancelButton">확인</button>
    </form>
</div>

</body>
</html>