let chatbot_chk=false;
function fn_chatbot() {
    //if(chatbot_chk==true) {  //boolean 값이 있기 때문에 true는 안써도 기본으로 적용됨
    if(chatbot_chk) {
        $("#chatbot_frame").css({
            display:"none"
        });
        chatbot_chk=false;
        $('.chatbot>a').html(" ☞ 챗봇에게 물어보기");
    }else {
        $("#chatbot_frame").css({
            display:"block"
        });
        chatbot_chk=true;
        $('.chatbot>a').html(" ☞ 챗봇감추기");
    }
}