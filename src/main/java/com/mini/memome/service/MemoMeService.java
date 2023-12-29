package com.mini.memome.service;

import java.util.List;

import com.mini.memome.dto.Note;
import com.mini.memome.dto.User;

public interface MemoMeService {
	void registerUser(User user);
    boolean loginUser(String username, String password);
    void updateUser(User user);
    User getUserById(int userId);
    void deleteUser(int userId);

    void createNote(Note note);
    void updateNote(Note note);
    Note getNoteById(int noteId);
    List<Note> getAllNotesByUser(int userId);
    void deleteNote(int noteId);
	int getUserIdByUsername(String username);
}
