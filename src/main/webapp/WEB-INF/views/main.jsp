<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<style>
.image.featured img {
    width: 100%; /* 너비를 부모 요소에 맞추어 조정 */
    height: 270px;; /* 비율을 유지하면서 높이 자동 조정 */
}
/* 카드 이미지 스타일 */
.card-img-top {
    width: 100%; /* 이미지의 너비를 카드의 너비에 맞춤 */
    height: 200px; /* 이미지 높이를 고정 */
    object-fit: cover; /* 비율 유지하며 잘라냄 */
}

/* 카드 스타일 */
.card {
    display: flex;
    flex-direction: column;
    height: 100%; /* 카드 높이를 열의 높이에 맞춤 */
    overflow: hidden; /* 카드의 넘치는 부분을 숨김 */
}

/* 카드 바디 스타일 */
.card-body {
    flex: 1; /* 카드 내용 영역이 남는 공간을 차지하도록 설정 */
}
footer.major {
    text-align: center; /* 자식 요소들을 가운데 정렬 */
}
.intro-text {
    font-size: 17px; /* 글꼴 크기를 작게 설정 */
    color: #888; /* 옅은 회색으로 설정 */
}

@font-face {
    font-family: 'S-CoreDream-3Light';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

/* 기본 폰트 적용 */
p {
    font-family: 'S-CoreDream-3Light', sans-serif; /* 폰트가 적용되지 않을 경우를 대비하여 대체 폰트 설정 */
}
h2{
	font-family: 'KorailRoundGothicBold';
}

</style>
<!-- Banner -->
<section id="banner">
    <div class="inner">
        <header>
            <h2>EASYGYM</h2>
        </header>
        <p>This is <strong>EASYGYM</strong>. Discover<br />
            the Best Gyms near you.
        </p>
        <footer>
            <ul class="buttons stacked">
                <li><a href="${contextPath}/detail/search.do?query=" class="button fit scrolly">Search My Location</a></li>
            </ul>
        </footer>
    </div>
</section>

<!-- Main -->
<article id="main">
    <header class="special container">
        <span></span>
        <h4 class="intro-text">Discover the Best Gyms, Pilates Studios, and Boxing Facilities Near You<br>
		Easily find the top fitness locations around you. <br>
		We help you find the perfect place that matches your fitness needs and lifestyle.
        </h4>
    </header>

<!-- CTA -->
<section id="cta">
    <header>
        <h2>사업자이신가요?</h2>
        <p>이지짐 사업자로 등록하고, 헬스장이나 운동시설을 보다 효과적으로 관리하고 홍보하세요.<br>지금 등록하셔서 귀하의 시설을 많은 고객들에게 알리고, 더 많은 기회를 잡아보세요!</p>
    </header>
    <footer>
        <ul class="buttons">
            <li><a href="/member/operLoginForm.do" class="button primary">Sign In</a></li>
            <li><a href="/member/oprJoin.do" class="button">Sign Up</a></li>
        </ul>
    </footer>
</section>
&nbsp;
<div class="container">
    <h2>헬스장 인기순위</h2>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <!-- 첫 번째 헬스장 -->
		<c:choose>
		    <c:when test="${not empty healthList[0]}">
		        <div class="col">
		                <a href="${contextPath}/detail/detail.do?detailNo=${healthList[0].detailNo}">
		            <div class="card shadow-sm">
		                    <img src="${contextPath}/images/detail/${healthList[0].detailClassification}/${healthList[0].detailBusinessEng}/${healthList[0].detailBusinessEng}1.PNG" class="card-img-top" alt="${healthList[0].detailBusinessName}">
		                <div class="card-body">
		                        <p class="card-text">${healthList[0].detailBusinessName}</p>
		                    	<p class="card-text">${healthList[0].detailRoadAddress}</p>
		                </div>
		            </div>
		                </a>
		        </div>
		    </c:when>
		    <c:otherwise>
		        <div class="col">
		            <div class="card shadow-sm">
		                <img src="${contextPath}/images/placeholder.png" class="card-img-top" alt="Placeholder">
		                <div class="card-body">
		                    <p class="card-text">헬스장 정보가 없습니다.</p>
		                </div>
		            </div>
		        </div>
		    </c:otherwise>
		</c:choose>
        <!-- 두 번째 헬스장 -->
        <c:choose>
            <c:when test="${not empty healthList[1]}">
                <div class="col">
					<a href="${contextPath}/detail/detail.do?detailNo=${healthList[1].detailNo}">
                    <div class="card shadow-sm">
                        <img src="${contextPath}/images/detail/${healthList[1].detailClassification}/${healthList[1].detailBusinessEng}/${healthList[1].detailBusinessEng}2.PNG" class="card-img-top" alt="${healthList[1].detailBusinessName}">
                        <div class="card-body">
                            <p class="card-text">${healthList[1].detailBusinessName}</p>
                            <p class="card-text">${healthList[1].detailRoadAddress}</p>
                        </div>
                    </div>
					</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col">
                    <div class="card shadow-sm">
                        <img src="${contextPath}/images/placeholder.png" class="card-img-top" alt="Placeholder">
                        <div class="card-body">
                            <p class="card-text">헬스장 정보가 없습니다.</p>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <!-- 세 번째 헬스장 -->
        <c:choose>
            <c:when test="${not empty healthList[2]}">
                <div class="col">
					<a href="${contextPath}/detail/detail.do?detailNo=${healthList[2].detailNo}">
                    <div class="card shadow-sm">
                        <img src="${contextPath}/images/detail/${healthList[2].detailClassification}/${healthList[2].detailBusinessEng}/${healthList[2].detailBusinessEng}3.PNG" class="card-img-top" alt="${healthList[2].detailBusinessName}">
                        <div class="card-body">
                            <p class="card-text">${healthList[2].detailBusinessName}</p>
                            <p class="card-text">${healthList[2].detailRoadAddress}</p>
                        </div>
                    </div>
					</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col">
                    <div class="card shadow-sm">
                        <img src="${contextPath}/images/placeholder.png" class="card-img-top" alt="Placeholder">
                        <div class="card-body">
                            <p class="card-text">헬스장 정보가 없습니다.</p>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
	<footer class="major">
	<ul class="buttons">
        <li><a href="${contextPath}/detail/search.do?query=&detailClassification=health" class="button primary">헬스 더보기</a></li>
   </ul>
   </footer>
    <h2>필라테스 인기순위</h2>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <!-- 첫 번째 필라테스 -->
        <div class="col">
			<a href="${contextPath}/detail/detail.do?detailNo=${pilatesList[0].detailNo}">
            <div class="card shadow-sm">
                <img src="${contextPath}/images/detail/${pilatesList[0].detailClassification}/${pilatesList[0].detailBusinessEng}/${pilatesList[0].detailBusinessEng}1.PNG" class="card-img-top" alt="${pilatesList[0].detailBusinessName}">
                <div class="card-body">
                    <p class="card-text">${pilatesList[0].detailBusinessName}</p>
                    <p class="card-text">${pilatesList[0].detailRoadAddress}</p>
                </div>
            </div>
			</a>
        </div>
        <!-- 두 번째 필라테스 -->
        <div class="col">
			<a href="${contextPath}/detail/detail.do?detailNo=${pilatesList[1].detailNo}">
            <div class="card shadow-sm">
                <img src="${contextPath}/images/detail/${pilatesList[1].detailClassification}/${pilatesList[1].detailBusinessEng}/${pilatesList[1].detailBusinessEng}2.PNG" class="card-img-top" alt="${pilatesList[1].detailBusinessName}">
                <div class="card-body">
                    <p class="card-text">${pilatesList[1].detailBusinessName}</p>
                    <p class="card-text">${pilatesList[1].detailRoadAddress}</p>
                </div>
            </div>
			</a>
        </div>
        <!-- 세 번째 필라테스 -->
        <div class="col">
			<a href="${contextPath}/detail/detail.do?detailNo=${pilatesList[2].detailNo}">
            <div class="card shadow-sm">
                <img src="${contextPath}/images/detail/${pilatesList[2].detailClassification}/${pilatesList[2].detailBusinessEng}/${pilatesList[2].detailBusinessEng}3.PNG" class="card-img-top" alt="${pilatesList[2].detailBusinessName}">
                <div class="card-body">
                    <p class="card-text">${pilatesList[2].detailBusinessName}</p>
                    <p class="card-text">${pilatesList[2].detailRoadAddress}</p>
                </div>
            </div>
			</a>
        </div>
    </div>
	<footer class="major">
	<ul class="buttons">
        <li><a href="${contextPath}/detail/search.do?query=&detailClassification=pilates"" class="button primary">필라테스 더보기</a></li>
   </ul>
   </footer>
   &nbsp;
    <h2>복싱장 인기순위</h2>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <!-- 첫 번째 복싱장 -->
        <div class="col">
			<a href="${contextPath}/detail/detail.do?detailNo=${boxingList[0].detailNo}">
            <div class="card shadow-sm">
                <img src="${contextPath}/images/detail/${boxingList[0].detailClassification}/${boxingList[0].detailBusinessEng}/${boxingList[0].detailBusinessEng}1.PNG" class="card-img-top" alt="${boxingList[0].detailBusinessName}">
                <div class="card-body">
                    <p class="card-text">${boxingList[0].detailBusinessName}</p>
                    <p class="card-text">${boxingList[0].detailRoadAddress}</p>
                </div>
            </div>
			</a>
        </div>
        <!-- 두 번째 복싱장 -->
        <div class="col">
			<a href="${contextPath}/detail/detail.do?detailNo=${boxingList[1].detailNo}">
            <div class="card shadow-sm">
                <img src="${contextPath}/images/detail/${boxingList[1].detailClassification}/${boxingList[1].detailBusinessEng}/${boxingList[1].detailBusinessEng}2.PNG" class="card-img-top" alt="${boxingList[1].detailBusinessName}">
                <div class="card-body">
                    <p class="card-text">${boxingList[1].detailBusinessName}</p>
                    <p class="card-text">${boxingList[1].detailRoadAddress}</p>
                </div>
            </div>
			</a>
        </div>
        <!-- 세 번째 복싱장 -->
        <div class="col">
			<a href="${contextPath}/detail/detail.do?detailNo=${boxingList[2].detailNo}">
            <div class="card shadow-sm">
                <img src="${contextPath}/images/detail/${boxingList[2].detailClassification}/${boxingList[2].detailBusinessEng}/${boxingList[2].detailBusinessEng}3.PNG" class="card-img-top" alt="${boxingList[2].detailBusinessName}">
                <div class="card-body">
                    <p class="card-text">${boxingList[2].detailBusinessName}</p>
                    <p class="card-text">${boxingList[2].detailRoadAddress}</p>
                </div>
            </div>
			</a>
        </div>
    </div>
	<footer class="major">
	<ul class="buttons">
        <li><a href="${contextPath}/detail/search.do?query=&detailClassification=boxing" class="button primary">복싱 더보기</a></li>
   </ul>
   </footer>
</div>
&nbsp;
<!-- Shop -->

    <section class="wrapper style1 container special">
      <h2>추천 쇼핑 및 참고 사이트</h2>
        <div class="row">
            <div class="col-4 col-12-narrower">
                <section>
                    <span>
                    <a href="https://5colorsfood.co.kr/?gad_source=1&gclid=CjwKCAjwnei0BhB-EiwAA2xuBsNhqOgv84-y_YRFY6s6ZxLzjr-ilMy-ZYddi_MArhc9dVGj8F9HvRoCExsQAvD_BwE" class="image featured">
                    <img src="/images/member/mainshop2.png" alt="" />
                    </a>
                    </span>
                    <header>
                        <h3>헬스 보충제 쇼핑몰</h3>
                    </header>
                    <p>체중 관리의 새로운 동반자! 효과적인 다이어트 보조제로 건강하게 목표를 달성하세요. 에너지와 자신감을 더해주는 완벽한 솔루션을 만나보세요.</p>
                </section>
            </div>
            <div class="col-4 col-12-narrower">
                <section>
                    <span><a href="https://zerotohero.co.kr/?gad_source=1&gclid=CjwKCAjw2dG1BhB4EiwA998cqJBk0UsTIn0cORgsB6ViiUCPsy3b9s8IC-0nJx1RATkIcp457mc2uxoCDTsQAvD_BwE" class="image featured"><img src="/images/member/mainShop1.png" alt="" /></a></span>
                    <header>
                        <h3>헬스 용품 쇼핑몰</h3>
                    </header>
                    <p>건강한 삶을 위한 맛있는 선택! 저탄수화물 베이커리로 풍미 가득한 맛과 영양을 동시에 즐기세요. 다이어트와 행복을 함께 느껴보세요.</p>
                </section>
            </div>
            <div class="col-4 col-12-narrower">
                <section>
                    <span><a href="http://demo047.megaweb1.kr/" class="image featured"><img src="/images/member/mainShop4.jpg" alt="" /></a></span>
                    <header>
                        <h3>홈트레이닝 참고 사이트</h3>
                    </header>
                    <p>집에서 편하게, 강력하게! 다양한 운동 프로그램과 전문가의 지도로 목표를 달성하고 운동의 새로운 즐거움을 경험해보세요.</p>
                </section>
            </div>
        </div>
    </section>

    
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>