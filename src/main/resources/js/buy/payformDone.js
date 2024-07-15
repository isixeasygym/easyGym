window.onload = function () {

    const url = new URL(window.location.href);
    const urlParams = url.searchParams;
    let payCheck_form = document.getElementById('payCheck_form'); //submit 결제하기 버튼
    let payName = document.getElementById('payName');
    let userTel = document.getElementById('userTel');
    let bisName = document.getElementById('bisName');
    let finalPrice = document.getElementById('finalPrice');

    payName.textContent = urlParams.get('name');
    userTel.textContent = urlParams.get('phoneNumber');
    bisName.textContent = urlParams.get('bisName');
    finalPrice.textContent = urlParams.get('finalPrice');

    payCheck_form.addEventListener('submit', handleSubmit);  //확인 버튼을 누를때 함수 호출

    function handleSubmit(event) {  // 결제가 완료되면 결제 완료 창으로 넘겨줌
        event.preventDefault(); //원래는
        window.location.href = `buyForm.html`;
    }
}