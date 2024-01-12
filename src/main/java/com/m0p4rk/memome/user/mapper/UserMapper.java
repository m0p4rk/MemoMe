package com.m0p4rk.memome.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.m0p4rk.memome.user.model.User;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO users (username, password, email) VALUES (#{user.username}, #{user.password}, #{user.email})")
    boolean insertUser(@Param("user") User user);
    
    @Select("SELECT * FROM users WHERE username = #{user.username}")
    User getUserByUsername(@Param("user") User user);
    
    @Select("SELECT password FROM users WHERE username = #{username}")
    String getHashedPasswordByUsername(String username);
}
