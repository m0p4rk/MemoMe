package com.mini.memome.dao;

import java.sql.SQLException;
import java.util.List;

import com.mini.memome.dto.Note;

public interface NoteDAO {
    void addNote(Note note) throws SQLException;
    Note getNote(int noteId) throws SQLException;
    void updateNote(Note note) throws SQLException; // 노트 업데이트 메서드 추가
    List<Note> getAllNotesByUser(int userId) throws SQLException; // 특정 사용자의 모든 노트 조회 메서드 추가
    void deleteNote(int noteId) throws SQLException; // 노트 삭제 메서드 추가
}