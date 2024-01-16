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
		if (username == null || username.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 혹은 다른 적절한 오류 코드
			return;
		}

		List<Note> notes = noteService.getNotesByUsername(username);

		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment;filename=notes.csv");

		try (ServletOutputStream outputStream = response.getOutputStream()) {
			// CSV 헤더 작성
			outputStream.write("Title,Content,CreateDate,LastModifiedDate\n".getBytes());

			// 각 노트를 CSV 포맷으로 변환
			for (Note note : notes) {
				String csvLine = String.format("\"%s\", \"%s\", \"%s\", \"%s\"%n",
						note.getTitle().replace("\"", "\"\""), // CSV 형식에 맞게 따옴표 처리
						note.getContent().replace("\"", "\"\""), note.getCreateDate(), note.getLastModifiedDate());
				outputStream.write(csvLine.getBytes());
			}
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
