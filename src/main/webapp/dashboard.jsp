<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.IOException" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - MemoMe</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            padding-top: 20px;
        }
        .header-area {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
        }
        .logout-button {
            order: 2;
        }
        .user-info {
            order: 1;
            font-size: 1.2em;
            font-weight: bold;
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
    <div class="header-area">
        <div class="user-info">
            Welcome, <%= session.getAttribute("username") %>
        </div>
        <div class="logout-button">
            <a href="logoutUser" class="btn btn-danger btn-sm">Logout</a>
        </div>
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
            <!-- 서버 측에서 노트 목록을 가져와 여기에 표시 -->
        </div>
    </div>

    <script>
        function saveNote() {
            var noteTitle = document.getElementById('noteTitle').value;
            var noteText = document.getElementById('noteText').value;

            var xhr = new XMLHttpRequest();
            xhr.open("POST", "/MemoMe/createNote", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    document.getElementById('noteTitle').value = ''; // 입력창 초기화
                    document.getElementById('noteText').value = '';
                    loadNotes(); // 메모 목록 다시 로딩
                }
            };
            xhr.send("title=" + encodeURIComponent(noteTitle) + "&content=" + encodeURIComponent(noteText));
        }

        function loadNotes() {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "/MemoMe/listNotes", true);
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var notes = JSON.parse(xhr.responseText);
                    var notesContainer = document.getElementById("savedNotes");
                    notesContainer.innerHTML = ""; // 목록 초기화
                    notes.forEach(function(note) {
                        var noteElement = document.createElement("div");
                        noteElement.className = "note-item";
                        noteElement.innerHTML = 
                            '<div class="note-item-header">' +
                            '<strong>' + note.title + '</strong>' +
                            '<div>' +
                            '<button class="btn btn-info btn-sm">Edit</button>' +
                            '<button class="btn btn-danger btn-sm">Delete</button>' +
                            '</div></div><p>' + note.content + '</p>';
                        notesContainer.appendChild(noteElement);
                    });
                }
            };
            xhr.send();
        }

        // 페이지 로드 시 메모 목록 로드
        window.onload = function() {
            loadNotes();
        };
    </script>
</body>
</html>
