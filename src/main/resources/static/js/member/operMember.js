function check(f){
	if(f.operatorId.value==""){
		alert("아이디를 입력하세요.");
		f.operatorId.focus();
		return false;
	}else if(f.operatorPwd.value==""){
		alert("비밀번호를 입력하세요.");
		f.operatorPwd.focus();
		return false;
	}
	return true;
}


const idTag = document.getElementById('operatorId');
   const pwTag = document.getElementById('operatorPwd');
   const repwTag = document.getElementById('repw');
   const emailTag = document.getElementById('operatorEmail');
   const regID = /^[A-Za-z0-9]{8,15}$/;
   const regPW = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
   //const regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
   const form = document.getElementById('form');
   const pwError = document.getElementById('pwError');
    const repwError = document.getElementById('repwError');
   const emailError = document.getElementById('emailError');
   const mailCheckBtn = document.getElementById('mailCheckBtn');
   const mailCheckInput = document.getElementById('mailCheckInput');
   const emailTag2 = document.getElementById("operatorEmail2");
   const resultMsg = document.getElementById('mailCheckError');
   const mailCheck = document.getElementById('mailCheck');
   let code;
   
   
   function checkJoin() {
      if (document.join.operatorId.value == "") {
         alert("아이디를 입력하십시오!");
      } /**  else if (!regID.test(idTag.value)) {
         alert("8~15사이의 아이디를 입력하십시오");
      } */
	  	else if (document.join.operatorPwd.value == "") {
         alert("비밀번호를 입력하십시오!");
      } else if (document.join.repw.value == "") {
         alert("비밀번호확인을 입력하십시오!");
      } else if (document.join.operatorName.value == "") {
         alert("이름을 입력하십시오!");
      } else if (document.join.operatorEmail.value == "") {
         alert("이메일을 입력하십시오!");
      } else if (document.join.operatorPhone.value == "") {
         alert("전화번호를 입력하십시오!");
      } else if (document.join.operatorPwd.value != document.join.repw.value) {
         alert("비밀번호가 일치하지 않습니다.");
      } else {
         document.join.submit();
      }
   }
   
   //ID 중복 확인
      	//id를 입력할 수 있는 input text 영역을 벗어나면 동작한다.
      	$("#operatorId").on("focusout", function() {
      		
      		var operatorId = $("#operatorId").val();
      		
      		if(operatorId == '' || operatorId.length == 0) {
      			$("#check").css("color", "red").text("공백은 ID로 사용할 수 없습니다.");
      			return false;
      		}
      		
          	//Ajax로 전송
      		$.ajax({
      		    url: '/operator/checkId.do',
      		    data: {
      		        operatorId: operatorId
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
          	}); //End Ajax
   </script>
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
    
   function combineEmail() {
       var email1 = document.getElementById('operatorEmail1').value;
       var email2 = document.getElementById('operatorEmail2').value;
       var fullEmail = email1 + email2;
       document.getElementById('operatorEmail').value = fullEmail;

       console.log("완성된 이메일: " + fullEmail); // 결합된 이메일 확인
   }