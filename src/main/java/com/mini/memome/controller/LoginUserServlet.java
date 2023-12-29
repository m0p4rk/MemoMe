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

@WebServlet("/loginUser")
public class LoginUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginUserServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        MemoMeService service = new MemoMeServiceImpl();
        if (service.loginUser(username, password)) {
            // 세션 생성
            HttpSession session = request.getSession();
            session.setAttribute("username", username); // 세션에 사용자 이름 저장

            // 메인 페이지로 리디렉션
            response.sendRedirect("dashboard.jsp");
        } else {
            // 로그인 실패 처리
            request.setAttribute("loginError", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}

