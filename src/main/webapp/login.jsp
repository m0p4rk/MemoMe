<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MemoMe 로그인</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="login-container">
        <h2>MemoMe 로그인</h2>
        <form action="LoginServlet" method="post">
            <input type="text" name="username" placeholder="사용자 이름" required>
            <input type="password" name="password" placeholder="비밀번호" required>
            <button type="submit">로그인</button>
        </form>
    </div>
</body>
</html>
