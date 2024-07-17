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
    <link rel="stylesheet" href="css/style.css">
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
            </tbody>
        </table>
        <button class="write-button" onclick="openPostForm()">글쓰기</button>
    </main>
    
    <!-- Post Modal -->
    <div id="postModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closePostForm()">&times;</span>
            <h2>새 글 작성</h2>
            <input type="text" id="post-title" placeholder="제목">
            <input type="text" id="post-author" placeholder="글쓴이">
            <textarea id="post-content" placeholder="내용"></textarea>
            <button onclick="addPost()">등록</button>
        </div>
    </div>

    <!-- View Modal -->
    <div id="viewModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeViewModal()">&times;</span>
            <h2 id="view-title"></h2>
            <p id="view-author"></p>
            <p id="view-timestamp"></p>
            <p id="view-content"></p>
        </div>
    </div>

    <script src="js/script.js"></script>
</body>
</html>
