<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<style>
.form-container {
	max-width: 600px;
	margin: 50px auto;
	padding: 20px;
	background-color: #f8f9fa;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-label {
	font-weight: bold;
}

.form-control, .btn, .input-group {
	margin-top: 10px;
}

.input-group {
	display: flex;
	align-items: center;
}

#boardContent {
	resize: none;
	height: 300px;
}

.icon {
	width: 60px;
	height: 60px;
}
</style>

<div class="form-container">
	<div class="title d-flex align-items-center">
		<img src="../resources/image/siren.png" alt="신고 아이콘" class="icon">
		<h2 class="me-3">신고하기</h2>

	</div>
	<div class="mb-3">
		<label for="boardTitle" class="form-label">신고글 제목</label> <input
			type="text" class="form-control" id="boardTitle"
			placeholder="신고 제목을 입력해주세요.">
	</div>
	<div class="mb-3">
		<label for="boardContent" class="form-label">내용</label>
		<textarea class="form-control" id="boardContent" rows="5"
			placeholder="공개된 게시물로 인해 명예훼손 또는 사생활 침해가 발생하면 명예훼손 신고를 통해 해당 게시물의 차단을 요청할 수 있습니다.

신고는 확인 후 조치되며, 거짓 정보 제공은 법적 책임을 수반할 수 있습니다.

개인정보는 신고 처리 목적 외에 사용되지 않습니다.

신고는 관련 부서에서 신속히 처리되며, 신고 접수 후 처리 상태를 추적할 수 있습니다."></textarea>
	</div>
	<div class="input-group mb-3">
		<input type="file" class="form-control" id="inputGroupFile04"
			aria-describedby="inputGroupFileAddon04" aria-label="Upload">
		<button class="btn btn-outline-secondary" type="button"
			id="inputGroupFileAddon04">확인</button>
	</div>
	<div class="d-grid gap-2">
		<button class="btn btn-outline-danger" type="button">신고하기</button>
	</div>
</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
