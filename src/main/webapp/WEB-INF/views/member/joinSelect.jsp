<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<div class="container my-5">
  <div class="p-5 text-center bg-body-tertiary rounded-3">
    <img src="/images/catgym.png" alt="이미지 설명" class="mt-4 mb-3" style="color: var(--bs-indigo); width: 100px; height: 100px;">
    <h1 class="text-body-emphasis">EASYGYM</h1>
    <p class="col-lg-8 mx-auto fs-5 text-muted">
      이지짐 회원가입 페이지에 오신 것을 환영합니다! 주변 헬스장을 검색하거나, 헬스장을 등록해보세요!
    </p>
    <div class="d-inline-flex gap-2 mb-5">
      <a href="/member/memJoin.do" class="btn btn-primary btn-lg px-4 rounded-pill" role="button">
        일반회원가입
      </a>
      <a href = "/member/operJoin.do" class="btn btn-outline-secondary btn-lg px-4 rounded-pill" type="button">
        사업자회원가입
      </a>
    </div>
  </div>
</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>
