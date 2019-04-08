package vaalikone;

import java.io.IOException;
import java.io.PrintWriter;
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

import persist.Ehdokkaat;
import persist.Kysymykset;

/**
 * Servlet implementation class EhdokkaidenHaku
 */
@WebServlet("/EhdokkaidenHaku")
public class EhdokkaidenHaku extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EhdokkaidenHaku() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
		
		//Hae ehdokas ID:n perusteella
		
		//Tulosta ehdokkaat
		
//		for (Ehdokkaat g: haeEhdokkaatKaikki()) {
//			out.println(g);
//		}
	}
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		List<Ehdokkaat> kaikkiEhdokkaat = haeEhdokkaatKaikki();
        
        //ohjaa tiedot tulosten esityssivulle
        request.setAttribute("kaikkiEhdokkaat", kaikkiEhdokkaat);
		
	}

	//Hae ehdokas ID:llä ja tulosta se
	public Ehdokkaat haeEhdokas(int ehdokas_ID) {
	
        EntityManagerFactory emf=null;
        EntityManager em = null;
        try {
  	      emf=Persistence.createEntityManagerFactory("vaalikones");
  	      em = emf.createEntityManager();
        }
        catch(Exception e) {
          	return null;         	
        }
        Ehdokkaat ehdokas = em.find(Ehdokkaat.class,  ehdokas_ID);
        if (ehdokas !=null) {
        
        	return ehdokas;
        } else {
        	return null;
        }
	} 	
		
	//Hae ehdokkaat-taulun tiedot ja palauta ne listana
    public List<Ehdokkaat> haeEhdokkaatKaikki() {
    	EntityManagerFactory emf =null;
    	EntityManager em = null;
    	try {
    		emf=Persistence.createEntityManagerFactory("vaalikones");
    		em = emf.createEntityManager();
    	}
    	catch(Exception e) {
    		return null;
    	}
    	
    	try {
    		Query q = em.createQuery("SELECT k FROM Ehdokkaat k");
    		List<Ehdokkaat> ehdokasList = q.getResultList();
    		return ehdokasList;
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    	}
    
	
}
