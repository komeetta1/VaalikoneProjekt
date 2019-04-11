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
import javax.swing.JOptionPane;

import com.google.cloud.sql.jdbc.ResultSet;
import com.google.protos.cloud.sql.Client.SqlException;
import com.mysql.jdbc.PreparedStatement;

@WebServlet(name = "kysymys", urlPatterns = { "/kysymys_Servlet" })
public class kysymys_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String lisaa = request.getParameter("lisaa");

		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaalikone", "pena", "kukkuu");
			Statement stmt = con.createStatement();

			stmt.executeUpdate("INSERT INTO kysymykset (kysymys) VALUES ('"+lisaa+"')");
			response.sendRedirect(request.getContextPath() + "/Kysymys_poisto");
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
