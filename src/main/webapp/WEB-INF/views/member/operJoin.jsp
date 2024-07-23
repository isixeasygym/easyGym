<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="form-container">
	<form name="opJoin" id="form" class="form" method="post"
		action="/member/operJoin.do" onsubmit="combineBizNum()">
		<img src="/images/member/fitness.png" class="img-fluid"
			alt="Fitness Image" style="width: 100px; height: auto;">
		<div class="column">
			<div class="input-box">
				<label>아이디</label><input type="text" name="operatorId"
					autocomplete="off" id="operatorId" tabindex="1"
					placeholder="아이디를 입력해주세요." required /><br> <span id="check"></span>
			</div>
			<!-- 			<div class="input-box">
				<label>&nbsp;</label><input type="button" value="중복확인" id=duplicateBtn" onclick="checkOpId(this.form)">
				</div> -->

			<div class="column">
				<div class="input-box">
					<label>비밀번호</label> <input type="password"
						placeholder="비밀번호를 입력해주세요." required name="operatorPwd"
						id="memberPwd" tabindex="2" /><br> <span id="pwError"></span>
				</div>
				<div class="input-box">
					<label>비밀번호 확인</label> <input type="password"
						placeholder="비밀번호를 재입력해주세요." required name="repw" id="repw"
						tabindex="3" /> <br> <span id="repwError"></span>
				</div>
			</div>

			<div class="column">
				<div class="input-box">
					<label>이름</label> <input type="text" placeholder="이름을 입력해주세요."
						name="memberName" autocomplete="off" tabindex="4" required /><br>
				</div>
			</div>

			<div class="input-box">
				<label>Email</label>
				<div class="flex_container">
					<div class="column">
						<input type="text" placeholder="이메일을 입력해 주세요" name="memberEmail1"
							id="memberEmail1" tabindex="6" required />
						<div class="select-box">
							<select class="emailControl" name="memberEmail2"
								id="memberEmail2" required>
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
					<label>이름</label> <input type="text" placeholder="이름을 입력해주세요."
						name="memberName" autocomplete="off" tabindex="4" required /><br>
				</div>
			</div>


			<div class="column">
				<div class="input-box">
					<label for="bizNum1">사업자등록번호</label>
					<div class="input-group">
						<input type="text" id="bizNum1" name="bizNum1" placeholder=""
							maxlength="3" class="form-control" /> <span
							class="input-group-text">-</span> <input type="text"
							id="bizNum2_1" name="bizNum2_1" placeholder="" maxlength="2"
							class="form-control" /> <span class="input-group-text">-</span>
						<input type="text" id="bizNum2_2" name="bizNum2_2" placeholder=""
							maxlength="6" class="form-control" /> <input type="hidden"
							name="operatorResNo" id="operatorResNo" />
					</div>
				</div>
			</div>

			<div class="column">
				<div class="input-box">
					<label for="inputGroupFile04">사업자등록증</label>
					<div class="input-group">
						<input type="file" id="operatorImgName" name="operatorImgName"
							class="form-control" aria-describedby="inputGroupFileAddon04"
							aria-label="Upload" />
						<button class="btn btn-outline-secondary" type="button"
							id="inputGroupFileAddon04">업로드</button>
					</div>
				</div>
			</div>

			<button type="submit" id="join" value="Join"
				class="btn btn-success btn-block" onclick="javascript:checkJoin()">가입하기</button>
	</form>
</div>

<script>
	function combineBizNum() {
		var bizNum1 = document.getElementsByName('bizNum1')[0].value;
		var bizNum2_1 = document.getElementsByName('bizNum2_1')[0].value;
		var bizNum2_2 = document.getElementsByName('bizNum2_2')[0].value;
		var fullBizNum = bizNum1 + '-' + bizNum2_1 + '-' + bizNum2_2;
		document.getElementById('operatorResNo').value = fullBizNum;
	}
</script>
<!-- <script>
  function redirectToPage() {
    // 여기서 'redirect.jsp'는 이동할 JSP 페이지의 경로입니다.
    window.location.href = 'afterEntJoin.do';
  }
</script> -->

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
