<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Freeboard</title>
    <link rel="stylesheet" href="/css/freeboard/style.css">
</head>
<body>
    <header>
        <div class="logo">
            <h1>이지짐</h1>
        </div>
        <nav>
            <ul>
                <li><a href="#">위젯</a></li>
                <li><a href="#">레이아웃과 섹션</a></li>
                <li><a href="#" class="active">자유게시판</a></li>
                <li><a href="#">리뷰게시판</a></li>
                <li><a href="#">자료실 게시판</a></li>
                <li><a href="#">마이페이지</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <h2>자유게시판</h2>
        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>작성시간</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody id="post-list">
				<c:choose>
					<c:when test="${empty fbmap.fblist}">
						<tr>
							<td colspan="5">
								<p align="center">등록된 게시글이 없습니다.</p>
							</td>
						</tr>
					</c:when>
					<c:when test="${!empty fbmap.fblist}">
						<c:forEach var="fboard" items="${fbmap.fblist}">
							<tr>
								<td>${fboard.freeNo}</td>
								<td><a href="/freeboard/viewfboard.do?freeNo=${fboard.freeNo}">${fboard.freeTitle}</a></td>
								<td>memberName</td>
								<td>${fboard.freeWriteDate}</td>
								<td>${fboard.freeHit}</td>
							<tr>
						</c:forEach>
					</c:when>
				</c:choose>
            </tbody>
        </table>
        <button class="write-button" onclick="openPostForm()">글쓰기</button>
    </main>
    <script src="/js/freeboard/script.js"></script>
</body>
</html>
