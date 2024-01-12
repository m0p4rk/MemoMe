package com.m0p4rk.memome.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 세션에서 loggedInUser 속성 가져오기
		Object loggedInUser = request.getSession().getAttribute("loggedInUser");

		if (loggedInUser == null) {
			// 로그인하지 않은 사용자는 로그인 페이지로 리디렉트
			response.sendRedirect("/login");
			return false; // 컨트롤러 핸들러 메소드 실행 중지
		}
		
		return true; // 로그인된 경우, 컨트롤러 핸들러 메소드 계속 실행
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		// 컨트롤러가 요청을 처리한 후, 뷰를 렌더링하기 전에 실행될 로직
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// 뷰를 렌더링하고 응답을 클라이언트에게 전송한 후에 실행될 로직
	}
}
