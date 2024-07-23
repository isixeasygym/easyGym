<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("utf-8");
%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" href="${contextPath}/css/mypage/mypageMain.css">
    <script src="${contextPath}/js/mypage/mypageMain.js"></script>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }
    .container {
		position: relative;
		top: 40px;
        width: 70%;
        margin: 30px auto;
        padding: 20px;
    }
    .header {
        display: flex;
        align-items: center;
        justify-content: center;
        margin-bottom: 50px;
    }
    .header img {
        margin-right: 20px;
    }
    .form-group {
        margin-bottom: 15px;
    }
    .form-group label {
        display: block;
        margin-bottom: 5px;
    }
    .form-group input, .form-group textarea, .form-group select {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .form-group textarea {
        resize: vertical;
    }
    .form-group button {
        display: block;
        width: 100%;
        padding: 10px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }
</style>
</head>
<body>
    <div class="container">
        <form id="detailForm" action="/detail/signUpForm.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="operatorNo" value="111">
            <div class="form-group">
                <label for="detailBusinessName">업체명</label>
                <input type="text" id="detailBusinessName" name="detailBusinessName">
            </div>
            <div class="form-group">
                <label for="detailBusinessEng">업체명(영어)</label>
                <input type="text" id="detailBusinessEng" name="detailBusinessEng">
            </div>
            <div class="form-group">
                <label for="detailRoadAddress">업체 도로명 주소</label>
                <input type="text" id="detailRoadAddress" name="detailRoadAddress">
            </div>
            <div class="form-group">
                <label for="detailDailyTicket">일일권</label>
                <input type="text" id="detailDailyTicket" name="detailDailyTicket" placeholder="10,000원 형식으로 입력해주세요.">
            </div>
            <div class="form-group">
                <label for="detailMonthlyTicket">회원권(월)</label>
                <input type="text" id="detailMonthlyTicket" name="detailMonthlyTicket" placeholder="30,000원~ 형식으로 입력해주세요.">
            </div>
            <div class="form-group">
                <label for="detailMonthlyPrice">회원권(월)</label>
                <input type="number" id="detailMonthlyPrice" name="detailMonthlyPrice" placeholder="10000 숫자만 입력해 주세요.">
            </div>
            <div class="form-group">
                <label for="detailComment">소개 글</label>
                <textarea id="detailComment" name="detailComment" placeholder="ex) 우리 회사는 ~~ 소개 글을 작성해주시면 됩니다."></textarea>
            </div>
            <div class="form-group">
                <label for="detailServiceProgram">운영 시간 및 서비스</label>
                <textarea id="detailServiceProgram" name="detailServiceProgram" placeholder="ex) 운영시간\n\n[평  일] 09:00 ~ 22:00\n[토요일] 10:00 ~ 15:00\n[일요일] 휴무\n[휴관일] 공휴일, 매주 일요일\n\n부가서비스\n\n무료 ✔️수건 ✔️공용락커 ✔️와이파이 ✔️샤워시설 ✔️주차\n' 이러한 형식으로 작성해 주시면 됩니다."></textarea>
            </div>
            <div class="form-group">
                <label for="detailFreeService">무료 서비스</label>
                <input type="text" id="detailFreeService" name="detailFreeService" placeholder="개인 라커 / 운동복 / 수건 / 샤워시설">
            </div>
            <div class="form-group">
                <label for="detailClassification">업체 분류(영어)</label>
                <input type="text" id="detailClassification" name="detailClassification" placeholder="헬스 -> health, 복싱 -> boxing, 필라테스 -> pilates 셋 중에 한 가지만 해당 하는 것을 기입해 주세요.">
            </div>
            <div class="form-group">
                <label for="detailKoClassification">업체 분류(한글)</label>
                <input type="text" id="detailKoClassification" name="detailKoClassification" placeholder="헬스, 복싱, 필라테스 셋 중에 한 가지만 해당 하는 것을 기입해 주세요.">
            </div>
            <div class="form-group">
                <label for="detailLatitude">지도에 표시할 위도</label>
                <input type="text" id="detailLatitude" name="detailLatitude" placeholder="ex) 37.5001556">
            </div>
            <div class="form-group">
                <label for="detailLongitude">지도에 표시할 경도</label>
                <input type="text" id="detailLongitude" name="detailLongitude" placeholder="ex) 126.9309597">
            </div>
            <div class="form-group">
                <label for="operatorNo">사업자 번호</label>
                <input type="number" id="operatorNo" name="operatorNo" placeholder="111-11-11111">
            </div>
            <!-- Image upload fields -->
			<input type="text" style="width:100%;" disabled  placeholder="영어로 기입 업체명을 기준으로 사진명을 수정해 주세요.PNG확장자로 부탁드립니다. ex)BBGym1.PNG">
            <div class="form-group">
                <label for="image1">이미지 1</label>
                <input type="file" id="image1" name="image1">
            </div>
            <div class="form-group">
                <label for="image2">이미지 2</label>
                <input type="file" id="image2" name="image2">
            </div>
            <div class="form-group">
                <label for="image3">이미지 3</label>
                <input type="file" id="image3" name="image3">
            </div>
            <div class="form-group">
                <label for="image4">이미지 4</label>
                <input type="file" id="image4" name="image4">
            </div>
            <div class="form-group">
                <label for="image5">이미지 5</label>
                <input type="file" id="image5" name="image5">
            </div>
            <div class="form-group">
                <label for="image6">이미지 6</label>
                <input type="file" id="image6" name="image6">
            </div>
            <div class="form-group">
                <label for="image7">이미지 7</label>
                <input type="file" id="image7" name="image7">
            </div>
            <div class="form-group">
                <label for="image8">이미지 8</label>
                <input type="file" id="image8" name="image8">
            </div>
            <div class="form-group">
                <label for="image9">이미지 9</label>
                <input type="file" id="image9" name="image9">
            </div>
            <div class="form-group">
                <label for="image10">이미지 10</label>
                <input type="file" id="image10" name="image10">
            </div>
            <div class="form-group">
                <button type="submit">등록하기</button>
            </div>
        </form>
    </div>

</body>
</html>