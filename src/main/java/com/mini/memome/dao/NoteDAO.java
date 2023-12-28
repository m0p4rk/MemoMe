package com.mini.memome.dao;

import java.sql.SQLException;

import com.mini.memome.dto.Note;

public interface NoteDAO {
	void addNote(Note note) throws SQLException;
	Note getNote(int noteId) throws SQLException;
}
