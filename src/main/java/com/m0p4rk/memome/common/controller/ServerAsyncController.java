package com.m0p4rk.memome.common.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.m0p4rk.memome.note.service.NoteService;
import com.m0p4rk.memome.note.model.Note;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ServerAsyncController {
    private final NoteService noteService;

    public ServerAsyncController(NoteService noteService) {
        this.noteService = noteService;
    }
    
    // Note related Async
    @GetMapping("/dashboard/notesync")
    public CompletableFuture<List<Note>> getDashboardNotes(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("loggedInUser");
        return noteService.getNotesByUsernameAsync(username);
    }
}
