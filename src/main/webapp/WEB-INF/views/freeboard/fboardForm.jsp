<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<main>
    <div id="insertForm">
        <form id="formId" name="frmFboard">
            <div class="form-container">
                <div class="mb-3">
                    <label for="boareWriter" class="form-label">작성자</label>
                    <input type="text" class="form-control" id="boareWriter" name="memberId" value="${sessionScope.member.memberId}" readonly>
                    <input type="hidden" name="memberNo" value="${sessionScope.member.memberNo}">
                </div>
                <div class="mb-3">
                    <label for="boardTitle" class="form-label">제목</label>
                    <input type="text" class="form-control" id="boardTitle" name="freeTitle">
                </div>
                <div class="mb-3">
                    <label for="boardContent" class="form-label">내용</label>
                    <textarea class="form-control" id="boardContent" rows="3" name="freeContent"></textarea>
                </div>
                <div class="mb-3">
                    <label for="fileInput" class="form-label">파일 추가</label>
                    <input type="button" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload" value="파일추가" onclick="fn_addFile()">
                    <div id="dock_file"></div>
                </div>
                <div class="button-group">
                    <button class="btn btn-outline-secondary" type="button" id="uploadButton">업로드</button>
                </div>
            </div>
        </form>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        var uploadButton = document.getElementById('uploadButton');
        uploadButton.addEventListener('click', function() {
            console.log('업로드 버튼 클릭됨');
            var form = document.getElementById('formId');
            form.action = '/freeboard/addFreeboard.do';
            form.method = 'post';
            form.enctype = 'multipart/form-data';
            form.submit();
        });
    });

    let count = 1;
    function fn_addFile() {
        $('#dock_file').append('<input type="file" name="imgFile' + count + '"><br>');
        count++;
    }
</script>
<link href="/css/freeboard/form.css" rel="stylesheet" />
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
