<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="container mt-5">
	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="card p-4 border border-light">
				<form class="text-center mb-3" action="/member/login.do"
					onsubmit="return check(this)" method="post">
					<img class="mb-4" src="/images/member/user.png" alt=""
						width="72" height="72">
					<h1 class="h3 mb-3 fw-normal">로그인</h1>
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="memberId"
							name="memberId"> <label for="floatingInput">아이디입력</label>
					</div>
					<div class="form-floating mb-3">
						<input type="password" class="form-control" id="memberPwd"
							name="memberPwd"> <label for="floatingPassword">비밀번호입력</label>
					</div>
					<div class="row justify-content-between mb-3">
						<div class="col-md-6 text-start">
							<small class="d-block mb-2"><a href="#">아이디 · 비밀번호 찾기</a></small>
							<small class="d-block"><a href="joinSelect">회원가입</a></small>
						</div>
					</div>
					<button class="btn btn-primary w-100 py-2" type="submit">로그인</button>
					<div class="wrap">
							<a class="kakao"href="https://kauth.kakao.com/oauth/authorize?client_id=3c843cca4013634dd38d454b2948d9de&redirect_uri=http://localhost:8090/kakao-login&response_type=code">
							<img src="/images/member/kakao_login_medium_wide.png" style="width: 100%;">
							</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
