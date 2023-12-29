package com.mini.memome.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.mini.memome.dto.Note;
import com.mini.memome.service.MemoMeService;
import com.mini.memome.service.MemoMeServiceImpl;

@WebServlet("/listNotes")
public class ListNotesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListNotesServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            MemoMeService service = new MemoMeServiceImpl();
            try {
                int userId = service.getUserIdByUsername(username); // 사용자 ID 조회
                if (userId > 0) {
                    List<Note> notes = service.getAllNotesByUser(userId); // 노트 목록 조회
                    String json = new Gson().toJson(notes);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } else {
                    response.sendRedirect("login.jsp");
                }
            } catch (Exception e) {
                response.getWriter().write("Error retrieving notes");
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
