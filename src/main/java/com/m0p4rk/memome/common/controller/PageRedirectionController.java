package com.m0p4rk.memome.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PageRedirectionController {

    @GetMapping("/register")
    public String redirectRegisterPage() {
        return "forward:/html/register.html"; // forward를 사용하여 정적 HTML 파일 반환
    }

    @GetMapping("/login")
    public String redirectLoginPage(HttpServletRequest request) {
        if (isLoggedIn(request)) {
            return "redirect:/dashboard";
        }
        return "forward:/html/login.html"; // forward를 사용하여 정적 HTML 파일 반환
    }

    @GetMapping("/dashboard")
    public String redirectDashboard() {
        return "forward:/html/dashboard.html"; // forward를 사용하여 정적 HTML 파일 반환
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("loggedInUser") != null;
    }
}
