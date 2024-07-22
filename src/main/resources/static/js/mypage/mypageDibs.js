function fn_dibsList() {
    console.log("fn_dibsList() 호출됨"); // 호출 여부 확인을 위한 로그
    $.ajax({
        type: "POST", // 데이터 가져오기
        async: false,  // 비동기식
        url: "/mypage/dibsList", // 서버 측 URL과 맞춰야 함
        dataType: "json", // 서버에서 JSON 형식으로 데이터 반환을 기대
        success: function(data) {
            if (data && data.dibsList) {
                var tableHtml = '<table><tr><th>번호</th><th>업체명</th><th>프로그램명</th><th>지역</th><th>찜</th></tr>';

                data.dibsList.forEach(function(dibs) {
                    tableHtml += '<tr>' +
                        '<td>' + dibs.detailNo + '</td>' +
                        '<td>' + dibs.detailBusinessName + '</td>' +
                        '<td>' + dibs.detailKoClassification + '</td>' +
                        '<td>' + dibs.detailRoadAddress + '</td>' +
                        '<td><button onclick="location.href=\'/mypage/removeDibs.do?detailNo=' + dibs.detailNo + '\'">찜 취소</button></td>' +
                        '</tr>';
                });

                tableHtml += '</table>';
                $('#dibs-list').html(tableHtml); // HTML로 표시
            } else {
                alert("찜 목록이 없습니다.");
            }
        },
        error: function(xhr, textStatus, errorThrown) {
            console.error("에러 발생: ", textStatus, errorThrown);
            alert("에러가 발생했습니다: " + textStatus + " " + errorThrown);
        }
    });
}