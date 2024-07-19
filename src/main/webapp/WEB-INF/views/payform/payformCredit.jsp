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
    <meta charset="utf-8"/>
    <title>결제하기</title>
    <style>
        #payment_success {
            position: relative;
            z-index: 9999; /* 매우 큰 값을 사용하여 최상위로 설정 */
        }

    </style>
    <script src="https://js.tosspayments.com/v1/payment"></script>
</head>
<body>

<script>
    var clientKey = "test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq";
    var tossPayments = TossPayments(clientKey);
    var memberNo = "${payformData.memberNo}";
    var detailNo = "${payformData.detailNo}";
    let paymethod = "payformPayment";

    switch (parseInt(${payformData.payformPayment})) {   //구독개월에 따라 원래가격 & 할인율 변경
        case 0:
            paymethod = "카드";
            break;
        case 1:
            paymethod = "가상계좌"
            break;
        case 2:
            paymethod = "계좌이체";
            break;
        case 3:
            paymethod = "휴대폰";
            break;
        case 4:
            paymethod = "문화상품권";
            break;
        case 5:
            paymethod = "도서문화상품권";
            break;
        case 6:
            paymethod = "게임문화상품권";
            break;
    }

    tossPayments
        .requestPayment(paymethod, {
            amount: parseInt(${payformData.price}),
            orderId: '1zalVNB7BZPoePa0xSRnf',   //이 값은 실제 결제 할때 select count(payformNo) from payform_tbl, 목업에서는 이 값으로 고정해야함.
            orderName: "${payformData.detailNa} 구매 폼", //헬스장 이름 들어가면서 구매 폼으로
            customerName: "${payformData.name}",
            successUrl: "https://docs.tosspayments.com/guides/payment/test-success",    //결제 성공 리다이렉트 페이지, 목업에서는 이 값으로 고정해야함.
            failUrl: "https://docs.tosspayments.com/guides/payment/test-fail",  //결제 실패 리다이렉트 페이지, 목업에서는 이 값으로 고정해야함.
        })
        .catch(function (error) {
            if (error.code === "USER_CANCEL") {
                alert("결제를 취소했습니다. 구매 폼 페이지로 돌아갑니다.")
                window.location.href = "${contextPath}/payform/payformForm.do?memberNo=" + memberNo + "&detailNo=" + detailNo;
            } else if (error.code === "INVALID_CARD_COMPANY") {
                alert("카드 정보가 유효하지 않습니다! 구매 폼 페이지로 돌아갑니다.")
                window.location.href = "${contextPath}/payform/payformForm.do?memberNo=" + memberNo + "&detailNo=" + detailNo;

            }
        });
</script>
</body>
</html>