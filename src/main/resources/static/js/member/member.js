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
