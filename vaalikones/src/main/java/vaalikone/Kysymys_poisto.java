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
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
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
			out.println("<h2>Otsikko</h2>");
			
			while (rs.next()) {
				out.print(rs.getString("kysymys_id"));
				out.print(" ");
				out.println(rs.getString("kysymys"));
				out.println("<br>");
			}
			
			out.println("<form action='Kysymys_poisto_handler' method='POST'/>");
			out.println("<p>Syötä poistettavan kysymyksen ID</p>");
			out.println("<input type='text' name='poista'</input>");	
			out.println("<input type='submit' value='Poista kysymys'</input>");
			out.println("</form>");
			out.println("<form action='uusi_kysymys.jsp' method='POST'/>");
			out.println("<input type='submit' value='Palaa' name='palaabtn'</input>");
			out.println("</form>");
			
			st.close();
			
		}catch (Exception e) {
			e.getMessage();
		}
		/*
		
		for (Kysymykset g : haeKysymyksetKaikki()) {
			out.println(g);
		}
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	/*
	// Hakee kaikki kysymykset-taulun tiedot, ja palauttaa ne listana
	public List<Kysymykset> haeKysymyksetKaikki() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("vaalikones");
			em = emf.createEntityManager();
		} catch (Exception e) {
			return null;
		}

		try {
			Query q = em.createQuery("SELECT k FROM Kysymykset k");
			List<Kysymykset> kysymysList = q.getResultList();

			return kysymysList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	*/
}
