<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Coming Soon</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 20px; /* 여백 추가 */
            box-sizing: border-box; /* 패딩 포함 */
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: 20px; /* 컨테이너 여백 추가 */
        }
        .container img {
            max-width: 100%;
            height: auto;
            margin-bottom: 20px;
        }
        .container h2 {
            margin: 10px 0;
            font-size: 24px;
        }
        .container p {
            margin: 10px 0;
            font-size: 16px;
        }
        .thumb-img {
            width: 80px; /* 엄지손톱 크기 */
            height: 80px; /* 엄지손톱 크기 */
            object-fit: cover; /* 이미지가 컨테이너를 채우도록 조정 */
            border-radius: 50%; /* 원형으로 설정 */
            margin: 0 auto 20px; /* 아래 여백 추가 */
        }
    </style>
</head>
<body>
    <div class="container">
        <img src="/images/member/wombat.png" alt="Coming Soon Image" class="thumb-img">
        <h2>Coming Soon ...</h2>
        <p>저희 사이트는 현재 개발 중입니다.</p>
        <p>곧 찾아뵙겠습니다. 기대해 주세요!</p>
    </div>
</body>
</html>