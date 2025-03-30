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
import com.logincashplus.models.User;
import com.logincashplus.utils.DatabaseConnection;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void dopost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, Throwable {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// validation des champs
		String erreur = null;
		if (email == null || email.trim().isEmpty() || email.length() > 50) {
            erreur = "Le nom est invalide (vide ou trop long).";
            
		} else if (password == null || password.trim().isEmpty() || password.length() > 50) {
            erreur = "Le prénom est invalide (vide ou trop long).";
        }
		
		if (erreur != null) {
            request.setAttribute("erreur", erreur);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
		
		try (Connection connection = DatabaseConnection.getConnection()) {
			UserDAO userDAO = new UserDAO(connection);
			User user = new User(0, email, password);
			userDAO.ajouterUser(user);
		} catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "");

		

		
		
	}

}
