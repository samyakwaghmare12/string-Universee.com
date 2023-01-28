package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//annotation
@WebServlet("/register")
public class regsaveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public regsaveUser() {
        super();
       
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw= response.getWriter();
		
	String uname=request.getParameter("name");
	String uemail=request.getParameter("email");
	String upwd=request.getParameter("pass");
	String umobile=request.getParameter("contact");
	RequestDispatcher dispatcher=null;
	Connection con =null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		 con =DriverManager.getConnection("jdbc:mysql://localhost:3306/phpmyadmin","root","");
		PreparedStatement ps=con.prepareStatement("insert into company (uname,upwd,uemail,umobile)values(?,?,?,?)");
		
		ps.setString(1, uname);
		ps.setString(2, upwd);
		ps.setString(3, uemail);
		ps.setString(4, umobile);
		
		int rowCount=ps.executeUpdate();
		dispatcher=request.getRequestDispatcher("registration.jsp");

		
		if(rowCount>0) {
			request.setAttribute("status", "success");
			pw.print("<h1>account is created successfully</h1>");
			request.getRequestDispatcher("registration.jsp").include(request, response);
		}
		else{
			
			request.setAttribute("status", "fail");
			
		}
		dispatcher.forward(request, response);
	}
	catch(Exception e) {
		e.printStackTrace();
	
	}
	finally {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	}

}
