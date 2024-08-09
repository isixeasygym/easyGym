function removeComment(reviewNo) {
    var memberNo = $('.memberNo').val(); // 회원 번호를 가져옵니다.
    var detailNo = $('.detailNo').val(); // 업체 번호를 가져옵니다.

    console.log("Member No: ", memberNo); // Debug: Check memberNo

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
                $('.ReviewRange').each(function() {
                    var currentReviewNo = $(this).data('review-no');
                    if (currentReviewNo == reviewNo) {
                        $(this).remove(); // 해당 리뷰 요소를 제거합니다.
                    }
                });
                
                // 리뷰와 이미지 새로고침
                refreshReviews(detailNo);
            } else if (data === "noBuy") {
                alert("해당 글은 해당 업체 회원권을 구매하고 자신이 작성한 글만 삭제 가능합니다.");
            }
        },
        error: function(xhr, status, error) {
            console.error("Error: " + error);
            alert("서버와의 통신에 문제가 발생했습니다. 다시 시도해 주세요.");
        }
    });
}
function refreshReviews(detailNo) {
    var memberNo = $('.memberNo').val(); // 회원 번호를 가져옵니다.
    
    if (!memberNo) {
        alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
        let address = window.location.href;
        window.location.href = '/member/loginForm.do?action=' + encodeURIComponent(address);
        return;
    }

    $.ajax({
        url: '/getReviews.do',
        type: 'GET',
        dataType: 'json',
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
                            <button class="deleteButton" onclick="removeComment(${review.reviewNo})">삭제</button>
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