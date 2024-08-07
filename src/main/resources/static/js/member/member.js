function check(f){
		if(f.memberId.value==""){
			alert("아이디를 입력하세요.");
			f.memberId.focus();
			return false;
		}else if(f.memberPwd.value==""){
			alert("비밀번호를 입력하세요.");
			f.memberPwd.focus();
			return false;
		}
		return true;
	}


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
		} /**  else if (!regID.test(idTag.value)) {
		 alert("8~15사이의 아이디를 입력하십시오");
		 } */
		else if (document.join.memberPwd.value == "") {
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

	//ID 중복 확인
	//id를 입력할 수 있는 input text 영역을 벗어나면 동작한다.
	$("#memberId").on("focusout", function() {

		var memberId = $("#memberId").val();

		if(memberId == '' || memberId.length == 0) {
			$("#check").css("color", "red").text("공백은 ID로 사용할 수 없습니다.");
			return false;
		}

		//Ajax로 전송
		$.ajax({
			url: '/member/checkId.do',
			data: {
				memberId: memberId
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
					$("#memberId").val('');
				}
			}
		});
	}); //End Ajax



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

	mailCheckBtn.addEventListener("click", ()=>{
		const email = $('#memberEmail1').val() + $('#memberEmail2').val(); // 이메일 주소 값 가져오기
		console.log("완성된 이메일" + email); // 이메일 확인
		const checkInput = $('.mail-check-input');
		
		$.ajax({
			type : 'get',
			url : '/member/mailCheck?email='+email, // Get방식이라 Url뒤에 email을 붙힘
			success : function(data){
				console.log("data : " + data);
				checkInput.attr('disabled',false);
				code = data;
				alert('인증번호가 전송되었습니다.');
			}
		});
	});
	
	$('.mail-check-input').blur(function(){
		const inputCode = $(this).val();
		const $resultMsg = $('#mail-check-warn');
		
		if(inputCode === code){
			$resultMsg.html('인증번호가 일치합니다.');
			$resultMsg.css('color','green');
			$('#mail-Check-Btn').attr('disabled',true);
			$('#userEamil1').attr('readonly',true);
			$('#userEamil2').attr('readonly',true);
			$('#userEmail2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
	         $('#userEmail2').attr('onChange', 'this.selectedIndex = this.initialSelect');
		}else{
			$resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
			$resultMsg.css('color','red');
		}
	});