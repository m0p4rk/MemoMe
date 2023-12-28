package com.mini.memome.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NoteDTO {
    private Long noteId; // 노트의 고유 ID
    private String userId; // 노트를 생성한 사용자 ID
    private String title; // 노트의 제목
    private String content; // 노트의 내용
    private LocalDateTime createdDate; // 생성 날짜
    private LocalDateTime modifiedDate; // 수정 날짜
    // getters and setters...
}

