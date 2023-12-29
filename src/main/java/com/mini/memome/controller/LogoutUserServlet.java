package com.mini.memome.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logoutUser")
public class LogoutUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 현재 세션 얻기
        HttpSession session = request.getSession(false); // false: 존재하지 않으면 새 세션을 생성하지 않음
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }

        // 로그인 페이지로 리디렉션
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
