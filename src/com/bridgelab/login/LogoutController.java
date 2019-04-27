package com.bridgelab.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//invalidate the session if exists
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/Login.jsp");
    
//		 //invalidate the session if exists
//		PrintWriter out = response.getWriter();
//
//        HttpSession session = request.getSession(false);
//        if(session != null){
//            session.invalidate();
//        }
//        response.sendRedirect(request.getContextPath() + "/Login.jsp");
//    
//        out.close();  
		//doGet(request, response);
	}

}