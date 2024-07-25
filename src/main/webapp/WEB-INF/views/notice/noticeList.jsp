<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<link href="/css/notice/style.css" rel="stylesheet" />
	<main>
      <h2>공지사항</h2>
	<table border="1" id="table">
        <thead>
            <tr>
                <th>공지사항 번호</th>
                <th>공지사항 제목</th>
                <th>작성자</th>
                <th>공지사항 작성일</th>
                <th>공지사항 조회수</th>
            </tr>
        </thead>
        <tbody>
			<c:choose>
				<c:when test="${empty noMap.nolist}">
					<tr>
						<td colspan="5">
							<p align="center">등록된 공지사항이 없습니다.</p>
						</td>
					</tr>
				</c:when>
				<c:when test="${!empty noMap.nolist}">
					<c:forEach var="notice" items="${noMap.nolist}">
						<tr>
						   <td>${notice.noticeNo}</td>
                           <td id="title">
                               <c:choose>
                                   <c:when test="${notice.noticeCategory == '1'}">
                                       <span class="hit">필독</span>
                                       <a href="/notice/viewNotice.do?noticeNo=${notice.noticeNo}">${notice.noticeTitle}</a>
                                   </c:when>
                                   <c:otherwise>
                                       <a href="/notice/viewNotice.do?noticeNo=${notice.noticeNo}">${notice.noticeTitle}</a>
                                   </c:otherwise>
                               </c:choose>
                           </td>
                           <td>관리자</td>
                           <td>${notice.noticeWriteDate}</td>
                           <td>${notice.noticeHit}</td>
						<tr>
					</c:forEach>
				</c:when>
			</c:choose>
        </tbody>
    </table>
	<div align="center">
	    <c:if test="${noMap.noBoard > 10}">
	        <c:if test="${noMap.noBoard > 100}">
	            <c:forEach var="num" begin="1" end="${noMap.section > noMap.noBoard / 100 ? (noMap.noBoard % 100) / 10 + 1 : 10}">
	                <c:if test="${noMap.section > 1 && num == 1}">
	                    <a href="/notice/noticeList.do?section=${noMap.section - 1}&pageNum=${(noMap.section - 1) * 10}">prev</a>
	                </c:if>
	                <c:if test="${num == (noMap.pageNum % 10 == 0 ? 10 : noMap.pageNum % 10)}">
	                    <a class="target" href="/notice/noticeList.do?section=${noMap.section}&pageNum=${(noMap.section - 1) * 10 + num}">${(noMap.section-1)*10+num}</a>
	                </c:if>
	                <c:if test="${num != (noMap.pageNum % 10 == 0 ? 10 : noMap.pageNum % 10)}">
	                    <a class="noLine" href="/notice/noticeList.do?section=${noMap.section}&pageNum=${(noMap.section - 1) * 10 + num}">${(noMap.section-1)*10+num}</a>
	                </c:if>
	                <c:if test="${num == 10}">
	                    <a href="/notice/noticeList.do?section=${noMap.section + 1}&pageNum=${noMap.section * 10 + 1}">next</a>
	                </c:if>
	            </c:forEach>
	        </c:if>
	        <c:if test="${noMap.noBoard <= 100}">
	            <c:forEach var="num" begin="1" end="${noMap.noBoard / 10 + 1}">
	                <c:if test="${num == noMap.pageNum}">
	                    <a class="target" href="/notice/noticeList.do?section=${noMap.section}&pageNum=${num}">${num}</a>
	                </c:if>
	                <c:if test="${num != noMap.pageNum}">
	                    <a class="noLine" href="/notice/noticeList.do?section=${noMap.section}&pageNum=${num}">${num}</a>
	                </c:if>
	            </c:forEach>
	        </c:if>
	    </c:if>
	</div>
	</main>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>