<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>신고글 목록창</title>
    <style type="text/css">
        a {
            text-decoration: none;
            color: black;
        }
        a:hover {
            text-decoration: underline;
        }
        .target {
            color: red;
            font-weight: bold;
            font-size: 1.5em;
        }
        .noLine {
            color: black;
            font-weight: normal;
            font-size: 1em;
        }
    </style>
    <script>
        function fn_articleForm(isLogOn, , loginForm) {
            if(isLogOn == 'true') {
                location.href = articleForm;
            } else {
                alert("로그인 후 글쓰기가 가능합니다.");
                location.href = loginForm + '?action=/board/articleForm.do';
            }
        }
    </script>
</head>
<body>
    <h2 align="center">신고글 목록창</h2>
    <table border="1" align="center" width="80%">
        <tr align="center" bgcolor="lightgreen">
            <th>글번호</th><th>작성자</th><th>제목</th><th>작성일</th>
        </tr>
        <c:forEach var="article" items="${articleMap.articlesList}">
            <tr align="center">
                <td width="10%">${article.articleNo}</td>
                <td width="20%">${article.id}</td>
                <td align="left" width="50%">
                    <a href="${contextPath}/board/viewArticle.do?articleNo=${article.articleNo}">${article.title}</a>
                </td>
                <td width="20%">
                    <fmt:formatDate value="${article.writeDate}" pattern="yyyy-MM-dd" />
                </td>
            </tr>
        </c:forEach>
    </table>
    
    <c:if test="${articleMap.totArticles > 10}">
        <div align="center">
            <c:if test="${articleMap.totArticles > 100}">
                <c:forEach var="num" begin="1" end="${articleMap.section > articleMap.totArticles / 100 ? (articleMap.totArticles % 100) / 10 + 1 : 10}">
                    <c:if test="${articleMap.section > 1 && num == 1}">
                        <a href="${contextPath}/board/listArticles.do?section=${articleMap.section - 1}&pageNum=${(articleMap.section - 1) * 10}">prev</a>
                    </c:if>
                    <c:choose>
                        <c:when test="${num == (articleMap.pageNum % 10 == 0 ? 10 : articleMap.pageNum % 10)}">
                            <a class="target" href="${contextPath}/board/listArticles.do?section=${articleMap.section}&pageNum=${(articleMap.section - 1) * 10 + num}">${(articleMap.section-1)*10+num}</a>
                        </c:when>
                        <c:otherwise>
                            <a class="noLine" href="${contextPath}/board/listArticles.do?section=${articleMap.section}&pageNum=${(articleMap.section - 1) * 10 + num}">${(articleMap.section-1)*10+num}</a>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${num == 10}">
                        <a href="${contextPath}/board/listArticles.do?section=${articleMap.section + 1}&pageNum=${articleMap.section * 10 + 1}">next</a>
                    </c:if>
                </c:forEach>
            </c:if>
            <c:if test="${articleMap.totArticles <= 100}">
                <c:forEach var="num" begin="1" end="${articleMap.totArticles/10 + 1}">
                    <c:choose>
                        <c:when test="${num == articleMap.pageNum}">
                            <a class="target" href="${contextPath}/board/listArticles.do?section=${articleMap.section}&pageNum=${num}">${num}</a>
                        </c:when>
                        <c:otherwise>
                            <a class="noLine" href="${contextPath}/board/listArticles.do?section=${articleMap.section}&pageNum=${num}">${num}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>
        </div>
    </c:if>
    
    <p align="center">
        <a href="javascript:fn_articleForm('${sessionScope.isLogOn}', '${contextPath}/board/articleForm.do', '${contextPath}/member/loginForm.do')">글쓰기</a>
    </p>
</body>
</html>