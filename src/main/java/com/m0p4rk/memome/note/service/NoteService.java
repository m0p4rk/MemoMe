package com.m0p4rk.memome.note.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.m0p4rk.memome.note.mapper.NoteMapper;
import com.m0p4rk.memome.note.model.Note;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    @Async
    public CompletableFuture<List<Note>> getNotesByUsernameAsync(String username) {
        return CompletableFuture.supplyAsync(() -> noteMapper.getNotesByUsername(username));
    }

	public List<Note> getNotesByUsername(String username) {
		return noteMapper.getNotesByUsername(username);
	}

	public boolean create(Note newNote) {
		return noteMapper.createNote(newNote);
	}

	public boolean delete(int noteId) {
		return noteMapper.deleteNote(noteId);
	}
}
