package com.m0p4rk.memome.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.m0p4rk.memome.user.model.User;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO users (username, password, email) VALUES (#{user.username}, #{user.password}, #{user.email})")
    boolean insertUser(@Param("user") User user);
}
