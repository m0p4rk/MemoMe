package com.mini.memome.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mini.memome.service.MemoMeService;
import com.mini.memome.service.MemoMeServiceImpl;

@WebServlet("/deleteNote")
public class DeleteNoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NumberFormatException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        System.out.println("Received noteId parameter: " + request.getParameter("noteId"));
        if (username != null) {
            try {
                String noteIdString = request.getParameter("noteId");
                if (noteIdString == null || noteIdString.equals("undefined")) {
                    throw new IllegalArgumentException("Invalid note ID");
                }
                int noteId = Integer.parseInt(noteIdString);

                MemoMeService service = new MemoMeServiceImpl();
                boolean isDeleted = service.deleteNote(noteId);

                if (isDeleted) {
                    response.getWriter().write("Note deleted successfully");
                } else {
                    response.getWriter().write("Note deletion failed");
                }
            } catch (IllegalArgumentException e) {
                response.getWriter().write("Invalid note ID");
                e.printStackTrace();
            } catch (Exception e) {
                response.getWriter().write("Error in deleting note");
                e.printStackTrace();
            }
        } else {
            response.getWriter().write("Missing username in session");
        }
    }
}
