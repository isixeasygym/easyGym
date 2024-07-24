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
        const targetSection = document.getElementById(event.target.dataset.target);
        if (targetSection) {
            targetSection.classList.add('active');
        }
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
        const updateInfoSection = document.getElementById('update-info');
        if (updateInfoSection) {
            updateInfoSection.classList.add('active', 'fullscreen');
        }
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

    // 비밀번호 확인 이벤트 (여기 addEventListener가 찜 목록과 충돌나게 함.)
	const passwordCheckBtn = document.getElementById('password-check-btn');
	if (passwordCheckBtn) {
	    passwordCheckBtn.addEventListener('click', function() {
	        var memberPwd = document.getElementById('password').value;
	        var memberNo = document.getElementById('memberNo').value; // memberNo를 가져오는 코드 추가

	        // FormData 객체 생성 및 데이터 추가
	        var formData = new FormData();
	        formData.append('memberNo', memberNo);
	        formData.append('memberPwd', memberPwd);

	        // AJAX를 사용하여 서버에 비밀번호 확인 요청을 보냄
	        fetch(`${contextPath}/mypage/checkPassword.do`, {
	            method: 'GET',
	            body: formData
	        })
	        .then(response => response.json())
	        .then(data => {
	            if (data) { // 서버에서 반환된 값이 true인지 확인
	                document.getElementById('password-check').style.display = 'none';
	                document.getElementById('update-form').style.display = 'block';
	            } else {
	                alert("비밀번호가 올바르지 않습니다.");
	            }
	        })
	        .catch(error => {
	            console.error('Error:', error);
	            alert("서버와의 통신 중 오류가 발생했습니다.");
	        });
	    });
	}


    // 포인트 내역 필터링 이벤트
    const filterPointsBtn = document.getElementById('filter-points-btn');
    if (filterPointsBtn) {
        filterPointsBtn.addEventListener('click', function() {
            var startDate = document.getElementById('start-date').value;
            var endDate = document.getElementById('end-date').value;
            alert('조회기간: ' + startDate + ' ~ ' + endDate);
        });
    }

    // 수정하기 버튼 클릭 이벤트
    const updateBtn = document.getElementById('update-btn');
    if (updateBtn) {
        updateBtn.addEventListener('click', function() {
            // 여기에 수정 로직을 추가합니다.
            alert("수정되었습니다.");
        });
    }

    // 취소 버튼 클릭 이벤트
    const cancelBtn = document.getElementById('cancel-btn');
    if (cancelBtn) {
        cancelBtn.addEventListener('click', function() {
            location.href = `${contextPath}/mypage/mypageMain.do`; // 마이페이지 첫 화면으로 이동
        });
    }

    // 비밀번호 확인 이벤트 및 추가 요소가 동적으로 생성되는 경우를 대비해 null 체크 추가

    // 찜 목록 버튼 클릭 이벤트 추가
    const dibsListBtn = document.querySelector('[data-target="dibs-list"]');
    if (dibsListBtn) {
        dibsListBtn.addEventListener('click', fn_dibsList);
    }
});

//1-2)찜 목록
function fn_dibsList() {
    $.ajax({
        type: "POST", // 데이터 가져오기
        async: false,  // 비동기식
        url: "/mypage/mypageMain.do", // 서버 측 URL과 맞춰야 함
        //dataType: "json", // 서버에서 JSON 형식으로 데이터 반환을 기대
		success: function(data) {
			var tableHtml = '<table><tr><th>번호</th><th>업체명</th><th>프로그램명</th><th>지역</th><th>찜</th></tr>';
            //if (data != null) {
			if (data && data.length > 0) {
				console.log(data); // 호출 여부 확인을 위한 로그
                $.each(data, function(index, dibs) {
					
                    tableHtml += '<tr>' +
                        '<td>' + (parseInt(index)+1) + '</td>' +
                        '<td>' + dibs.detailBusinessName + '</td>' +
                        '<td>' + dibs.detailKoClassification + '</td>' +
                        '<td>' + dibs.detailRoadAddress + '</td>' +
                        '<td><button onclick="location.href=\'/mypage/removeDibs.do?detailNo=' + dibs.detailNo + '\'">찜 취소</button></td>' +
						//'<td><button class="remove-dibs-btn" data-detail-no="${dibs.detailNo}">찜 취소</button></td>' +
                        '</tr>';
				});
            } else {
                tableHtml += '<tr><td colspan="5">찜 목록이 없습니다.</td></tr>';
            }

            tableHtml += '</table>';
            $('#dibs-list').html(tableHtml); // HTML로 표시
			requestInProgress = false;
        },
        error: function(data, textStatus, errorThrown) {
            console.error("에러 발생: ", textStatus, errorThrown);
            alert("에러가 발생했습니다: " + textStatus + " " + errorThrown);
        }
   });
}

//1-2)찜 취소
function fn_removeDibs(detailNo) {
	if (!detailNo) {
        alert("찜 번호가 올바르지 않습니다.");
		console.log("Invalid detailNo:", detailNo); // 디버깅용 로그
        return;
    }
    $.ajax({
        type: "POST",
		async: false,
        url: "/mypage/removeDibs.do",
        success: function() {
            alert("찜이 취소되었습니다.");
			console.log("찜 취소 성공:", detailNo); // 디버깅용 로그
            fn_dibsList(); // 찜 목록 새로고침
        },
		
        error: function(data, textStatus, errorThrown) {
            console.error("에러 발생: ", textStatus, errorThrown);
			console.log("Error response data:", data); // 디버깅용 로그
            alert("에러가 발생했습니다: " + textStatus + " " + errorThrown);
        }
    });
}

		
