package com.m0p4rk.memome.note.api;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.m0p4rk.memome.note.model.Note;
import com.m0p4rk.memome.note.service.NoteService;
import com.m0p4rk.memome.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/notes")
public class NoteApi {

    private final NoteService noteService;
    private final UserService userService;

    public NoteApi(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createNote(@RequestParam("title") String title,
                                           @RequestParam("content") String content,
                                           HttpServletRequest request) {
        String username = getUsernameFromRequest(request);
        int userId = userService.findUserIdByUsername(username);
        Note newNote = createNewNote(userId, title, content);
        noteService.create(newNote);
        return ResponseEntity.ok().build();
    }
    
    private String getUsernameFromRequest(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("loggedInUser");
    }

    private Note createNewNote(int userId, String title, String content) {
        validateNoteInput(title, content);
        LocalDateTime currentTime = LocalDateTime.now();
        return new Note(0, userId, title, content, currentTime, null);
    }

    private void validateNoteInput(String title, String content) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteNote(@RequestParam("noteId") int noteId) {
        noteService.delete(noteId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<Note>> getNotesByUser(HttpServletRequest request) {
        String username = getUsernameFromRequest(request);
        List<Note> notes = noteService.getNotesByUsername(username);
        return ResponseEntity.ok(notes);
    }

    
}
