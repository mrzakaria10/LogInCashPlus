package com.logincashplus.dao;

import java.sql.*;
import com.logincashplus.models.User;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password, verification_code, is_verified) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPassword());
        stmt.setString(4, user.getVerificationCode());
        stmt.setBoolean(5, false);
        stmt.executeUpdate();
    }

    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }

    public boolean verifyUser(String email, String verificationCode) throws SQLException {
        String sql = "SELECT id FROM users WHERE email = ? AND verification_code = ? AND is_verified = FALSE";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, verificationCode);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String updateSql = "UPDATE users SET is_verified = TRUE WHERE email = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateSql);
            updateStmt.setString(1, email);
            updateStmt.executeUpdate();
            return true;
        }
        return false;
    }
}
