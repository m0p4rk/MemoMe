package com.m0p4rk.memome.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PageRedirectionController {

    @GetMapping("/register")
    public String redirectRegisterPage() {
        return "register.html";
    }

    @GetMapping("/login")
    public String redirectLoginPage(HttpServletRequest request) {
        if (isLoggedIn(request)) {
            return "redirect:/dashboard";
        }
        return "login.html";
    }

    @GetMapping("/dashboard")
    public String redirectDashboard() {
        return "dashboard.html";
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("loggedInUser") != null;
    }
}
