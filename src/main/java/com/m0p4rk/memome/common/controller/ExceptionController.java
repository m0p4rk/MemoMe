package com.m0p4rk.memome.common.controller;

import java.io.IOException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    // 일반적인 예외 처리
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneralException(HttpServletRequest request, Exception ex, Model model) {
        model.addAttribute("errorMessage", "Internal server error: " + ex.getMessage());
        return "error"; // 오류 페이지로 리디렉션
    }

//    // 사용자 인증 예외 처리
//    @ExceptionHandler(UserNotLoggedInException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public String handleUserNotLoggedInException(HttpServletRequest request, UserNotLoggedInException ex, Model model) {
//        model.addAttribute("errorMessage", "Unauthorized: " + ex.getMessage());
//        return "login"; // 로그인 페이지로 리디렉션
//    }

    // 데이터 처리 관련 예외 처리
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIOException(HttpServletRequest request, IOException ex, Model model) {
        model.addAttribute("errorMessage", "IO Error: " + ex.getMessage());
        return "error";
    }

    // HTTP 요청 관련 예외 처리
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String handleMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException ex, Model model) {
        model.addAttribute("errorMessage", "Method Not Allowed: " + ex.getMessage());
        return "error";
    }
    
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)  // 409 충돌 상태 코드
    public String handleDuplicateKeyException(HttpServletRequest request, DuplicateKeyException ex, Model model) {
        model.addAttribute("errorMessage", "Conflict: " + ex.getMessage());
        return "error"; // 오류 페이지로 리디렉션
    }

    // 사용자 정의 예외 또는 추가적인 예외 처리를 위한 핸들러들을 여기에 추가할 수 있습니다.
}
