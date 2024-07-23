<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<style>
.bg-image {
	background-image: url('/images/member/gym2.png'); /* 배경 이미지 경로 */
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	color: white; /* 텍스트 색상을 배경과 대조되도록 설정 */
	height: 500px; /* 배경 이미지의 높이 설정 */
	display: flex;
	align-items: center;
	justify-content: center;
	text-align: center; /* 텍스트 중앙 정렬 */
	padding: 50px; /* 내부 여백 설정 */
}

.display-3 {
	font-size: 3.5rem; /* 대제목의 글자 크기 */
	font-weight: 700; /* 대제목의 글자 굵기 */
}

.fw-normal {
	font-weight: bold; /* 일반 텍스트의 글자 굵기 */
}

.lead {
	font-size: 1.25rem; /* 부제목의 글자 크기 */
}

.icon-link {
	color: white; /* 링크 텍스트 색상 */
	text-decoration: none; /* 링크 밑줄 제거 */
	font-weight: bold; /* 링크 텍스트 굵기 */
	padding: 10px 20px; /* 링크 버튼의 여백 설정 */
	border: 2px solid white; /* 링크 버튼의 테두리 설정 */
	border-radius: 25px; /* 링크 버튼의 모서리 둥글게 설정 */
	transition: all 0.3s ease; /* 링크 버튼에 호버 효과를 부드럽게 적용 */
}

.icon-link:hover {
	background-color: rgba(255, 255, 255, 0.2); /* 링크 버튼에 호버 시 배경 색상 변경 */
	color: white; /* 링크 버튼 텍스트 색상 다시 설정 */
}

@font-face {
	font-family: 'HancomMalangMalang-Regular';
	src:
		url('https://fastly.jsdelivr.net/gh/projectnoonnu/2406-1@1.0/HancomMalangMalang-Regular.woff2')
		format('woff2');
	font-weight: 400;
	font-style: normal;
}

.display-3 {
	font-family: 'HancomMalangMalang-Regular';
	font-weight: 1000;
}

.icon-link, h2 {
	font-family: 'HancomMalangMalang-Regular';
	font-weight: 800;
}

.carousel-container {
	width: 100%; /* 원하는 너비 */
	height: 200px; /* 원하는 높이 */
	margin: 0 auto; /* 중앙 정렬 */
}

.carousel-container img {
	width: 100%;
	object-fit: cover; /* 이미지가 컨테이너를 채우도록 조정 */
}

.card-img-top {
	height: 200px; /* 원하는 이미지 높이 설정 */
	object-fit: cover; /* 이미지가 카드에 맞게 조정되도록 설정 */
}
.row{
text-align: center;
}

#chatbot_frame {
        position: absolute;
        margin-top: -50vh;  /* vh : 버티칼 */
        left: -300px;
        display: none;
    }

    .chatbot {
        position: absolute;
        border: 1px solid blue;
        z-index: 9999;  /* 항상 맨 앞으로 오게 => 9999 최고 숫자 부여 */
        margin-top: -50px;
        margin-left: 80%;
    }

</style>
</div>
<div
	class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-image">
	<div class="col-md-6 p-lg-5 mx-auto my-5">
		<h1 class="display-3">운동할 땐, EasyGym부터</h1>
		<div class="d-flex gap-3 justify-content-center lead fw-normal">
			<a class="icon-link" href="#"> 내 주변 운동시설 찾기</a>
		</div>
	</div>
	<div class="product-device shadow-sm d-none d-md-block"></div>
	<div class="product-device product-device-2 shadow-sm d-none d-md-block"></div>
</div>

<div class="container marketing">

    <div class="row">
  <div class="col-lg-4 text-center">
    <img src="/images/member/whey-protein.png" class="bd-placeholder-img rounded-circle" width="140" height="140" alt="Placeholder">
    <h2 class="fw-normal">헬스 보충제 쇼핑몰</h2>
    <p>체중 관리의 새로운 동반자! 효과적인 다이어트 보조제로 건강하게 목표를 달성하세요. 에너지와 자신감을 더해주는 완벽한 솔루션을 만나보세요.</p>
    <p><a class="btn btn-secondary" href="https://5colorsfood.co.kr/?gad_source=1&gclid=CjwKCAjwnei0BhB-EiwAA2xuBsNhqOgv84-y_YRFY6s6ZxLzjr-ilMy-ZYddi_MArhc9dVGj8F9HvRoCExsQAvD_BwE">바로가기 &raquo;</a></p>
  </div><!-- /.col-lg-4 -->
  <div class="col-lg-4 text-center">
    <img src="/images/member/low-carb-diet.png" class="bd-placeholder-img rounded-circle" width="140" height="140" alt="Placeholder">
    <h2 class="fw-normal">저탄수화물 베이커리</h2>
    <p>건강한 삶을 위한 맛있는 선택! 저탄수화물 베이커리로 풍미 가득한 맛과 영양을 동시에 즐기세요. 다이어트와 행복을 함께 느껴보세요.</p>
    <p><a class="btn btn-secondary" href="https://ketopantry.co.kr/29">바로가기 &raquo;</a></p>
  </div><!-- /.col-lg-4 -->
  <div class="col-lg-4 text-center">
    <img src="/images/member/weight.png" class="bd-placeholder-img rounded-circle" width="140" height="140" alt="Placeholder">
    <h2 class="fw-normal">홈트레이닝 플랫폼</h2>
    <p>집에서 편하게, 강력하게! 다양한 운동 프로그램과 전문가의 지도로 목표를 달성하고 운동의 새로운 즐거움을 경험해보세요.</p>
    <p><a class="btn btn-secondary" href="http://demo047.megaweb1.kr/">바로가기 &raquo;</a></p>
  </div><!-- /.col-lg-4 -->
</div><!-- /.row -->
</div>

<div id="carouselExampleAutoplaying"
	class="carousel slide carousel-container" data-bs-ride="carousel">
	<div class="carousel-inner">
		<div class="carousel-item active">
			<img src="/images/member/commerc1.png" class="d-block w-100"
				alt="광고이미지">
		</div>
		<div class="carousel-item">
			<img src="/images/member/commerc2.png" class="d-block w-100"
				alt="광고이미지">
		</div>
		<div class="carousel-item">
			<img src="/images/member/commerc3.png" class="d-block w-100"
				alt="광고이미지">
		</div>
<!-- 		<div class="carousel-item">
			<img src="/images/member/commerc4.png" class="d-block w-100"
				alt="광고이미지">
		</div> -->
	</div>
	<button class="carousel-control-prev" type="button"
		data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
		<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="visually-hidden">Previous</span>
	</button>
	<button class="carousel-control-next" type="button"
		data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
		<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="visually-hidden">Next</span>
	</button>
</div>

        <div class="container">
        <h2>헬스장 인기순위</h2>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">정글짐</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">멋짐</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">마음가짐</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <form action="${contextPath}/detail/showAll.do" method="get">
				<input type=hidden name="detailClassification" value="health">
				<button type="submit">헬스 전체 더 보기</button>
			</form>
        </div>
		
        <h2>요가/필라테스 인기순위</h2>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">호주 필라테스</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">슬림 필라테스</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">멋쟁이 필라테스</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <h2>복싱장 인기순위</h2>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">펀치 복싱장</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">몸짱 복싱</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">엄청나 복싱장</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            			<form action="${contextPath}/detail/showAll.do" method="get">
					<input type=hidden name="detailClassification" value="boxing">
					<input type=hidden name="detailStatus" value="popular">
					<button type="submit">복싱 인기 더 보기</button>
			</form>
        </div>
        
    </div>
<!-- 챗봇 -->
      <div class="chatbot">
           <iframe id="chatbot_frame" width="350" height="430" allow="microphone;"
           src="https://console.dialogflow.com/api-client/demo/embedded/835aec7e-894b-4357-b90d-e6fabbadfb94"></iframe>
           <a href="#" onclick="fn_chatbot()"> ☞ 챗봇에게 물어보기</a>  <!-- 챗봇 함수 만들기 -->
       </div>
    
<script src="/js/member/chatbot.js"></script>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
