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
<title>${details.detailBusinessName}</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="${contextPath}/css/detail/detail.css">
<script src="${contextPath}/js/detail/detail.js"></script>
<script>
    $(document).ready(function() {
        $('#myTextarea').on('input', function() {
            var charCount = $(this).val().length;
            $('#charCount').text(charCount + '/150');
        });
    });
</script>
</head>
<body>
    <c:choose>
        <c:when test="${!empty details}">
            <div id="detailRange">
                <div id="animation_canvas">
                    <!-- 슬라이더 패널 : 움직이는 이미지가 전환되는 효과 -->
                    <div class="slider_panel">
                        <c:forEach var="i" begin="1" end="10">
                            <img class="slider_image" src="${contextPath}/images/detail/${details.detailClassification}/${details.detailBusinessEng}/${details.detailBusinessEng}${i}.PNG"/>
                        </c:forEach>
                    </div>
                    <!-- 좌우 컨트롤 버튼 패널 -->
                    <div class="left_right_control_panel">
                        <img class="left_control" src="${contextPath}/images/detail/detailpage/SImage_Left.gif" alt="">
                        <img class="right_control" src="${contextPath}/images/detail/detailpage/SImage_Right.gif" alt="">
                    </div>
                </div>
                <div id="firstInfo">
                    <h2>종로 스포짐</h2>
                    <img id="pointer" src="/works/image/pointer.png" alt=""><h4 id="address">${details.detailRoadAddress}</h4><a href="#2"> ></a><br>
                    <img id="fiveStar" src="/works/image/html/sStar.JPG">
                    <p id="grade">4.8</p>
                    <a href="#1"><p id="gradeLink">후기 ></p></a><br>
                    <img id="dailyTicket" src="/works/image/html/dailyTicket.PNG"><br><br>
                </div>
				<div class="buttonRange">
	               <button class="favorite-button" >
						<input  type="hidden" class="userId" value="${member.memberNo}">
						<input  type="hidden" class="companyId" value="${details.detailNo}">
	                   <img class="dibs" src="${contextPath}/images/detail/detailpage/dibs.png" alt="Favorite">
	               </button>
	            </div>
                <div id="memberTicketRange">
                    <h4 id="memberTicket">회원권</h4>
                    <div class="memberTicketBox">
                        <h2>${details.detailKoClassification} 회원권</h2>
                        <p class="common fieldPrice">현장가</p>
                        <h2 class="ticketPrice">${details.detailMonthlyTicket}<p class="month">/월</p></h2>
                    </div>
                </div>
                <div id="dailyAndInfoRange">
                    <h4 id="dailyTicketRange">일일권</h4>
                    <div class="memberTicketBox">
                        <h2>헬스</h2>
                        <p class="common dailyLimit"></p>
                        <p class="common fieldPrice">현장가</p>
                        <h2 class="ticketPrice">${details.detailDailyTicket}</h2>
                    </div>
                    <div id="dailyInfoBox">
                        <h5 id="dailyInfo">일일권 제공 및 안내 사항</h5>
                        <h5 id="dailyInfoDetail">공용락커 사용 | 사워실 이용 가능 | 수건 무료제공 | 운동복 무료제공 | 실내용 운동화 지침</h5>
                    </div>
                </div>
                <div id="commentRange">
                    <textarea spellcheck="false" class="auto-resize-textarea" readonly>
                       ${details.detailComment}
                    </textarea>
                </div>
                <div id="operatingRange" >
                    <textarea spellcheck="false" class="auto-resize-textarea" readonly>
                        ${details.detailServiceProgram}
                    </textarea>
                </div>
                <div id="convenienceFacility">
                    <h4>편의시설</h4>
                    <div id="FacilityRange">
                        <img class="FacilityImage" src="${contextPath}/images/detail/conImage/analysis.PNG" alt="">
                        <img class="FacilityImage" src="${contextPath}/images/detail/conImage/wifi.PNG" alt="">
                        <img class="FacilityImage" src="${contextPath}/images/detail/conImage/bodyComposition.PNG" alt="">
                        <img class="FacilityImage" src="${contextPath}/images/detail/conImage/cloth.PNG" alt="">
                        <img class="FacilityImage" src="${contextPath}/images/detail/conImage/locker.PNG" alt="">
                        <img class="FacilityImage" src="${contextPath}/images/detail/conImage/shower.PNG" alt="">
                        <img class="FacilityImage" src="${contextPath}/images/detail/conImage/tower.PNG" alt="">
                    </div>
                </div>
                <div id="imageRange">
                    <h5>사진</h5>
					<div id="imageBox">
	                    <c:forEach var="i" begin="1" end="10">
	                        <img src="${contextPath}/images/detail/${details.detailClassification}/${details.detailBusinessEng}/${details.detailBusinessEng}${i}.PNG" height="165" width="165"/>
	                    </c:forEach>
					</div>	
                </div>
				<div id="reviewImageRange">
					<div id="reviewImage">
					</div>
				</div>
                <div id="reviewRange">
					<a name="1"></a>
					<div class="reviewWriteRange">
						<div id="writeBorder">
							<div class="rating">
							    <input type="radio" id="star5" name="detailScope" value="5" /><label for="star5" title="5 stars">★</label>
							    <input type="radio" id="star4" name="detailScope" value="4" /><label for="star4" title="4 stars">★</label>
							    <input type="radio" id="star3" name="detailScope" value="3" /><label for="star3" title="3 stars">★</label>
							    <input type="radio" id="star2" name="detailScope" value="2" /><label for="star2" title="2 stars">★</label>
							    <input type="radio" id="star1" name="detailScope" value="1" /><label for="star1" title="1 star">★</label>
							</div>
							<div id="textArea">
								<textarea id="myTextarea" maxlength="150"></textarea>
								<div id="charCount">0/150</div>
							</div>
							<!--<div id="fileRange">
								<p id="fileInfo">이미지파일 첨부</p>
								 <input type="file" id="reviewImageName" name="reviewImageName">
			               	</div>--> 
							<button id="writeButton" onclick="writeSubmit()">글쓰기
							</button>
						</div>	
			        </div>
					<div id="reviewContainer">
				       <c:choose>
				           <c:when test="${sessionScope.getReview == 1}">
				               <c:forEach var="review" items="${review}">
				                   <div class="ReviewRange">
									<button class="deleteButton" onclick="deleteComment(${review.reviewNo})">Delete</button> 
								
				                       <div class="personReviewRange">
				                           <img class="reviewImage" src="${contextPath}/images/detail/detailpage/reviewImage.PNG">
				                           <p>(익명의 회원)</p>
				                           <img src="${contextPath}/images/detail/detailpage/star.JPG">
				                           <p>${review.reviewDate}</p>
				                           <p class="reviewComment">${review.reviewComment}</p>
				                       </div>
				                   </div>
				               </c:forEach>
				           </c:when>
				           <c:otherwise>
				               <h2>리뷰가 없습니다</h2>
				           </c:otherwise>
				       </c:choose>
				   	</div>
	                <div id="mapRange">
	                    <h5>위치</h5>
	                    <a name="2"></a>
						<div id="map" style="width:85%;height:350px;"></div>
					</div>
						<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c4473ba88781ad9e6acab08ae4ef53e5"></script>
						<script>
						var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
						    mapOption = { 
						        center: new kakao.maps.LatLng(37.5001556, 126.9309597), // 지도의 중심좌표
						        level: 3 // 지도의 확대 레벨
						    };
	
						var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
						// 마커가 표시될 위치입니다 
						var markerPosition  = new kakao.maps.LatLng(37.5001556, 126.9309597); 
	
						// 마커를 생성합니다
						var marker = new kakao.maps.Marker({
						    position: markerPosition
						});
	
						// 마커가 지도 위에 표시되도록 설정합니다
						marker.setMap(map);
						</script>
	                
                <p id="produ">easyGym은 통신판매의 중개자이며, 통신판매의 당사자가 아닙니다. 따라서<br>
                   다짐은 상품의 구매, 이용 및 환불 등과 관련한 의무와 책임은 각 판매자에게 있습니다.<br>
                   단, 회사가 직접 판매하는 통합회원권 상품의 경우, 다짐이 통신판매 당사자의 지위를 갖게 됩니다.
                </p>
                <footer>
					<form action="${contextPath}/payform/payform.do" method="get">
						<input  type="hidden" name="memberNo" value="${member.memberNo}">
						<input type="hidden" name="detailNo" value="${allList.detailNo}">
						<button type="submit" id="ticketChoice">회원권 선택</button>
					</form>	
                <footer>
            </div>
        </c:when>    
    </c:choose>            
</body>
</html>