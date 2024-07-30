<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="container mt-5">
   <div class="row justify-content-center">
      <div class="col-md-6">
         <div class="card p-4 border border-light">
            <form class="text-center mb-3" action="/member/operLogin.do" onsubmit="return check(this)" method="post">
               <img class="mb-4" src="/images/member/user.png" alt="로그인" width="72" height="72">
               <h1 class="h3 mb-3 fw-normal">로그인</h1>
               <div class="form-floating mb-3">
                  <input type="text" class="form-control" id="operatorId"
                     name="operatorId"> <label for="floatingInput">아이디입력</label>
               </div>
               <div class="form-floating mb-3">
                  <input type="password" class="form-control" id="operatorPwd"
                     name="operatorPwd"> <label for="floatingPassword">비밀번호입력</label>
               </div>
               <div class="row justify-content-between mb-3">
                  <div class="col-md-6 text-start">
                     <small class="d-block"><a href="/member/operJoin.do">회원가입</a></small>
                  </div>
               </div>
               <button class="btn btn-primary w-100 py-2" type="submit">로그인</button>
            </form>
         </div>
      </div>
   </div>
</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>

