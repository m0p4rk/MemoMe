package com.m0p4rk.memome.note.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.m0p4rk.memome.note.service.NoteService;
import com.m0p4rk.memome.note.model.Note;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/dashboard") // 클래스 레벨에서 공통 경로 정의
public class NoteAsyncController {

    private final NoteService noteService;

    public NoteAsyncController(NoteService noteService) {
        this.noteService = noteService;
    }

    // 대시보드 노트 비동기 조회
    @GetMapping("/notesync")
    public CompletableFuture<List<Note>> getDashboardNotes(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("loggedInUser");
        if (username == null) {
            // 적절한 예외 처리 또는 오류 응답
            // 예: throw new UserNotLoggedInException("User not logged in.");
        }
        return noteService.getNotesByUsernameAsync(username);
    }
}
