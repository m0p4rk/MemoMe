package com.m0p4rk.memome.user.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId; // UserID int(11) AI PK
    private String username; // Username varchar(50)
    private String password; // Password varchar(255)
    private String email; // Email varchar(100)
    private LocalDateTime createDate; // CreateDate datetime
}
