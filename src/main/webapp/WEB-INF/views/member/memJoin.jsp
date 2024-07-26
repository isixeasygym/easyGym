<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="/css/member/join.css">
</div>
<div class="form-container">
	<form name="join" id="register_form" class="form" method="post"
		action="/member/addMember.do">
		<div class="column">
			<div class="input-box">
				<label for="memberId"><small>아이디</small></label> <input type="text"
					name="memberId" autocomplete="off" id="memberId" tabindex="1"
					placeholder="아이디를 입력해주세요." required autofocus /> <br> <span
					id="check"></span>
			</div>
		</div>
		<div class="column">
			<div class="input-box">
				<label>비밀번호</label> <input type="password"
					placeholder="비밀번호를 입력해주세요." required name="memberPwd"
					id="memberPwd" tabindex="2" /> <br> <span id="pwError"></span>
			</div>
			<div class="input-box">
				<label>비밀번호 확인</label> <input type="password"
					placeholder="비밀번호를 재입력해 주세요." required name="repw" id="repw"
					tabindex="3" /> <br> <span id="repwError"></span>
			</div>
		</div>
		<div class="column">
			<div class="input-box">
				<label>이름</label> <input type="text" placeholder="이름을 입력해 주세요."
					name="memberName" autocomplete="off" tabindex="4" required /> <br>
			</div>
		</div>

		<div class="input-box">
			<label>Email</label>
			<div class="flex_container">
				<div class="column">
					<input type="text" placeholder="이메일을 입력해 주세요" name="memberEmail1"
						id="memberEmail1" tabindex="6" required />
					<div class="select-box">
						<select class="emailControl" name="memberEmail2" id="memberEmail2"
							required>
							<option value="">이메일 선택</option>
							<option value="@naver.com">@naver.com</option>
							<option value="@daum.net">@daum.net</option>
							<option value="@gmail.com">@gmail.com</option>
							<option value="@hanmail.com">@hanmail.com</option>
							<option value="@yahoo.co.kr">@yahoo.co.kr</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" name="memberEmail" id="memberEmail">
		<button type="submit" id="join" value="Join"
			class="btn btn-success btn-block" onclick="combineEmail()">이메일
			입력완료</button>
		<div class="column">
			<div class="input-box">
				<label>전화번호</label> <input type="text" placeholder="전화번호를 입력해 주세요."
					name="memberPhone" tabindex="7" required />
			</div>
		</div>

		<div class="column">
			<div class="input-box">
				<label>주소</label> <input type="text" id="sample6_postcode"
					name="memberAddr" placeholder="우편번호"> <input type="button"
					onclick="sample6_execDaumPostcode()" value="우편번호 찾기"> <br>
				<input type="text" id="sample6_address" name="memberPost"
					placeholder="주소"> <br> <input type="text"
					id="sample6_detailAddress" placeholder="상세주소"> <input
					type="text" id="sample6_extraAddress" placeholder="참고항목">
			</div>
		</div>

		<div class="gender-box">
			<h3>성별</h3>
			<div class="gender-option">
				<div class="gender">
					<label for="check-male" class="gender_text">남성</label> <input
						type="radio" id="check-male" name="memberGender" id="male"
						value="1" tabindex="8" checked />
				</div>
				<div class="gender">
					<label for="check-female" class="gender_text">여성</label> <input
						type="radio" id="check-female" name="memberGender" id="female"
						value="2" tabindex="9" />
				</div>
			</div>
		</div>

		<div class="agree-box">
			<h3>광고성 수신 동의</h3>
			<div class="agree-option">
				<div class="agree">
					<label for="memberMarketing">마케팅정보 수신 동의</label> <input
						type="checkbox" id="agree" name="memberMarketing" value="1"
						tabindex="10">
				</div>
			</div>
		</div>

		<input type="hidden" name="memberState" value="1">
		<button type="submit" id="join" value="Join"
			class="btn btn-success btn-block" onclick="javascript:checkJoin()">가입하기</button>
	</form>
</div>

<script src="/js/member/member.js"></script>