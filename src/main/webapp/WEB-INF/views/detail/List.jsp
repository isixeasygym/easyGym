<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    Object member=session.getAttribute("member");
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
</script>
</head>
<body>
    <div class="search-container">
        <button type="button" class="back-button" onclick="goBack()">&lt;</button>
        <form action="/detail/search.do" method="get" class="search-form">
            <input type="text" name="query" placeholder="검색어를 입력하세요..." class="search-input">
			<input type="hidden" name="detailClassfication" value="health">
            <button type="submit" class="search-button">검색</button>
        </form>
    </div>
    <div class="content">    
        <c:choose>
            <c:when test="${!empty allList}">
                <c:forEach var="allList" items="${allList}">   
                        <div class="contentRange" onclick="goToDetail(${allList.detailNo})">
                           <div class="imgRange">
                               <img class="img" src="${contextPath}/images/detail/${allList.detailClassification}/${allList.detailBusinessEng}/${allList.detailBusinessEng}1.PNG" alt="">
                           </div>
                            <div class="buttonRange">
                                   <button class="favorite-button" >
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
	<div id="mapRange">
        <h5>위치</h5>
        <a name="2"></a>
		<div id="map" style="width:30%;height:350px;"></div>
	</div>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c4473ba88781ad9e6acab08ae4ef53e5"></script>
		<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new kakao.maps.LatLng(${details.detailLatitude}, ${details.detailLongitude})								, // 지도의 중심좌표
				draggable: false, 
		        level: 3 // 지도의 확대 레벨
		    };

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

		// 마커가 표시될 위치입니다 
		var markerPosition  = new kakao.maps.LatLng(${details.detailLatitude}, ${details.detailLongitude}); 

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
		</script>
</body>
</html>