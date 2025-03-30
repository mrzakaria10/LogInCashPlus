package com.logincashplus.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
    private static final String URL = "jdbc:mysql://localhost:3306/logincashplus_db";
    private static final String USER = "root";  // Remplace par ton utilisateur
    private static final String PASSWORD = ""; // Remplace par ton mot de passe
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Chargement du driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }	catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC non trouvé", e);
        }
    }
    public static void main(String[] args) {
    	
	}
}
