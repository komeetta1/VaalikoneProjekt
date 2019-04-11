package vaalikone;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.mysql.jdbc.Statement;

import persist.Kysymykset;

/**
 * Servlet implementation class KysymystenHaku
 */
@WebServlet(description = "Hakee kysymykset tietokannasta", urlPatterns = { "/KysymystenHaku" })
public class KysymystenHaku extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public KysymystenHaku() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		List<Kysymykset> kaikkiKysymykset = haeKysymyksetKaikki();
        
        //ohjaa tiedot tulosten esityssivulle
        request.setAttribute("kaikkiKysymykset", kaikkiKysymykset);
	}

	// Hakee kysymyksen ID:llä, ja tulostaa sen
	public Kysymykset haeKysymys(int kysymysID) {
        EntityManagerFactory emf=null;
        EntityManager em = null;
        try {
  	      emf=Persistence.createEntityManagerFactory("vaalikones");
  	      em = emf.createEntityManager();
        }
        catch(Exception e) {
          	return null;
        }
	    Kysymykset kysymys = em.find(Kysymykset.class, kysymysID);
	    if (kysymys != null) {
	    
	    	return kysymys;
	    } else {
	    	return null;
	    }
	    
	}
	
	// Hakee kaikki kysymykset-taulun tiedot, ja palauttaa ne listana
	public List<Kysymykset> haeKysymyksetKaikki() {
        EntityManagerFactory emf=null;
        EntityManager em = null;
        try {
  	      emf=Persistence.createEntityManagerFactory("vaalikones");
  	      em = emf.createEntityManager();
        }
        catch(Exception e) {
          	return null;
        }
        
        try {
			Query q = em.createQuery(
			        "SELECT k FROM Kysymykset k");
	        List<Kysymykset> kysymysList = q.getResultList();
	        
	        return kysymysList;
		} catch (Exception e) {
			e.printStackTrace();
		}
        finally {
        	em.close();
        }
        return null;
	}
}
