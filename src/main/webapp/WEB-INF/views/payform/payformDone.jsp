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
    <title>이지짐 구매 완료</title>
    <script src="${contextPath}/resources/static/JS/payform/payformDone.js"></script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/static/CSS/payform/payformDone.css">
</head>
<body>
<div class="bg-image"></div>
<div class="container">
    <h1>구매 완료되었습니다!</h1>
    <form id="payCheck_form">
        <div class="form_group">
            <div class="paymentInfo">
                <div class="form_group">
                    <label for="payName">구매자:</label>
                    <input type="text" id="payName" name="payName" value="${result.memberName}" readonly required>
                </div>
                <div class="form_group">
                    <label for="userTel">휴대전화:</label>
                    <input type="text" id="userTel" name="userTel" value="${result.memberPhone}" readonly required>
                </div>
                <div class="form_group">
                    <label for="bisName">헬스장 이름:</label>
                    <input type="text" id="bisName" name="bisName" value="${result.detailBusinessName}" readonly required>
                </div>
            </div>

            <label for="finalPrice">최종 결제 금액:</label>
            <div id="finalPrice">${result.finalPrice}원</div>
        </div>
        <button type="submit" id="goBack" >확인</button>
    </form>
</div>

</body>
</html>