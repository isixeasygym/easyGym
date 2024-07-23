<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>
        <div class="card mb-4">
           
            <div class="card-body">
				<form action="/admin/modNotice.do" id="formId" name="frmNotice" method="post" enctype="multipart/form-data">
				    <div class="form-container">
				        <div class="mb-3">
				            <label for="boardWriter" class="form-label">작성자</label>
				            <input type="text" class="form-control" name="adminId" id="boardWriter" value="${sessionScope.admin.adminId}" readonly>
				            <input type="hidden" name="noticeNo" value="${noticeMap.notice.noticeNo}"> 
				        </div>
				        <div class="mb-3">
				            <label for="boardTitle" class="form-label">제목</label>
				            <input type="text" class="form-control" name="noticeTitle" id="boardTitle" value="${noticeMap.notice.noticeTitle}" disabled>
				        </div>
				        <div class="mb-3">
				            <label for="noticeContent" class="form-label">내용</label>
				            <textarea class="form-control" id="noticeContent" name="noticeContent" rows="3" disabled>${noticeMap.notice.noticeContent}</textarea>
				        </div>
						<div class="mb-3">
				            <label for="noticeHit" class="form-label">조회수</label>
				            <textarea class="form-control" id="noticeHit" name="noticeHit" rows="3" disabled>${noticeMap.notice.noticeHit}</textarea>
				        </div>
				        <div class="input-group mb-3">
							<c:if test="${not empty noticeMap.imageFileList}">
							    <c:forEach var="imgList" items="${noticeMap.imageFileList}" varStatus="status">
							        <tr>
							            <td width="150" align="center" bgcolor="#ff9933" rowspan="2">이미지<span>${status.count}</span></td>
							            <td>
							                <input type="hidden" name="originalFileName${status.count}" value="${imgList.imageFileName}">
							                <input type="hidden" name="imageFileNo${status.count}" value="${imgList.imageFileNo}">
							                <img id="preview${status.count}" src="<c:url value='/download.do'/>?noticeNo=${imgList.noticeNo}&imageFileName=${imgList.imageFileName}">
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
				                <button type="button" class="btn btn-outline-info dropdown-toggle custom-select-btn" id="dropdownMenuButton" onclick="toggleDropdown()">
				                    선택
				                </button>
				                <div class="dropdown-menu custom-select-dropdown" id="customSelectDropdown">
				                    <a class="dropdown-item" name="noticeCategory" onclick="selectOption(1)" value="1">필독</a>
				                    <a class="dropdown-item" name="noticeCategory" onclick="selectOption(0)" value="0">일반</a>
				                </div>
				            </div>
							<div class="" id="div_button_modify" style="display: none;">
						        <button class="btn btn-outline-secondary" type="button"  onclick="fn_modify_notice(this.form)">수정반영하기</button>
						        <input type="button" value="취소" onclick="toList(this.form)">
						    </div>
						    <div class="" id="div_button">
						        <c:choose>
						            <c:when test="${not empty noticeMap and not empty sessionScope.admin.adminId}">
						                <input class="btn btn-outline-secondary" type="button"  value="수정하기" onclick="fn_enable(this.form)">
						                <input class="btn btn-outline-secondary" type="button" id="deleteButton" value="삭제하기" onclick="fn_remove_notice('/admin/removeNotice.do','${noticeMap.notice.noticeNo}')">
										<input class="btn btn-outline-secondary" type="button"  value="돌아가기" onclick="location.href='noticeList.do'" >
						            </c:when>
						        </c:choose>
						    </div>
				        </div>
				    </div>
				</form>
            </div>
        </div>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>







