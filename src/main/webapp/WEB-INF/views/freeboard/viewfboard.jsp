<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<style>
        /* 기본적인 스타일 추가 */
        .comment-form {
            margin-top: 20px;
        }

        .form-control {
            display: block;
            margin-bottom: 10px;
            width: 100%;
        }

        .delBtn {
            margin-top: 10px;
            cursor: pointer;
            color: red;
        }
    </style>
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
				                <img id="preview${status.count}" src="<c:url value='/download.do'/>?freeNo=${imgList.freeNo}&imageFileName=${imgList.imageFileName}">
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
			            <c:when test="${fbmap != null && sessionScope != null && sessionScope.member != null && fbmap.fboard.memberNo == sessionScope.member.memberNo}">
			                <input class="btn btn-outline-secondary" type="button"  value="수정하기" onclick="fn_enable(this.form)">
			                <input class="btn btn-outline-secondary" type="button" id="deleteButton" value="삭제하기" onclick="fn_remove_fboard('/freeboard/removeFboard.do','${fbmap.fboard.freeNo}')">
							<input class="btn btn-outline-secondary" type="button"  value="돌아가기" onclick="location.href='fboardList.do'" >
			            </c:when>
			        </c:choose>
			    </div>
	        </div>
	    </div>
	</form>
	<!--<pre>${amap}</pre>

	<c:choose>
	    <c:when test="${empty amap.alist}">
	        등록된 리뷰가 없습니다.
	    </c:when>
	    <c:otherwise>
	        <c:forEach var="answer" items="${amap.alist}">
	            <div>
	                <input type="text" class="form-control" name="memberId" id="boardWriter" value="${answer.memberName}" readonly>
	                <input type="text" class="form-control" name="fbanswerContent" id="fbanswerContent" value="${answer.fbanswerContent}" readonly>
	                <input type="text" class="form-control" name="fbanswerWriteDate" id="fbanswerWriteDate" value="${answer.fbanswerWriteDate}" readonly>
	                <button class="delBtn" onclick="del(${answer.fbanswerNo})">삭제</button>
	            </div>
	        </c:forEach>
	    </c:otherwise>
	</c:choose>-->
	<h2>댓글 목록</h2>

	    <div id="commentList">
	        <c:choose>
				
	            <c:when test="${sessionScope.getAnswer == 0}">
	                등록된 댓글이 없습니다.
	            </c:when>
	            <c:otherwise>
	                <c:forEach var="answer" items="${amap.alist}">
	                    <div>
	                        <input type="text" class="form-control" name="memberNo" value="${sessionScope.member.memberNo}" readonly>
	                        <input type="text" class="form-control" name="fbanswerContent" value="${answer.fbanswerContent}" readonly>
	                        <input type="text" class="form-control" name="fbanswerWriteDate" value="${answer.fbanswerWriteDate}" readonly>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!--<div>
	    <button id="showCommentForm">댓글 입력하기</button>
	</div>

	<div class="comment-form" id="commentForm" style="display: none;">
		<form id="commentForm" enctype="multipart/form-data">
		    <input type="hidden" name="freeNo" value="${freeNo}">
		    <input type="hidden" name="memberNo" value="${memberNo}">
		    <textarea name="fbanswerContent" rows="4" cols="50"></textarea>
		    <button type="button" onclick="submitComment()">댓글 등록</button>
		</form>
		
	</div>-->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function() {
		        // 페이지 로딩 시 댓글 리스트를 불러옵니다.
		        loadComments();

		        // 댓글 등록 버튼 클릭 이벤트
		        $('#submitCommentBtn').click(function() {
		            submitComment();
		        });

		        // 댓글 입력 폼 토글
		        $('#showCommentForm').click(function() {
		            $('#commentForm').toggle(); // 폼을 표시하거나 숨기기
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
		                        commentListHtml += '<div style="border: 1px solid #ddd; padding: 10px; margin-bottom: 10px;">' +
		                            '<div>회원 번호: <input type="text" class="form-control" name="memberNo" value="' + comment.memberNo + '" readonly></div>' +
		                            '<div>댓글 내용: <input type="text" class="form-control" name="fbanswerContent" value="' + comment.fbanswerContent + '" readonly></div>' +
		                            '<div>댓글 작성날짜: <input type="text" class="form-control" name="fbanswerWriteDate" value="' + comment.fbanswerWriteDate + '" readonly></div>' +
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
		                    loadComments(); // 댓글 추가 후 목록 다시 불러오기
		                    $('#commentForm').hide(); // 폼 숨기기
		                    $('#fbanswerContent').val(''); // 댓글 내용 초기화
		                } else {
		                    alert(response.message);
		                }
		            },
		            error: function(xhr, status, error) {
		                alert("댓글 등록 중 오류 발생: " + error);
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
		                    loadComments(); // 댓글 삭제 후 목록 다시 불러오기
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
</div>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>