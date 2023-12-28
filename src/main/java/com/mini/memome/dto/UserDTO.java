package com.mini.memome.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDTO {
    private String userId; // 사용자 ID
    private String password; // 비밀번호
    private String name; // 사용자 이름
    // 필요한 경우, 추가 정보들 (이메일, 전화번호 등)
    // getters and setters...
}

