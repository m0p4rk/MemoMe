package com.mini.memome.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public User getUserByUsername(String username) throws SQLException {
		User user = null;
		try (Connection conn = DButil.getConnection()) {
			String sql = "SELECT * FROM Users WHERE Username = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
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

	@Override
	public void updateUser(User user) throws SQLException {
	    StringBuilder sql = new StringBuilder("UPDATE Users SET ");
	    List<Object> params = new ArrayList<>();

	    // 각 필드가 변경되었는지 확인하고, 변경된 필드만 업데이트합니다.
	    if (user.isUsernameChanged()) {
	        sql.append("Username = ?, ");
	        params.add(user.getUsername());
	    }
	    if (user.isPasswordChanged()) {
	        sql.append("Password = ?, ");
	        params.add(user.getPassword()); // 실제 애플리케이션에서는 해시된 비밀번호를 사용합니다.
	    }
	    if (user.isEmailChanged()) {
	        sql.append("Email = ?, ");
	        params.add(user.getEmail());
	    }

	    // 변경된 필드가 없으면 업데이트를 수행하지 않습니다.
	    if (params.isEmpty()) {
	        return;
	    }

	    // 마지막 쉼표 제거 및 WHERE 절 추가
	    sql = new StringBuilder(sql.substring(0, sql.length() - 2));
	    sql.append(" WHERE UserID = ?");
	    params.add(user.getUserId());

	    // 데이터베이스 연결 및 SQL 실행
	    try (Connection conn = DButil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

	        for (int i = 0; i < params.size(); i++) {
	            stmt.setObject(i + 1, params.get(i));
	        }
	        stmt.executeUpdate();
	    }
	}

	@Override
	public User getUserById(int userId) throws SQLException {
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

	@Override
	public void deleteUser(int userId) throws SQLException {
	    try (Connection conn = DButil.getConnection()) {
	        String sql = "DELETE FROM Users WHERE UserID = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, userId);
	            stmt.executeUpdate();
	        }
	    }
	}
}