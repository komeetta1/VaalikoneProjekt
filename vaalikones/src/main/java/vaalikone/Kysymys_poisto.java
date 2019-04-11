package vaalikone;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.sql.Connection;
import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.services.discovery.model.RestMethod.Request;

import persist.Kysymykset;

/**
 * Servlet implementation class Kysymys_poisto
 */
@WebServlet(name = "kysymysPoisto", urlPatterns = { "/Kysymys_poisto" })
public class Kysymys_poisto extends HttpServlet {

	public int longtoint() {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {

			emf = Persistence.createEntityManagerFactory("vaalikones");
			em = emf.createEntityManager();
			Query lkm = em.createNativeQuery("SELECT COUNT(*) FROM kysymykset");
			List listlkm = lkm.getResultList();
			Long lukumaara = (Long) (listlkm.get(0));
			int a = lukumaara != null ? lukumaara.intValue() : null;
			// int s =(int)(long)lukumaara;
			return a;
		} catch (Exception e) {
			return 0;
		}
	}

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Kysymys_poisto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaalikone", "pena", "kukkuu");

			Statement st = conn.createStatement();
			String query = "SELECT * FROM kysymykset";
			PrintWriter out = response.getWriter();

			ResultSet rs = st.executeQuery(query);
			out.println("<h2>Kysymyksien hallinta</h2>");

			while (rs.next()) {
				out.print(rs.getString("kysymys_id"));
				out.print(" ");
				out.println(rs.getString("kysymys"));
				out.println("<br>");
			}
			
			// LISAA
			out.println("<form action='kysymys_Servlet' method='POST'/>");
			out.println("<p>Lis‰‰ kysymys</p>");
			out.println("<input type='text' name='lisaa' size='100' placeholder='Kirjoita uusi kysymys'</input>");
			out.println("<input type='submit' value='Lisaa' style=color:green;'</input>");
			out.println("</form>");
			
			// MUOKKAUS
			out.println("<form action='Kysymys_muokkaus' method='POST'/>");
			out.println("<p>Kirjoita uudestaan / muokkaa kysymyst‰</p>");
			out.println("<input type='text' name='id' size='3' maxlength='3' placeholder='ID'</input>");
			out.println("<input type='text' name='muokkaa' size='100' placeholder='Kirjoita kysymys uudelleen'</input>");
			out.println("<input type='submit' value='Muokkaa' style=color:blue;'</input>");
			out.println("</form>");

			// POISTO
			out.println("<form action='Kysymys_poisto_handler' method='POST'/>");
			out.println("<p>Poista kysymys</p>");
			out.println("<input type='text' name='poista' placeholder='ID' size='3' maxlength='3'</input>");
			out.println("<input type='submit' value='Poista' style=color:red;'</input>");
			out.println("</form>");
			
			// PALAA
			out.println("<form action='uusi_kysymys.jsp' method='POST'/>");
			out.println("<input type='submit' value='Palaa edelliselle sivulle' name='palaabtn'</input>");
			out.println("</form>");

			// PALAA ETUSIVULLE
			out.println("<form action='index.html' method='POST'/>");
			out.println("<input type='submit' value='Palaa etusivulle' name='palaaetusbtn'</input>");
			out.println("</form>");

			st.close();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
