document.querySelectorAll('.nav-btn').forEach(button => {
    button.addEventListener('click', () => {
        document.querySelectorAll('.nav-btn').forEach(btn => btn.classList.remove('active'));
        button.classList.add('active');

        document.querySelectorAll('.section').forEach(section => section.classList.remove('active'));
        
        if (button.dataset.target === 'inquiry-history' || button.dataset.target === 'report-history' || button.dataset.target === 'purchase-history') {
            document.querySelector('.sidebar').style.display = 'none';
            document.querySelector('.main-content').style.width = '100%';
            document.getElementById(button.dataset.target).classList.add('active', 'fullscreen');
        } else {
            document.querySelector('.sidebar').style.display = 'block';
            document.querySelector('.main-content').style.width = '70%';
            document.querySelectorAll('.section').forEach(section => section.classList.remove('fullscreen'));
            
            if (button.dataset.target === 'my-info') {
                document.getElementById('using-products').classList.add('active');
                document.querySelector('.sidebar-btn[data-target="using-products"]').classList.add('active');
            } else {
                document.getElementById(button.dataset.target).classList.add('active');
            }
        }
    });
});

document.querySelectorAll('.sidebar-btn').forEach(button => {
    button.addEventListener('click', () => {
        document.querySelectorAll('.sidebar-btn').forEach(btn => btn.classList.remove('active'));
        button.classList.add('active');
        
        document.querySelectorAll('.section').forEach(section => section.classList.remove('active'));
        document.getElementById(button.dataset.target).classList.add('active');
    });
});

function checkPassword() {
    // 비밀번호 확인 로직 (여기서는 단순히 비밀번호를 "password"로 체크)
    var password = document.getElementById('password').value;
    if (password === "password") {
        document.getElementById('password-check').style.display = 'none';
        document.getElementById('update-form').style.display = 'block';
    } else {
        alert("비밀번호가 올바르지 않습니다.");
    }
}

function filterPoints() {
    // 조회기간에 따른 포인트 내역 필터링 로직
    var startDate = document.getElementById('start-date').value;
    var endDate = document.getElementById('end-date').value;
    // 여기에 필터링 로직 추가 (예: Ajax 호출로 서버에서 데이터 가져오기)
    alert('조회기간: ' + startDate + ' ~ ' + endDate);
}