window.onload = function() {
    let subscriptionMonths = document.getElementById('subscriptionMonths');   //구독 개월수
    let originalPrice = document.getElementById('originalPrice');   //원래 가격
    let finalPrice = document.getElementById('finalPrice');   //최종 결제금액
    let discountRate = document.getElementById('discountRate');   //할인율

    subscriptionMonths.addEventListener('change', updateSub);    //구독 개월이 바뀔때 함수 호출

    function updateSub() {  //구독 개월 업데이트
        let months = parseInt(subscriptionMonths.value); //구독 개월수를 가져와서 int로 변환
        let price = 0;  //현재 가격
        let discount = 0;   //할인율

        /*
        original-price 속성을 부여함으로써 취소선을 생성함. 1개월일때는 할인율이 0프로이므로 취소선 없이 글자가 출력되지만 3,6개월일때는 취소선 속성이 부여되서 출력됨
        .original-price {
        text-decoration: line-through;
        }
        */
        switch (months) {   //구독개월에 따라 원래가격 & 할인율 변경
            case 1:
                price = 30000;
                discount = 0;
                originalPrice.classList.remove('original-price');
                break;
            case 3:
                price = 90000;
                discount = 10;
                originalPrice.classList.add('original-price');
                break;
            case 6:
                price = 180000;
                discount = 30;
                originalPrice.classList.add('original-price');
                break;
        }
        //최종 결제 금액, 할인가격 계산해서 변수값 설정
        let discountedPrice = price * (1 - discount / 100);

        originalPrice.textContent = price.toLocaleString() + '원';   //price 변수를 원래 가격 값으로 지정, toLocaleString으로 3자리마다 콤마 찍음
        finalPrice.textContent = discountedPrice.toLocaleString() + '원';    //finalPrice 변수를 할인 가격 값으로 지정, toLocaleString으로 3자리마다 콤마 찍음
        discountRate.textContent = discount + '%';  //할인율 %랑 같이 찍음
    }

    //실행될때 구독개월수하고 결제수단은 계속해서 업데이트
    updateSub();
}