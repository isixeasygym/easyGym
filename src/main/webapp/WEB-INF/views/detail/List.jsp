<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/detail/header.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    Object member = session.getAttribute("member");
    request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메인페이지</title>
    <link rel="stylesheet" href="${contextPath}/css/detail/list.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="${contextPath}/js/detail/list.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9a9906a8b7e291e6dddbb2bd165b6d7f&libraries=services"></script>
    <script>
        $(document).ready(function() {
            var requestInProgress = false;

            // 페이지 로드 시 초기화 작업
            initializePage();

            function initializePage() {
                $(".favorite-button").each(function() {
                    var button = this;
                    var detailNo = $(button).find('.detailNo').val();
                    var memberNo = $(button).find('.memberNo').val();

                    $.ajax({
                        type: "GET",
                        url: "${contextPath}/getFavoriteStatus",
                        data: { detailNo: detailNo, memberNo: memberNo },
                        success: function(data) {
                            if (data === "insert" || data === "delete") {
                                updateFavoriteButton(button, data);
                            } else if (data === "nologin") {
                                // 로그인 필요 알림
                            } else {
                                alert("알 수 없는 오류가 발생했습니다.");
                            }
                        },
                        error: function(xhr, status, error) {
                            console.error("Error: " + error);
                            alert("오류가 발생했습니다. 관리자에게 문의하세요.");
                        }
                    });
                });

                // URL 파라미터로 초기값 설정
                const urlParams = new URLSearchParams(window.location.search);
                const query = urlParams.get('query');
                const detailClassification = urlParams.get('detailClassification');

                if (query) {
                    const district = query.split(' ')[1];
                    document.getElementById('districtSelect').value = district;
                }

                if (detailClassification) {
                    document.getElementById('facilityType').value = detailClassification;
                }
            }

            function updateFavoriteButton(button, status) {
                var newSrc = (status === "insert")
                    ? '/images/detail/detailpage/pickDibs.png'
                    : '/images/detail/detailpage/dibs.png';
                $(button).find('.dibs').attr('src', newSrc);
            }

            $(".favorite-button").click(function(event) {
                var memberNo = $(this).find('.memberNo').val();
                if (!memberNo) {
                    alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
                    let address = window.location.href;
                    window.location.href = '/member/loginForm.do?action=' + encodeURIComponent(address);
                    return;
                }
                if (requestInProgress) return;

                var button = this;
                var detailNo = $(button).find('.detailNo').val();

                requestInProgress = true;

                $.ajax({
                    type: "GET",
                    url: "${contextPath}/addFavorite",
                    data: { detailNo: detailNo, memberNo: memberNo },
                    success: function(data) {
                        if (data === "insert" || data === "delete") {
                            alert(data === "insert"
                                ? "찜 목록에 추가되었습니다."
                                : "찜 목록에서 삭제되었습니다.");
                            updateFavoriteButton(button, data);
                        }  else {
                            alert("알 수 없는 오류가 발생했습니다.");
                        }
                        requestInProgress = false;
                    },
                    error: function(xhr, status, error) {
                        console.error("Error: " + error);
                        alert("오류가 발생했습니다. 관리자에게 문의하세요.");
                        requestInProgress = false;
                    }
                });

                event.stopPropagation();
            });

            $(window).on('pageshow', function(event) {
                initializePage();
            });

            // 옵션값이 바뀔 때 URL 업데이트
            document.getElementById('districtSelect').addEventListener('change', updateUrl);
            document.getElementById('facilityType').addEventListener('change', updateUrl);

            function updateUrl() {
                const selectedDistrict = document.getElementById('districtSelect').value;
                const facilityType = document.getElementById('facilityType').value;
                let queryParam = '';
                if (selectedDistrict != "default") {
                    queryParam = '서울특별시 ' + selectedDistrict;
                }
                const url = `${contextPath}/detail/search.do?query=${encodeURIComponent(queryParam)}&detailClassification=${facilityType}`;
                window.location.href = url;
            }
        });

        // 지도 관련 설정
        window.onload = function() {
            var mapContainer = document.getElementById('map');
            var mapOption = {
                center: new kakao.maps.LatLng(37.56682194967411, 126.97864942970189),
                level: 8
            };
            var map = new kakao.maps.Map(mapContainer, mapOption);

            <% if (!empty allList) { %>
                <% for (var i = 0; i < allList.size(); i++) { 
                    var item = allList.get(i);
                %>
                    addMarker(<%= item.detailLatitude %>, <%= item.detailLongitude %>, "<%= item.detailBusinessName %>");
                <% } %>
            <% } %>

            function addMarker(lat, lng, content) {
                var position = new kakao.maps.LatLng(lat, lng);
                var marker = new kakao.maps.Marker({
                    position: position
                });
                marker.setMap(map);

                var infowindow = new kakao.maps.InfoWindow({
                    position: position,
                    content: content
                });
                infowindow.open(map, marker);
            }
        }
    </script>
</head>
<body>

<div class="search-container">
    <form class="search-form">
        <input type="text" name="query" placeholder="업체명을 입력하세요..." class="search-input">
        <button type="submit" class="search-button">검색</button>
    </form>
</div>

<div class="options-container">
    <div class="option-group">
        <label for="districtSelect">지역명:</label>
        <select id="districtSelect" class="option-select">
            <option value="default">구/군 선택</option>
            <option value="중구">서울특별시 중구</option>
            <option value="강남구">서울특별시 강남구</option>
            <option value="동작구">서울특별시 동작구</option>
            <option value="마포구">서울특별시 마포구</option>
            <option value="종로구">서울특별시 종로구</option>
            <option value="성동구">서울특별시 성동구</option>
            <option value="동작구">서울특별시 동작구</option>
            <option value="중랑구">서울특별시 중랑구</option>
            <option value="성북구">서울특별시 성북구</option>
            <option value="영등포구">서울특별시 영등포구</option>
            <option value="서초구">서울특별시 서초구</option>
            <option value="광진구">서울특별시 광진구</option>
            <option value="강동구">서울특별시 강동구</option>
            <option value="관악구">서울특별시 관악구</option>
            <option value="송파구">서울특별시 송파구</option>
            <option value="도봉구">서울특별시 도봉구</option>
            <!-- 다른 구/군 옵션들 추가 -->
        </select>
    </div>
    <div class="option-group">
        <label for="facilityType">시설 종류:</label>
        <select id="facilityType" class="option-select">
            <option value="health">헬스</option>
            <option value="pilates">필라테스</option>
            <option value="boxing">복싱</option>
        </select>
    </div>
</div>

<div class="main-container">
    <div class="left-margin"></div>
    <div class="content">
        <c:choose>
            <c:when test="${!empty allList}">
                <c:forEach var="allList" items="${allList}">
                    <div class="contentRange" onclick="goToDetail(${allList.detailNo})">
                        <div class="imgRange">
                            <img class="img"
                                 src="${contextPath}/images/detail/${allList.detailClassification}/${allList.detailBusinessEng}/${allList.detailBusinessEng}1.PNG"
                                 alt="">
                        </div>
                        <div class="buttonRange">
                            <button class="favorite-button">
                                <input type="hidden" class="memberNo" value="${member.memberNo}">
                                <input type="hidden" class="detailNo" value="${allList.detailNo}">
                                <img class="dibs" src="${contextPath}/images/detail/detailpage/dibs.png" alt="Favorite">
                            </button>
                        </div>
                        <div class="infoRange">
                            <h6 class="classification">${allList.detailKoClassification}</h6>
                            <h4 class="name">${allList.detailBusinessName}</h4>
                            <h6 class="address">${allList.detailRoadAddress}</h6>
                        </div>
                        <div class="ticketRange">
                            <div class="dailyTicket"><p class="boxText">일일권</p></div>
                            <div class="memberTicket"><p class="boxText">이지짐회원가</p></div>
                        </div>
                        <div class="priceRange">
                            <p class="price">${allList.detailMonthlyTicket}</p>
                            <p class="month">/월</p>
                        </div>
                        <div class="serviceRange">
                            <p class="freeService">무료 서비스</p>
                            <p class="provide">${allList.detailFreeService}</p>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>
    </div>
    <div class="map_wrap">
        <div id="map"></div>
    </div>
    <div class="right-margin"></div>
</div>

<script>
    function goToDetail(detailNo) {
        window.location.href = '${contextPath}/detail/view.do?detailNo=' + detailNo;
    }
</script>

</body>
</html>