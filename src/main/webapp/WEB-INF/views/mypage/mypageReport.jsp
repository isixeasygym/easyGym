<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지 신고내역</title>
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
        <h2>신고내역</h2>
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
        <button class="write-button" action="#">글쓰기</button>
    </main>
    <script src="js/script.js"></script>
</body>
</html>