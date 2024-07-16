function addToFavorites(companyId, userId) {
    $.ajax({
        type: "POST",
        url: "addFavorite",
        data: { companyId: companyId, userId: userId },
        success: function(response) {
            if (response === "insert") {
                alert("찜 목록에 추가되었습니다.");
            } else if (response === "delete") {
                alert("찜 목록에서 삭제되었습니다.");
            } else if (response.startsWith("redirect:")) {
                // 리다이렉트 처리
                var redirectUrl = response.substring(9); // "redirect:" 이후의 문자열을 가져옴
                window.location.href = redirectUrl; // 리다이렉트 수행
            } else {
                alert("알 수 없는 오류가 발생했습니다.");
            }
        },
        error: function() {
            alert("서버와의 통신 중 오류가 발생했습니다.");
        }
    });
}

$(document).ready(function() {
    $(".favorite-button").click(function() {
        var companyId = $(this).data("company-id");
        var userId = $(this).data("user-id");
        addToFavorites(companyId, userId);
    });
});