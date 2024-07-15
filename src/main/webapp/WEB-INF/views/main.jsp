<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<style>
.bg-image {
	background-image: url('/images/gym2.png'); /* 배경 이미지 경로 */
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
</style>

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
<div id="carouselExampleAutoplaying"
	class="carousel slide carousel-container" data-bs-ride="carousel">
	<div class="carousel-inner">
		<div class="carousel-item active">
			<img src="/images/commerc1.png" class="d-block w-100"
				alt="광고이미지">
		</div>
		<div class="carousel-item">
			<img src="/images/commerc2.png" class="d-block w-100"
				alt="광고이미지">
		</div>
		<div class="carousel-item">
			<img src="/images/commerc3.png" class="d-block w-100"
				alt="광고이미지">
		</div>
		
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
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                            <small class="text-body-secondary">9 mins</small>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                            <small class="text-body-secondary">9 mins</small>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                            <small class="text-body-secondary">9 mins</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <h2>요가/필라테스 인기순위</h2>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                            <small class="text-body-secondary">9 mins</small>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                            <small class="text-body-secondary">9 mins</small>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                            <small class="text-body-secondary">9 mins</small>
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
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                            <small class="text-body-secondary">9 mins</small>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                            <small class="text-body-secondary">9 mins</small>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="https://via.placeholder.com/800x600.png" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                            </div>
                            <small class="text-body-secondary">9 mins</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
