package com.logincashplus.dao;

import com.logincashplus.models.User;
import com.logincashplus.utils.DatabaseConnection;

import java.sql.*;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }


    
    public void saveUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password, verification_code, is_verified) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPassword());
        stmt.setString(4, user.getVerificationCode());
        stmt.setBoolean(5, user.isVerified());
        stmt.executeUpdate();
    }

    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("verification_code"),
                rs.getBoolean("is_verified")
            );
        }
        return null;
    }

    public boolean verifyUser(String email, String verificationCode) throws SQLException {
        String sql = "UPDATE users SET is_verified = TRUE WHERE email = ? AND verification_code = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, verificationCode);
        
        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0; // Returns true if the update was successful
    }


}
