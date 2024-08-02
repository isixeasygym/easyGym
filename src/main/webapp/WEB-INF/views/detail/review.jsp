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
<div id="reviewContainer">
    <c:choose>
        <c:when test="${not empty reviewMap.reviews}">
            <c:forEach var="review" items="${reviewMap.reviews}">
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
    <div>
        <!-- 총 리뷰 수가 5개를 초과하는 경우 페이징 처리 -->
        <c:if test="${reviewMap.tReview > 5}">
            <!-- 총 리뷰 수가 50개를 초과하는 경우 -->
            <c:choose>
                <c:when test="${reviewMap.tReview > 50}">
                    <c:forEach var="num" begin="1" end="${reviewMap.tReview / 5 + (reviewMap.tReview % 5 == 0 ? 0 : 1)}">
                        <!-- 이전 페이지 링크 -->
                        <c:if test="${reviewMap.section > 1 && num == 1}">
                            <a href="/detail/reviewViewer.do?section=${reviewMap.section - 1}&pageNum=${reviewMap.tReview - 5}">prev</a>
                        </c:if>
                        <!-- 현재 페이지 링크 -->
                        <c:if test="${num == (reviewMap.pageNum % 5 == 0 ? 5 : reviewMap.pageNum % 5)}">
                            <a class="target" href="/detail/reviewViewer.do?section=${reviewMap.section}&pageNum=${num}">${num}</a>
                        </c:if>
                        <!-- 비활성 페이지 링크 -->
                        <c:if test="${num != (reviewMap.pageNum % 5 == 0 ? 5 : reviewMap.pageNum % 5)}">
                            <a class="noLine" href="/detail/reviewViewer.do?section=${reviewMap.section}&pageNum=${num}">${num}</a>
                        </c:if>
                        <!-- 다음 페이지 링크 -->
                        <c:if test="${num == reviewMap.tReview / 5 + (reviewMap.tReview % 5 == 0 ? 0 : 1)}">
                            <a href="/detail/reviewViewer.do?section=${reviewMap.section + 1}&pageNum=${reviewMap.tReview + 1}">next</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="num" begin="1" end="${reviewMap.tReview / 5 + (reviewMap.tReview % 5 == 0 ? 0 : 1)}">
                        <!-- 현재 페이지 링크 -->
                        <c:if test="${num == reviewMap.pageNum}">
                            <a class="target" href="/detail/reviewViewer.do?section=${reviewMap.section}&pageNum=${num}">${num}</a>
                        </c:if>
                        <!-- 비활성 페이지 링크 -->
                        <c:if test="${num != reviewMap.pageNum}">
                            <a class="noLine" href="/detail/reviewViewer.do?section=${reviewMap.section}&pageNum=${num}">${num}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>
</div>
</body>
</html>