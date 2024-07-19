function fn_fboardForm(isLogOn, freeboard, loginForm) {
	
    if (isLogOn == true) {
        location.href = freeboard;
    } else {
        alert("로그인 후 글쓰기가 가능합니다.");
        location.href = loginForm + '?action=/member/loginForm.do';
    }
}
function readImage(input, num){
	if(input.files && input.files[0]){
		let reader = new FileReader();
		reader.onload = function(e){
			$('#preview' + num).attr('src',e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}else {
		$('#preview').attr('src','#'); // 이미지 선택 x -> src 속성에 # 값을 넣는다.
	}
}






//글 수정하기 위해 글 정보 활성화
function fn_enable(obj) {
		document.getElementById("div_button_modify").style.display = "block";
		document.getElementById("div_button").style.display = "none";
		document.getElementById("boardTitle").disabled = false;
		document.getElementById("noticeContent").disabled = false;
		 let imgName=document.querySelectorAll(".id_imgFile");
		if (imgName != null) {
			for(let i=0; i<imgName.length; i++){				
	         	imgName[i].disabled=false;
			}
		}
	}
	   
	// 글 수정 반영하기
	function fn_modify_fboard(obj) {
		obj.action = "/freeboard/modFboard.do";
		obj.submit();
	}

	// 글 상세보기 전환(취소)
	function toList(obj) {
		obj.action = "/freeboard/viewfboard.do";
		obj.submit();
	}

	// 리스트로 돌아가기
	function backToList(obj) {
		obj.action = "/freeboard/fboardList.do";
		obj.submit();
	}

	// 삭제하기
	function fn_remove_fboard(url, freeNo) {
	   

	    // 동적으로 폼 태그를 생성
	    let del_form = document.createElement("form");
	    del_form.setAttribute("action", url);
	    del_form.setAttribute("method", "post");

	    let freeNoInput = document.createElement("input"); // 동적으로 input 태그 생성
	    freeNoInput.setAttribute("type", "hidden");
	    freeNoInput.setAttribute("name", "freeNo");
	    freeNoInput.setAttribute("value", freeNo);
	    
	    del_form.appendChild(freeNoInput);
	    document.body.appendChild(del_form); // del_form을 추가해서 body안에 form태그가 두개가된다.

	    // 폼 제출
	    del_form.submit();
	}

