window.onload = function() {
    let finalPrice = document.getElementById('finalPrice');   //최종 결제금액
    let subForm = document.getElementById('payCheck_form'); //submit 결제하기 버튼

    subForm.addEventListener('submit', handleSubmit);  //결제하기 버튼을 누를때 함수 호출
    //TODO DB에서 금액 불러와서 이 변수에 넣기
    let buyPrice = 90000;

    let cancelDay = document.getElementById('cancelDay').value;
    if(cancelDay <= 7) {
        document.getElementById('cancelAble').textContent = '가능';

    }else if (cancelDay <= 15) {
        document.getElementById('cancelAble').textContent = '부분적으로 가능'
        buyPrice = Math.floor(buyPrice - (buyPrice * (cancelDay / 31)));

    }else {
        document.getElementById('cancelAble').textContent = '불가능';
        buyPrice = 0;
    }

    finalPrice.textContent = buyPrice.toLocaleString() + '원';    //finalPrice 변수를 할인 가격 값으로 지정, toLocaleString으로 3자리마다 콤마 찍음

    function handleSubmit(event) {  //결제하기 버튼 눌렀을때
        event.preventDefault();

        //TODO 모든 변수 & html요소의 값을 받아서 POST로 넘겨줄거임
        let payName = document.getElementById('payName').value;
        let userTel = document.getElementById('userTel').value;
        let bisName = document.getElementById('bisName').value;
        let cancelDay = document.getElementById('cancelDay').value;
        let finalAmount = finalPrice.textContent;

        //FIXME 일단 임시로 GET 쿼리로 보내도록 함
        let queryParams = new URLSearchParams({
            payName: payName,
            userTel: userTel,
            bisName: bisName,
            cancelDay: cancelDay,
            finalPrice: finalAmount
        });

        // 환불이 완료되면 환불 완료 창으로 넘겨줌
        window.location.href=`buyRefund.html?${queryParams.toString()}`;
    }
}
