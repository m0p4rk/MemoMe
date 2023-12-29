package com.mini.memome.dao;

import java.sql.SQLException;

import com.mini.memome.dto.User;

public interface UserDAO {
	void addUser(User user) throws SQLException;
    User getUser(int userId) throws SQLException;
	User getUserByUsername(String username) throws SQLException;
	void updateUser(User user) throws SQLException;
	User getUserById(int userId) throws SQLException;
	void deleteUser(int userId) throws SQLException;
}
