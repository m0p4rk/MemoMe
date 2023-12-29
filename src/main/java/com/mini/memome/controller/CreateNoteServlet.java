package com.mini.memome.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mini.memome.dto.Note;
import com.mini.memome.service.MemoMeService;
import com.mini.memome.service.MemoMeServiceImpl;

@WebServlet("/createNote")
public class CreateNoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateNoteServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            MemoMeService service = new MemoMeServiceImpl();
            try {
                int userId = service.getUserIdByUsername(username);
                if (userId > 0) {
                    Note note = new Note();
                    note.setUserId(userId);
                    note.setTitle(title);
                    note.setContent(content);

                    // 현재 시간을 CreateDate와 LastModifiedDate로 설정
                    Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                    note.setCreatedDate(currentTimestamp);
                    note.setModifiedDate(currentTimestamp);

                    service.createNote(note);
                    response.getWriter().write("Note created successfully");
                } else {
                    response.getWriter().write("Invalid user");
                }
            } catch (Exception e) {
                response.getWriter().write("Error creating note");
                e.printStackTrace();
            }
        } else {
            response.getWriter().write("Missing username in session");
        }
    }


}
