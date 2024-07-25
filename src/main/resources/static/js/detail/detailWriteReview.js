function writeSubmit() {
    // 입력된 값들을 변수에 저장
    var companyId = $('.companyId').val();
    var memberNo = $('.userId').val();
    var reviewComment = $('#myTextarea').val();
    var reviewRating = $("input[name='detailScope']:checked").val();
    var fileInput = $('#reviewImageName')[0].files[0];
    
    // FormData 객체 생성
    var formData = new FormData();
    formData.append('companyId', companyId);
    formData.append('memberNo', memberNo);
    formData.append('reviewComment', reviewComment);
    formData.append('reviewRating', reviewRating);
    if (fileInput) {
        formData.append('reviewImageName', fileInput);
    }

    // 회원 정보가 없으면 로그인 페이지로 리다이렉트
    if (memberNo === '') {
        alert("회원 정보가 없습니다.");
        window.location.href = '/member/loginForm.do';
        return; // 함수 종료
    }

    // 리뷰 작성 AJAX 요청
    $.ajax({
        url: '/writeReview.do', // 요청을 보낼 URL
        type: 'POST', // HTTP 요청 메소드 (POST)
        data: formData, // 전송할 데이터 (FormData 객체)
        processData: false, // 데이터 처리 방식 설정 (FormData 사용 시 false)
        contentType: false // 컨텐츠 타입 설정 (FormData 사용 시 false)
    })
    .done(function(response) {
        if (response.status === "success") {
            alert("작성하신 후기가 등록되었습니다.");
            
            // 리뷰 목록을 새로고침
            $.ajax({
                url: '/getReviews.do', // 리뷰 목록을 가져오는 URL
                type: 'GET', // HTTP 요청 메소드 (GET)
                data: { companyId: companyId }, // 전송할 데이터
                dataType: 'json', // 서버가 JSON 데이터를 반환하는 경우
                success: function(reviews) {
                    var reviewContainer = $('#reviewContainer');
                    reviewContainer.empty(); // 기존 리뷰 목록을 비웁니다

                    if (reviews.length > 0) {
                        reviews.forEach(function(review) {
                            var reviewHtml = `
                                <div class="ReviewRange" data-review-no="${review.reviewNo}">
                                    <button class="deleteButton" onclick="deleteComment(${review.reviewNo})">삭제</button>
                                    <div class="personReviewRange">
                                        <img class="reviewImage" src="${contextPath}/images/detail/detailpage/reviewImage.PNG">
                                        <p class="anonymous">(익명의 회원)</p>
                                        <img src="${contextPath}/images/detail/detailpage/star.JPG">
                                        <p class="reviewDate">${review.reviewDate}</p>
                                        <p class="reviewComment">${review.reviewComment}</p>
                                    </div>
                                </div>
                            `;
                            reviewContainer.append(reviewHtml);
                        });
                        
                        // 리뷰 이미지 목록을 가져오기
                        $.ajax({
                            url: '/getReviewImages.do', // 리뷰 이미지 목록을 가져오는 URL
                            type: 'GET', // HTTP 요청 메소드 (GET)
                            data: { companyId: companyId }, // 전송할 데이터
                            dataType: 'json', // 서버가 JSON 데이터를 반환하는 경우
                            success: function(images) {
                                var reviewImageRange = $('#reviewImageRange');
                                reviewImageRange.empty(); // 기존 이미지 비우기

                                // 이미지가 있는 경우만 추가
                                if (images.length > 0) {
                                    images.forEach(function(image) {
                                        var imgHtml = `<img class="reviewImage" style="width:130px; height:130px;" src="${contextPath}/images/detail/reviewImage/${image.detailNo}/${image.memberNo}/${image.reviewImgName}"/>`;
                                        reviewImageRange.append(imgHtml);
                                    });
                                }
                            },
                            error: function(xhr, status, error) {
                                console.error("Error:", error);
                                console.error("Status:", status);
                                console.error("Response:", xhr.responseText);
                            }
                        });
                    } else {
                        reviewContainer.html('<h2>리뷰가 없습니다</h2>');
                    }
                },
                error: function(xhr, status, error) {
                    console.error("Error:", error);
                    console.error("Status:", status);
                    console.error("Response:", xhr.responseText);
                }
            });
        } else if (response.status === "noBuy") {
            alert("후기 작성은 회원권을 구매하신 회원님만 가능합니다");
        } else if (response.status === "noLogin") {
            alert("해당 글을 삭제하기 위해서는 로그인 정보가 필요합니다.");
            window.location.href = '/member/loginForm.do';
        }
    })
    .fail(function(xhr, status, error) {
        console.error("Error:", error);
        console.error("Status:", status);
        console.error("Response:", xhr.responseText);
    });
}