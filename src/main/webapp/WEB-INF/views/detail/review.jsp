<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/detail/header.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    Object member = session.getAttribute("member");
    request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${details.detailBusinessName}</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        var contextPath = "${pageContext.request.contextPath}";
    </script>
    <link rel="stylesheet" href="${contextPath}/css/detail/review.css">
    <script src="${contextPath}/js/detail/review.js"></script>
</head>
<body>
<div id="reviewRange">
    <input type="hidden" class="userId" value="${member.memberNo}"/>
    <input type="hidden" class="companyId" value="${details.detailNo}">
    <div id="reviewContainer">
        <c:choose>
            <c:when test="${not empty reviews}">
                <c:forEach var="review" items="${reviews}">
                    <div class="ReviewRange">
                        <button class="deleteButton" onclick="deleteComment(${review.reviewNo})">삭제</button>
                        <div class="personReviewRange">
                            <img class="reviewPicture" src="${contextPath}/images/detail/detailpage/reviewImage.PNG">
                            <p class="anonymous">(익명의 회원)</p>
                            <p class="reviewDate">${review.reviewDate}</p>
                            <textarea class="reviewComment" readonly>${review.reviewComment}</textarea>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>