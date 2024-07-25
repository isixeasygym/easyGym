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
<script>
    function goBack() {
        window.history.back();
    }
	$(document).ready(function() {
	    $(".favorite-button").each(function() {
	        var button = this;
	        var companyId = $(button).find('.companyId').val();
	        var userId = $(button).find('.userId').val();

	        $.ajax({
	            type: "GET",
	            url: "/getFavoriteStatus",
	            data: { companyId: companyId, userId: userId },
	            success: function(data) {
	                updateFavoriteButton(button, data);
	            },
	            error: function(xhr, status, error) {
	                console.error("Error: " + error);
	            }
	        });
	    });

	    $(".favorite-button").click(function(event) {
	        var button = this;

	        if ($(button).data('requestInProgress')) return; // 요청이 진행 중이면 무시

	        $(button).data('requestInProgress', true); // 요청 진행 중으로 설정
	        $(button).addClass('loading'); // 로딩 상태 CSS 적용

	        var companyId = $(button).find('.companyId').val();
	        var userId = $(button).find('.userId').val();

	        $.ajax({
	            type: "GET",
	            url: "/addFavorite",
	            data: { companyId: companyId, userId: userId },
	            success: function(data) {
	                if (data == "insert" || data == "delete") {
	                    alert(data == "insert" ? "찜 목록에 추가되었습니다." : "찜 목록에서 삭제되었습니다.");
	                    updateFavoriteButton(button, data);
	                } else if (data.startsWith("redirect:")) {
	                    window.location.href = data.substring(9);
	                } else {
	                    alert("알 수 없는 오류가 발생했습니다.");
	                }
	                $(button).data('requestInProgress', false); // 요청 완료로 설정
	                $(button).removeClass('loading'); // 로딩 상태 CSS 해제
	            },
	            error: function(xhr, status, error) {
	                console.error("Error: " + error);
	                alert(xhr + status + "오류가 발생했습니다." + error);
	                $(button).data('requestInProgress', false); // 요청 완료로 설정
	                $(button).removeClass('loading'); // 로딩 상태 CSS 해제
	            }
	        });

	        event.stopPropagation();
	    });

	    function updateFavoriteButton(button, status) {
	        var newSrc = (status == "insert") ? '${contextPath}/images/detail/detailpage/pickDibs.png' : '${contextPath}/images/detail/detailpage/dibs.png';
	        $(button).find('.dibs').attr('src', newSrc);
	    }
	});
	
</script>
    <meta charset="UTF-8">
    <title>메인페이지</title>
    <link rel="stylesheet" href="${contextPath}/css/detail/list.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="${contextPath}/js/detail/list.js"></script>
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9a9906a8b7e291e6dddbb2bd165b6d7f&libraries=services"></script>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</head>
<body>
    <div class="search-container">
        <button type="button" class="back-button" onclick="goBack()">&lt;</button>
        <form action="/detail/search.do" method="get" class="search-form">
            <input type="text" name="query" placeholder="검색어를 입력하세요..." class="search-input">
			<input type="hidden" name="detailClassification" value="health">
            <button type="submit" class="search-button">검색</button>
        </form>
    </div>
    <div class="content">
<div class="main-container">
    <div class="content">
        <c:choose>
            <c:when test="${!empty allList}">
                <c:forEach var="allList" items="${allList}">   
                        <div class="contentRange" onclick="goToDetail(${allList.detailNo})">
                           <div class="imgRange">
                               <img class="img" src="${contextPath}/images/detail/${allList.detailClassification}/${allList.detailBusinessEng}/${allList.detailBusinessEng}1.PNG" alt="">
                           </div>
                            <div class="buttonRange">
                                   <button class=" n" >
                                        <input  type="hidden" class="userId" value="${member.memberNo}">
                                        <input  type="hidden" class="companyId" value="${allList.detailNo}">
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
                               <p class="price">${allList.detailMonthlyTicket}</p><p class="month">/월</p>
                           </div>
                           
                           <div class="serviceRange">
                               <p class="freeService">무료 서비스</p>
                               <p class="provide">${allList.detailFreeService}</p>
                           </div>
                           <div class="contentBorder"></div>
                       </div>        
                    </form>                             
                </c:forEach>              
            </c:when>                
        </c:choose>        
    </div>       
    <div class="map_wrap">
        <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    </div>
</div>

<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(37.56682194967411, 126.97864942970189), // 지도의 중심좌표
            level: 8 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 지도를 클릭했을때 클릭한 위치에 마커를 추가하도록 지도에 클릭이벤트를 등록합니다
    <c:choose>
    <c:when test="${!empty allList}">
    <c:forEach var="allList" items="${allList}">
    addMarker(${allList.detailLatitude}, ${allList.detailLongitude}, "${allList.detailBusinessName}"); //마커 위치 입력
    </c:forEach>
    </c:when>
    </c:choose>

    // 마커를 생성하고 지도위에 표시하는 함수입니다
    function addMarker(p1, p2, content) {

        iwPosition = new kakao.maps.LatLng(p1, p2); //인포윈도우 표시 위치입니다

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            position: iwPosition
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);

// 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
            position: iwPosition,
            content: content
        });

// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
        infowindow.open(map, marker);

    }

</script>
</body>
</html>