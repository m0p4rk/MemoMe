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

    // 노트 생성 API
    @PostMapping("/create")
    public ResponseEntity<Void> createNote(@RequestParam("title") String title,
                                           @RequestParam("content") String content,
                                           HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("loggedInUser");
        int userId = userService.findUserIdByUsername(username);
        LocalDateTime currentTime = LocalDateTime.now();
        Note newNote = new Note(0, userId, title, content, currentTime, null);
        noteService.create(newNote);
        return ResponseEntity.ok().build();
    }

    // 노트 삭제 API
    @PostMapping("/delete")
    public ResponseEntity<Void> deleteNote(@RequestParam("noteId") int noteId) {
        noteService.delete(noteId);
        return ResponseEntity.ok().build();
    }

    // 사용자별 노트 목록 조회 API
    @GetMapping("/user")
    public ResponseEntity<List<Note>> getNotesByUser(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("loggedInUser");
        List<Note> notes = noteService.getNotesByUsername(username);
        return ResponseEntity.ok(notes);
    }
}
