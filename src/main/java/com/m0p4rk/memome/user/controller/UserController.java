package com.m0p4rk.memome.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.m0p4rk.memome.user.model.User;
import com.m0p4rk.memome.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/") // 클래스 레벨에서 공통 경로 정의
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 등록
    @PostMapping("/register.do")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    // 사용자 로그인
    @PostMapping("/login.do")
    public String loginUser(@ModelAttribute User user, HttpServletRequest request) {
        if (userService.authenticateUser(user)) {
            request.getSession().setAttribute("loggedInUser", user.getUsername());
            return "redirect:/dashboard";
        } else {
            return "redirect:/login";
        }
    }

    // 사용자 로그아웃
    @PostMapping("/logout.do")
    public String logoutUser(HttpServletRequest request) {
        request.getSession().invalidate(); // 세션 무효화
        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 리디렉트
    }

}
