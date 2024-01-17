<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-12">
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading">오류 발생!</h4>
                <p>죄송합니다, 요청 처리 중 오류가 발생했습니다.</p>
                <hr>
                <p class="mb-0" id="error-details">오류 상세 정보: ${errorMessage}</p> <!-- 여기에 서버에서 전달한 오류 메시지를 출력합니다 -->
            </div>

            <button class="btn btn-primary" onclick="goBack()">이전 페이지로 돌아가기</button>
            <a href="/login" class="btn btn-secondary">홈으로 가기</a>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    function goBack() {
        window.history.back();
    }
</script>

</body>
</html>
