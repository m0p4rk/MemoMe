package com.mini.memome.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int userId; // 사용자의 고유 ID
    private String username; // 사용자 이름 또는 로그인 ID
    private String password; // 비밀번호
    private String email; // 이메일 주소
    private Timestamp createDate; // 계정 생성 날짜

    // 변경 플래그 - 필드가 변경되었는지 여부를 나타냅니다.
    private boolean isUsernameChanged = false;
    private boolean isPasswordChanged = false;
    private boolean isEmailChanged = false;

    // 사용자 이름 설정 및 변경 플래그 업데이트
    public void setUsername(String username) {
        this.username = username;
        this.isUsernameChanged = true;
    }

    // 비밀번호 설정 및 변경 플래그 업데이트
    public void setPassword(String password) {
        this.password = password;
        this.isPasswordChanged = true;
    }

    // 이메일 설정 및 변경 플래그 업데이트
    public void setEmail(String email) {
        this.email = email;
        this.isEmailChanged = true;
    }

    // 추가로 필요한 메서드들...
}