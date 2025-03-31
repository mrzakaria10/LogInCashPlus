package com.logincashplus.servlets;

import com.logincashplus.dao.UserDAO;
import com.logincashplus.models.User;
import com.logincashplus.utils.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handles GET request
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the verification page
        request.getRequestDispatcher("verify.jsp").forward(request, response);
    }

    // Handles POST request (where the user submits the verification code)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String verificationCode = request.getParameter("verificationCode");
        String email = (String) request.getSession().getAttribute("email"); // get the user's email from session
        String error = null;

        if (verificationCode == null || verificationCode.trim().isEmpty()) {
            error = "Verification code cannot be empty.";
        }

        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("verify.jsp").forward(request, response);
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            User user = userDAO.getUserByEmail(email); // Retrieve the user from the database

            // Check if the verification code matches the stored one
            if (user != null && verificationCode.equals(user.getVerificationCode())) {
                userDAO.verifyUser(user); // Mark the user as verified
                response.sendRedirect("home.jsp"); // Redirect to home page
            } else {
                error = "Invalid verification code.";
                request.setAttribute("error", error);
                request.getRequestDispatcher("verify.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }
}
