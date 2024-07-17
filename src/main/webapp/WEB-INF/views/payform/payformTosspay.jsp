<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>결제하기</title>
    <script src="https://js.tosspayments.com/v1/payment"></script>
</head>
<body>
<script>
    var clientKey = "${tossPaymentInfo.clientKey}";
    var tossPayments = TossPayments(clientKey);

    tossPayments
        .requestPayment("카드", {
            amount: ${tossPaymentInfo.amount},
            orderId: '${tossPaymentInfo.orderId}',
            orderName: "${tossPaymentInfo.orderName}",
            customerName: "${tossPaymentInfo.customerName}",
            successUrl: "${tossPaymentInfo.successUrl}",
            failUrl: "${tossPaymentInfo.failUrl}",
        })
        .catch(function (error) {
            if (error.code === "USER_CANCEL") {
                // 결제 고객이 결제창을 닫았을 때 에러 처리
            } else if (error.code === "INVALID_CARD_COMPANY") {
                // 유효하지 않은 카드 코드에 대한 에러 처리
            }
        });
</script>
</body>
</html>