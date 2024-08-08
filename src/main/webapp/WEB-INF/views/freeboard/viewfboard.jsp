<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<link href="/css/freeboard/detail.css" rel="stylesheet" />
<script src="/js/freeboard/script.js"></script>

<main>
    <form name="frmArticle" method="post" enctype="multipart/form-data">
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
                    <td ><input class="title2" id="freeTitle" name="freeTitle" value="${fbmap.fboard.freeTitle}" disabled></td>
                    <td class="author">${fbmap.mDTO.memberName}</td>
                    <td class="hit">${fbmap.fboard.freeHit}</td>
					<input type="hidden" name="freeNo" value="${fbmap.fboard.freeNo}">
                </tr>
                <tr>
                    <td colspan="3" ><input class="content" id="freeContent" name="freeContent" value="${fbmap.fboard.freeContent}" disabled></td>
                </tr>
				<c:choose>
	                <c:when test="${not empty fbmap.imageFileList}">
	                    <!-- 이미지 리스트가 있을 때 -->
	                    <tr>
	                        <td colspan="4">
	                            <div class="image-container">
	                                <c:forEach var="imgList" items="${fbmap.imageFileList}" varStatus="status">
	                                    <img id="preview${status.count}" src="<c:url value='/frdownload.do'/>?freeNo=${imgList.freeNo}&imageFileName=${imgList.imageFileName}">
	                                    <input type="file" class="id_imgFile" name="imageFileName${status.count}" onchange="readImage(this, ${status.count})" disabled>
	                                </c:forEach>
	                            </div>
	                        </td>
	                    </tr>
	                </c:when>
	                <c:otherwise>
	                    <!-- 이미지 리스트가 비어 있을 때 -->
	                    <tr>
	                        <td colspan="4">
	                            <div class="no-image-message">
	                                등록된 이미지가 없습니다.
	                            </div>
	                        </td>
	                    </tr>
	                </c:otherwise>
	            </c:choose>
            </tbody>
        </table>
        <div class="button-group bg" id="div_button">
            <c:choose>
                <c:when test="${fbmap != null && sessionScope != null && sessionScope.member != null && fbmap.fboard.memberNo == sessionScope.member.memberNo}">
                    <input class="btn btn-outline-secondary" type="button" value="수정하기" onclick="fn_enable(frmArticle)">
                    <input class="btn btn-outline-secondary" type="button" id="deleteButton" value="삭제하기" onclick="fn_remove_fboard('/freeboard/removeFboard.do','${fbmap.fboard.freeNo}')">
                </c:when>
            </c:choose>
            <input class="btn btn-outline-secondary reBtn" type="button" value="돌아가기" onclick="location.href='/freeboard/fboardList.do'">
        </div>
        <div class="button-group bg" id="div_button_modify" style="display: none;">
            <input class="btn btn-outline-secondary" value="수정 확인" onclick="fn_modify_fboard(frmArticle)">
        </div>
    </form>
    <div id="commentList">
        <c:choose>
            <c:when test="${sessionScope.getAnswer == 0}">
                등록된 댓글이 없습니다.
            </c:when>
            <c:otherwise>
                <c:forEach var="answer" items="${amap.alist}">
                    <div class="comment">
                        <div class="comment-header">회원 번호: ${answer.memberNo}</div>
                        <div class="comment-content">댓글 내용: ${answer.fbanswerContent}</div>
                        <div class="comment-date">댓글 작성일: ${answer.fbanswerWriteDate}</div>
                        <button class="delBtn" onclick="del(${answer.fbanswerNo})">삭제</button>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    <button id="showCommentForm">댓글 입력하기</button>
    <div class="comment-form" id="commentForm" style="display: none;">
        <form id="commentFormForm" enctype="multipart/form-data">
            <input type="hidden" id="memberNo" name="memberNo" value="${sessionScope.member.memberNo}">
            <input type="hidden" id="freeNo" name="freeNo" value="${fbmap.fboard.freeNo}">
            <textarea id="fbanswerContent" name="fbanswerContent" rows="4" cols="50"></textarea>
            <button type="button" id="submitCommentBtn">댓글 등록</button>
        </form>
    </div>
</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(document).ready(function() {
        loadComments();

        $('#submitCommentBtn').click(function() {
            submitComment();
        });

        $('#showCommentForm').click(function() {
            $('#commentForm').toggle();
        });
    });

    function loadComments() {
        var freeNo = $('#freeNo').val();
        var memberNo = $('#memberNo').val();

        $.ajax({
            type: 'GET',
            url: '/freeboard/getAnswerListAjax.do',
            data: { freeNo: freeNo },
            success: function(response) {
                if (response.status === 'success') {
                    var comments = response.alist;
                    var commentListHtml = '';

                    for (var i = 0; i < comments.length; i++) {
                        var comment = comments[i];
                        commentListHtml += '<div class="comment">' +
                            '<div class="comment-header">회원 번호: ' + comment.memberNo + '</div>' +
                            '<div class="comment-content">댓글 내용: ' + comment.fbanswerContent + '</div>' +
                            '<div class="comment-date">댓글 작성일: ' + comment.fbanswerWriteDate + '</div>' +
                            '<button class="delBtn" onclick="del(' + comment.fbanswerNo + ')">삭제</button>' +
                            '</div>';
                    }

                    $('#commentList').html(commentListHtml);
                } else {
                    alert(response.message);
                }
            },
            error: function(xhr, status, error) {
                alert("댓글 리스트 불러오기 중 오류 발생: " + error);
            }
        });
    }

    function submitComment() {
        var freeNo = $('#freeNo').val();
        var memberNo = $('#memberNo').val();
        var fbanswerContent = $('#fbanswerContent').val();

        if (!memberNo) {
            alert('로그인 후 작성해주세요');
            window.location.href = '/member/loginForm.do'; // 로그인 페이지로 리디렉션
            return; // 로그인하지 않은 상태에서는 추가 처리를 하지 않음
        }

        $.ajax({
            type: 'POST',
            url: '/freeboard/addAnswerAjax.do',
            data: {
                freeNo: freeNo,
                memberNo: memberNo,
                fbanswerContent: fbanswerContent
            },
            success: function(response) {
                if (response.status === 'success') {
                    loadComments();
                    $('#commentForm').hide();
                    $('#fbanswerContent').val('');
                } else {
                    alert(response.message);
                }
            },
            error: function(xhr, status, error) {
                if (xhr.status === 401) { // 로그인하지 않은 경우
                    alert('로그인 후 작성해주세요');
                    window.location.href = '/member/loginForm.do'; // 로그인 페이지로 리디렉션
                } else {
                    alert("댓글 등록 중 오류 발생: " + error);
                }
            }
        });
    }

    function del(fbanswerNo) {
        $.ajax({
            type: 'POST',
            url: '/freeboard/removeAnswerAjax.do',
            data: { fbanswerNo: fbanswerNo },
            success: function(response) {
                if (response.status === 'success') {
                    loadComments();
                } else {
                    alert(response.message);
                }
            },
            error: function(xhr, status, error) {
                alert("댓글 삭제 중 오류 발생: " + error);
            }
        });
    }
</script>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
