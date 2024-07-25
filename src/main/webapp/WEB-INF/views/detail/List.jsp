<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9a9906a8b7e291e6dddbb2bd165b6d7f&libraries=services"></script>
    <script>
        function goHome() {
            window.location.href="${contextPath}/main.do";
        }
    </script>
</head>
<body>
<div class="search-container">
    <form action="${contextPath}/detail/search.do" method="get" class="search-form">
        <button type="button" class="back-button" onclick="goHome()"><img src="${contextPath}/images/detail/listimg/home_icon.png" alt="홈으로 가기"></button>
        <input type="text" name="query" placeholder="업체명을 입력하세요..." class="search-input">
        <input type="hidden" name="detailClassification" value="health">
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
            <option value="송파구">서울특별시 송파구</option>
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
                                <input type="hidden" class="userId" value="${member.memberNo}">
                                <input type="hidden" class="companyId" value="${allList.detailNo}">
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
    const urlParams = new URLSearchParams(window.location.search); //url param들을 불러옴

    window.onload = function () { //윈도우가 로드되면 url 확인해서 옵션을 해당 옵션으로 설정
        const query = urlParams.get('query');
        const detailClassification = urlParams.get('detailClassification');

        if (query) { //쿼리는 '서울특별시 강남구' 이런 형식으로 되어있는데 이걸 배열로 받아서 1번 배열이 들어있는 String만 확인
            const district = query.split(' ')[1];  // "서울특별시 강남구"에서 "강남구" 추출
            document.getElementById('districtSelect').value = district; //옵션값을 읽어옴
        }

        if (detailClassification) {
            document.getElementById('facilityType').value = detailClassification; //옵션값을 읽어옴
        }
    }

    document.getElementById('districtSelect').addEventListener('change', updateUrl); //옵션값이 바뀔때마다 이 함수를 실행
    document.getElementById('facilityType').addEventListener('change', updateUrl);  //옵션값이 바뀔때마다 이 함수를 실행

    function updateUrl() { //옵션값이 바뀌면 해당 옵션값을 읽어와서 url로 보냄
        const selectedDistrict = document.getElementById('districtSelect').value;
        const facilityType = document.getElementById('facilityType').value;
        if (selectedDistrict != "default") {
            const url = '${contextPath}/detail/search.do?query=서울특별시 ' + selectedDistrict + '+&detailClassification=' + facilityType;
            window.location.href = url;
        } else {
            const url = '${contextPath}/detail/showAll.do?detailClassification='+facilityType;
            window.location.href = url;
        }
    }

    var mapContainer = document.getElementById('map'),
        mapOption = {
            center: new kakao.maps.LatLng(37.56682194967411, 126.97864942970189),
            level: 8
        };

    var map = new kakao.maps.Map(mapContainer, mapOption);

    <c:choose>
    <c:when test="${!empty allList}">
    <c:forEach var="allList" items="${allList}">
    addMarker(${allList.detailLatitude}, ${allList.detailLongitude}, "${allList.detailBusinessName}");
    </c:forEach>
    </c:when>
    </c:choose>

    function addMarker(p1, p2, content) {
        iwPosition = new kakao.maps.LatLng(p1, p2);

        var marker = new kakao.maps.Marker({
            position: iwPosition
        });

        marker.setMap(map);

        var infowindow = new kakao.maps.InfoWindow({
            position: iwPosition,
            content: content
        });

        infowindow.open(map, marker);
    }
</script>

</body>
</html>