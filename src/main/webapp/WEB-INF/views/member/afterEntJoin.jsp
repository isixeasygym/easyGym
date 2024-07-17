<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<style>
.content-container {
	width: 700px; /* 원하는 너비 */
	height: auto; /* 비율을 유지하며 높이를 자동 조정 */
	margin-top: 20px; /* 헤더와의 여백 */
	display: flex;
	justify-content: center; /* 가로 중앙 정렬 */
	align-items: center; /* 세로 중앙 정렬 */
	height: calc(100vh - 40px); /* 화면의 높이 - 헤더와 여백 */
}

.card {
	border: 1px solid #d3d3d3; /* 회색 보더 */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 약간의 그림자 */
	border-radius: 10px; /* 둥근 모서리 */
	padding: 20px; /* 내부 여백 추가 */
	width: 28rem; /* 카드의 너비를 넓게 설정 */
}

.welcome-image {
	width: 350px; /* 원하는 너비 */
	height: auto; /* 비율을 유지하며 높이를 자동 조정 */
	margin: 0 auto; /* 가운데 정렬 */
	display: block; /* 이미지를 블록 요소로 설정하여 가운데 정렬이 적용되도록 함 */
}

}
.card-body {
	text-align: center; /* 내용을 가운데 정렬 */
}
</style>

<div class="container content-container">
	<div class="card">
		<img src="../../resources/image/welcome1.png"
			class="card-img-top welcome-image" alt="...">
		<div class="card-body">
			<h5 class="card-title">사업자회원으로 가입해주셔서 감사합니다!</h5>
			<p class="card-text">사업자 등록이 완료되었으니 로그인을 하고 헬스장 플랫폼에 당신의 헬스장을
				등록해주세요.</p>
			<a href="login" class="btn btn-primary stretched-link">로그인하러가기</a>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
