document.addEventListener('DOMContentLoaded', function () {
    // 초기 설정
    const navButtons = document.querySelectorAll('.nav-btn');
    const sections = document.querySelectorAll('.section');
    const sidebar = document.querySelector('.sidebar');
    const mainContent = document.querySelector('.main-content');

    // 사이드바 버튼 클릭 이벤트 핸들러
    function sidebarBtnClickHandler(event) {
        const sidebarButtons = document.querySelectorAll('.sidebar-btn');
        sidebarButtons.forEach(btn => btn.classList.remove('active'));
        event.target.classList.add('active');

        sections.forEach(section => section.classList.remove('active'));
        document.getElementById(event.target.dataset.target).classList.add('active');
    }

    // 내 정보 탭을 클릭할 때의 처리
    function myInfoTabHandler() {
        sidebar.style.display = 'block';
        mainContent.style.width = '70%';
        sidebar.innerHTML = `
            <button class="sidebar-btn active" data-target="using-products">이용중인 상품</button>
            <button class="sidebar-btn" data-target="dibs-list">찜 목록</button>
            <button class="sidebar-btn" data-target="purchase-history">구매내역</button>
        `;
        document.querySelectorAll('.sidebar-btn').forEach(btn => btn.addEventListener('click', sidebarBtnClickHandler));
        document.getElementById('using-products').classList.add('active');
    }

    // 포인트&쿠폰 탭을 클릭할 때의 처리
    function pointsCouponsTabHandler() {
        sidebar.style.display = 'block';
        mainContent.style.width = '70%';
        sidebar.innerHTML = `
            <button class="sidebar-btn active" data-target="points">포인트</button>
            <button class="sidebar-btn" data-target="coupons">쿠폰</button>
        `;
        document.querySelectorAll('.sidebar-btn').forEach(btn => btn.addEventListener('click', sidebarBtnClickHandler));
        document.getElementById('points').classList.add('active');
    }

    // 정보 수정 탭을 클릭할 때의 처리
    function updateInfoTabHandler() {
        sidebar.style.display = 'none';
        mainContent.style.width = '100%';
        document.getElementById('update-info').classList.add('active', 'fullscreen');
    }

    // 탭 버튼 클릭 이벤트
    navButtons.forEach(button => {
        button.addEventListener('click', () => {
            navButtons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');

            sections.forEach(section => section.classList.remove('active', 'fullscreen'));

            if (button.dataset.target === 'my-info') {
                myInfoTabHandler();
            } else if (button.dataset.target === 'points-coupons') {
                pointsCouponsTabHandler();
            } else {
                updateInfoTabHandler();
            }
        });
    });

    // 초기 내 정보 탭 설정
    myInfoTabHandler();

    // 비밀번호 확인 이벤트
    document.getElementById('password-check-btn').addEventListener('click', function() {
        var password = document.getElementById('password').value;
        if (password === "password") { // 실제로는 서버와의 통신이 필요합니다.
            document.getElementById('password-check').style.display = 'none';
            document.getElementById('update-form').style.display = 'block';
        } else {
            alert("비밀번호가 올바르지 않습니다.");
        }
    });

    // 포인트 내역 필터링 이벤트
    document.getElementById('filter-points-btn').addEventListener('click', function() {
        var startDate = document.getElementById('start-date').value;
        var endDate = document.getElementById('end-date').value;
        alert('조회기간: ' + startDate + ' ~ ' + endDate);
    });

    // 수정하기 버튼 클릭 이벤트
    document.getElementById('update-btn').addEventListener('click', function() {
        // 여기에 수정 로직을 추가합니다.
        alert("수정되었습니다.");
    });

    // 취소 버튼 클릭 이벤트
    document.getElementById('cancel-btn').addEventListener('click', function() {
        location.href = '${contextPath}/mypage.jsp'; // 마이페이지 첫 화면으로 이동
    });
});