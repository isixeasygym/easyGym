<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<link href="/css/notice/style.css" rel="stylesheet" />
        <div class="card mb-4">
            <div class="card-body">
				<form action="/notice/viewNotice.do" id="formId" name="frmNotice" method="post" enctype="multipart/form-data">
				    <div class="form-container">
				        <div class="mb-3">
				            <label for="boardWriter" class="form-label">작성자</label>
				            <input type="text" class="form-control" name="adminId" id="boardWriter" value="admin" readonly>
				            <input type="hidden" name="noticeNo" value="${noMap.notice.noticeNo}"> 
				        </div>
				        <div class="mb-3">
				            <label for="boardTitle" class="form-label">제목</label>
				            <input type="text" class="form-control" name="noticeTitle" id="boardTitle" value="${noMap.notice.noticeTitle}" disabled>
				        </div>
				        <div class="mb-3">
				            <label for="noticeContent" class="form-label">내용</label>
				            <textarea class="form-control" id="noticeContent" name="noticeContent" rows="3" disabled>${noMap.notice.noticeContent}</textarea>
				        </div>
						<div class="mb-3">
				            <label for="noticeHit" class="form-label">조회수</label>
				            <textarea class="form-control" id="noticeHit" name="noticeHit" rows="3" disabled>${noMap.notice.noticeHit}</textarea>
				        </div>
				        <div class="input-group mb-3">
							<c:if test="${not empty noMap.imageFileList}">
							    <c:forEach var="imgList" items="${noMap.imageFileList}" varStatus="status">
							        <tr>
							            <td width="150" align="center" bgcolor="#ff9933" rowspan="2">이미지<span>${status.count}</span></td>
							            <td>
							                <input type="hidden" name="originalFileName${status.count}" value="${imgList.imageFileName}">
							                <input type="hidden" name="imageFileNo${status.count}" value="${imgList.imageFileNo}">
							                <img id="preview${status.count}" src="<c:url value='/nodownload.do'/>?noticeNo=${imgList.noticeNo}&imageFileName=${imgList.imageFileName}">
							            </td>
							        </tr>
							        <tr>
							            <td><input type="file" class="id_imgFile" name="imageFileName${status.count}" onchange="readImage(this,${status.count})" disabled></td>
							        </tr>
							    </c:forEach>
							</c:if>
				        </div>
				       
				    </div>
				</form>
            </div>
        </div>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>







