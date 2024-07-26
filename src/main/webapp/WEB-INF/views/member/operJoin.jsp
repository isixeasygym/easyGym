<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="/css/member/operJoin.css">
</div>
<div class="form-container">
	<form name="opJoin" id="form" class="form" method="post"
		action="/member/operJoin.do" onsubmit="combineBizNum()">
		<img src="/images/member/fitness.png" class="img-fluid"
			alt="Fitness Image" />

		<div class="input-box">
			<label for="operatorId"><small>아이디</small></label> <input type="text"
				name="operatorId" autocomplete="off" id="operatorId" tabindex="1"
				placeholder="아이디를 입력해주세요." required autofocus /> <br>
			<span id="check"></span>
		</div>
		<div class="column">
			<div class="input-box">
				<label>비밀번호</label> <input type="password"
					placeholder="비밀번호를 입력해주세요." required name="operatorPwd"
					id="operatorPwd" tabindex="2" /> <span id="pwError"></span>
			</div>


			<div class="input-box">
				<label>비밀번호 확인</label> <input type="password"
					placeholder="비밀번호를 재입력해주세요." required name="repw" id="repw"
					tabindex="3" /> <span id="repwError"></span>
			</div>
		</div>

		<div class="input-box">
			<label>이름</label> <input type="text" placeholder="이름을 입력해주세요."
				name="operatorName" autocomplete="off" tabindex="4" required />
		</div>

		<div class="input-box">
			<label>Email</label>
			<div class="flex_container">
				<div class="column">
					<input type="text" placeholder="이메일을 입력해 주세요" name="operatorEmail1"
						id="operatorEmail1" tabindex="6" required />
				</div>
				<div class="column">
					<select class="emailControl" name="operatorEmail2"
						id="operatorEmail2" required>
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

		<input type="hidden" name="operatorEmail" id="operatorEmail">

		<button type="button" class="btn btn-success" onclick="combineEmail()">이메일
			입력완료</button>

		<!--
        <div class="input-box">
            <div class="column">
                <input type="text" placeholder="인증번호 6자리를 입력해 주세요." id="mailCheckInput" disabled="disabled" maxlength="6" size="30" required />
                <input type="button" class="specialBtn" id="mailCheck" value="확인">
            </div>
            <span id="mailCheckError"></span>
        </div>
        -->

		<div class="input-box">
			<label>전화번호</label> <input type="text" placeholder="전화번호를 입력해 주세요."
				name="operatorPhone" tabindex="7" required />
		</div>

		<div class="input-box">
			<label for="bizNum1">사업자등록번호</label> <input type="text"
				id="operatorResNo" name="operatorResNo" placeholder=""
				maxlength="10" class="form-control" />
		</div>

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

		<button type="submit" id="join" value="Join" class="btn btn-success"
			onclick="javascript:checkJoin()">가입하기</button>
	</form>
</div>
<script src="/js/member/member.js"></script>

<script>
    // ID 중복 확인
    $("#operatorId").on("focusout", function() {
        var operatorId = $("#operatorId").val();

        if (operatorId === '' || operatorId.length === 0) {
            $("#check").css("color", "red").text("공백은 ID로 사용할 수 없습니다.");
            return false;
        }

        // Ajax로 전송
        $.ajax({
            url: '/operator/checkId.do',
            data: {
                opeatorId: operatorId
            },
            type: 'POST',
            dataType: 'json',
            success: function(result) {
                if (result === true) {
                    $("#check").css({
                        "color": "blue",
                        "font-size": "10px"
                    }).text("사용 가능한 ID 입니다.");
                } else {
                    $("#check").css({
                        "color": "red",
                        "font-size": "10px"
                    }).text("사용 불가능한 ID 입니다.");
                    $("#operatorId").val('');
                }
            }
        });
    }); // End Ajax
</script>

<script>
    function combineEmail() {
        var email1 = document.getElementById('operatorEmail1').value;
        var email2 = document.getElementById('operatorEmail2').value;
        var fullEmail = email1 + email2;
        document.getElementById('operatorEmail').value = fullEmail;
    }
</script>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>