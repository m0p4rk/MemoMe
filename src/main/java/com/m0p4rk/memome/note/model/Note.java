package com.m0p4rk.memome.note.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
	private int noteId;
	private int userId;
	private String title;
	private String content;
	private LocalDateTime createDate;
	private LocalDateTime lastModifiedDate;
}
