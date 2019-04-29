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
 * Servlet implementation class Kysymys_muokkaus
 */
@WebServlet(name = "kysymys_muokkaus_hand", urlPatterns = { "/Kysymys_muokkaus" })
public class Kysymys_muokkaus_handler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Kysymys_muokkaus_handler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String muokkaa = request.getParameter("muokkaa");

		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaalikone", "pena", "kukkuu");
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE kysymykset SET kysymys = \"" + muokkaa + "\" WHERE kysymys_id = \"" + id + "\"");
			
			response.sendRedirect(request.getContextPath() + "/Kysymys_hallinta");
			System.out.println("Kysymys muokattu.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
