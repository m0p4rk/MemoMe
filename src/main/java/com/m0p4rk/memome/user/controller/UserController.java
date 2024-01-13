package com.m0p4rk.memome.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.m0p4rk.memome.user.model.User;
import com.m0p4rk.memome.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register.do")
	public String registerUser(@ModelAttribute User user) {
		userService.registerUser(user);
		return "redirect:/login";
	}

	@PostMapping("/login.do")
	public String loginUser(@ModelAttribute User user, HttpServletRequest request) {
		if (userService.AuthenticateUser(user)) {
			request.getSession().setAttribute("loggedInUser", user.getUsername());
			return "redirect:/dashboard";
		} else {
			return "redirect:/login";
		}

	}
	
	@PostMapping("/logout.do")
    public String logoutUser(HttpServletRequest request) {
        // 현재 세션을 가져와서 무효화
        request.getSession().invalidate();

        // 로그아웃 후 리디렉트할 페이지 지정 (예: 로그인 페이지)
        return "redirect:/login";
    }
	
}
