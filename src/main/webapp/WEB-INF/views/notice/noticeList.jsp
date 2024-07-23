<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<link href="/css/notice/style.css" rel="stylesheet" />
<style>

</style>

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
	    <c:if test="${fbmap.tFreeboard > 10}">
	        <c:if test="${fbmap.tFreeboard > 100}">
	            <c:forEach var="num" begin="1" end="${fbmap.section > fbmap.tFreeboard / 100 ? (fbmap.tFreeboard % 100) / 10 + 1 : 10}">
	                <c:if test="${fbmap.section > 1 && num == 1}">
	                    <a href="/freeboard/fboardList.do?section=${fbmap.section - 1}&pageNum=${(fbmap.section - 1) * 10}">prev</a>
	                </c:if>
	                <c:if test="${num == (fbmap.pageNum % 10 == 0 ? 10 : fbmap.pageNum % 10)}">
	                    <a class="target" href="/freeboard/fboardList.do?section=${fbmap.section}&pageNum=${(fbmap.section - 1) * 10 + num}">${(fbmap.section-1)*10+num}</a>
	                </c:if>
	                <c:if test="${num != (fbmap.pageNum % 10 == 0 ? 10 : fbmap.pageNum % 10)}">
	                    <a class="noLine" href="/freeboard/fboardList.do?section=${fbmap.section}&pageNum=${(fbmap.section - 1) * 10 + num}">${(fbmap.section-1)*10+num}</a>
	                </c:if>
	                <c:if test="${num == 10}">
	                    <a href="/freeboard/fboardList.do?section=${fbmap.section + 1}&pageNum=${fbmap.section * 10 + 1}">next</a>
	                </c:if>
	            </c:forEach>
	        </c:if>
	        <c:if test="${fbmap.tArticles <= 100}">
	            <c:forEach var="num" begin="1" end="${fbmap.tArticles / 10 + 1}">
	                <c:if test="${num == fbmap.pageNum}">
	                    <a class="target" href="/freeboard/fboardList.do?section=${fbmap.section}&pageNum=${num}">${num}</a>
	                </c:if>
	                <c:if test="${num != amap.pageNum}">
	                    <a class="noLine" href="/freeboard/fboardList.do?section=${fbmap.section}&pageNum=${num}">${num}</a>
	                </c:if>
	            </c:forEach>
	        </c:if>
	    </c:if>
	</div>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>