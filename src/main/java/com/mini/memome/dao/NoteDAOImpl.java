package com.mini.memome.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.mini.memome.dto.Note;
import com.mini.memome.util.DButil;

public class NoteDAOImpl implements NoteDAO {
	@Override
	public void addNote(Note note) throws SQLException {
		try (Connection conn = DButil.getConnection()) {
			String sql = "INSERT INTO Notes (UserID, Title, Content, CreateDate, LastModifiedDate) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, note.getUserId());
				stmt.setString(2, note.getTitle());
				stmt.setString(3, note.getContent());
				stmt.setTimestamp(4, new Timestamp(note.getCreatedDate().getTime()));
				stmt.setTimestamp(5, new Timestamp(note.getModifiedDate().getTime()));
				stmt.executeUpdate();
			}
		}
	}

	@Override
	public Note getNote(int noteId) throws SQLException {
		Note note = null;
		try (Connection conn = DButil.getConnection()) {
			String sql = "SELECT * FROM Notes WHERE NoteID = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, noteId);
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						note = new Note();
						note.setNoteId(rs.getInt("NoteID"));
						note.setUserId(rs.getInt("UserID"));
						note.setTitle(rs.getString("Title"));
						note.setContent(rs.getString("Content"));
						note.setCreatedDate(rs.getTimestamp("CreateDate"));
						note.setModifiedDate(rs.getTimestamp("LastModifiedDate"));
					}
				}
			}
		}
		return note;
	}
}