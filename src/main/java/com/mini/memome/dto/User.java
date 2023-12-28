package com.mini.memome.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int userId; // 사용자의 고유 ID
    private String username; // 사용자 이름 또는 로그인 ID
    private String password; // 비밀번호
    private String email; // 이메일 주소
    private Timestamp createDate; // 계정 생성 날짜

    // 필요한 경우, 추가 정보들 (전화번호 등)
    // getters and setters...
}