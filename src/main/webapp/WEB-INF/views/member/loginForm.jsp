<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<script src="/js/member/kakao.js"></script>
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
                     <small class="d-block"><a href="${contextPath}/member/joinSelect.do">회원가입</a></small>
                  </div>
               </div>
               <button class="btn btn-primary w-100 py-2" type="submit">로그인</button>
			   <a href="javascript:fn_kakao()">
	   				<img src="/images/member/kakao_login_large_wide.png"
	   				width="100%" height="45px" alt="카카오 로그인 버튼">
	   			</a>
            </form>
         </div>
      </div>
   </div>
</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>