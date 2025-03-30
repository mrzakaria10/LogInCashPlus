package com.logincashplus.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.logincashplus.models.User;

public class UserDAO {
	private Connection connection;
	
	public UserDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void ajouterUser(User user) throws SQLException {
		String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, user.getEmail());
		stmt.setString(2, user.getPassword());

		stmt.executeUpdate();
		
	}
	public void LoginServlet(User user) {
		
	}
}