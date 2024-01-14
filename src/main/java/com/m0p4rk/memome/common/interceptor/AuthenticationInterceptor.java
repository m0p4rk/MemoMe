package com.m0p4rk.memome.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isUserNotLoggedIn(request)) {
            redirectToLoginPage(response);
            return false;
        }
        return true;
    }

    private boolean isUserNotLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("loggedInUser") == null;
    }

    private void redirectToLoginPage(HttpServletResponse response) throws Exception {
        response.sendRedirect("/login");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // Post-processing if required
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // Actions after completing the request and response cycle
    }
}
