package com.m0p4rk.memome.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.m0p4rk.memome.user.mapper.UserMapper;
import com.m0p4rk.memome.user.model.User;

@Service
public class UserService {
	
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	public boolean registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userMapper.insertUser(user);
	}

	public boolean loginUser(User user) {
	    String storedHashedPassword = userMapper.getHashedPasswordByUsername(user.getUsername());
	    return passwordEncoder.matches(user.getPassword(), storedHashedPassword);
	}
}
