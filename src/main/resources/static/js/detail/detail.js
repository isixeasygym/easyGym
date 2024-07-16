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
               $('.control_button').removeClass('active');
               $('.control_button').eq(index-1).addClass('active');  //5. 설명도 -1해줘야함.
               $('.slider_text').hide();
               $('.slider_text').eq(index-1).fadeIn('slow');  //5. 설명도 -1해줘야함.
           }
});

function autoResizeTextarea() {
    const textareas = document.querySelectorAll('.auto-resize-textarea');

    textareas.forEach(textarea => {
        textarea.style.height = 'auto'; // 높이를 auto로 설정하여 기존 높이를 초기화
        
        // scrollHeight로 콘텐츠의 전체 높이를 구하고, 그 값을 textarea의 높이로 설정
        textarea.style.height = `${textarea.scrollHeight}px`;
    });
}

// 페이지 로드 후 초기화 작업
window.addEventListener('load', autoResizeTextarea);

// textarea에 입력이 있을 때마다 자동으로 높이를 조절
document.querySelectorAll('.auto-resize-textarea').forEach(textarea => {
    textarea.addEventListener('input', autoResizeTextarea);
});