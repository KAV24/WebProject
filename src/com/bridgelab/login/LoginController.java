package com.bridgelab.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PreparedStatement stmt = null;
		Connection con = null;
		ResultSet rs = null;
		String query = "select * from login where username= ? and password= ?";
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Databasename", "root", "root");
			stmt = con.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
//		// Creating two cookies
//			 Cookie c1 = new Cookie("name", username);
//			 Cookie c2 = new Cookie("password", password);
//			// Adding the cookies to response header
//			 response.addCookie(c1);
//			 response.addCookie(c2);
//			// Reading cookies
//			 Cookie c[] = request.getCookies();
//			// Displaying User name value from cookie
//			 out.print("Name: " + c[0].getValue());
//			// Displaying user password value from cookie
//			 out.print("Password: " + c[1].getValue());
			
			if (rs.next()) {
				out.print("welcom********");
				 //get the old session and invalidate
		         
	            HttpSession oldSession = request.getSession(false);
	            if (oldSession != null) {
	                oldSession.invalidate();
	            }
	            //generate a new session
	            HttpSession newSession = request.getSession(true);

	            //setting session to expiry in 10SEC
	            newSession.setAttribute("username", username);
	            newSession.setMaxInactiveInterval(10);
	            response.sendRedirect("WelCome.jsp");

	          //  Cookie message = new Cookie("message", "Welcome");
	            //response.addCookie(message);
	           // response.sendRedirect("Login.jsp");
				//out.print("Welcome : " + name);
			HttpSession session = request.getSession();
				session.setAttribute("username", username);
//
		//	RequestDispatcher rqst = request.getRequestDispatcher("/WelCome.jsp");
		//		rqst.forward(request, response);
 
			} else {
				System.out.println("invalid user");
				RequestDispatcher rqst = request.getRequestDispatcher("/Login.jsp");

				out.println("<font color=red>Invalid user!!!  Please Register new user.</font>");
				rqst.forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}