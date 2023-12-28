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
public class Note {
    private int noteId; // 노트의 고유 ID
    private int userId; // 노트를 생성한 사용자 ID
    private String title; // 노트의 제목
    private String content; // 노트의 내용
    private Timestamp createdDate; // 생성 날짜
    private Timestamp modifiedDate; // 수정 날짜
    // getters and setters...
}

