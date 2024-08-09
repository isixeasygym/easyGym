<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
	#main{
	background-color: #f0f0f0;
	}
</style>
  <article id = "main">
  <header class = "special container">
  	<span class="icon solid fa-envelope"></span>
  	<h1>Login</h1>
  	<p>로그인 페이지</p>
  	</header>
  	<!-- One -->
	<section class="wrapper style4 special container medium">

		<!-- Content -->
			<div class="content">
				<form class="text-center mb-3" action="/member/login.do"
               onsubmit="return check(this)" method="post">
					<div class="row gtr-50">
						<div class="col-12">
						<input type="text" name="memberId" id="memberId" placeholder="아이디를 입력해주세요." />	
						</div>
							
							<div class="col-12">		
							<input type="password" name="memberPwd" placeholder="비밀번호를 입력해주세요." id="memberPwd" autocomplete="current-password" />
						</div>
					</div>
					&nbsp;
                     <div class="joinSubmit"><a href="${contextPath}/member/memJoin.do">회원가입</a></div>
                     &nbsp;
               <button class="btn btn-primary w-100 py-2" type="submit">로그인</button>
			   <a href="javascript:fn_kakao()">
	   				<img src="/images/member/kakao_login_large_wide.png"
	   				width="100%" height="45px" alt="카카오 로그인 버튼">
	   			</a>
				</form>
			</div>
	</section>
  </article>
  <script src="/js/member/member.js"></script>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
