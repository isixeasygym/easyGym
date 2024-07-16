<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<link rel="stylesheet" href="${contextPath}/css/detail/detail.css">
<script src="${contextPath}/js/detail/detail.js"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=7a8cfao4qf"></script>
<script>

        var map = new naver.maps.Map('map', {
            center: new naver.maps.LatLng(37.5112, 127.0981), // 잠실 롯데월드를 중심으로 하는 지도
            zoom: 15
        });

        var marker = new naver.maps.Marker({
            position: new naver.maps.LatLng(37.5112, 127.0981),
            map: map
        });
</script>
</head>
<style>
    #map {
        width: 100%;
        height: 500px;
    }
</style>
<body>
    <c:choose>
        <c:when test="${!empty details}">
            <div id="detailRange">
                <div id="animation_canvas">
                    <!-- 슬라이더 패널 : 움직이는 이미지가 전환되는 효과 -->
                    <div class="slider_panel">
                        <c:forEach var="i" begin="1" end="10">
                            <img class="slider_image" src="/images/${details.detailBusinessEng}/${details.detailBusinessEng}${i}.PNG"/>
                        </c:forEach>
                    </div>
                    <div class="control_panel">
                        <div class="control_button"></div>
                        <div class="control_button active"></div>
                        <div class="control_button"></div>
                        <div class="control_button"></div>
                        <div class="control_button"></div>
                        <div class="control_button"></div>
                        <div class="control_button"></div>
                        <div class="control_button"></div>
                        <div class="control_button"></div>
                        <div class="control_button"></div>
                    </div>
                    <!-- 좌우 컨트롤 버튼 패널 -->
                    <div class="left_right_control_panel">
                        <img class="left_control" src="${contextPath}/images/slide/leftArrow.png" alt="">
                        <img class="right_control" src="${contextPath}/images/slide/rightArrow.png" alt="">
                    </div>
                </div>
                <div id="firstInfo">
                    <h2>종로 스포짐</h2>
                    <img id="pointer" src="/works/image/pointer.png" alt=""><h4 id="address">서울특별시 종로구</h4><a href="#2"> ></a><br>
                    <img id="fiveStar" src="/works/image/html/sStar.JPG">
                    <p id="grade">4.8</p>
                    <a href="#1"><p id="gradeLink">후기 ></p></a><br>
                    <img id="dailyTicket" src="/works/image/html/dailyTicket.PNG"><br><br>
                </div>
                <div id="memberTicketRange">
                    <h4 id="memberTicket">회원권</h4>
                    <div class="memberTicketBox">
                        <h2>헬스 회원권</h2>
                        <p class="common fieldPrice">현장가</p>
                        <h2 class="ticketPrice">45,000</h2>
                    </div>
                </div>
                <div id="dailyAndInfoRange">
                    <h4 id="dailyTicketRange">일일권</h4>
                    <div class="memberTicketBox">
                        <h2>헬스</h2>
                        <p class="common dailyLimit"></p>
                        <p class="common fieldPrice">현장가</p>
                        <h2 class="ticketPrice">20,000</h2>
                    </div>
                    <div id="dailyInfoBox">
                        <h5 id="dailyInfo">일일권 제공 및 안내 사항</h5>
                        <h5 id="dailyInfoDetail">공용락커 사용 | 사워실 이용 가능 | 수건 무료제공 | 운동복 무료제공 | 실내용 운동화 지침</h5>
                    </div>
                </div>
                <div id="commentRange">
                    <textarea>
                       ${details.detailComment}
                    </textarea>
                </div>
                <div id="operatingRange">
                    <textarea>
                        ${details.detailServiceProgram}
                    </textarea>
                </div>
                <div id="convenienceFacility">
                    <h4>편의시설</h4>
                    <div id="FacilityRange">
                        <img class="FacilityImage" src="${contextPath}/images/conImage/analysis.PNG" alt="">
                        <img class="FacilityImage" src="${contextPath}/images/conImage/wifi.PNG" alt="">
                        <img class="FacilityImage" src="${contextPath}/images/conImage/bodyComposition.PNG" alt="">
                        <img class="FacilityImage" src="${contextPath}/images/conImage/cloth.PNG" alt="">
                        <img class="FacilityImage" src="${contextPath}/images/conImage/locker.PNG" alt="">
                        <img class="FacilityImage" src="${contextPath}/images/conImage/shower.PNG" alt="">
                        <img class="FacilityImage" src="${contextPath}/images/conImage/tower.PNG" alt="">
                    </div>
                </div>
                <div id="imageRange">
                    <h5>사진</h5>
                    <c:forEach var="i" begin="1" end="10">
                        <img src="/images/bbGym/${details.detailBusinessEng}${i}.PNG" height="200" width="200"/>
                    </c:forEach>
                </div>
                <div id="reviewRange">
                    <img src="/works/image/html/reviewImage.PNG">
                    <a name="1"></a>
                    <p>(익명의 회원)</p><br>
                    <img src="/works/image/html/star.JPG">
                    <p>sysdate</p>
                    <p id="reviewComment">review</p>
                </div>
                <div>
                    <h5>위치</h5>
                    <a name="2"></a>
                    <h1>맵 API 마커 표시 예시</h1>
                    <div id="map" style="width:100%;height:350px;"></div>
                </div>
                <p id="produ">easyGym은 통신판매의 중개자이며, 통신판매의 당사자가 아닙니다. 따라서<br>
                   다짐은 상품의 구매, 이용 및 환불 등과 관련한 의무와 책임은 각 판매자에게 있습니다.<br>
                   단, 회사가 직접 판매하는 통합회원권 상품의 경우, 다짐이 통신판매 당사자의 지위를 갖게 됩니다.
                </p>
                <footer>
                    <a href="www.naver.com"><button id="ticketChoice">회원권 선택</button></a>
                <footer>
            </div>
        </c:when>    
    </c:choose>            
</body>
</html>