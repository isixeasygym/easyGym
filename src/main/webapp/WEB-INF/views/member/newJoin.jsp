<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/css/joinpage/join.css">
<link rel="sytlesheet" href="/js/member/member.js">
<form name="join" id="form" class="form">
	<div class="column">
		<div class="input-box">
			<label>아이디</label> <input type="text" name="memberId"
				autocomplete="off" id="memberId" tabindex="1"
				placeholder="아이디를 입력해주세요." required /> <br> <span id="check"></span>
		</div>
		<div class="input-box">
			<label>&nbsp;</label> <input type="button" value="중복확인"
				id="duplicateBtn" onclick="checkId(this.form)">
		</div>
	</div>

	<div class="column">
		<div class="input-box">
			<label>비밀번호</label> <input type="password"
				placeholder="비밀번호를 입력해주세요." required name="memberPwd" id="memberPWd"
				tabindex="2" /> <br> <span id="pwError"></span>
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
				name="memberName" autocomplete="off" tabindex="4" required />
		</div>
	</div>

	<div class="input-box">
		<label>Email</label>
		<div class="flex_container">
			<div class="column">
				<input type="text" placeholder="이메일을 입력해 주세요" name="memberEmail"
					id="memberEmail" tabindex="6" required />
				<div class="select-box">
					<select class="emailControl" name="memberEmail2" id="memberEmail2">
						<option>이메일 선택</option>
						<option>@naver.com</option>
						<option>@daum.net</option>
						<option>@gmail.com</option>
						<option>@hanmail.com</option>
						<option>@yahoo.co.kr</option>
					</select>
				</div>
				<input type="button" value="본인인증" class="btnPrimary"
					id="mailCheckBtn">
			</div>
		</div>
		<div class="input-box">
			<div class="column">
				<input type="text" placeholder="인증번호 6자리를 입력해 주세요."
					id="mailCheckInput" disabled="disabled" maxlength="6" size="30"
					required /> <input type="button" class="specialBtn" id="mailCheck"
					value="확인">
			</div>
			<br> <span id="mailCheckError"></span>
		</div>
		<div class="column">
			<div class="input-box">
				<label>전화번호</label> <input type="text" placeholder="전화번호를 입력해 주세요."
					name="memberPhone" tabindex="7" required />
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
					<label for="emailAgree">E-mail 수신 동의</label> <input type="checkbox"
						id="agree" name="uuserEcheck" value="1" tabindex="10">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label
						for="smsAgree">SMS 수신 동의</label> <input type="checkbox" id="agree"
						name="uuserScheck" value="1" tabindex="11">
				</div>
			</div>
		</div>
	</div>
	</div>
	<button id="join" value="Join" disabled="disabled"
		onclick="javascript:checkJoin()">가입하기</button>
	<!--  <button  onclick="document.join.reset()" tabindex="12" id="reset">Reset</button> -->
</form>