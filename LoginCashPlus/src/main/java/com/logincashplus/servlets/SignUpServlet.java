package com.logincashplus.servlets;

import com.logincashplus.dao.UserDAO;
import com.logincashplus.models.User;
import com.logincashplus.utils.DatabaseConnection;
import com.logincashplus.utils.EmailSender;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("signup.jsp"); // Redirects to the sign-up page
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Generate a 6-digit verification code
        String verificationCode = String.format("%06d", new Random().nextInt(999999));
        
        // Hash the password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            
            // Create a new user object
            User user = new User(0, name, email, hashedPassword, verificationCode, false);
            
            // Save user to database
            userDAO.saveUser(user);
            
            // Send verification email
            EmailSender.sendVerificationEmail(email, verificationCode);
            
            // Redirect to verification page
            response.sendRedirect("verify.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("signup.jsp?error=database_error");
        }
    }
}
