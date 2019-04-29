package vaalikone;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;

import javax.servlet.*;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "kysymys", urlPatterns = { "/Kysymys_lisays" })
public class Kysymys_lisays_handler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String lisaa = request.getParameter("lisaa");

		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaalikone", "pena", "kukkuu");
			Statement stmt = con.createStatement();

			stmt.executeUpdate("INSERT INTO kysymykset (kysymys) VALUES ('"+lisaa+"')");
			response.sendRedirect(request.getContextPath() + "/Kysymys_hallinta");
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
