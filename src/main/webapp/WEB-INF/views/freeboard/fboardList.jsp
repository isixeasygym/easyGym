<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
   request.setCharacterEncoding("utf-8");
%>
<link rel="stylesheet" href="/css/freeboard/style.css">
<script src="/js/freeboard/script.js"></script>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<main>
    <h2 id="h2">자유게시판</h2>
    
    <div class="table-header">
        <a class="write-button" href="javascript:fn_fboardForm(${sessionScope.isLogOn ? 'true' : 'false'}, '/freeboard/fboardForm.do', '/member/loginForm.do');">글쓰기</a>
    </div>
    
    <table class="table table-hover">
        <thead class="headtr">
            <tr>
                <th scope="col" width="5%">No</th>
                <th scope="col" width="50%" class="title">제목</th>
                <th scope="col" width="15%">글쓴이</th>
                <th scope="col" width="20%">작성시간</th>
                <th scope="col" width="10%">조회수</th>
            </tr>
        </thead>
        <tbody id="post-list">
            <c:choose>
                <c:when test="${empty fbmap.fblist}">
                    <tr>
                        <td colspan="5" class="text-center">등록된 게시글이 없습니다.</td>
                    </tr>
                </c:when>
                <c:when test="${!empty fbmap.fblist}">
                    <c:forEach var="fboard" items="${fbmap.fblist}">
                        <tr>
                            <td class="bd-num">${fboard.freeNo}</td>
                            <td class="L">
                                <a href="/freeboard/viewfboard.do?freeNo=${fboard.freeNo}" class="link">
                                    ${fboard.freeTitle}
                                </a>
                            </td>
                            <td>${fboard.memberName}</td>
                            <td class="bd-date">${fboard.freeWriteDate}</td>
                            <td class="bd-readcnt">${fboard.freeHit}</td>
                        </tr>
                    </c:forEach>
                </c:when>
            </c:choose>
        </tbody>
    </table>
    
    <div class="pagination-container text-center">
        <c:if test="${fbmap.tFreeboard > 10}">
            <c:if test="${fbmap.tFreeboard > 100}">
                <c:forEach var="num" begin="1" end="${fbmap.section > fbmap.tFreeboard / 100 ? (fbmap.tFreeboard % 100) / 10 + 1 : 10}">
                    <c:if test="${fbmap.section > 1 && num == 1}">
                        <a class="pagination-link" href="/freeboard/fboardList.do?section=${fbmap.section - 1}&pageNum=${(fbmap.section - 1) * 10}">prev</a>
                    </c:if>
                    <c:if test="${num == (fbmap.pageNum % 10 == 0 ? 10 : fbmap.pageNum % 10)}">
                        <a class="pagination-link active" href="/freeboard/fboardList.do?section=${fbmap.section}&pageNum=${(fbmap.section - 1) * 10 + num}">${(fbmap.section-1)*10+num}</a>
                    </c:if>
                    <c:if test="${num != (fbmap.pageNum % 10 == 0 ? 10 : fbmap.pageNum % 10)}">
                        <a class="pagination-link" href="/freeboard/fboardList.do?section=${fbmap.section}&pageNum=${(fbmap.section - 1) * 10 + num}">${(fbmap.section-1)*10+num}</a>
                    </c:if>
                    <c:if test="${num == 10}">
                        <a class="pagination-link" href="/freeboard/fboardList.do?section=${fbmap.section + 1}&pageNum=${fbmap.section * 10 + 1}">next</a>
                    </c:if>
                </c:forEach>
            </c:if>
            <c:if test="${fbmap.tArticles <= 100}">
                <c:forEach var="num" begin="1" end="${fbmap.tArticles / 10 + 1}">
                    <c:if test="${num == fbmap.pageNum}">
                        <a class="pagination-link active" href="/freeboard/fboardList.do?section=${fbmap.section}&pageNum=${num}">${num}</a>
                    </c:if>
                    <c:if test="${num != fbmap.pageNum}">
                        <a class="pagination-link" href="/freeboard/fboardList.do?section=${fbmap.section}&pageNum=${num}">${num}</a>
                    </c:if>
                </c:forEach>
            </c:if>
        </c:if>
    </div>
</main>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
