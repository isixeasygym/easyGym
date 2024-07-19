//글 삭제
function deleteReview(){ 
		var reviewNo = $(this).children('.companyId').val();
       var userId = $('.userId').val(); // 형제 input 필드의 값 가져오기
       console.log("companyId: " + companyId);
       console.log("userId: " + userId);
		
        $.ajax({
            type: "POST",
            url: "/delete.do",
			async:false,
            data: { 
                companyId: companyId, 
                userId: userId 
            },
			success: function(data)			{
                if (data == "success") {
                    alert("해당 글은 삭제되었습니다.");
                } else if (data == "noLogin") {
                    alert("해당 글을 삭제하기 위해서는 로그인 정보가 필요합니다.");
					window.location.href='/member/loginForm.do';
                } else if(data == "noBuy") {
					alert("해당 글은 해당 업체 회원권을 구매하고 자신이 작성한 글만 삭제 가능합니다.")
				}
            },
            error: function() {
                alert("해당 글은 작성하신 글이 아닙니다.");
            }
        });
}
//글 등록하기

function writeSubmit() {
            // 입력된 값들을 변수에 저장
            var companyId = $('.companyId').val();
            var userId = $('.userId').val();
            var reviewComment = $('#myTextarea').val();
            var reviewRating = $("input[name='detailScope']:checked").val();
            var fileInput = $('#reviewImageName')[0].files[0];

            // FormData 객체 생성
            var formData = new FormData();
            formData.append('companyId', companyId);
            formData.append('userId', userId);
            formData.append('reviewComment', reviewComment);
            formData.append('reviewRating', reviewRating);
            if (fileInput) {
                formData.append('reviewImageName', fileInput);
            }

            // AJAX 요청
            $.ajax({
				async:false,
                enctype: 'multipart/form-data',
                cache: false,
                url: '/writeReview.do', // 요청을 보낼 URL
                type: 'POST', // HTTP 요청 메소드 (POST)
                data: formData, // 전송할 데이터 (FormData 객체)
                processData: false, // 데이터 처리 방식 설정 (FormData 사용 시 false)
                contentType: false, // 컨텐츠 타입 설정 (FormData 사용 시 false)
                success: function(response) {
                    if (response == "success") {
                        alert("작성하신 후기가 등록되었습니다.");
                    } else if (response == "noBuy") {
                        alert("후기 작성은 회원권을 구매하신 회원님만 가능합니다");
                    } else if (response == "noLogin") {
                        alert("해당 글을 삭제하기 위해서는 로그인 정보가 필요합니다.");
                        window.location.href = '/member/loginForm.do';
                    }
                },
                error: function(xhr, status, error) {
                    console.error("Error:", error);
                    console.error("Status:", status);
                    console.error("Response:", xhr.responseText);
                }
            });
        }
//찜 기능
$(document).ready(function() {
    $(".favorite-button").click(function(event) {
        var reviewNo = $(this).children('.reviewNo').val();
        var userId = $('.userId').val(); // 형제 input 필드의 값 가져오기
        console.log("companyId: " + companyId);
        console.log("userId: " + userId);
   
		
        $.ajax({
            type: "GET",
            url: "/addFavorite",
			async:false,
            data: { 
                reviewNo: reviewNo, 
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
$(function() {
           $('.slider_panel').append($('.slider_image').first().clone());  //마지막 5번째 사진 뒤에 1번째 사진을 복제해서 붙여둠
           $('.slider_panel').prepend($('.slider_image').eq(-2).clone());  //첫번째 사진 앞에 마지막 5번째 사진을 복제해서 붙여둠
           //let index=0;  //1. 원래는 0이면 첫번째 사진이 나오게 되는데
           let index=1;  //2. 마지막 5번 사진을 붙였으니 1로 바꿔야함.
           //moveSlider(index);
           $('.slider_panel').css('left',-900);  //4-1) 사이트 첫 화면에서 5번이 보이고 1번으로 나오는 화면을 없애기 위한 작업
           $('.slider_text').hide();  //4-2) 사이트 첫 화면에서 5번이 보이고 1번으로 나오는 화면을 없애기 위한 작업
           $('.slider_text').eq(0).show();  //4-3) 사이트 첫 화면에서 5번이 보이고 1번으로 나오는 화면을 없애기 위한 작업
           $('.control_button').click(function() {
               index=$(this).index();
               moveSlider(index+1);  //3. 아래 버튼도 +1을 해줌
           });

           $('.left_control').click(function() {
               if(index > 1 ) {
                   index--;
                   moveSlider(index);
               }else {
                   $('.slider_panel').css('left',-9900)
                   index=10;
                   moveSlider(index);
               }
           });

           $('.right_control').click(function() {
               if(index < 10) {  //4. 기존 : < 4 / 이미지 앞뒤로 추가하고는 < 5 로 바꿈
                   index++;
                   moveSlider(index);
               }else {
                   $('.slider_panel').css('left',0);
                   index=1;
                   moveSlider(index);
               }
           });

           //이미지 슬라이드 구현 함수 (아래 버튼)
           function moveSlider(index) {  //내가 만든 함수 이름(매개변수를 index로 받겠다)
               $('.slider_panel').animate({
                   left:-(index*900)
               },'slow');
               $('.control_button').removeClass('active');
               $('.control_button').eq(index-1).addClass('active');
               $('.slider_text').hide();  //설명글을 fadeout 안하고 바로 사라지게
               $('.slider_text').eq(index-1).fadeIn('slow');  //그럼 어떨 때 설명글을 보이게 하느냐? => eq(index)일 때 ~ 
           }

           //이미지 슬라이드 구현 함수 (좌우 버튼)
           function moveSlider(index) {
               $('.slider_panel').animate({
                   left:-(index*900)
               },'slow');
           }
});

document.addEventListener('DOMContentLoaded', (event) => {
    const textareas = document.querySelectorAll('.auto-resize-textarea');

    const adjustHeight = (textarea) => {
        textarea.style.height = 'auto'; // 높이 자동 설정
        textarea.style.height = `${textarea.scrollHeight}px`; // 실제 내용 높이로 설정
    };

    textareas.forEach(textarea => {
        textarea.addEventListener('input', () => adjustHeight(textarea));
        // 초기 높이 조절
        adjustHeight(textarea);
    });
});


