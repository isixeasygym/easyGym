let scrollTop;
$(window).on('scroll', function() {
	scrollTop = $(window).scrollTop();
	var fixedContainer = $('#fixedContainer');

	if (scrollTop > 500) {
		fixedContainer.fadeIn(); // 1000px 스크롤 시 보이게 함
	} else {
		fixedContainer.fadeOut(); // 1000px 이하일 때 숨김
	}
});
$(document).ready(function() {
	var requestInProgress = false;

	function centerModal() {
		var modal = document.getElementById('reportModal');
		var modalContent = document.querySelector('.modal-content');
		if (modal && modalContent) {
			var windowHeight = window.innerHeight;
			var modalHeight = modalContent.offsetHeight;
			var scrollTop = window.scrollY || window.pageYOffset;

			// 스크롤 위치와 화면 중앙을 기준으로 모달 콘텐츠 위치 조정
			modalContent.style.position = 'absolute'; // 절대 위치로 설정
			modalContent.style.top = `${Math.max((windowHeight - modalHeight) / 2, 0) + scrollTop}px`;
		}
	}

	$(".report-button").click(function(event) {
		var memberNo = $('.memberNo').val();
		event.stopPropagation();

		if (!memberNo) {
			alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
			let address = window.location.href;
			window.location.href = '/member/loginForm.do?action=' + encodeURIComponent(address);
			return;
		}

		// 서버 요청 처리 중일 때 추가 요청 방지
		if (requestInProgress) return;
		requestInProgress = true;

		$.ajax({
			type: "POST",
			url: `${contextPath}/detail/selectReport.do`,
			data: {
				memberNo: memberNo
			},
			success: function(response) {
				requestInProgress = false; // 요청 완료 후 플래그 리셋
				if (response === "noBuy") {
					alert("회원권을 구매하신 고객님만 불편 사항을 제출할 수 있습니다.");
				}else if (response === "alreadyReport"){
					alert("이미 불편 사항을 제출한 이력이 있습니다.");
				} else if (response === "memberShip") {
					var modal = document.getElementById('reportModal');
					if (modal) {
						modal.style.display = 'flex'; // 모달을 화면 중앙에 표시
						centerModal(); // 중앙 위치 조정
					} else {
						console.error('모달 창을 찾을 수 없습니다.');
					}
				}
			},
			error: function(xhr, status, error) {
				requestInProgress = false; // 오류 발생 시 플래그 리셋
				console.error('Error:', error);
				alert('서버 오류가 발생했습니다.');
			}
		});
	});

	// 취소 버튼 클릭 시 모달 창 숨기기
	document.getElementById('cancelReport').addEventListener('click', function() {
		document.getElementById('reportModal').style.display = 'none';
	});

	// 신고 유형 선택 시 기타 입력 필드 표시
	document.getElementById('reportType').addEventListener('change', function() {
		var selectedValue = this.value;
		var otherInput = document.getElementById('otherInput');

		if (selectedValue === 'other') {
			otherInput.style.display = 'block';
		} else {
			otherInput.style.display = 'none';
		}
	});

	// 확인 버튼 클릭 시 모달 닫기 및 입력 값 처리
	document.getElementById('confirmReport').addEventListener('click', function() {
		var reportType = document.getElementById('reportType').value;
		var otherDetail = document.getElementById('otherDetail').value;
		var reportContent = (reportType === 'other') ? otherDetail : reportType; // 신고 내용

		if (!reportContent) {
			alert('신고 내용을 입력하세요.');
			return;
		}

		// Ajax 요청
		$.ajax({
			type: "POST",
			url: `${contextPath}/report.do`,
			data: {
				detailNo: $('.detailNo').val(),
				memberNo: $('.memberNo').val(),
				reportContent: reportContent // 선택된 신고 유형 또는 기타 입력 값
			},
			success: function(response) {
				// 요청 성공 시 처리
				alert('신고가 접수되었습니다.');
				document.getElementById('reportModal').style.display = 'none'; // 모달 닫기
			},
			error: function(xhr, status, error) {
				// 요청 실패 시 처리
				alert('신고 접수에 실패하였습니다.');
				console.error('Error:', error);
			}
		});
	});

	// 윈도우 크기 조정 및 스크롤 시 모달 중앙 위치 조정
	window.addEventListener('resize', centerModal);
	window.addEventListener('scroll', centerModal);
});

//글 삭제
	    function deleteComment(reviewNo) {
			var memberNo = $('.memberNo').val(); // 회원 번호를 가져옵니다.
			var detailNo = $('.detailNo').val(); // 회사 번호를 가져옵니다.

			if (!memberNo) {
				alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
				let address = window.location.href;
				window.location.href = '/member/loginForm.do?action=' + encodeURIComponent(address);
				return;
			}
	        $.ajax({
	            type: "POST",
	            url: "/delete.do",
	            data: { 
	                reviewNo: reviewNo, 
	                memberNo: memberNo,
					detailNo: detailNo
	            },
	            success: function(data) {
	                if (data === "success") {
	                    alert("해당 글은 삭제되었습니다.");
	                    // 삭제된 리뷰를 화면에서 제거합니다.
						refreshReviews(detailNo);
	                    $('.ReviewRange').each(function() {
	                        var currentReviewNo = $(this).data('review-no');
	                        if (currentReviewNo == reviewNo) {
	                            $(this).remove(); // 해당 리뷰 요소를 제거합니다.
	                        }
	                    });
						
	                } else if (data === "noBuy") {
	                    alert("해당 글은 해당 업체 회원권을 구매하고 자신이 작성한 글만 삭제 가능합니다.");
	                }
	            },
	            error: function(xhr, status, error) {
	  			 alert("회원 정보가 없습니다.");
	  			 window.location.href = '/member/loginForm.do' 
	            }			
	  		 		            
	        });
	    }



// 글 등록하기
function writeSubmit() {
	var memberNo = $('.memberNo').val();

	if (!memberNo) {
		alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
		let address = window.location.href;
		window.location.href = '/member/loginForm.do?action=' + encodeURIComponent(address);
		return;
	}

	var detailNo = $('.detailNo').val();
	var reviewComment = $('#myTextarea').val();
	var reviewRating = $("input[name='detailScope']:checked").val();
	var fileInput = $('#reviewImageName')[0].files;

	// 파일 크기 검사 및 필터링
	const MAX_SIZE = 1 * 1024 * 1024; // 1MB in bytes
	const filesToUpload = Array.from(fileInput).filter(file => {
		if (file.size > MAX_SIZE) {
			alert("1MB 이상의 파일은 등록할 수 없습니다. 다른 파일을 선택해 주세요.");
			return false;
		}
		return true;
	});

	if (filesToUpload.length !== fileInput.length) {
		// 파일 크기 초과로 인해 업로드할 수 없는 경우
		return;
	}

	// FormData 객체 생성
	var formData = new FormData();
	formData.append('detailNo', detailNo);
	formData.append('memberNo', memberNo);
	formData.append('reviewComment', reviewComment);
	formData.append('reviewRating', reviewRating);

	// FormData에 파일 추가
	filesToUpload.forEach((file) => {
		formData.append('reviewImageName', file);
	});

	$.ajax({
		async: false,
		enctype: 'multipart/form-data',
		cache: false,
		url: '/writeReview.do',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
	})
		.done(function(response) {
			if (response === "success") {
				alert("작성하신 후기가 등록되었습니다.");

				// 리뷰 목록을 새로고침
				refreshReviews(detailNo);
			} else if (response === "noBuy") {
				alert("후기 작성은 회원권을 구매하신 회원님만 가능합니다");
			}
		})
		.fail(function(xhr, status, error) {
			console.error("Error:", error);
			console.error("Status:", status);
			console.error("Response:", xhr.responseText);
		});
}

// 리뷰 목록을 새로고침
function refreshReviews(detailNo) {
	$.ajax({
		async: false,
		url: '/getReviews.do',
		type: 'GET',
		contentType: "application/json",
		dataType: "json",
		data: {
			detailNo: detailNo
		},
		success: function(reviews) {
			var reviewContainer = $('#reviewContainer');
			reviewContainer.empty(); // 기존 리뷰 목록을 비웁니다

			// 리뷰 개수에 따라 로직 분기
			const reviewsToShow = reviews.slice(0, 2); // 첫 2개 리뷰만 표시
			const totalReviews = reviews.length;

			reviewsToShow.forEach(function(review) {
				var reviewHtml = `
                    <div class="ReviewRange" data-review-no="${review.reviewNo}">
                        <input class="reviewNo" type="hidden" value="${review.reviewNo}"/>
                        <button class="deleteButton" onclick="deleteComment(${review.reviewNo})">삭제</button>
                        <div class="personReviewRange">
                            <img class="reviewPicture" src="${contextPath}/images/detail/detailpage/reviewImage.PNG">
                            <p class="anonymous">(익명의 회원)</p>
                            <p class="reviewDate">${review.reviewDate}</p>
                            <textarea class="reviewComment" readonly>${review.reviewComment}</textarea>
                        </div>
                    </div>
                `;
				reviewContainer.append(reviewHtml);
			});

			// 전체보기 링크 추가
			if (totalReviews > 2) {
				var viewAllReviewsHtml = `<a href="${contextPath}/detail/reviewViewer.do?detailNo=${detailNo}" class="viewAllReviews">후기 ${totalReviews}개 전체보기</a>`;
				reviewContainer.append(viewAllReviewsHtml);
			}

			// 리뷰 이미지 새로고침
			updateReviewImages(detailNo);
		},

		error: function(xhr, status, error) {
			console.error("Error:", error);
			console.error("Status:", status);
			console.error("Response:", xhr.responseText);
		}
	});
}

// 리뷰 이미지 업데이트
function updateReviewImages(detailNo) {
	$.ajax({
		url: '/getReviewImages.do',
		type: 'GET',
		dataType: 'json',
		data: { detailNo: detailNo },
		success: function(response) {
			var reviewImageContainer = $('#reviewImage');
			reviewImageContainer.empty(); // 기존 이미지 제거

			var imagesToShow = response.slice(0, 5); // 최대 5개 이미지 표시
			if (imagesToShow.length > 0) {
				imagesToShow.forEach(function(image) {
					var imageHtml =
						`<img class="reviewImage" style="width:130px; height:130px;" 
                                src="${contextPath}/images/detail/reviewImage/${image.detailNo}/${image.memberNo}/${image.reviewImgName}"/>`;
					reviewImageContainer.append(imageHtml);
				});
			}
		},
		error: function(xhr, status, error) {
			console.error("Error:", error);
			console.error("Status:", status);
			console.error("Response:", xhr.responseText);
		}
	});
}
//이미지 슬라이드
$(function() {
	$('.slider_panel').append($('.slider_image').first().clone());  //마지막 5번째 사진 뒤에 1번째 사진을 복제해서 붙여둠
	$('.slider_panel').prepend($('.slider_image').eq(-2).clone());  //첫번째 사진 앞에 마지막 5번째 사진을 복제해서 붙여둠
	//let index=0;  //1. 원래는 0이면 첫번째 사진이 나오게 되는데
	let index=1;  //2. 마지막 5번 사진을 붙였으니 1로 바꿔야함.
	//moveSlider(index);
	$('.slider_panel').css('left',-700);  //4-1) 사이트 첫 화면에서 5번이 보이고 1번으로 나오는 화면을 없애기 위한 작업
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
			$('.slider_panel').css('left',-7700)
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
			left:-(index*700)
		},'slow');
		$('.control_button').removeClass('active');
		$('.control_button').eq(index-1).addClass('active');
		$('.slider_text').hide();  //설명글을 fadeout 안하고 바로 사라지게
		$('.slider_text').eq(index-1).fadeIn('slow');  //그럼 어떨 때 설명글을 보이게 하느냐? => eq(index)일 때 ~
	}

	//이미지 슬라이드 구현 함수 (좌우 버튼)
	function moveSlider(index) {
		$('.slider_panel').animate({
			left:-(index*700)
		},'slow');
		$('.control_button').removeClass('active');
		$('.control_button').eq(index-1).addClass('active');  //5. 설명도 -1해줘야함.

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