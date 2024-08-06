<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/detail/header.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="tReview" value="${reviewMap.tReview}"/>
<c:set var="section" value="${reviewMap.section}"/>
<c:set var="pageNum" value="${reviewMap.pageNum}"/>
<c:set var="tot50" value="${tReview mod 50}"/>
<c:choose>
	<c:when test = "${section > tReview/50}">
		<c:set var="endValue" value="${(tot50%10)==0?tot50/10:tot50/10+1}"/>
	</c:when>
	<c:otherwise>
		<c:set var="endValue" value="10"/>
	</c:otherwise>	
</c:choose>
<%
    Object member = session.getAttribute("member");
    request.setCharacterEncoding("utf-8");
%>
<style>
	.selPage {
		color:red;
		font-weight:bold;
	}
	.noLine{
		color:black;
		font-weight:normal;
	}
</style>
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
	<input type="hidden" class="memberNo" value="${member.memberNo}">
	<input type="hidden" class="detailNo" value="${reviewMap.detailNo}">
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
                    <c:forEach var="page" begin="1" end="${endValue}" step="1" varStatus="num">
                        <!-- 이전 페이지 링크 -->
                        <c:if test="${section > 1 && page == 1}">
                            <a href="/detail/reviewViewer.do?section=${section - 1}&pageNum=${currentPage}&detailNo=${reviewMap.detailNo}">prev</a>
                        </c:if>
                        <!-- 현재 페이지 링크 -->
						<c:choose>
							<c:when test="${pageNum==page}">
								<a class="selPage" href="${contextPath}/detail/reviewViewer.do?section=${section}&pageNum=${page}&detailNo=${reviewMap.detailNo}" >${(section-1)*10+page}</a>
								<c:set var="currentPage" value="${pageNum}" scope="application"/>
							</c:when>
                        	<c:otherwise>
								<a class="noLine" href="${contextPath}/detail/reviewViewer.do?section=${section}&pageNum=${page}&detailNo=${reviewMap.detailNo}">${(section-1)*10+page}</a>
							</c:otherwise>
						</c:choose>
                        <!-- 다음 페이지 링크 -->
                        <c:if test="${page== 10 and tReview/50>section}">
                            <a href="{contextPage}/detail/reviewViewer.do?section=${section + 1}&pageNum=${currentPage}&detailNo=${reviewMap.detailNo}">next</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:when test="${tReview <= 50}">
					<c:if test ="${(tReview mod 10 == 0)}">
						<c:set var ="tReview" value="${tReview-1}"/>
					</c:if>
					
                    <c:forEach var="page" begin="1" end="${tReview/10+1}" step="1">
						<c:choose>
							<c:when test="{page == pageNum}">
								<a class= "selPage" href="${contextPath}/detail/reviewViewer.do?section=${section}&pageNum=${page}&detailNo=${reviewMap.detailNo}">${page}</a>
								<c:set var="currentPage" value="${page}"/>
							</c:when>
							<c:otherwise>
								<a class= "noLine" href="${contextPath}/detail/reviewViewer.do?section=${section}&pageNum=${page}&detailNo=${reviewMap.detailNo}">${page}</a>
							</c:otherwise>
						</c:choose>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:if>
    </div>
</div>
</body>
</html>