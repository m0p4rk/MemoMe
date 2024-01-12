package com.m0p4rk.memome.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RedirectionController {
	@GetMapping("/register")
	public String redirectRegisterPage() {
		return "register.html"; // register.html 페이지로 이동
	}
	
	@GetMapping("/login")
    public String redirectLoginPage(HttpServletRequest request) {
        // 세션에서 로그인 상태 확인
        if (request.getSession().getAttribute("loggedInUser") != null) {
            // 이미 로그인한 경우 대시보드로 리디렉트
            return "redirect:/dashboard";
        }
        // 로그인하지 않은 경우 로그인 페이지 반환
        return "login.html";
    }
	
	@GetMapping("/dashboard")
	public String redirectDashboard() {
		return "dashboard.html";
	}
}
