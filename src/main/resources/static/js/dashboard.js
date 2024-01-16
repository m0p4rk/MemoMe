function saveNote() {
	let noteTitle = document.getElementById('noteTitle').value;
	let noteText = document.getElementById('noteText').value;

	let xhr = new XMLHttpRequest();
	xhr.open("POST", "/api/notes/create", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('noteTitle').value = '';
			document.getElementById('noteText').value = '';
			loadNotes();
		}
	};
	xhr.send("title=" + encodeURIComponent(noteTitle) + "&content=" + encodeURIComponent(noteText));
}

function deleteNote(noteId) {
	let xhr = new XMLHttpRequest();
	xhr.open("DELETE", "/api/notes/delete?noteId=" + encodeURIComponent(noteId), true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			loadNotes();
		}
	};
	xhr.send();
}

function loadNotes() {
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "/api/notes/readall", true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let notes = JSON.parse(xhr.responseText);
			displayNotes(notes);
		}
	};
	xhr.send();
}


function updateNote(noteId) {
	let noteElement = document.getElementById(`note-item-${noteId}`);
	if (!noteElement) {
		console.error('Note element not found for ID:', noteId);
		return;
	}

	let noteTitle = noteElement.querySelector(".note-title").innerText;
	let noteContent = noteElement.querySelector(".note-content").innerText;
	// 편집 가능한 필드로 변환
	noteElement.innerHTML = `<div class="note-item-header">
        <input type="text" id="edit-title-${noteId}" value="${noteTitle}" class="form-control">
        <textarea id="edit-content-${noteId}" class="form-control">${noteContent}</textarea>
        <button class="btn btn-success btn-sm" onclick="saveUpdatedNote(${noteId})">Save</button>
        <button class="btn btn-secondary btn-sm" onclick="cancelUpdate(${noteId})">Cancel</button>
    </div>`;
}

function saveUpdatedNote(noteId) {
	let editedTitle = document.getElementById(`edit-title-${noteId}`).value;
	let editedContent = document.getElementById(`edit-content-${noteId}`).value;

	let xhr = new XMLHttpRequest();
	xhr.open("POST", "/api/notes/update", true);
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			loadNotes();
		}
	};
	xhr.send(JSON.stringify({ noteId: noteId, title: editedTitle, content: editedContent }));
}

function cancelUpdate() {
	loadNotes(); // 현재 노트 목록을 다시 로드하여 취소
}

function downloadNotes() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "/api/cdn/download", true);
    xhr.responseType = 'blob'; // 텍스트 파일을 blob 형태로 받음

    xhr.onload = function () {
        if (xhr.status === 200) {
            // Blob을 다운로드로 처리
            let blob = new Blob([xhr.response], { type: 'text/plain' });
            let downloadUrl = URL.createObjectURL(blob);
            let a = document.createElement("a");
            a.href = downloadUrl;
            a.download = "notes.txt";
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
            URL.revokeObjectURL(downloadUrl);
        } else {
            console.error("서버에서 파일을 다운로드하는 데 실패했습니다.");
        }
    };

    xhr.onerror = function () {
        console.error("네트워크 오류가 발생했습니다.");
    };

    xhr.send();
}

function displayNotes(notes) {
    let notesContainer = document.getElementById("savedNotes");
    notesContainer.innerHTML = '';
    notes.forEach(function(note) {
        let noteElement = document.createElement("div");
        noteElement.id = `note-item-${note.noteId}`; // 노트의 noteId 사용
        noteElement.className = "note-item";
        noteElement.innerHTML = `
            <div class="note-item-header">
                <strong class="note-title">${note.title}</strong>
                <div>
                    <button class="btn btn-danger btn-sm" onclick="deleteNote(${note.noteId})">Delete</button>
                    <button class="btn btn-warning btn-sm" onclick="updateNote(${note.noteId})">Edit</button>
                </div>
            </div>
            <p class="note-content">${note.content}</p>`;
        notesContainer.appendChild(noteElement);
    });
}
window.onload = function() {
	loadNotes();
};
