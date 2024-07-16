function fn_noticeForm(isLogOn, noticeForm, loginForm) {
	
    if (isLogOn == true) {
        location.href = noticeForm;
    } else {
        alert("로그인 후 글쓰기가 가능합니다.");
        location.href = loginForm + '?action=/admin/noticeForm.do';
    }
}

function toggleDropdown() {
            var dropdown = document.getElementById('customSelectDropdown');
            dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
        }

function selectOption(value) {
    var button = document.querySelector('.custom-select-btn');
    button.textContent = value === 1 ? '필독' : '일반';
    document.getElementById('customSelectDropdown').style.display = 'none';

	    let noticeCategory = document.createElement("input");
		var formId = document.getElementById('formId');
		noticeCategory.setAttribute("type","hidden");
		noticeCategory.setAttribute("name","noticeCategory");
		noticeCategory.setAttribute("value",value);
		formId.appendChild(noticeCategory);
}

document.addEventListener('click', function(event) {
    var isClickInside = document.querySelector('.btn-group').contains(event.target);
    if (!isClickInside) {
        document.getElementById('customSelectDropdown').style.display = 'none';
    }
});



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
	
	
	// 여러개의 이미지 추가
	let count = 1;
	function fn_addFile(){
		$('#dock_file').append('<input type="file" name="imgFile' + count + '"><br>')
		count++;
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
		function fn_modify_notice(obj) {
			obj.action = "/admin/modNotice.do";
			obj.submit();
		}

		// 글 상세보기 전환(취소)
		function toList(obj) {
			obj.action = "/admin/viewNotice.do";
			obj.submit();
		}

		// 리스트로 돌아가기
		function backToList(obj) {
			obj.action = "/admin/listNotice.do";
			obj.submit();
		}

		// 삭제하기
		function fn_remove_notice(url, noticeNo) {
			// 동적으로 폼 태그를 생성
			let del_form = document.createElement("form");
			del_form.setAttribute("action", url);
			del_form.setAttribute("method", "post");
			let noticeNoInput = document.createElement("input"); // 동적으로 input 태그 생성
			noticeNoInput.setAttribute("type", "hidden");
			noticeNoInput.setAttribute("name", "noticeNo");
			noticeNoInput.setAttribute("value", noticeNo);
			del_form.appendChild(noticeNoInput);
			document.body.appendChild(del_form); // del_form을 추가해서 body안에 form태그가 두개가된다.
			del_form.submit();
		}

