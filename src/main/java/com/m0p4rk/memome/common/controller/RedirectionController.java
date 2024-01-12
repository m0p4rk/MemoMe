package com.m0p4rk.memome.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectionController {
	@GetMapping("/register")
	public String RedirectRegisterPage() {
		return "register.html"; // register.html 페이지로 이동
	}
	
	@GetMapping("/login")
	public String RedirectLoginPage() {
		return "login.html";
	}
}
