<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MemoMe 회원가입</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="register-container">
        <h2>회원가입</h2>
        <form action="RegisterServlet" method="post">
            <input type="text" name="username" placeholder="사용자 이름" required>
            <input type="password" name="password" placeholder="비밀번호" required>
            <input type="password" name="confirmPassword" placeholder="비밀번호 확인" required>
            <button type="submit">회원가입</button>
        </form>
    </div>
</body>
</html>
