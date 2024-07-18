package com.servlet;

import java.io.IOException;

import com.conn.DbConnect;
import com.dao.UserDAO;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		UserDAO dao=new UserDAO(DbConnect.getConn());
		User u=dao.loginUser(email, password);
		
		HttpSession session=req.getSession();
		if(u!=null)
		{
			session.setAttribute("user", u);
			resp.sendRedirect("index.jsp");
			//System.out.println("User Is Available="+u);
		}
		else
		{
			session.setAttribute("invalidMsg", "Invalid Email & Password");
			resp.sendRedirect("login.jsp");
			//System.out.println("User Is Not Available="+u);
		}
	}

}
