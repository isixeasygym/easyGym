<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<link rel="stylesheet" href="/css/member/join.css">

</div>
<div class="form-container">
	<form name="join" id="form" class="form" method="post" action="/member/memJoin.do">
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
					placeholder="비밀번호를 입력해주세요." required name="memberPwd"
					id="memberPWd" tabindex="2" /> <br> <span id="pwError"></span>
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
		<!--  <input type="button" value="본인인증" class="btnPrimary" id="mailCheckBtn"> -->

		<!--<div class="input-box">
            <div class="column">
                <input type="text" placeholder="인증번호 6자리를 입력해 주세요." id="mailCheckInput" disabled="disabled" maxlength="6" size="30" required />
                <input type="button" class="specialBtn" id="mailCheck" value="확인">
            </div>
            <br>
            <span id="mailCheckError"></span>
        </div> -->
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

		<button type="submit" id="join" value="Join"
			class="btn btn-success btn-block" onclick="javascript:checkJoin()">가입하기</button>
	</form>
</div>

<script src="/js/member/member.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
<script>
function combineEmail() {
    var email1 = document.getElementById('memberEmail1').value;
    var email2 = document.getElementById('memberEmail2').value;
    var fullEmail = email1 + email2;
    document.getElementById('memberEmail').value = fullEmail;

    console.log("완성된 이메일: " + fullEmail); // 결합된 이메일 확인
}
</script>


<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
