package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String uemail=request.getParameter("username");
		String upwd= request.getParameter("password");
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/phpmyadmin","root","");
			
			PreparedStatement ps=con.prepareStatement("select*from company where uemail=? and upwd=?");
			
			ps.setString(1, uemail);
			ps.setString(2, upwd);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
			pw.print("<p>success</p>");
			request.getRequestDispatcher("index.jsp").include(request, response);
			}
			else {
				pw.print("<p>FAIL</p>");
//				request.getRequestDispatcher("login.jsp").include(request, response);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			
		}
	}

}
