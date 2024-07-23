window.onload = function() {
    let finalPr = document.getElementById('finalPr');   //최종 결제금액

    let finalPrice = finalPr.textContent;
    if(diffDays <= 7) {
        document.getElementById('cancelAble').textContent = '가능';

    }else if (diffDays <= 15) {
        document.getElementById('cancelAble').textContent = '부분적으로 가능'
        finalPrice = Math.floor(finalPrice - (finalPrice * (diffDays / 31)));

    }else {
        document.getElementById('cancelAble').textContent = '불가능';
        finalPrice = 0;
    }
    finalPr.textContent = finalPrice.toLocaleString();    //finalPr 변수를 할인 가격 값으로 지정, toLocaleString으로 3자리마다 콤마 찍음
}
