<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<link href="/css/notice/detail.css" rel="stylesheet" />

 <main>
        <table class="details-table">
            <thead>
                <tr>
                    <th class="title">제목</th>
                    <th class="author">작성자</th>
                    <th class="hit">조회수</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="title">${noMap.notice.noticeTitle}</td>
                    <td class="author">admin</td>
                    <td class="hit">${noMap.notice.noticeHit}</td>
                </tr>
                <tr>
                    <td colspan="3" class="content">${noMap.notice.noticeContent}</td>
                </tr>
                <c:if test="${not empty noMap.imageFileList}">
                    <tr>
                        <td colspan="3">
                            <div class="image-container">
                                <c:forEach var="imgList" items="${noMap.imageFileList}" varStatus="status">
                                    <img id="preview${status.count}" src="<c:url value='/nodownload.do'/>?noticeNo=${imgList.noticeNo}&imageFileName=${imgList.imageFileName}">
                                </c:forEach>
                            </div>
                        </td>
                    </tr>
                </c:if>
            </tbody>
        </table>

        <div class="button-group">
            <input class="btn btn-outline-secondary reBtn" type="button" value="돌아가기" onclick="location.href='/notice/noticeList.do'">
        </div>
</main>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
