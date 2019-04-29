package vaalikone;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Kysymys_poisto_handler
 */
@WebServlet(name = "kysymys_poisto_hand", urlPatterns = { "/Kysymys_poisto" })
public class Kysymys_poisto_handler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Kysymys_poisto_handler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String poista = request.getParameter("poista");

		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaalikone", "pena", "kukkuu");
			Statement stmt = con.createStatement();

			stmt.executeUpdate(("DELETE FROM kysymykset WHERE kysymys_id = \"" + poista + "\""));
			stmt.executeUpdate(("DELETE FROM vastaukset WHERE kysymys_id = \"" + poista + "\""));
			try {
				stmt.executeUpdate("ALTER TABLE kysymykset DROP COLUMN KYSYMYS_ID");
				stmt.executeUpdate("ALTER TABLE kysymykset AUTO_INCREMENT = 1");
				stmt.executeUpdate("ALTER TABLE kysymykset ADD KYSYMYS_ID int NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST");
			} catch (Exception e) {
				// TODO: handle exception
			}
			response.sendRedirect(request.getContextPath() + "/Kysymys_hallinta");
			System.out.println("Kysymys poistettu.");
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
