package com.mini.memome.service;

import java.sql.SQLException;
import java.util.List;

import com.mini.memome.dao.NoteDAO;
import com.mini.memome.dao.NoteDAOImpl;
import com.mini.memome.dao.UserDAO;
import com.mini.memome.dao.UserDAOImpl;
import com.mini.memome.dto.Note;
import com.mini.memome.dto.User;

public class MemoMeServiceImpl implements MemoMeService {

	@Override
	public void registerUser(User user) {
		// UserDAO 인스턴스를 가져옴 (의존성 주입 또는 직접 생성)
		UserDAO userDao = new UserDAOImpl();

		try {
			// UserDAO의 addUser 메서드를 사용하여 사용자를 데이터베이스에 추가
			userDao.addUser(user);
		} catch (SQLException e) {
			// SQLException 처리
			// 예외 처리 로직 (예: 로그 기록, 사용자에게 오류 메시지 전달 등)
			throw new RuntimeException("Failed to register user", e);
		}
	}

	@Override
	public boolean loginUser(String username, String password) {
	    UserDAO userDao = new UserDAOImpl(); // UserDAO 인스턴스

	    try {
	        // 데이터베이스에서 사용자 검색
	        User user = userDao.getUserByUsername(username);
	        if (user != null && user.getPassword().equals(password)) {
	            // 비밀번호가 일치하는 경우, true 반환
	            return true;
	        }
	    } catch (SQLException e) {
	        // SQLException 처리
	        throw new RuntimeException("Login error", e);
	    }

	    // 사용자를 찾지 못하거나 비밀번호가 일치하지 않는 경우, false 반환
	    return false;
	}


	@Override
	public void updateUser(User user) {
		UserDAO userDao = new UserDAOImpl(); // UserDAO 인스턴스

		try {
			// 변경된 필드만 업데이트하는 로직
			if (user.isUsernameChanged() || user.isPasswordChanged() || user.isEmailChanged()) {
				userDao.updateUser(user);
			}
		} catch (SQLException e) {
			// SQLException 처리
			throw new RuntimeException("Failed to update user", e);
		}
	}

	@Override
	public User getUserById(int userId) {
		UserDAO userDao = new UserDAOImpl();

		try {
			return userDao.getUserById(userId);
		} catch (SQLException e) {
			// SQLException 처리. 로그 기록 등의 예외 처리 로직 구현.
			e.printStackTrace(); // 실제 애플리케이션에서는 보다 적절한 예외 처리 방법을 고려해야 함.
		}
		return null;
	}

	@Override
	public void deleteUser(int userId) {
		UserDAO userDao = new UserDAOImpl();

		try {
			userDao.deleteUser(userId);
		} catch (SQLException e) {
			// SQLException 처리. 로그 기록 등의 예외 처리 로직 구현.
			e.printStackTrace(); // 실제 애플리케이션에서는 보다 적절한 예외 처리 방법을 고려해야 함.
		}
	}

	@Override
	public void createNote(Note note) {
		NoteDAO noteDao = new NoteDAOImpl();
		
		try {
			noteDao.addNote(note);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to create note", e);
		}

	}

	@Override
    public void updateNote(Note note) {
        NoteDAO noteDao = new NoteDAOImpl();
        try {
            noteDao.updateNote(note);
        } catch (SQLException e) {
            // 적절한 예외 처리
            throw new RuntimeException("Failed to update note", e);
        }
    }

    @Override
    public Note getNoteById(int noteId) {
        NoteDAO noteDao = new NoteDAOImpl();
        try {
            return noteDao.getNote(noteId);
        } catch (SQLException e) {
            // 적절한 예외 처리
            throw new RuntimeException("Failed to retrieve note", e);
        }
    }

    @Override
    public List<Note> getAllNotesByUser(int userId) {
        NoteDAO noteDao = new NoteDAOImpl();
        try {
            return noteDao.getAllNotesByUser(userId);
        } catch (SQLException e) {
            // 적절한 예외 처리
            throw new RuntimeException("Failed to retrieve notes", e);
        }
    }

    @Override
    public void deleteNote(int noteId) {
        NoteDAO noteDao = new NoteDAOImpl();
        try {
            noteDao.deleteNote(noteId);
        } catch (SQLException e) {
            // 적절한 예외 처리
            throw new RuntimeException("Failed to delete note", e);
        }
    }

    @Override
    public int getUserIdByUsername(String username) {
        UserDAO userDao = new UserDAOImpl();
        try {
            User user = userDao.getUserByUsername(username);
            if (user != null) {
                return user.getUserId();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 실제 애플리케이션에서는 적절한 로깅과 예외 처리를 수행해야 합니다.
        }
        return -1; // 사용자가 존재하지 않거나 오류 발생 시
    }

}
