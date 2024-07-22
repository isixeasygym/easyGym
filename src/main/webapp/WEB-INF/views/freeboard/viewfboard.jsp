<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<link href="/css/freeboard/style.css" rel="stylesheet" />
<script src="/js/freeboard/script.js"></script>
<div id="insertForm">
	<h1 class="mt-4">자유게시판 글쓰기</h1>
        <ol class="breadcrumb mb-4">
            <li class="breadcrumb-item"><a href="/freeboard/fboardList.do">자유게시판으로</a></li>
        </ol>
	<form action="/freeboard/modFboard.do" id="formId" name="frmFboard" method="post" enctype="multipart/form-data">
	    <div class="form-container">
	        <div class="mb-3">
	            <label for="boardWriter" class="form-label">작성자</label>
	            <input type="text" class="form-control" name="adminId" id="boardWriter" value="${fbmap.mDTO.memberName}" readonly>
	            <input type="hidden" name="freeNo" value="${fbmap.fboard.freeNo}"> 
	        </div>
	        <div class="mb-3">
	            <label for="freeTitle" class="form-label">제목</label>
	            <input type="text" class="form-control" name="freeTitle" id="freeTitle" value="${fbmap.fboard.freeTitle}" disabled>
	        </div>
	        <div class="mb-3">
	            <label for="freeContent" class="form-label">내용</label>
	            <textarea class="form-control" id="freeContent" name="freeContent" rows="3" disabled>${fbmap.fboard.freeContent}</textarea>
	        </div>
			<div class="mb-3">
	            <label for="freeHit" class="form-label">조회수</label>
	            <textarea class="form-control" id="freeHit" name="freeHit" rows="3" disabled>${fbmap.fboard.freeHit}</textarea>
	        </div>
	        <div class="input-group mb-3">
				<c:if test="${not empty fbmap.imageFileList}">
				    <c:forEach var="imgList" items="${fbmap.imageFileList}" varStatus="status">
				        <tr>
				            <td width="150" align="center" bgcolor="#ff9933" rowspan="2">이미지<span>${status.count}</span></td>
				            <td>
				                <input type="hidden" name="originalFileName${status.count}" value="${imgList.imageFileName}">
				                <input type="hidden" name="imageFileNo${status.count}" value="${imgList.imageFileNo}">
				                <img id="preview${status.count}" src="<c:url value='/download.do'/>?noticeNo=${imgList.freeNo}&imageFileName=${imgList.imageFileName}">
				            </td>
				        </tr>
				        <tr>
				            <td><input type="file" class="id_imgFile" name="imageFileName${status.count}" onchange="readImage(this,${status.count})" disabled></td>
				        </tr>
				    </c:forEach>
				</c:if>
	        </div>
	        <div class="dropdown-container">
	            <div class="btn-group" id="dropdownContainer">
	                
	            </div>
				<div class="" id="div_button_modify" style="display: none;">
			        <button class="btn btn-outline-secondary" type="button"  onclick="fn_modify_fboard(this.form)">수정반영하기</button>
			        <input type="button" value="취소" onclick="toList(this.form)">
			    </div>
			    <div class="" id="div_button">
			        <c:choose>
			            <c:when test="${not empty fbmap and not empty sessionScope.member.memberId}">
			                <input class="btn btn-outline-secondary" type="button"  value="수정하기" onclick="fn_enable(this.form)">
			                <input class="btn btn-outline-secondary" type="button" id="deleteButton" value="삭제하기" onclick="fn_remove_fboard('/freeboard/removeFboard.do','${fbmap.fboard.freeNo}')">
							<input class="btn btn-outline-secondary" type="button"  value="돌아가기" onclick="location.href='fboardList.do'" >
			            </c:when>
			        </c:choose>
			    </div>
	        </div>
	    </div>
	</form>
</div>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>