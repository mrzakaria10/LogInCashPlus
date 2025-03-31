package com.logincashplus.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logincashplus.dao.UserDAO;
import com.logincashplus.utils.DatabaseConnection;

@WebServlet("/verify")
public class VerificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String verificationCode = request.getParameter("verificationCode");

        if (email == null || email.trim().isEmpty() || verificationCode == null || verificationCode.trim().isEmpty()) {
            request.setAttribute("error", "Invalid email or verification code.");
            request.getRequestDispatcher("verify.jsp").forward(request, response);
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            
            boolean isVerified = userDAO.verifyUser(email, verificationCode);
            
            if (isVerified) {
                request.setAttribute("message", "Verification successful. You can now log in.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Invalid verification code.");
                request.getRequestDispatcher("verify.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
