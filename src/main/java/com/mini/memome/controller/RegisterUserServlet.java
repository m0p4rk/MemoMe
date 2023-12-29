package com.mini.memome.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mini.memome.service.MemoMeService;
import com.mini.memome.service.MemoMeServiceImpl;
import com.mini.memome.dto.User;

@WebServlet("/registerUser")
public class RegisterUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterUserServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 사용자 입력 데이터 받기
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // User 객체 생성 및 초기화
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // 실제 앱에서는 비밀번호를 해싱 처리하는 것을 고려하세요.

        // 서비스 계층 호출
        MemoMeService service = new MemoMeServiceImpl();
        service.registerUser(user);

        // 등록 후 처리 (예: 로그인 페이지로 리디렉션)
        response.sendRedirect("login.jsp");
    }
}