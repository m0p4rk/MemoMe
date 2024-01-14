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
    xhr.open("GET", "/api/notes/user", true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let notes = JSON.parse(xhr.responseText);
            displayNotes(notes);
        }
    };
    xhr.send();
}

function displayNotes(notes) {
    let notesContainer = document.getElementById("savedNotes");
    notesContainer.innerHTML = '';
    notes.forEach(function(note) {
        let noteElement = document.createElement("div");
        noteElement.className = "note-item";
        noteElement.innerHTML = `<div class="note-item-header">
            <strong>${note.title}</strong>
            <div><button class="btn btn-danger btn-sm" onclick="deleteNote(${note.noteId})">Delete</button></div>
            </div><p>${note.content}</p>`;
        notesContainer.appendChild(noteElement);
    });
}

window.onload = function() {
    loadNotes();
};
