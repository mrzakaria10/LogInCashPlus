package com.logincashplus.servlets;

import com.logincashplus.dao.UserDAO;
import com.logincashplus.utils.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VerifyServlet")
public class VerifyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String verificationCode = request.getParameter("verificationCode");

        try (Connection connection = DatabaseConnection.getConnection()) { // ✅ Get DB connection
            UserDAO userDAO = new UserDAO(connection); // ✅ Pass the connection
            boolean isVerified = userDAO.verifyUser(email, verificationCode);

            if (isVerified) {
                response.sendRedirect("login.jsp?message=Verification successful, please log in.");
            } else {
                response.sendRedirect("verify.jsp?error=Invalid verification code.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("verify.jsp?error=Something went wrong. Try again.");
        }
    }
}
