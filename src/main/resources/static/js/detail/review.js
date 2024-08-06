function deleteComment(reviewNo) {
	var memberNo = $('.memberNo').val();
	if (!memberNo) {
		alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
		let address = window.location.href;
		window.location.href = '/member/loginForm.do?action=' + encodeURIComponent(address);
		return;
	}
    var detailNo = $('.detailNo').val(); // 회사 번호를 가져옵니다.
    $.ajax({
        type: "POST",
        url: "/delete.do",
        data: {
            reviewNo: reviewNo,
            memberNo: memberNo// detailNo를 데이터에 포함합니다.
        },
        success: function(data) {
            if (data === "success") {
                alert("해당 글은 삭제되었습니다.");
                // 리뷰 목록을 새로고침하여 삭제된 리뷰를 반영합니다.
                refreshReviews(detailNo);
            }else if (data === "noBuy") {
                alert("해당 글은 해당 업체 회원권을 구매하고 자신이 작성한 글만 삭제 가능합니다.");
            }
        },
        error: function(xhr, status, error) {
            alert("회원 정보가 없습니다.");
            window.location.href = '/member/loginForm.do';
        }
    });
}

function refreshReviews(detailNo) {
    console.log("Company ID:", detailNo);
    $.ajax({
        url: '/getReviews.do',
        type: 'GET',
        dataType: "json",
        data: {
            detailNo: detailNo
        },
        success: function(reviews) {
            var reviewContainer = $('#reviewContainer');
            reviewContainer.empty(); // 기존 리뷰 목록을 비웁니다

            if (reviews.length === 0) {
                // 리뷰가 없는 경우
                alert("후기가 더 이상 없습니다.");
                // 이전 페이지로 이동
                window.history.back(); // 이전 페이지로 이동
            } else {
                // 모든 리뷰를 표시
                reviews.forEach(function(review) {
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

                    reviewContainer.append(reviewHtml); // 생성된 리뷰 HTML을 컨테이너에 추가
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