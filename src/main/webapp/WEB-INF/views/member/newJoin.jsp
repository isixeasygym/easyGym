<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<link rel="stylesheet" href="/css/joinpage/join.css">
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
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>


<script>

   const idTag = document.getElementById('memberId');
   const pwTag = document.getElementById('memberPwd');
   const repwTag = document.getElementById('repw');
   const emailTag = document.getElementById('memberEmail');
   const regID = /^[A-Za-z0-9]{8,15}$/;
   const regPW = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
   //const regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
   const form = document.getElementById('form');
   const pwError = document.getElementById('pwError');
    const repwError = document.getElementById('repwError');
   const emailError = document.getElementById('emailError');
   const mailCheckBtn = document.getElementById('mailCheckBtn');
   const mailCheckInput = document.getElementById('mailCheckInput');
   const emailTag2 = document.getElementById("memberEmail2");
   const resultMsg = document.getElementById('mailCheckError');
   const mailCheck = document.getElementById('mailCheck');
   let code;
   
   
   function checkJoin() {
      if (document.join.memberId.value == "") {
         alert("아이디를 입력하십시오!");
      } else if (!regID.test(idTag.value)) {
         alert("8~15사이의 아이디를 입력하십시오");
      } else if (document.join.memberPwd.value == "") {
         alert("비밀번호를 입력하십시오!");
      } else if (document.join.repw.value == "") {
         alert("비밀번호확인을 입력하십시오!");
      } else if (document.join.memberName.value == "") {
         alert("이름을 입력하십시오!");
      } else if (document.join.memberEmail.value == "") {
         alert("이메일을 입력하십시오!");
      } else if (document.join.memberPhone.value == "") {
         alert("전화번호를 입력하십시오!");
      } else if (!(document.join.memberGender.value == 1 || document.join.uuserGender.value == 2)) {
         alert("성별을 선택하십시오!");
      } else if (document.join.memberPwd.value != document.join.repw.value) {
         alert("비밀번호가 일치하지 않습니다.");
      } else {
         document.join.submit();
      }
   }
   // 아이디
   function checkId(f) {
      if (f.memberId.value == "") {
         alert("아이디를 입력하십시오!");
         return;
      }
      if (!regID.test(idTag.value)) {
         alert("8~15사이의 아이디를 입력하십시오");
         return;
      }
    
      var url = "${pageContext.request.contextPath}/uuser/checkId";
      var param = "id=" + encodeURIComponent(f.uuserId.value);
    
      sendRequest(url, param, resultFn, "POST");
   } 
   function resultFn() {
      if (xhr.readyState == 4 && xhr.status == 200) {
         //도착된 데이터를 읽어오기
         var data = xhr.responseText;
         const join = document.getElementById("join");
         const check = document.getElementById('check');
         const id = document.getElementById('uuserId');
    
         check.innerText = '';
    
         if (data === '사용 가능한 ID입니다') {
            check.style.cssText = "color: blue; font-size: 10px;";
            join.disabled = false;
         } else {
            check.style.cssText = "color: red; font-size: 10px;";
            join.disabled = true;
         }
         
         check.innerText = data;
      }
   }
   
   // 비밀번호
   
   pwTag.addEventListener("focus",() =>{
      pwError.innerHTML = "'숫자', '문자', '특수문자' 무조건 1개 이상, 비밀번호 '최소 8자이상 작성해주세요";
      pwError.style.cssText = "color: red; font-size: 10px;";
   }); 
      
   pwTag.addEventListener("blur",() => {
      if(pwTag.value == ""){
         pwError.innerHTML = "";
      }else if(!regPW.test(pwTag.value)){
         pwError.innerHTML = "'숫자', '문자', '특수문자' 무조건 1개 이상, 비밀번호 '최소 8자이상 작성해주세요";
         pwError.style.cssText = "color: red; font-size: 10px;";
         //pwTag.focus();
      }else {
         pwError.innerHTML = "";
      }
   });
   
   // 비밀번호 확인
   
   repwTag.addEventListener("blur",() =>{
      if(pwTag.value == ""){
         repwError.innerHTML = "";
      }
      else if(pwTag.value != repwTag.value){
         repwError.innerHTML = "비밀번호가 일치하지 않습니다";
         repwError.style.cssText = "color: red; font-size: 10px;";
         
      }else{
         repwError.innerHTML = "비밀번호가 일치 합니다";
         repwError.style.cssText = "color: blue; font-size: 10px;";
      }
   });
   
   // 이메일 
   

   
   mailCheckBtn.addEventListener("click", ()=>{
      const memberEmail = $('#memberEmail').val() + $('#memberEmail2').val(); // 이메일 주소값 가져오기
      console.log("완성된 이메일" + memberEmail); // 이메일 확인
      
      
      $.ajax({
         type : 'post',
         url : '<c:url value="/uuser/mailCheck?email="/>' + email,
         success : function(data){
            console.log("data : " + data);
            code = data;
            alert("인증번호가 전송되었습니다.");
            mailCheckInput.disabled = false;
         }
      });
   });
   
   mailCheck.addEventListener("click", ()=>{
   
      if(mailCheckInput.value === code){
         resultMsg.innerHTML = "인증번호가 일치합니다";
         resultMsg.style.cssText = "color: blue; font-size: 10px;";
      }else{
         resultMsg.innerHTML = "인증번호가 불일치 합니다. 다시 확인해주세요";
         resultMsg.style.cssText = "color: red; font-size: 10px;";
      }
   });

   
</script>
