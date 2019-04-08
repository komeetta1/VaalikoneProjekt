package vaalikone;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.Hashtable;
import java.sql.Connection;
import java.io.*;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Statement;
import com.sun.xml.bind.CycleRecoverable.Context;

import persist.Kysymykset;

import com.*;
import java.*;

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
			
			RequestDispatcher reqdisp = request.getRequestDispatcher("/Kysymys_poisto");
			reqdisp.forward(request, response);
		}catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}
	
	// JOTAIN JPA YRITYSTÄ TÄSSÄ HAETTU.
	
	/*
	public void lisaaKysymys(Kysymykset kysymys) {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		try {
			emf = Persistence.createEntityManagerFactory("vaalikones");
			em = emf.createEntityManager();
		}catch (Exception e) {
			return;
		}
		
		try {
			em.persist(kysymys);
		}catch (Exception e) {
			e.getMessage();
		}
	}*/
}
