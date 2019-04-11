package vaalikone;

import java.io.IOException;
import java.util.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persist.*;

/**
 * Servlet implementation class VastaustenHaku
 */
@WebServlet(name = "VastaustenHaku",
		description = "Hakee ehdokkaiden vastauksia tietokannasta", 
			urlPatterns = { "/VastaustenHaku" })
public class VastaustenHaku extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VastaustenHaku() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		
		//RequestDispatcher rdVL=request.getRequestDispatcher("vastaustenLisays");
		//rdVL.include(request, response);
		
		String vastaaja1 = (String) request.getAttribute("vastannut");
		int vastaaja = Integer.parseInt(vastaaja1);
		
		request.setAttribute("onkovastannut", onkovastannut(vastaaja));
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	
	
	// Hakee kaikki vastaukset-taulun tiedot, ja palauttaa ne listana
	public List<Vastaukset> haeVastauksetKaikki() {
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
			        "SELECT v FROM Vastaukset v");
	        List<Vastaukset> vastausList = q.getResultList();
	        
	        return vastausList;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}
	
	public boolean onkovastannut(int vastaaja) {
        EntityManagerFactory emf=null;
        EntityManager em = null;
        try {
  	      emf=Persistence.createEntityManagerFactory("vaalikones");
  	      em = emf.createEntityManager();
        }
        catch(Exception e) {
        	System.out.println("Ei voitu hakea vastaajan IDtä.");
          	return false;
        }
        try {
			Query q = em.createQuery(
			        "SELECT e FROM VASTAUKSET e WHERE e.EHDOKAS_ID = "+vastaaja);
	        List vast = q.getResultList();
	        if (vast == null || vast.size() == 0) {
	        	return false;
	        	
	        }        
	       
		} catch (Exception e) {
			e.printStackTrace();
		}
        finally {
        	em.close();
        }
        return true;
	}

}
