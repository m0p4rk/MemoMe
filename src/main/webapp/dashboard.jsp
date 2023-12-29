<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - MemoMe</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            padding-top: 20px;
        }
        .logout-button {
            position: absolute;
            right: 10px;
            top: 10px;
        }
        .note-display {
            background-color: #f8f9fa;
            border: 1px solid #e0e0e0;
            padding: 15px;
            margin-top: 10px;
            min-height: 150px;
        }
        .note-item {
            background-color: white;
            border: 1px solid #ddd;
            padding: 10px;
            margin-bottom: 10px;
        }
        .note-item-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
    </style>
</head>
<body>
    <div class="logout-button">
        <a href="logout" class="btn btn-danger btn-sm">Logout</a>
    </div>

    <div class="container">
        <h2>Welcome to MemoMe Dashboard</h2>
        <p>Here you can manage your notes, view your profile, and more.</p>

        <!-- 메모 작성 폼 -->
        <form id="noteForm">
            <div class="form-group">
                <label for="noteTitle">Note Title</label>
                <input type="text" class="form-control" id="noteTitle" placeholder="Enter title">
            </div>
            <div class="form-group">
                <label for="noteText">Note Content</label>
                <textarea class="form-control" id="noteText" placeholder="Write a note..."></textarea>
            </div>
            <button type="button" class="btn btn-primary" onclick="saveNote()">Save Note</button>
        </form>

        <!-- 저장된 메모 목록 -->
        <h3>Your Notes</h3>
        <div id="savedNotes" class="note-display">
            <%-- 서버 측에서 노트 목록을 가져와 여기에 표시합니다. --%>
            <!-- 예시: 하드코딩된 노트 아이템 -->
            <div class="note-item">
                <div class="note-item-header">
                    <strong>Note Title</strong>
                    <div>
                        <button class="btn btn-info btn-sm">Edit</button>
                        <button class="btn btn-danger btn-sm">Delete</button>
                    </div>
                </div>
                <p>Note content goes here...</p>
            </div>
            <!-- 반복하여 노트 아이템을 표시 -->
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script>
        function saveNote() {
            var noteTitle = document.getElementById('noteTitle').value;
            var noteText = document.getElementById('noteText').value;
            // ... 저장 로직
        }
    </script>
</body>
</html>