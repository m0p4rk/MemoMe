<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Join MemoMe</title>
    <!-- 부트스트랩 CSS 추가 -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .register-container {
            max-width: 400px;
            padding: 15px;
            margin: auto;
        }
    </style>
</head>
<body>
    <div class="register-container mt-5">
        <h2 class="text-center">Join MemoMe</h2>
        <form id="registerForm" action="/MemoMe/registerUser" method="post" class="mt-4">
            <div class="form-group">
                <input type="text" class="form-control" name="username" placeholder="Username" required>
            </div>
            <div class="form-group">
                <input type="email" class="form-control" name="email" placeholder="Email" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password" placeholder="Password" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm Password" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Sign in</button>
        </form>
    </div>

    <!-- 부트스트랩 JS 추가 -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script>
        document.getElementById('registerForm').onsubmit = function() {
            var username = this.username.value;
            var email = this.email.value;
            var password = this.password.value;
            var confirmPassword = this.confirmPassword.value;

            // Username 유효성 검사
            var usernamePattern = /^[A-Za-z0-9_-]+$/;
            if (!usernamePattern.test(username)) {
                alert('Username can contain only letters, numbers, underscores, and dashes.');
                return false;
            }

            // Email 유효성 검사 (기본적인 형식)
            var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailPattern.test(email)) {
                alert('Please enter a valid email address.');
                return false;
            }

            // 비밀번호 일치 확인
            if (password !== confirmPassword) {
                alert('Passwords do not match.');
                return false;
            }

            return true;
        };
    </script>
</body>
</html>
