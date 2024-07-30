<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="member" value="${member}" scope="session"/>
<%

	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${details.detailBusinessName}</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		var contextPath = "${pageContext.request.contextPath}";
	</script>
	<link rel="stylesheet" href="${contextPath}/css/detail/detail.css">
	<script src="${contextPath}/js/detail/detail.js"></script>
	<script>
		$(document).ready(function() {
			var requestInProgress = false;

			function updateFavoriteButton(button, status) {
				var newSrc = (status === "insert")
						? '${contextPath}/images/detail/detailpage/pickDibs.png'
						: '${contextPath}/images/detail/detailpage/dibs.png';
				$(button).find('.dibs').attr('src', newSrc);
			}

			function checkFavoriteStatus() {
				$(".favorite-button").each(function() {
					var button = this;
					var companyId = $(button).find('.companyId').val();
					var userId = $(button).find('.userId').val();

					$.ajax({
						type: "GET",
						url: "${contextPath}/getFavoriteStatus",
						data: { companyId: companyId, userId: userId },
						success: function(data) {
							if (data === "insert" || data === "delete") {
								updateFavoriteButton(button, data);
							} else if (data === "nologin") {
								// Handle no login case
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
			}

			// Page load
			checkFavoriteStatus();

			// Button click
			$(".favorite-button").click(function(event) {
				if (requestInProgress) return;

				requestInProgress = true;

				var button = this;
				var companyId = $(button).find('.companyId').val();
				var userId = $(button).find('.userId').val();

				if (!userId || !companyId) {
					alert("회원 정보가 없습니다.");
					var currentPageUrl = encodeURIComponent(window.location.href);
					window.location.href = '${contextPath}/member/loginForm.do?redirect=' + currentPageUrl;
					requestInProgress = false;
					return;
				}

				$.ajax({
					type: "GET",
					url: "${contextPath}/addFavorite",
					data: { companyId: companyId, userId: userId },
					success: function(data) {
						if (data === "insert" || data === "delete") {
							alert(data === "insert"
									? "찜 목록에 추가되었습니다."
									: "찜 목록에서 삭제되었습니다.");
							updateFavoriteButton(button, data);
						} else if (data === "nologin") {
							alert("회원 정보가 없습니다.");
							var currentPageUrl = encodeURIComponent(window.location.href);
							window.location.href = '${contextPath}/member/loginForm.do?redirect=' + currentPageUrl;
						} else {
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
					<img class="left_control" src="${contextPath}/images/detail/detailpage/arrow_pre.png" alt="">
					<img class="right_control" src="${contextPath}/images/detail/detailpage/arrow_next.png" alt="">
				</div>
			</div>
			<div id="firstInfo">
				<h3 id="companyName">${details.detailBusinessName}</h3>
				<img id="pointer" src="${contextPath}/images/detail/detailpage/address.PNG" alt=""><h4 id="address">${details.detailRoadAddress}</h4><a id ="toAddress" href="#2"> ></a><br>
				<img id="fiveStar" src="${contextPath}/images/detail/detailpage/sStar.JPG">
				<!--<p id="grade">4.8</p>-->
				<p id="gradeLink">후기<a id="toReview"href="#1"> ></a></p><br>
				<img id="dailyTicket" src="${contextPath}/images/detail/detailpage/dailyTicket.PNG"><br><br>
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
					<h3 class="ticket">이지짐 회원권</h3>
					<h4 id="fieldMonthlyPrice">이지짐회원가</h4>
					<h3 class="ticketPrice">${details.detailMonthlyTicket}<p class="month">/월</p></h3>
				</div>
			</div>
			<div id="dailyAndInfoRange">
				<h4 id="dailyTicketRange">일일권</h4>
				<div class="memberTicketBox">
					<h3 class="ticket">${details.detailKoClassification}</h3>
					<p  id="dailyLimit">1회 입장 제한</p>
					<h4 id="fieldDailyPrice">이지짐회원가</h4>
					<h3 class="ticketPrice">${details.detailDailyTicket}</h3>
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
				<h4 id="facilityInfo">편의시설</h4>
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
				<h4 id="imageInfo">사진</h4>
				<div id="imageBox">
					<c:forEach var="i" begin="1" end="10">
						<img src="${contextPath}/images/detail/${details.detailClassification}/${details.detailBusinessEng}/${details.detailBusinessEng}${i}.PNG" height="130" width="130"/>
					</c:forEach>
				</div>
			</div>
			<div id="reviewImageRange">
				<div id="reviewImage">
					<c:choose>
						<c:when test="${!empty reviewImage}">
							<c:forEach var="reviewImage" items="${reviewImage}">
								<img class="reviewImage" style="width:130px; height:130px;" src="${contextPath}/images/detail/reviewImage/${reviewImage.detailNo}/${reviewImage.memberNo}/${reviewImage.reviewImgName}"/>
							</c:forEach>
						</c:when>
					</c:choose>
				</div>
			</div>
			<div id="reviewRange">
				<a name="1"></a>
				<div class="reviewWriteRange">
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
					<div id="fileRange">
						<p id="fileInfo">이미지파일 첨부</p>
						<input type="file" id="reviewImageName" name="reviewImageName">
					</div>
					<button id="writeButton" onclick="writeSubmit()">글쓰기
					</button>
					<div id="writeBorder"></div>
				</div>
			</div>
			<div id="reviewContainer">
			    <c:choose>
			        <c:when test="${reviewCount > 2}">
			            <c:forEach var="review" items="${review}" varStatus="status">
			                <c:if test="${status.index < 2}">
			                    <div class="ReviewRange" data-review-no="${review.reviewNo}">
			                        <button class="deleteButton" onclick="deleteComment(${review.reviewNo})">삭제</button>
			                        <div class="personReviewRange">
			                            <img class="reviewPicture" src="${contextPath}/images/detail/detailpage/reviewImage.PNG">
			                            <p class="anonymous">(익명의 회원)</p>
			                            <p class="reviewDate">${review.reviewDate}</p>
			                            <textarea class="reviewComment" readonly>${review.reviewComment}</textarea>
			                        </div>
			                    </div>
			                </c:if>
			            </c:forEach>
			            <!-- 두 개의 리뷰가 출력된 후에 전체보기 링크를 추가 -->
			            <a href="${contextPath}/review.jsp?companyId=${details.detailNo}" class="viewAllReviews">후기 ${reviewCount}개 전체보기</a>
			        </c:when>
			        <c:otherwise>
			            <c:forEach var="review" items="${review}">
			                <div class="ReviewRange" data-review-no="${review.reviewNo}">
			                    <button class="deleteButton" onclick="deleteComment(${review.reviewNo})">삭제</button>
			                    <div class="personReviewRange">
			                        <img class="reviewPicture" src="${contextPath}/images/detail/detailpage/reviewImage.PNG">
			                        <p class="anonymous">(익명의 회원)</p>
			                        <p class="reviewDate">${review.reviewDate}</p>
			                        <textarea class="reviewComment" readonly>${review.reviewComment}</textarea>
			                    </div>
			                </div>
			            </c:forEach>
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
				var mapContainer = document.getElementById('map'),
						mapOption = {
							center: new kakao.maps.LatLng(${details.detailLatitude}, ${details.detailLongitude}),
							draggable: false,
							level: 3
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

			<p id="produ">easyGym은 통신판매의 중개자이며, 통신판매의 당사자가 아닙니다. 따라서<br>
				이지짐은 상품의 구매, 이용 및 환불 등과 관련한 의무와 책임은 각 판매자에게 있습니다.<br>
				단, 회사가 직접 판매하는 통합회원권 상품의 경우, 다짐이 통신판매 당사자의 지위를 갖게 됩니다.
			</p>
			<div id="fixedContainer">
				<form action="${contextPath}/payform/payformForm.do" method="POST">
					<input type="hidden" name="memberNo" value="${member.memberNo}">
					<input type="hidden" name="detailNo" value="${details.detailNo}">
					<button type="submit" id="ticketChoice">회원권 선택</button>
				</form>
			</div>
		</div>
	</c:when>
</c:choose>
</body>
</html>