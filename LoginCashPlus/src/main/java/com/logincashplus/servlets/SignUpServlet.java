package com.logincashplus.servlets;

import com.logincashplus.dao.UserDAO;
import com.logincashplus.models.User;
import com.logincashplus.utils.EmailSender;
import com.logincashplus.utils.DatabaseConnection;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String error = null;

        if (name == null || name.trim().isEmpty()) {
            error = "Name is required.";
        } else if (email == null || email.trim().isEmpty()) {
            error = "Email is required.";
        } else if (password == null || password.trim().isEmpty()) {
            error = "Password is required.";
        }

        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);

            // Check if email exists
            if (userDAO.emailExists(email)) {
                request.setAttribute("error", "Email already exists.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                return;
            }

            // Generate verification code
            String verificationCode = generateVerificationCode();

            // Create user object
            User user = new User(0, name, email, password, verificationCode, false);
            userDAO.registerUser(user);

            // Send verification email
            EmailSender.sendVerificationEmail(email, verificationCode);

            // Redirect to verification page
            response.sendRedirect("verify.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error. Please try again.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(999999);
        return String.format("%06d", code);
    }
}
