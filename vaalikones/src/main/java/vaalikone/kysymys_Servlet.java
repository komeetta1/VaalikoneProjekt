package vaalikone;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.Hashtable;
import java.sql.Connection;
import java.io.*;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Statement;
import com.sun.xml.bind.CycleRecoverable.Context;
import com.*;
import java.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.sql.jdbc.ResultSet;
import com.google.protos.cloud.sql.Client.SqlException;
import com.mysql.jdbc.PreparedStatement;

@WebServlet(name = "kysymys", urlPatterns = { "/kysymys_Servlet" })
public class kysymys_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public kysymys_Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// PrintWriter out = response.getWriter();

		// String kysymys = request.getParameter("kysymys_add");

		// out.print(kysymys);
		// Connection conn = null;

		try {
			Connection conn = null;
			String kysymys = request.getParameter("kysymys_add");

			// create a mysql database connection
			String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/vaalikone";
			Class.forName(myDriver);
			conn = DriverManager.getConnection(myUrl, "pena", "kukkuu");

			// the mysql insert statement
			String query = "INSERT INTO kysymykset VALUES ('" + kysymys + "')";

			// create the mysql insert preparedstatement
			java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(0, kysymys);

			// execute the preparedstatement
			preparedStmt.execute();

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String kysymys = request.getParameter("kysymys_add");
		
		try {
			String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/vaalikone";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "pena", "kukkuu");
			
			Statement st = conn.createStatement();
			
			st.executeUpdate("INSERT INTO kysymykset (kysymys) VALUES ('"+kysymys+"')");

			conn.close();
		}catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}

	}
}
