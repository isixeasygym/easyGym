<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<style>
  .email-group {
    display: flex; /* Flexbox를 사용하여 가로 배치 */
    align-items: center; /* 세로 가운데 정렬 */
    gap: 10px; /* 요소 간의 간격 설정 */
  }

  .email-input,
  .email-select {
    flex: 1; /* Flexbox로 크기 조절 */
  }

  .email-select {
    max-width: 200px; /* 드롭다운의 최대 너비 설정 */
  }

  .input-group-addon {
    margin-left: 10px; /* 버튼과 입력 필드 간의 여백 */
  }

  .address-group {
    display: flex; /* Flexbox를 사용하여 가로 배치 */
    align-items: center; /* 세로 가운데 정렬 */
    gap: 10px; /* 요소 간의 간격 설정 */
  }

  .address-group input[type="text"] {
    flex: 1; /* Flexbox로 입력 필드 크기 조절 */
    margin-right: 10px; /* 버튼과 입력 필드 간의 간격 설정 */
  }

  .address-group input[type="button"] {
    flex-shrink: 0; /* 버튼의 크기를 고정 */
  }
	*{
	background-color: #f0f0f0;
}
</style>
<article id="main">
  <header class="special container">
    <span class="icon solid fa-envelope"></span>
    <h1>Sign up</h1>
    <p>회원가입페이지입니다.</p>
  </header>
  <!-- One -->
  <section class="wrapper style4 special container medium">

    <!-- Content -->
    <div class="content">
      <form>
        <div class="row gtr-50">
          <div class="col-12">
            <input type="text" name="memberId" autocompleted="off" id="memberId"
              tabindex="1" placeholder="아이디를 입력해주세요." required autofocus />
            <br><span id="check"></span>
          </div>
          <div class="col-12">
            <input type="password" name="memberPwd" placeholder="비밀번호를 입력해주세요."
              required id="memberPwd" tabindex="2" />
            <br><span id="pwError"></span>
          </div>
          <div class="col-12">
            <input type="password" name="repw" placeholder="비밀번호를 재입력해주세요."
              required id="repw" tabindex="3" />
            <br><span id="pwError"></span>
          </div>
          <div class="col-12">
            <input type="text" name="memberName" placeholder="이름을 입력해주세요." autocomplete="off"
              tabindex="4" required />
          </div>
          <div class="col-12 email-group">
            <div class="email-input">
              <input type="text" name="memberEmail1" id="memberEmail1" tabindex="6"
                placeholder="이메일을 입력해주세요." required />
            </div>
            <div class="email-select">
              <select class="emailControl" name="memberEmail2" id="memberEmail2" required>
                <option value="">이메일 선택</option>
                <option value="@naver.com">@naver.com</option>
                <option value="@daum.net">@daum.net</option>
                <option value="@gmail.com">@gmail.com</option>
                <option value="@hanmail.com">@hanmail.com</option>
                <option value="@yahoo.co.kr">@yahoo.co.kr</option>
              </select>
            </div>
            <br>
            <input type="hidden" name="memberEmail" id="memberEmail">
            <div class="input-group-addon">
              <button type="button" class="button primary" id="mailCheckBtn">인증번호전송</button>
            </div>
            <div class="mail-check-box input-box" style="display:none;">
              <input class="mail-check-input" placeholder="인증번호 6자리를 입력해주세요" disabled="disabled" maxlength="6">
            </div>
            <span id="mail-check-warn"></span>
          </div>
          <div class="col-12">
            <div class="input-box">
              <div class="address-group">
                <input type="text" id="sample6_postcode" name="memberAddr" placeholder="우편번호">
                <input type="button" class="button primary" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
              </div>
              <br>
              <input type="text" id="sample6_address" name="memberPost" placeholder="주소">
              <br>
              <input type="text" id="sample6_detailAddress" placeholder="상세주소">

            </div>
          </div>
          <div class="col-12">
            <div class="gender-box">
              <div class="gender-option">
                <div class="gender">
                  <label for="check-male" class="gender_text">남성</label>
                  <input type="radio" id="check-male" name="memberGender" id="male" value="1" tabindex="8" checked />
                </div>
                <div class="gender">
                  <label for="check-female" class="gender_text">여성</label>
                  <input type="radio" id="check-female" name="memberGender" id="female" value="2" tabindex="9" />
                </div>
              </div>
            </div>
          </div>
          <div class="col-12">
            <div class="agree-box">
              <h3>광고성 수신 동의</h3>
              <div class="agree-option">
                <div class="agree">
                  <label for="memberMarketing">마케팅정보 수신 동의</label>
                  <input type="checkbox" id="agree" name="memberMarketing" value="1" tabindex="10">
                </div>
              </div>
            </div>
          </div>
          <input type="hidden" name="memberState" value="1">
          <div class="col-12">
            <ul class="buttons">
              <li><input type="submit" id="join" value="join" class="button primary" onclick="javascript:checkJoin()" /></li>
            </ul>
          </div>
        </div>
      </form>
    </div>
  </section>
</article>
<script src="/js/member/member.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
