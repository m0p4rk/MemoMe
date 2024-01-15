package com.m0p4rk.memome.note.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.m0p4rk.memome.note.model.Note;

@Mapper
public interface NoteMapper {
	@Insert("INSERT INTO notes (userId, title, content, lastmodifieddate) VALUES (#{note.userId},#{note.title},#{note.content},#{note.lastModifiedDate})")
	boolean createNote(@Param("note") Note note);

	@Delete("DELETE FROM notes WHERE noteId = #{noteId}")
	boolean deleteNote(@Param("noteId") int noteId);

	@Select("SELECT n.NoteID, n.Title, n.Content, n.CreateDate, n.LastModifiedDate "
			+ "FROM notes n JOIN users u ON n.UserID = u.UserID " + "WHERE u.Username = #{username}")
	List<Note> getNotesByUsername(@Param("username") String username);

	@Update("UPDATE notes SET title = #{note.title}, content = #{note.content}, lastModifiedDate = CURRENT_TIMESTAMP WHERE noteId = #{note.noteId}")
	boolean updateNote(@Param("note") Note note);
}
