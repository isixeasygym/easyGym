$(document).ready(function() {
    $(".favorite-button").click(function(event) {
		//alert("안녕")ㅣ
		
        var companyId = $(this).children('.companyId').val();
        var userId = $('.userId').val(); // 형제 input 필드의 값 가져오기
        console.log("companyId: " + companyId);
        console.log("userId: " + userId);
   
		
        $.ajax({
            type: "GET",
            url: "/addFavorite",
			async:false,
            data: { 
                companyId: companyId, 
                userId: userId 
            },
            success: function(data) {
                if (data == "insert") {
                    alert("찜 목록에 추가되었습니다.");
                } else if (data == "delete") {
                    alert("찜 목록에서 삭제되었습니다.");
                } else if (data.startsWith("redirect:")) {
                    // 리다이렉트 처리
                    var redirectUrl = data.substring(9); // "redirect:" 이후의 문자열을 가져옴
                    window.location.href = redirectUrl; // 리다이렉트 수행
                } else {
                    alert("알 수 없는 오류가 발생했습니다.");
                }
            },
			
            error: function(xhr, status, error) {
                console.error("Error: " + error);
                alert(xhr+status+"오류가 발생했습니다."+error);
            }
        });
		event.stopPropagation();
	});
});
function goToDetail(detailNo){
	let detailForm = document.createElement('form');
	let inputData = document.createElement('input');
	inputData.setAttribute('type', 'hidden');
	inputData.setAttribute('name', 'detailNo');
	inputData.setAttribute('value', detailNo);
	detailForm.appendChild(inputData);
	document.body.appendChild(detailForm);
	detailForm.setAttribute('action', '/detail/detail.do');
	detailForm.setAttribute('method', 'get');
	detailForm.submit();
}
