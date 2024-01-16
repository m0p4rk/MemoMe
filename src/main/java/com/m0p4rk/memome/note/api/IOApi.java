package com.m0p4rk.memome.note.api;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m0p4rk.memome.note.model.Note;
import com.m0p4rk.memome.note.service.NoteService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/cdn")
public class IOApi {

	private final NoteService noteService;

	public IOApi(NoteService noteService) {
		this.noteService = noteService;
	}

	@GetMapping("/download")
	public void downloadNotes(HttpServletRequest request, HttpServletResponse response) {
		String username = (String) request.getSession().getAttribute("loggedInUser");
		List<Note> notes = noteService.getNotesByUsername(username);

		response.setContentType("text/plain");
		response.setHeader("Content-Disposition", "attachment;filename=notes.txt");

		try {
			ServletOutputStream outputStream = response.getOutputStream();
			for (Note note : notes) {
				outputStream.write(note.toString().getBytes());
				outputStream.write("\n".getBytes());
			}
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace(); // handle the exception as needed
		}
	}
}
