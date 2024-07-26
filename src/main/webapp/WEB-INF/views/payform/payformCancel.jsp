<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <script src="${contextPath}/JS/payform/payformCancel.js"></script>
    <script>
        let diffDays;

        function localeStringToInt(localeString) {
            // 1. 모든 숫자가 아닌 문자 제거 (소수점은 제외)
            const numericString = localeString.replace(/[^0-9.]/g, '');

            // 2. 문자열을 숫자로 변환
            const number = parseFloat(numericString);

            // 3. 정수로 변환
            return Math.round(number);
        }

        function setCancelDay() {
            // payformDate를 Date 객체로 변환
            const [year, month, day] = "${payform.payformDate}".split('-').map(Number);
            const d1 = new Date(year, month - 1, day); // 월은 0부터 시작하므로 1을 빼줍니다

            // 오늘 날짜
            const d2 = new Date();

            // 시간 차이를 밀리초 단위로 계산
            const diffTime = Math.abs(d2 - d1);

            // 밀리초를 일 단위로 변환
            diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) - 1;

            document.getElementById('cancelDay').value = diffDays;

            return diffDays;
        }

        function CheckRefund() {
            //사용자가 임의로 변수값 조작하지 못하도록 다시 한번 일수 계산함
            let refundDay = setCancelDay();
            let finalPr = localeStringToInt(document.getElementById('finalPr').textContent);

            if (parseInt('${payform.payformStatus}') === -1) {
                alert("이미 환불이 완료된 결제건입니다.\n메인페이지로 돌아갑니다.");
                window.location.replace("${contextPath}/main.do");
            } else {
                if (refundDay <= 7) {
                    var result = confirm("전액 환불이 가능합니다. 환불금액은 " + finalPr.toLocaleString() + "원입니다.\n환불을 진행하시겠습니까?");
                    if (result) {
                        submitRefundForm(finalPr);
                    } else
                        alert("환불 진행을 취소했습니다.");
                } else if (refundDay <= 15) {
                    var result = confirm("부분적인 환불이 가능합니다. 환불금액은 " + finalPr.toLocaleString() + "원입니다.\n환불을 진행하시겠습니까?");
                    if (result) {
                        submitRefundForm(finalPr);
                    } else
                        alert("환불 진행을 취소했습니다.");
                } else {
                    alert("환불 가능 기간이 경과하여 환불이 불가능합니다.");
                    window.location.replace("${contextPath}/main.do");
                }
            }
        }

        function submitRefundForm(finalPr) {
            document.getElementById('refundPrice').value = finalPr;
            document.getElementById('refundForm').submit();
        }

    </script>

    <link rel="stylesheet" type="text/css" href="${contextPath}/CSS/payform/payformCancel.css">
</head>
<body>
<form id="refundForm" action="${contextPath}/payform/payformRefund.do" method="POST" style="display: none;">
    <input type="hidden" name="payformNo" value="${payform.payformNo}">
    <input type="hidden" name="refundPrice" id="refundPrice">
</form>

<div class="bg-image"></div>
<div class="container">
    <div class="receipt_info">
        <span class="hidden">주문번호: <span id="payformNo">${payform.payformNo}</span></span>
        <span class="hidden">멤버 번호: <span id="memberNo">${payform.memberNo}</span></span>
        <span class="hidden">헬스장 번호: <span id="detailNo">${payform.detailNo}</span></span>
    </div>

    <h1><span id="detailName">${payform.detailBusinessName}</span> 헬스장 구매 취소</h1>

    <h2>환불 정보</h2>
    <form id="payCheck_form">
        <div class="form_group">
            <div class="paymentInfo">
                <div class="form_group">
                    <label for="payName">구매자:</label>
                    <input type="text" id="payName" name="payName" value="${payform.memberName}" readonly required>
                </div>
                <div class="form_group">
                    <label for="userTel">휴대전화:</label>
                    <input type="text" id="userTel" name="userTel" value="${payform.memberPhone}" readonly required>
                </div>
                <div class="form_group">
                    <label for="bisName">헬스장 이름:</label>
                    <input type="text" id="bisName" name="bisName" value="${payform.detailBusinessName}" readonly
                           required>
                </div>
                <div class="form_group">
                    <p>구매일로부터<input type="text" id="cancelDay" name="cancelDay" width="10px" value="" readonly required>일
                        지나서 환불이 <span id="cancelAble"></span>합니다</p>
                    <script>
                        setCancelDay();
                    </script>
                </div>
            </div>

            <label for="finalPr">환불 금액:</label>
            <div id="finalPrice"><span id="finalPr">${payform.payformPrice}</span>원</div>
            <script>
                let fpElement = document.getElementById('finalPr');
                let finalPrice = fpElement.innerText;
                let fp = parseInt(finalPrice);
                fpElement.innerText = fp.toLocaleString();
            </script>
        </div>
        <button type="button" id="cancelButton" onclick="CheckRefund(${payform.payformDate})">확인</button>
    </form>
</div>

</body>
</html>