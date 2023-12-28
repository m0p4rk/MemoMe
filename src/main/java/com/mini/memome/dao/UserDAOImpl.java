package com.mini.memome.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.mini.memome.dto.User;
import com.mini.memome.util.DButil;

public class UserDAOImpl implements UserDAO {
	@Override
    public void addUser(User user) throws SQLException {
        try (Connection conn = DButil.getConnection()) {
            String sql = "INSERT INTO Users (Username, Password, Email, CreateDate) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getEmail());
                stmt.setTimestamp(4, new Timestamp(user.getCreateDate().getTime()));
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public User getUser(int userId) throws SQLException {
        User user = null;
        try (Connection conn = DButil.getConnection()) {
            String sql = "SELECT * FROM Users WHERE UserID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        user = new User();
                        user.setUserId(rs.getInt("UserID"));
                        user.setUsername(rs.getString("Username"));
                        user.setPassword(rs.getString("Password"));
                        user.setEmail(rs.getString("Email"));
                        user.setCreateDate(rs.getTimestamp("CreateDate"));
                    }
                }
            }
        }
        return user;
    }
}